package com.example.isirss_examenapp.framework.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.isirss_examenapp.R
import com.example.isirss_examenapp.data.network.model.PersonajesBase
import com.example.isirss_examenapp.databinding.FragmentPersonajesBinding
import com.example.isirss_examenapp.framework.adapter.PersonajesAdapter
import com.example.isirss_examenapp.framework.viewmodel.PersonajesViewModel
import kotlinx.coroutines.Job

class PersonajesFragment : Fragment() {
    private var _binding: FragmentPersonajesBinding? = null

    // Esta propiedad es sólo válida entre onCreateView y onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PersonajesViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter: PersonajesAdapter = PersonajesAdapter()
    private lateinit var data: ArrayList<PersonajesBase>

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

        // Búsqueda
        binding.searchView.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    showLoading() // Mostrar barra de progreso
                    viewModel.searchPersonajeByName(query) // Invoca el método de búsqueda
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
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = gridLayoutManager
        adapter.PersonajesAdapter(dataForList, requireContext())
        recyclerView.adapter = adapter
    }

    private fun initializeComponents(root: View) {
        recyclerView = root.findViewById(R.id.RVPersonajes)
    }

    private fun initializeObservers() {
        viewModel.personajesObjectLiveData.observe(viewLifecycleOwner) { personajeObject ->
            hideLoading() // Ocultar barra de progreso
            setUpRecyclerView(personajeObject.items)
        }

        // Observador para los resultados de búsqueda
        viewModel.personajesSearchResultLiveData.observe(viewLifecycleOwner) { personajesList ->
            hideLoading() // Ocultar barra de progreso
            setUpRecyclerView(ArrayList(personajesList)) // Convierte a ArrayList
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE // Mostrar la barra de progreso
        binding.RVPersonajes.visibility = View.GONE // Ocultar RecyclerView mientras se carga
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE // Ocultar la barra de progreso
        binding.RVPersonajes.visibility = View.VISIBLE // Mostrar RecyclerView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
