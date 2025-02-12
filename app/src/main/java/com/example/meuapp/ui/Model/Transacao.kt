package com.example.meuapp.model

data class Transacao(
    val id: String = "",
    val tipo: String = "",
    val nome: String = "",
    val categoria: String = "",
    val valor: Double = 0.0,
    val formaPagamento: String = "",
    val descricao: String = "",
    val valorFixo: Boolean = false
)
