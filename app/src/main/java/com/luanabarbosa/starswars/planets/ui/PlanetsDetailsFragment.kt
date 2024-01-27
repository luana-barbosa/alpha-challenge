package com.luanabarbosa.starswars.planets.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.DetailsFragmentBinding
import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.utils.extensions.ImageHelper
import com.luanabarbosa.starswars.utils.extensions.gone
import com.luanabarbosa.starswars.utils.extensions.load
import com.luanabarbosa.starswars.utils.extensions.setValueOrDefault
import com.luanabarbosa.starswars.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetsDetailsFragment : Fragment() {
    private val planetViewModel: PlanetViewModel by viewModel()
    private val binding: DetailsFragmentBinding by lazy {
        DetailsFragmentBinding.inflate(
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
        setupOnLoading()
        setupToolbar()
        planetViewModel.handleArguments(arguments)
        setupObserver()
    }

    private fun setupOnLoading() {
        with(binding) {
            containerSuccess.gone()
            loading.visible()
        }
    }

    private fun setupObserver() {
        planetViewModel.details.observe(viewLifecycleOwner) { setupInfoProfile(it) }
        planetViewModel.position.observe(viewLifecycleOwner) { setupImage(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun setupInfoProfile(planet: PlanetListModel) {
        with(binding) {
            containerSuccess.visible()
            loading.gone()
            txtTitle.setValueOrDefault(planet.name, UNKNOWN)
            txtInfoOne.setValueOrDefault(
                getString(R.string.txt_population, planet.population),
                getString(R.string.txt_population, UNKNOWN)
            )

            txtInfoTwo.setValueOrDefault(
                getString(R.string.txt_rotation, planet.rotationPeriod),
                getString(R.string.txt_rotation, UNKNOWN)
            )
            txtInfoThree.setValueOrDefault(
                getString(R.string.txt_orbital, planet.orbitalPeriod),
                getString(R.string.txt_orbital, UNKNOWN)
            )
            txtInfoFour.setValueOrDefault(
                getString(R.string.txt_diameter, planet.diameter),
                getString(R.string.txt_diameter, UNKNOWN)
            )
            txtInfoFive.setValueOrDefault(
                getString(R.string.txt_terrain, planet.terrain),
                getString(R.string.txt_terrain, UNKNOWN)
            )
            txtInfoSix.setValueOrDefault(
                getString(R.string.txt_climate, planet.climate),
                getString(R.string.txt_climate, UNKNOWN)
            )
        }
    }

    private fun setupImage(position: Int) {
        with(binding) {
            imgDetails.load(ImageHelper.getPlanetsImage(position + 1))
        }
    }

    private fun setupToolbar() {
        with(binding.detailsActionHeader) {
            onTextMenu().text = getString(R.string.planets_details_tit_toolbar)
            invisibleRightButton()
            onLeftButton().setOnClickListener {
                findNavController().navigate(R.id.planetsFragment)
            }
        }
    }

    companion object {
        private const val UNKNOWN = "Unknown"
    }
}
