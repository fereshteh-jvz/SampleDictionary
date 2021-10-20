package com.shetabit.sampledictionary

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shetabit.sampledictionary.data.local.WordsEntity

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    private var items: List<WordsEntity> = ArrayList()

    fun setItems(items: List<WordsEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dictionary, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = items[position]
        holder.txtTitle.text = item.title
        if (item.isSelected)
            holder.imgSeen.visibility = View.VISIBLE
        else
            holder.imgSeen.visibility = View.GONE
        holder.card.setOnClickListener {
            navigateToDetail(it, item)
        }
    }


    private fun navigateToDetail(view: View, item: WordsEntity) {
        val direction = WordListFragmentDirections.actionWordListToWordDetail(
            item.title, item.id
        )
        view.findNavController().navigate(direction)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class WordViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var card: CardView = view.findViewById(R.id.card)
        var txtTitle: TextView = view.findViewById(R.id.txtTitle)
        var imgSeen: ImageView = view.findViewById(R.id.imgSeen)
    }
}
