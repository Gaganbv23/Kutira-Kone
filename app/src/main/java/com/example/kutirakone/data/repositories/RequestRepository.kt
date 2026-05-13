package com.example.kutirakone.data.repositories

import com.example.kutirakone.data.models.Request
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

class RequestRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun createRequest(fromUser: String, toUser: String, scrapId: String, type: String): Result<Boolean> {
        return try {
            val id = UUID.randomUUID().toString()
            val request = Request(id, fromUser, toUser, scrapId, type, "pending")
            firestore.collection("requests").document(id).set(request).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getIncomingRequests(userId: String): List<Request> {
        return try {
            val snapshot = firestore.collection("requests").whereEqualTo("toUser", userId).get().await()
            snapshot.toObjects(Request::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getMyRequests(userId: String): List<Request> {
        return try {
            val snapshot = firestore.collection("requests").whereEqualTo("fromUser", userId).get().await()
            snapshot.toObjects(Request::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
