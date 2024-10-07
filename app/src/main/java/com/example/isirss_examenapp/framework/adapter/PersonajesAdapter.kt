package com.example.isirss_examenapp.framework.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.isirss_examenapp.data.network.model.PersonajesBase
import com.example.isirss_examenapp.databinding.ItemPersonajesBinding
import com.example.isirss_examenapp.framework.adapter.viewholder.PersonajesViewHolder

class PersonajesAdapter : RecyclerView.Adapter<PersonajesViewHolder>() {

    private lateinit var binding: ItemPersonajesBinding
    lateinit var context: Context
    var data:ArrayList<PersonajesBase> = ArrayList()

    fun PersonajesAdapter(basicData : ArrayList<PersonajesBase>, context:Context){
        this.data = basicData
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesViewHolder {
        val binding = ItemPersonajesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PersonajesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonajesViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}