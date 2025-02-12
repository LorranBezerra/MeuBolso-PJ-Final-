package com.example.meuapp.ui.BtnMenu
import TransacaoAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meuapp.databinding.FragmentDespesasBinding
import com.example.meuapp.model.Transacao
import com.example.meuapp.ui.helper.FirebaseHelper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ReceitasFragment : Fragment() {

    private lateinit var binding: FragmentDespesasBinding
    private lateinit var database: DatabaseReference
    private lateinit var transacaoList: MutableList<Transacao>
    private lateinit var adapter: TransacaoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDespesasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().getReference("transacoes")
        transacaoList = mutableListOf()
        adapter = TransacaoAdapter(transacaoList)

        binding.RvDepesas.layoutManager = LinearLayoutManager(requireContext())
        binding.RvDepesas.setHasFixedSize(true)
        binding.RvDepesas.adapter = adapter
        initRecyclerView()
        getDespesasFromFirebase()

    }

    private fun getDespesasFromFirebase() {
        FirebaseHelper
            .getDatabase()
            .child("transacao")  // Verifique se esse é realmente o nó correto
            .child(FirebaseHelper.getIdUser() ?: "")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        transacaoList.clear()

                        for (snap in snapshot.children) {
                            val transacao = snap.getValue(Transacao::class.java)
                            Log.d("FirebaseData", "Transação recebida: $transacao")

                            if (transacao != null && transacao.tipo == "Receita") {
                                transacaoList.add(transacao)
                            }
                        }

                        adapter.notifyDataSetChanged()
                        Log.d("FirebaseData", "Total de transações carregadas: ${transacaoList.size}")
                    } else {
                        Log.d("FirebaseData", "Nenhuma transação encontrada no Firebase")
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("FirebaseError", "Erro ao carregar dados: ${error.message}")
                }
            })
    }
    private fun initRecyclerView() {
        adapter = TransacaoAdapter(transacaoList)
        binding.RvDepesas.layoutManager = LinearLayoutManager(requireContext())
        binding.RvDepesas.adapter = adapter
    }


}

