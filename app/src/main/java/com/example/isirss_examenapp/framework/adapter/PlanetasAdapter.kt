package com.example.isirss_examenapp.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.isirss_examenapp.data.network.model.PlanetasBase
import com.example.isirss_examenapp.databinding.ItemPlanetasBinding
import com.example.isirss_examenapp.framework.adapter.viewholder.PlanetasViewHolder

class PlanetasAdapter : RecyclerView.Adapter<PlanetasViewHolder>() {

    private lateinit var binding: ItemPlanetasBinding
    lateinit var context: Context
    var data:ArrayList<PlanetasBase> = ArrayList()

    fun PlanetasAdapter(basicData : ArrayList<PlanetasBase>, context:Context){
        this.data = basicData
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetasViewHolder {
        val binding = ItemPlanetasBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlanetasViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetasViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}