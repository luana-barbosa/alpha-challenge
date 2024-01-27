package com.luanabarbosa.starswars.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.HomeFragmentBinding
import com.luanabarbosa.starswars.utils.extensions.ImageHelper
import com.luanabarbosa.starswars.utils.extensions.load

class HomeFragment : Fragment() {
    private val binding: HomeFragmentBinding by lazy {
        HomeFragmentBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupLoadImage()
    }

    private fun setupListeners() {
        with(binding) {
            imgPeople.setOnClickListener { findNavController().navigate(R.id.peopleFragment) }
            imgPlanets.setOnClickListener { findNavController().navigate(R.id.planetsFragment) }
            imgFilms.setOnClickListener { findNavController().navigate(R.id.filmsFragment) }
        }
    }

    private fun setupLoadImage() {
        with(binding) {
            imgPeople.load(ImageHelper.IMAGE_HOME_PEOPLE)
            imgPlanets.load(ImageHelper.IMAGE_HOME_PLANETS)
            imgFilms.load(ImageHelper.IMAGE_HOME_FILMS)
        }
    }
}