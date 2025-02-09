package com.example.meuapp.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentCriarContaBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class CriarContaFragment : Fragment() {

    private var _binding: FragmentCriarContaBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCriarContaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        initClicks()
    }

    private fun initClicks() {
        binding.txtbacklogin.setOnClickListener {
            findNavController().navigate(R.id.action_criarContaFragment_to_loginFragment)
        }

        binding.btnCriarConta.setOnClickListener {
            validarDados()
        }
    }

    private fun validarDados() {
        val nome = binding.editNomeUsuario.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()
        val senha = binding.editSenha.text.toString().trim()
        val termosAceitos = binding.checkTermos.isChecked

        if (nome.isEmpty()) {
            Toast.makeText(requireContext(), "Informe seu nome", Toast.LENGTH_SHORT).show()
            return
        }

        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Informe seu e-mail", Toast.LENGTH_SHORT).show()
            return
        }

        if (senha.isEmpty()) {
            Toast.makeText(requireContext(), "Informe sua senha", Toast.LENGTH_SHORT).show()
            return
        }

        if (!termosAceitos) {
            Toast.makeText(requireContext(), "Você deve aceitar os termos", Toast.LENGTH_SHORT).show()
            return
        }

        criarContaFirebase(email, senha)
    }

    private fun criarContaFirebase(email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()?.addOnSuccessListener {
                        Toast.makeText(requireContext(), "E-mail de verificação enviado!", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_criarContaFragment_to_verificarEmailFragment)
                    }?.addOnFailureListener {
                        Toast.makeText(requireContext(), "Erro ao enviar e-mail de verificação", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Erro ao criar conta", Toast.LENGTH_SHORT).show()
                    Log.e("CriarConta", "Erro: ${task.exception?.message}")
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
