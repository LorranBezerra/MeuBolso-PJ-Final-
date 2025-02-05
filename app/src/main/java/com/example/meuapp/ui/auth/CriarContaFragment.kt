package com.example.meuapp.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentCriarContaBinding
import com.example.meuapp.databinding.FragmentLoginBinding


class CriarContaFragment : Fragment() {
    private var _binding: FragmentCriarContaBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCriarContaBinding.inflate(inflater, container, false)
        return binding.root
    }
    private fun initClicks(){
        binding.txtbacklogin.setOnClickListener {
            findNavController().navigate(R.id.action_criarContaFragment_to_loginFragment)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}