package com.example.meuapp.ui.Adiciona

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.example.meuapp.databinding.FragmentAdicionarBinding
import com.example.meuapp.model.Transacao
import com.example.meuapp.ui.helper.FirebaseHelper

class AdicionarFragment : Fragment() {
    private var binding: FragmentAdicionarBinding? = null
private lateinit var transacao: Transacao
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdicionarBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()

        // Configuração do primeiro Spinner
        val spinner1 = view.findViewById<Spinner>(R.id.spinner_options)
        val adapter1 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_options,
            android.R.layout.simple_spinner_item
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter1

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Spinner 1: $selectedItem", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Configuração do segundo Spinner
        val spinner2 = view.findViewById<Spinner>(R.id.spinner_2)
        val adapter2 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dropdown_options_2,
            android.R.layout.simple_spinner_item
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = adapter2

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(requireContext(), "Spinner 2: $selectedItem", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun initListeners() {
        binding!!.btnConfirmar.setOnClickListener { view ->
            val selectedOption1 =
                binding!!.spinnerOptions.selectedItem.toString()
            val selectedOption2 = binding!!.spinner2.selectedItem.toString()
            Toast.makeText(
                requireContext(),
                "Opções selecionadas: $selectedOption1 e $selectedOption2",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding!!.btnConfirmar.setOnClickListener {
            val nome = binding!!.etNome.text.toString()
            val categoria = binding!!.spinnerOptions.selectedItem.toString()
            val formaPagamento = binding!!.spinner2.selectedItem.toString()
            val descricao = binding!!.etDescricao.text.toString()
            val valorFixo = binding!!.switchValorFixo.isChecked
            val valor = binding!!.etValor.text.toString().toDoubleOrNull() ?: 0.0

            val tipo = when {
                binding!!.rbGanho.isChecked -> "Receita"
                binding!!.rbGasto.isChecked -> "Despesa"
                binding!!.rbMeta.isChecked -> "Meta"
                else -> {
                    Toast.makeText(requireContext(), "Selecione um tipo de transação!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            if (nome.isEmpty() || valor == 0.0) {
                Toast.makeText(requireContext(), "Preencha os campos obrigatórios!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val transacao = Transacao(
                id = System.currentTimeMillis().toString(),
                tipo = tipo,
                nome = nome,
                categoria = categoria,
                valor = valor,
                formaPagamento = formaPagamento,
                descricao = descricao,
                valorFixo = valorFixo
            )

            salvarTransacaoNoFirebase(transacao)
        }
    }
    private fun salvarTransacaoNoFirebase(transacao: Transacao) {
        FirebaseHelper
            .getDatabase()
            .child("transacao")
            .child(FirebaseHelper.getIdUser() ?:"")
            .child(transacao.id)
            .setValue(transacao)
            .addOnCompleteListener { transacao ->
                if (transacao.isSuccessful){
                    Toast.makeText(requireContext(), "Transação salva com sucesso!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Erro ao salvar a transação!", Toast.LENGTH_SHORT).show()

            }

    }





    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}