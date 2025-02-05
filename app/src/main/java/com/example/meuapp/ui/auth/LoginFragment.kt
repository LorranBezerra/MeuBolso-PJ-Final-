package com.example.meuapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentLoginBinding
import com.example.meuapp.databinding.FragmentSplashBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()


    }
    private fun initClicks(){
        binding.txtCadastro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_criarContaFragment)

        }
        binding.txtRecuperarSenha.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_esqueceu_SenhaFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}