package com.luanabarbosa.starswars.planets.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.PlanetsFragmentBinding
import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.planets.ui.adapter.PlanetAdapter
import com.luanabarbosa.starswars.utils.extensions.gone
import com.luanabarbosa.starswars.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlanetFragment : Fragment() {
    private val planetsViewModel: PlanetViewModel by viewModel()
    private val planetAdapter: PlanetAdapter by lazy {
        PlanetAdapter { item, position -> navigateToPlanetDetails(item, position) }
    }
    private val binding: PlanetsFragmentBinding by lazy {
        PlanetsFragmentBinding.inflate(
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
        setupObserver()
        getUsersList()
        setupToolbar()
    }

    private fun getUsersList() {
        with(binding) {
            loading.visible()
            planetsActionHeader.gone()
            containerSuccess.visible()
            errorContainer.gone()
            planetsViewModel.getPlanets()
        }
    }

    private fun setupObserver() {
        planetsViewModel.observerCards(this) {
            setupAdapter()
            planetAdapter.submitList(it)
            searchListDisplay(it)
            adapterPagination()
        }

        planetsViewModel.isError.observe(viewLifecycleOwner) {
            with(binding) {
                containerSuccess.gone()
                errorContainer.visible()
                txtTryAgain.setOnClickListener { planetsViewModel.getPlanets() }
            }
        }
    }

    private fun setupAdapter() {
        with(binding) {
            planetsActionHeader.visible()
            rvList.visible()
            loading.gone()
            rvList.adapter = planetAdapter
        }
    }

    private fun setupToolbar() {
        binding.planetsActionHeader.onLeftButton().setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun navigateToPlanetDetails(model: PlanetListModel, position: Int) {
        findNavController().navigate(
            R.id.planetDetailsFragment,
            bundleOf(
                KEY_PLANET to model,
                POSITION_PLANET to position
            )
        )
    }

    private fun adapterPagination() {
        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > ZERO) {
                    val lastVisibleItemPosition = recyclerView.getLastVisibleItemPosition()
                    val totalItemCount = recyclerView.adapter?.itemCount ?: ZERO
                    if (totalItemCount <= lastVisibleItemPosition + FOUR) {
                        planetsViewModel.loadNextPage()
                    }
                }
            }
        })
    }

    fun RecyclerView.getLastVisibleItemPosition(): Int {
        val lastVisibleChild = this.getChildAt(this.childCount - FOUR)
        return this.getChildAdapterPosition(lastVisibleChild)
    }

    private fun searchListDisplay(list: List<PlanetListModel>) {
        with(binding.planetsActionHeader) {
            onRightButton().setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    resultListSearch(query, list)
                    onRightButton().clearFocus()
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    resultListSearch(newText, list)
                    return false
                }
            })
        }
    }

    private fun resultListSearch(search: String, list: List<PlanetListModel>) {
        val listResultSearch: MutableList<PlanetListModel> = arrayListOf()
        for (element in list) {
            if (element.name.startsWith(search, ignoreCase = true)) {
                listResultSearch.add(element)
            }

            if (listResultSearch.isEmpty()) {
                binding.errorSearch.visible()
            } else{
                binding.errorSearch.gone()
            }
        }
        planetAdapter.submitList(listResultSearch)
    }

    companion object {
        private const val ZERO = 0
        private const val FOUR = 4
        const val ONE = 1
        const val KEY_PLANET = "PLANET"
        const val POSITION_PLANET = "POSITION"
    }
}