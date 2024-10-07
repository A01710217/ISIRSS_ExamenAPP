package com.example.isirss_examenapp.framework.adapter.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.isirss_examenapp.data.network.model.PersonajesBase
import com.example.isirss_examenapp.data.network.model.PlanetasBase
import com.example.isirss_examenapp.databinding.ItemPlanetasBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetasViewHolder(private val binding: ItemPlanetasBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PlanetasBase, context: Context) {
        binding.TVName.text = item.name

        CoroutineScope(Dispatchers.IO).launch {
            val urlImage = item.image
            CoroutineScope(Dispatchers.Main).launch {
                Glide.with(binding.IVPhoto.context)
                    .load(urlImage)
                    .into(binding.IVPhoto)
            }
        }
    }

}