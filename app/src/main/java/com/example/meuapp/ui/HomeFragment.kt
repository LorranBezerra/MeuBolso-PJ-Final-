package com.example.meuapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var selectedTab = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificarUsuario()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
        setupTabs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initClicks() {
        binding.btnMenuMetas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_metasFragment)
        }
        binding.btnMenuDespesas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_despesasFragment)
        }
        binding.btnMenuReceitas.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_receitasFragment)
        }
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
                1 -> {
                    parentFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_no_home_fragment, HomeFragment())
                        .commit()
                }
                2 -> {
                    findNavController().navigate(R.id.action_homeFragment_to_adicionarFragment)
                }
                3 -> {
                    findNavController().navigate(R.id.action_homeFragment_to_configuracoesFragment)
                }
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

    private fun verificarUsuario() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && !user.isEmailVerified) {
            Toast.makeText(requireContext(), "Verifique seu e-mail antes de continuar", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_criarContaFragment_to_verificarEmailFragment)
        }
    }
}
