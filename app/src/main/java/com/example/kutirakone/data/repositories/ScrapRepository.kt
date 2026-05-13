package com.example.kutirakone.data.repositories

import android.net.Uri
import com.example.kutirakone.data.models.Scrap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID

class ScrapRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    suspend fun uploadScrap(
        title: String, material: String, color: String, size: String, 
        imageUri: Uri, userId: String
    ): Result<Boolean> {
        return try {
            val scrapId = UUID.randomUUID().toString()
            val storageRef = storage.reference.child("scraps/$scrapId.jpg")
            storageRef.putFile(imageUri).await()
            val downloadUrl = storageRef.downloadUrl.await().toString()
            
            val scrap = Scrap(
                id = scrapId,
                title = title,
                material = material,
                color = color,
                size = size,
                imageUrl = downloadUrl,
                userId = userId,
                latitude = 12.9716, 
                longitude = 77.5946
            )
            firestore.collection("scraps").document(scrapId).set(scrap).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllScraps(): List<Scrap> {
        return try {
            val snapshot = firestore.collection("scraps").get().await()
            snapshot.toObjects(Scrap::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
