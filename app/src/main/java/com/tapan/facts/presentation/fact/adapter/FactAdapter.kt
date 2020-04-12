package com.tapan.facts.presentation.fact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tapan.facts.R
import com.tapan.facts.data.models.Fact
import com.tapan.facts.loadImage
import kotlinx.android.synthetic.main.item_fact_list.view.*
class FactAdapter(private val articles: List<Fact> = listOf()) :
    RecyclerView.Adapter<FactAdapter.ViewHolder>() {


    private val differ: AsyncListDiffer<Fact> =
        object : AsyncListDiffer<Fact>(this, FactDiffCallBack()) {}


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun setData(fact: Fact) {
            itemView.apply {
                tvTitle.text = fact.title.orEmpty()
                tvDescription.text = fact.description.orEmpty()
                ivFactImage.loadImage(fact.imageHref.orEmpty())
            }
        }
    }

    fun setList(list: List<Fact>) {
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_fact_list,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
    }
}