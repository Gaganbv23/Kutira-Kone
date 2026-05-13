package com.example.kutirakone.ui.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kutirakone.data.models.Scrap
import com.example.kutirakone.data.repositories.AuthRepository
import com.example.kutirakone.data.repositories.ScrapRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScrapViewModel : ViewModel() {
    private val repository = ScrapRepository()
    private val authRepo = AuthRepository()

    private val _scraps = MutableStateFlow<List<Scrap>>(emptyList())
    val scraps: StateFlow<List<Scrap>> = _scraps

    private val _uploadState = MutableStateFlow<UploadState>(UploadState.Idle)
    val uploadState: StateFlow<UploadState> = _uploadState

    init {
        fetchScraps()
    }

    fun fetchScraps() {
        viewModelScope.launch {
            val result = repository.getAllScraps()
            _scraps.value = result
        }
    }

    fun uploadScrap(title: String, material: String, color: String, size: String, imageUri: Uri) {
        viewModelScope.launch {
            _uploadState.value = UploadState.Loading
            val userId = authRepo.getCurrentUserId()
            if (userId == null) {
                _uploadState.value = UploadState.Error("User not logged in")
                return@launch
            }
            
            val result = repository.uploadScrap(title, material, color, size, imageUri, userId)
            if (result.isSuccess) {
                _uploadState.value = UploadState.Success
                fetchScraps() 
            } else {
                _uploadState.value = UploadState.Error(result.exceptionOrNull()?.message ?: "Upload failed")
            }
        }
    }
    
    fun resetUploadState() {
        _uploadState.value = UploadState.Idle
    }
}

sealed class UploadState {
    object Idle : UploadState()
    object Loading : UploadState()
    object Success : UploadState()
    data class Error(val message: String) : UploadState()
}
