package com.example.s8137077assignment2.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.s8137077assignment2.R

class EntityAdapter(
    private var entities: List<Map<String, Any?>>,
    private val onItemClick: (Map<String, Any?>) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        val property1: TextView =
            itemView.findViewById(R.id.tvProperty1)

        val property2: TextView =
            itemView.findViewById(R.id.tvProperty2)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntityViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)

        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: EntityViewHolder,
        position: Int
    ) {

        val entity = entities[position]

        val visibleProperties =
            entity.filterKeys { it != "description" }
                .entries
                .take(2)

        holder.property1.text =
            visibleProperties.getOrNull(0)?.let {
                "${it.key}: ${it.value}"
            } ?: ""

        holder.property2.text =
            visibleProperties.getOrNull(1)?.let {
                "${it.key}: ${it.value}"
            } ?: ""

        holder.itemView.setOnClickListener {
            onItemClick(entity)
        }
    }

    override fun getItemCount(): Int =
        entities.size

    fun updateData(newEntities: List<Map<String, Any?>>) {

        entities = newEntities

        notifyDataSetChanged()
    }
}