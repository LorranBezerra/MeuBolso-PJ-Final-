package com.example.meuapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentVerificacaoEmailBinding
import com.example.meuapp.ui.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class VerificarEmailFragment : Fragment() {

    private var _binding: FragmentVerificacaoEmailBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerificacaoEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.buttonVerificaremail.setOnClickListener {
            verificarEmail()
        }
    }

    /*private fun verificarEmail() {
        val user = auth.currentUser
        user?.reload()?.addOnCompleteListener {
            if (user.isEmailVerified) {
                Toast.makeText(requireContext(), "E-mail verificado!", Toast.LENGTH_SHORT).show()

                // Redireciona para a tela inicial (MainActivity)
                val intent = Intent(requireContext(), HomeFragment::class.java)
                startActivity(intent)
                requireActivity().finish()

            } else {
                Toast.makeText(requireContext(), "E-mail ainda não verificado!", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

    private fun verificarEmail() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.reload()?.addOnCompleteListener { task ->
            if (task.isSuccessful && user.isEmailVerified) {
                // Usuário verificou o e-mail, navegue para a tela de login
                findNavController().navigate(R.id.action_verificarEmailFragment_to_loginFragment)
            } else {
                // Caso contrário, mantenha na tela de verificação
                Toast.makeText(
                    requireContext(),
                    "Ainda não verificou o e-mail.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
