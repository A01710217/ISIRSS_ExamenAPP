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

class PlanetasFragment : Fragment()  {
    private var _binding: FragmentPlanetasBinding? = null

    // Esta propiedad es sólo válida entre onCreateView y onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: PlanetasViewModel

    private lateinit var recyclerView: RecyclerView
    private val adapter : PlanetasAdapter = PlanetasAdapter()
    private lateinit var data:ArrayList<PlanetasBase>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PlanetasViewModel::class.java]

        _binding = FragmentPlanetasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        data = ArrayList()

        initializeComponents(root)
        initializeObservers()
        viewModel.getPlanetasList()

        return root
    }

    private fun setUpRecyclerView(dataForList:ArrayList<PlanetasBase>){
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            2,
            GridLayoutManager.VERTICAL,
            false    )
        recyclerView.layoutManager = gridLayoutManager
        adapter.PlanetasAdapter(dataForList,requireContext())
        recyclerView.adapter = adapter
    }

    private fun initializeComponents(root: View){
        recyclerView = root.findViewById(R.id.RVPlanetas)
    }

    private fun initializeObservers(){
        viewModel.planetasObjectLiveData.observe(viewLifecycleOwner) { planetaObject ->
            setUpRecyclerView(planetaObject.items)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}