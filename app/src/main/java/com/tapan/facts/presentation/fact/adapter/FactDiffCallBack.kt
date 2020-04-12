package com.tapan.facts.presentation.fact.adapter

import androidx.recyclerview.widget.DiffUtil
import com.tapan.facts.data.models.Fact

/**
 * Diff utils for swipe refresh if data changes it can set new data efficiently
 */
class FactDiffCallBack : DiffUtil.ItemCallback<Fact>() {
    override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
        return (oldItem.title == newItem.title)
    }

    override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
        return (oldItem.title == newItem.title
                && oldItem.description == newItem.description
                && oldItem.imageHref == newItem.imageHref)
    }
}