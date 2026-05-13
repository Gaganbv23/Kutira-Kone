package com.example.kutirakone.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kutirakone.data.models.Request
import com.example.kutirakone.data.repositories.AuthRepository
import com.example.kutirakone.data.repositories.RequestRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RequestViewModel : ViewModel() {
    private val repository = RequestRepository()
    private val authRepo = AuthRepository()

    private val _incomingRequests = MutableStateFlow<List<Request>>(emptyList())
    val incomingRequests: StateFlow<List<Request>> = _incomingRequests

    private val _myRequests = MutableStateFlow<List<Request>>(emptyList())
    val myRequests: StateFlow<List<Request>> = _myRequests

    private val _requestState = MutableStateFlow<RequestState>(RequestState.Idle)
    val requestState: StateFlow<RequestState> = _requestState

    init {
        fetchRequests()
    }

    fun fetchRequests() {
        viewModelScope.launch {
            val userId = authRepo.getCurrentUserId() ?: return@launch
            _incomingRequests.value = repository.getIncomingRequests(userId)
            _myRequests.value = repository.getMyRequests(userId)
        }
    }

    fun createRequest(toUser: String, scrapId: String, type: String) {
        viewModelScope.launch {
            _requestState.value = RequestState.Loading
            val fromUser = authRepo.getCurrentUserId()
            if (fromUser == null) {
                _requestState.value = RequestState.Error("User not logged in")
                return@launch
            }
            
            val result = repository.createRequest(fromUser, toUser, scrapId, type)
            if (result.isSuccess) {
                _requestState.value = RequestState.Success
                fetchRequests()
            } else {
                _requestState.value = RequestState.Error("Failed to create request")
            }
        }
    }
    
    fun resetState() {
        _requestState.value = RequestState.Idle
    }
}

sealed class RequestState {
    object Idle : RequestState()
    object Loading : RequestState()
    object Success : RequestState()
    data class Error(val message: String) : RequestState()
}
