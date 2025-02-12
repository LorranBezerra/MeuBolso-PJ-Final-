package com.example.meuapp.ui.Config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meuapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ExcluirContaFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar o layout para este fragmento
        val binding = inflater.inflate(R.layout.fragment_excluir_conta, container, false)

        // Referência ao botão para excluir a conta
        val deleteButton: Button = binding.findViewById(R.id.apagarconta)

        // Configurar o evento de clique para excluir a conta
        deleteButton.setOnClickListener {
            deleteUserAccount()
        }

        return binding
    }

    private fun deleteUserAccount() {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Conta apagada com sucesso
                        Toast.makeText(requireContext(), "Conta apagada com sucesso", Toast.LENGTH_SHORT).show()

                        // Navegar para a tela de login após a exclusão
                        findNavController().navigate(R.id.action_excluirContaFragment_to_autenticacao) // Ajuste conforme sua navegação
                    } else {
                        // Erro ao apagar conta
                        Toast.makeText(requireContext(), "Erro ao apagar a conta", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            // Usuário não está autenticado
            Toast.makeText(requireContext(), "Usuário não autenticado", Toast.LENGTH_SHORT).show()
        }
    }
}
