package molano.molano.listviewapp



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*


class MainAdapter(val gameInfos: dataArray) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindGames(game: Data){
            itemView.setOnClickListener {
                this@MainAdapter.gameInfos.open(game);
            }
            itemView.textView.text = game.name
            Picasso.get().load(game.img).into(itemView.imageView2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val element = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return ViewHolder(element)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ViewHolder(holder.itemView).bindGames(gameInfos.games[position])
    }

    override fun getItemCount() = this.gameInfos.games.size
}