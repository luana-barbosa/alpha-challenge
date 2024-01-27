package com.luanabarbosa.starswars.people.ui

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
import com.luanabarbosa.starswars.databinding.PeopleFragmentBinding
import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import com.luanabarbosa.starswars.people.ui.adapter.PeopleAdapter
import com.luanabarbosa.starswars.utils.extensions.gone
import com.luanabarbosa.starswars.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleFragment : Fragment() {
    private val peopleViewModel: PeopleViewModel by viewModel()
    private val peopleAdapter: PeopleAdapter by lazy {
        PeopleAdapter { item, position -> navigateToPeopleDetails(item, position) }
    }
    private val binding: PeopleFragmentBinding by lazy {
        PeopleFragmentBinding.inflate(
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
            peopleActionHeader.gone()
            containerSuccess.visible()
            errorContainer.gone()
            peopleViewModel.getPeople()
        }
    }

    private fun setupObserver() {
        peopleViewModel.observerPeople(this) {
            setupAdapter()
            peopleAdapter.submitList(it)
            searchListDisplay(it)
            adapterPagination()
        }

        peopleViewModel.isError.observe(viewLifecycleOwner) {
            with(binding) {
                containerSuccess.gone()
                errorContainer.visible()
                txtTryAgain.setOnClickListener { peopleViewModel.getPeople() }
            }
        }
    }

    private fun setupAdapter() {
        with(binding) {
            peopleActionHeader.visible()
            rvList.visible()
            loading.gone()
            rvList.adapter = peopleAdapter
        }
    }

    private fun setupToolbar() {
        binding.peopleActionHeader.onLeftButton().setOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun navigateToPeopleDetails(model: PeopleListModel, position: Int) {
        findNavController().navigate(
            R.id.peopleDetailsFragment,
            bundleOf(
                KEY_PEOPLE to model,
                POSITION_PEOPLE to position
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
                        peopleViewModel.loadNextPage()
                    }
                }
            }
        })
    }

    fun RecyclerView.getLastVisibleItemPosition(): Int {
        val lastVisibleChild = this.getChildAt(this.childCount - FOUR)
        return this.getChildAdapterPosition(lastVisibleChild)
    }

    private fun searchListDisplay(list: List<PeopleListModel>) {
        with(binding.peopleActionHeader) {
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

    private fun resultListSearch(search: String, list: List<PeopleListModel>) {
        val listResultSearch: MutableList<PeopleListModel> = arrayListOf()
        for (element in list) {
            if (element.name.startsWith(search, ignoreCase = true)) {
                listResultSearch.add(element)
            }
            if (listResultSearch.isEmpty()) {
                binding.errorSearch.visible()
            } else {
                binding.errorSearch.gone()
            }
        }
        peopleAdapter.submitList(listResultSearch)
    }

    companion object {
        private const val ZERO = 0
        private const val FOUR = 4
        const val ONE = 1
        const val KEY_PEOPLE = "PEOPLE"
        const val POSITION_PEOPLE = "POSITION"
    }
}