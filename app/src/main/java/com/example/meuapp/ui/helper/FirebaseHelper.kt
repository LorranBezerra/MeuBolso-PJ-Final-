package com.example.meuapp.ui.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {
    companion object {
        fun getDatabase() = FirebaseDatabase.getInstance().reference

        private fun getAuth() = FirebaseAuth.getInstance()

        fun getIdUser(): String? = getAuth().uid

        fun isAuthenticated(): Boolean = getAuth().currentUser != null

        fun validateError(error: String): Int {
            return when {
                error.contains("network") -> 1 // Exemplo de código de erro para falha de rede
                error.contains("permission") -> 2 // Exemplo de código de erro para permissão negada
                else -> 0
            }
        }
    }
}
