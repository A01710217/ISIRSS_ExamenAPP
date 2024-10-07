package com.example.isirss_examenapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.isirss_examenapp.R
import com.example.isirss_examenapp.data.network.model.PersonajesBase
import com.example.isirss_examenapp.databinding.FragmentPersonajesBinding
import com.example.isirss_examenapp.framework.adapter.PersonajesAdapter
import com.example.isirss_examenapp.framework.viewmodel.PersonajesViewModel

class PersonajesFragment : Fragment() {
    private var _binding: FragmentPersonajesBinding? = null

    // Esta propiedad es sólo válida entre onCreateView y onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PersonajesViewModel

    private lateinit var recyclerView: RecyclerView
    private val adapter : PersonajesAdapter = PersonajesAdapter()
    private lateinit var data:ArrayList<PersonajesBase>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PersonajesViewModel::class.java]

        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        data = ArrayList()

        initializeComponents(root)
        initializeObservers()
        viewModel.getPersonajesList()

        return root
    }

    private fun setUpRecyclerView(dataForList:ArrayList<PersonajesBase>){
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            2,
            GridLayoutManager.VERTICAL,
            false    )
        recyclerView.layoutManager = gridLayoutManager
        adapter.PersonajesAdapter(dataForList,requireContext())
        recyclerView.adapter = adapter
    }

    private fun initializeComponents(root: View){
        recyclerView = root.findViewById(R.id.RVPersonajes)
    }

    private fun initializeObservers(){
        viewModel.personajesObjectLiveData.observe(viewLifecycleOwner) { personajeObject ->
            setUpRecyclerView(personajeObject.items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}