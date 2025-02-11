package com.example.meuapp.ui.helper


import com.google.firebase.database.FirebaseDatabase

class FirebaseHelper {
    companion object{
        fun get Database() = FirebaseDataBase.getInstance().reference

        private fun getAuth() = FirabaseAuth.getInstance()

        fun getIdUser() = getAuth().uid
        fun IsAutenticated() = getAuth().currentUser != null
        fun validError(error: String) : int {
            return when (error){
                error.contains()
                else ->{
                    0
                }
            }
        }
    }
}