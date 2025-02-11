package com.example.meuapp.ui.Adiciona

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R

class AdicionarFragment : Fragment() {
    private var selectedTab = 2
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_adicionar, container, false)

        val layoutHome = view.findViewById<View>(R.id.layout_home)
        val layoutAdicionar = view.findViewById<View>(R.id.layout_Adicionar)
        val layoutConfiguracao = view.findViewById<View>(R.id.layout_configuracoes)

        val imgHome = view.findViewById<ImageView>(R.id.img_home)
        val imgAdicionar = view.findViewById<ImageView>(R.id.img_Adicionar)
        val imgConfiguracao = view.findViewById<ImageView>(R.id.img_configuracao)

        val txtHome = view.findViewById<TextView>(R.id.txt_home)
        val txtAdicionar = view.findViewById<TextView>(R.id.txt_Adicionar)
        val txtConfiguracao = view.findViewById<TextView>(R.id.txt_configuracao)

        layoutHome.setOnClickListener { setTab(1, layoutHome, imgHome, txtHome, R.drawable.icon_home) }
        layoutAdicionar.setOnClickListener { setTab(2, layoutAdicionar, imgAdicionar, txtAdicionar, R.drawable.icon_more) }
        layoutConfiguracao.setOnClickListener { setTab(3, layoutConfiguracao, imgConfiguracao, txtConfiguracao, R.drawable.icon_settings) }

        return view
    }
    private fun setTab(tab: Int, layout: View, imageView: ImageView, textView: TextView, iconRes: Int) {
        if (selectedTab != tab) {
            when (tab) {
                1 -> {
                    findNavController().navigate(R.id.action_adicionarFragment_to_homeFragment)
                }
                2 -> {
                    parentFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.adicionarFragment, AdicionarFragment())
                        .commit()
                }
                3 -> {
                    findNavController().navigate(R.id.action_adicionarFragment_to_configuracoesFragment)
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
        val layoutHome = requireView().findViewById<View>(R.id.layout_home)
        val layoutAdicionar = requireView().findViewById<View>(R.id.layout_Adicionar)
        val layoutConfiguracao = requireView().findViewById<View>(R.id.layout_configuracoes)

        val imgHome = requireView().findViewById<ImageView>(R.id.img_home)
        val imgAdicionar = requireView().findViewById<ImageView>(R.id.img_Adicionar)
        val imgConfiguracao = requireView().findViewById<ImageView>(R.id.img_configuracao)

        val txtHome = requireView().findViewById<TextView>(R.id.txt_home)
        val txtAdicionar = requireView().findViewById<TextView>(R.id.txt_Adicionar)
        val txtConfiguracao = requireView().findViewById<TextView>(R.id.txt_configuracao)

        txtHome.visibility = View.GONE
        txtAdicionar.visibility = View.GONE
        txtConfiguracao.visibility = View.GONE

        imgHome.setImageResource(R.drawable.icon_home)
        imgAdicionar.setImageResource(R.drawable.icon_more)
        imgConfiguracao.setImageResource(R.drawable.icon_settings)

        layoutHome.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
        layoutAdicionar.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
        layoutConfiguracao.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.transparent))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração do primeiro Spinner
        val spinner1: Spinner = view.findViewById(R.id.spinner_options)
        val adapter1 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_options, // Lista de opções
            android.R.layout.simple_spinner_item
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter1

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Spinner 1: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Configuração do segundo Spinner
        val spinner2: Spinner = view.findViewById(R.id.spinner_2)
        val adapter2 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_options_2, // Lista de opções
            android.R.layout.simple_spinner_item
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Spinner 2: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}
