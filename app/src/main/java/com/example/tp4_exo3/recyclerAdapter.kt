package com.example.tp4_exo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *Created by Fedala Amira.
 */
class recyclerAdapter(val activity : MainActivity) : RecyclerView.Adapter<recyclerAdapter.recyclerViewHolder>(){
    class recyclerViewHolder(v : View) : RecyclerView.ViewHolder(v){

        val itemLayout = v.findViewById<RelativeLayout>(R.id.tacheLayoutView)
        val nom = v.findViewById<TextView>(R.id.tacheView)
        val date = v.findViewById<TextView>(R.id.tacheDateView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
        return recyclerViewHolder(LayoutInflater.from(activity).inflate(R.layout.tache_view, parent, false))
    }

    override fun getItemCount(): Int {
        return activity.list.size
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {

        holder.date.text = activity.itemList[activity.list[position]].dateToString()
        holder.nom.text = activity.itemList[activity.list[position]].nom
        holder.itemLayout.setOnClickListener {
            activity.itemList.removeAt(activity.list[position])
            activity.changeTasksViewing(activity.pst)
        }
    }
}