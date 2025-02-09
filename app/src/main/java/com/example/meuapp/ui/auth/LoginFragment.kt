package com.example.meuapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentLoginBinding
import com.example.meuapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        initClicks()


    }
    private fun initClicks(){
        binding.buttonlogin.setOnClickListener {
           validarDados()
        }
        binding.txtRecuperarSenha.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_esqueceu_SenhaFragment)
        }
        binding.txtCadastro.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_criarContaFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun validarDados() {
        val email = binding.editEmail.text.toString().trim()
        val senha = binding.editSenha.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(requireContext(), "Informe seu e-mail", Toast.LENGTH_SHORT).show()
            return
        }

        if (senha.isEmpty()) {
            Toast.makeText(requireContext(), "Informe sua senha", Toast.LENGTH_SHORT).show()
            return
        }

        loginFirebase(email, senha)
    }

    private fun loginFirebase(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Email ou Senha incorreta", Toast.LENGTH_SHORT).show()

                }
            }
    }

}