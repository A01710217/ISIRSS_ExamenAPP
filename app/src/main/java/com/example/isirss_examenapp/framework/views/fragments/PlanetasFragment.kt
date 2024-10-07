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
import com.example.isirss_examenapp.data.network.model.PlanetasBase
import com.example.isirss_examenapp.databinding.FragmentPlanetasBinding
import com.example.isirss_examenapp.framework.adapter.PlanetasAdapter
import com.example.isirss_examenapp.framework.viewmodel.PlanetasViewModel

class PlanetasFragment : Fragment() {
    private var _binding: FragmentPlanetasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PlanetasViewModel
    private lateinit var recyclerView: RecyclerView
    private val adapter: PlanetasAdapter = PlanetasAdapter()
    private var isLoading = false // Variable para evitar cargas múltiples

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PlanetasViewModel::class.java]
        _binding = FragmentPlanetasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initializeComponents(root)
        initializeObservers()
        viewModel.getPlanetasList() // Cargar la lista inicial de planetas

        return root
    }

    private fun setUpRecyclerView(dataForList: ArrayList<PlanetasBase>) {
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = gridLayoutManager
        adapter.PlanetasAdapter(dataForList, requireContext())
        recyclerView.adapter = adapter

        // Añadir ScrollListener para detectar el final del RecyclerView
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && !isLoading) {
                    // Si no se puede hacer scroll más abajo y no estamos cargando
                    isLoading = true
                    viewModel.getPlanetasList(loadMore = true) // Cargar más planetas
                }
            }
        })
    }

    private fun initializeComponents(root: View) {
        recyclerView = root.findViewById(R.id.RVPlanetas)
    }

    private fun initializeObservers() {
        viewModel.planetasObjectLiveData.observe(viewLifecycleOwner) { planetaObject ->
            setUpRecyclerView(planetaObject.items) // Configura el RecyclerView con los planetas
            isLoading = false // Restablecer el indicador de carga
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
