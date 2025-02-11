package com.example.meuapp.ui.Config

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentConfiguracoesBinding
import com.example.meuapp.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ConfiguracoesFragment : Fragment() {
    private var _binding: FragmentConfiguracoesBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private var selectedTab = 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfiguracoesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        initClicks()
        setupTabs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initClicks() {
        binding.txtidioma.setOnClickListener {

        }
        binding.tvInfoApp.setOnClickListener {
            findNavController().navigate(R.id.action_configuracoesFragment_to_informacoesFragment)
        }
        binding.tvConta.setOnClickListener {

        }
        binding.txtSenhacofig.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_esqueceu_SenhaFragment)
        }
        binding.tvExcluirConta.setOnClickListener {
            findNavController().navigate(R.id.action_configuracoesFragment_to_excluirContaFragment)
        }
        binding.btnSair.setOnClickListener {
            LogoutApp()
        }
    }

    private fun LogoutApp() {
        auth.signOut()
        findNavController().navigate(R.id.action_configuracoesFragment_to_autenticacao)
    }

    private fun setupTabs() {
        binding.layoutHome.setOnClickListener {
            setTab(1, binding.layoutHome, binding.imgHome, binding.txtHome, R.drawable.icon_home)
        }
        binding.layoutAdicionar.setOnClickListener {
            setTab(2, binding.layoutAdicionar, binding.imgAdicionar, binding.txtAdicionar, R.drawable.icon_more)
        }
        binding.layoutConfiguracoes.setOnClickListener {
            setTab(3, binding.layoutConfiguracoes, binding.imgConfiguracao, binding.txtConfiguracao, R.drawable.icon_settings)
        }
    }

    private fun setTab(tab: Int, layout: View, imageView: ImageView, textView: TextView, iconRes: Int) {
        if (selectedTab != tab) {
            when (tab) {
                1 -> findNavController().navigate(R.id.action_configuracoesFragment_to_homeFragment)
                2 -> findNavController().navigate(R.id.action_configuracoesFragment_to_adicionarFragment)
                3 -> findNavController().navigate(R.id.configuracoesFragment)
            }

            resetTabs()
            textView.visibility = View.VISIBLE
            imageView.setImageResource(iconRes)
            layout.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_purple))

            val scaleAnimation = ScaleAnimation(
                0.8f, 1.0f, 1f, 1f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
            )
            scaleAnimation.duration = 200
            scaleAnimation.fillAfter = true
            layout.startAnimation(scaleAnimation)

            selectedTab = tab
        }
    }

    private fun resetTabs() {
        binding.txtHome.visibility = View.GONE
        binding.txtAdicionar.visibility = View.GONE
        binding.txtConfiguracao.visibility = View.GONE

        binding.imgHome.setImageResource(R.drawable.icon_home)
        binding.imgAdicionar.setImageResource(R.drawable.icon_more)
        binding.imgConfiguracao.setImageResource(R.drawable.icon_settings)

        binding.layoutHome.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
        binding.layoutAdicionar.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
        binding.layoutConfiguracoes.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
    }
}
