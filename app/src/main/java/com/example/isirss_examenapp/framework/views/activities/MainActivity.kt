package com.example.isirss_examenapp.framework.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.isirss_examenapp.R
import com.example.isirss_examenapp.databinding.ActivityMainBinding
import com.example.isirss_examenapp.framework.views.fragments.PersonajesFragment
import com.example.isirss_examenapp.framework.views.fragments.PlanetasFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var currentFragment: Fragment
    private var currentMenuOption: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        initializeListeners()
        exchangeCurrentFragment(PersonajesFragment(), "Personajes") // Fragmento por defecto
    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeListeners() {
        binding.appBarMain.llPersonajes.setOnClickListener {
            selectMenuOption("Personajes")
        }

        binding.appBarMain.llPlanetas.setOnClickListener {
            selectMenuOption("Planetas")
        }
    }

    private fun exchangeCurrentFragment(newFragment: Fragment, newMenuOption: String) {
        currentFragment = newFragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, currentFragment)
            .commit()

        currentMenuOption = newMenuOption
    }

    private fun selectMenuOption(menuOption: String) {
        if (menuOption == currentMenuOption) {
            return
        }

        when (menuOption) {
            "Personajes" -> exchangeCurrentFragment(PersonajesFragment(), "Personajes")
            "Planetas" -> exchangeCurrentFragment(PlanetasFragment(), "Planetas")
        }
    }
}
