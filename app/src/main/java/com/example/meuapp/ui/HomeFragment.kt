package com.example.meuapp.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var selectedTab = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificarUsuario()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar o layout para o fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val layoutHome: View = view.findViewById(R.id.layout_home)
        val layoutAdicionar: View = view.findViewById(R.id.layout_Adicionar)
        val layoutConfiguracao: View = view.findViewById(R.id.layout_configuracoes)

        val imgHome: ImageView = view.findViewById(R.id.img_home)
        val imgAdicionar: ImageView = view.findViewById(R.id.img_Adicionar)
        val imgConfiguracao: ImageView = view.findViewById(R.id.img_configuracao)

        val txtHome: TextView = view.findViewById(R.id.txt_home)
        val txtAdicionar: TextView = view.findViewById(R.id.txt_Adicionar)
        val txtConfiguracao: TextView = view.findViewById(R.id.txt_configuracao)

        layoutHome.setOnClickListener {
            if (selectedTab != 1) {

                parentFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_no_home_fragment, HomeFragment())
                    .commit()

                txtAdicionar.visibility = View.GONE
                txtConfiguracao.visibility = View.GONE

                imgAdicionar.setImageResource(R.drawable.icon_more)
                imgConfiguracao.setImageResource(R.drawable.icon_settings)

                layoutAdicionar.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
                layoutConfiguracao.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))

                txtHome.visibility = View.VISIBLE
                imgHome.setImageResource(R.drawable.icon_home)
                layoutHome.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_purple))

                val scaleAnimation = ScaleAnimation(
                    0.8f, 1.0f, 1f, 1f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                layoutHome.startAnimation(scaleAnimation)

                selectedTab = 1
            }
        }

        layoutAdicionar.setOnClickListener {
            if (selectedTab != 2) {

                parentFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.adicionarFragment, AdicionarFragment())
                    .commit()

                txtHome.visibility = View.GONE
                txtConfiguracao.visibility = View.GONE

                imgHome.setImageResource(R.drawable.icon_home)
                imgConfiguracao.setImageResource(R.drawable.icon_settings)

                layoutHome.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
                layoutConfiguracao.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))

                txtAdicionar.visibility = View.VISIBLE
                imgAdicionar.setImageResource(R.drawable.icon_more)
                layoutAdicionar.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_purple))

                val scaleAnimation = ScaleAnimation(
                    0.8f, 1.0f, 1f, 1f,
                    Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                layoutAdicionar.startAnimation(scaleAnimation)

                selectedTab = 2
            }
        }

        layoutConfiguracao.setOnClickListener {
            if (selectedTab != 3) {

                parentFragmentManager.beginTransaction()
                findNavController().navigate(R.id.action_homeFragment_to_configuracoesFragment)


                txtAdicionar.visibility = View.GONE
                txtHome.visibility = View.GONE

                imgAdicionar.setImageResource(R.drawable.icon_more)
                imgHome.setImageResource(R.drawable.icon_home)

                layoutAdicionar.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
                layoutHome.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))

                txtConfiguracao.visibility = View.VISIBLE
                imgConfiguracao.setImageResource(R.drawable.icon_settings)
                layoutConfiguracao.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_purple))

                val scaleAnimation = ScaleAnimation(
                    0.8f, 1.0f, 1f, 1f,
                    Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                layoutConfiguracao.startAnimation(scaleAnimation)

                selectedTab = 3
            }
        }

        return view
    }

    private fun verificarUsuario() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null && !user.isEmailVerified) {
            Toast.makeText(requireContext(), "Verifique seu e-mail antes de continuar", Toast.LENGTH_SHORT).show()
            val navController = findNavController()
            navController.navigate(R.id.action_criarContaFragment_to_verificarEmailFragment)
        }
    }
}

