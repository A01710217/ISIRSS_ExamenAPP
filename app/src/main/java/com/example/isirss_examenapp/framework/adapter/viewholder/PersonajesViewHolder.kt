package com.example.isirss_examenapp.framework.adapter.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.isirss_examenapp.data.network.model.PersonajesBase
import com.example.isirss_examenapp.databinding.ItemPersonajesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajesViewHolder(private val binding: ItemPersonajesBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonajesBase, context: Context) {
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