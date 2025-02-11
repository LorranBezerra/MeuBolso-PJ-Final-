package com.example.meuapp.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentSplashBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import kotlinx.coroutines.delay


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private lateinit var auth: FirebaseAuth

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(this::checkAuth, 2000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun checkAuth(){
        auth = Firebase.auth
        if (auth.currentUser == null){
            findNavController().navigate(R.id.action_splashFragment_to_autenticacao)
        } else{
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }


    }
}