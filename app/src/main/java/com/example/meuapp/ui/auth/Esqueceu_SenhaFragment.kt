package com.example.meuapp.ui.auth

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentEsqueceuSenhaBinding
import com.google.firebase.auth.FirebaseAuth

class Esqueceu_SenhaFragment : Fragment() {

    private var _binding: FragmentEsqueceuSenhaBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEsqueceuSenhaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.btnrredefinirSenha.setOnClickListener {
            val email = binding.txtEmailRecuperarsenha.text.toString().trim()

            if (email.isEmpty()) {
                binding.txtEmailRecuperarsenha.error = "Digite um e-mail válido"
                return@setOnClickListener
            }

            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showPopup()
                    } else {
                        Toast.makeText(requireContext(), "Erro ao enviar e-mail!", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun showPopup() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setTitle("E-mail de Recuperação")
        builder.setMessage("Um e-mail foi enviado com um link para redefinir sua senha. Clique no link para continuar.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            findNavController().navigate(R.id.action_esqueceu_SenhaFragment_to_loginFragment)
        }
        builder.show()

        // Usar Handler para navegar após 10 segundos
        Handler().postDelayed({
            findNavController().navigate(R.id.action_esqueceu_SenhaFragment_to_loginFragment)
        }, 10000) // 10 segundos
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
