import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.meuapp.R
import com.example.meuapp.model.Transacao

class TransacaoAdapter(private val transacaoList: MutableList<Transacao>,
    ) :

    RecyclerView.Adapter<TransacaoAdapter.TransacaoViewHolder>() {

    inner class TransacaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.itemNome)
        val categoria: TextView = itemView.findViewById(R.id.itemCategoria)
        val valor: TextView = itemView.findViewById(R.id.itemValor)
        val descricao: TextView = itemView.findViewById(R.id.itemDescricao)
        val editButton: Button = itemView.findViewById(R.id.editButton)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransacaoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return TransacaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransacaoViewHolder, position: Int) {
        val transacao = transacaoList[position]
        holder.nome.text = transacao.nome
        holder.categoria.text = transacao.categoria
        holder.valor.text = "R$ ${String.format("%.2f", transacao.valor)}"
        holder.descricao.text = transacao.descricao

        holder.editButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Editando: ${transacao.nome}", Toast.LENGTH_SHORT).show()
        }

        holder.deleteButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Excluindo: ${transacao.nome}", Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount(): Int = transacaoList.size
}
