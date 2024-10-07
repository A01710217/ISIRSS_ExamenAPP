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
    private val binding get() = _binding!!
    private lateinit var viewModel: PersonajesViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter: PersonajesAdapter = PersonajesAdapter()
    private var isLoading = false // Variable para evitar cargas múltiples

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PersonajesViewModel::class.java]
        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initializeComponents(root)
        initializeObservers()
        viewModel.getPersonajesList()

        // Búsqueda
        binding.searchView.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null && query.isNotEmpty()) {
                        showLoading()
                        viewModel.searchPersonajeByName(query)
                        hideLoading()
                        isLoading = true
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })

        return root
    }

    private fun setUpRecyclerView(dataForList: ArrayList<PersonajesBase>) {
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
        adapter.PersonajesAdapter(dataForList, requireContext())
        recyclerView.adapter = adapter

        // Añadir ScrollListener para detectar el final del RecyclerView
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    // Si no se puede hacer scroll más abajo y no estamos cargando
                    isLoading = true
                    showLoading()
                    viewModel.getPersonajesList() // Cargar más personajes
                }
            }
        })
    }

    private fun initializeComponents(root: View) {
        recyclerView = root.findViewById(R.id.RVPersonajes)
    }

    private fun initializeObservers() {
        // Cargar nueva paginacion cuando se llegue al final del RecyclerView
        viewModel.personajesObjectLiveData.observe(viewLifecycleOwner) { personajeObject ->
            hideLoading() // Ocultar barra de progreso
            setUpRecyclerView(personajeObject.items)
            isLoading = false
        }

        viewModel.personajesSearchResultLiveData.observe(viewLifecycleOwner) { personajes ->
            hideLoading() // Ocultar barra de progreso
            setUpRecyclerView(personajes as ArrayList<PersonajesBase>)
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
