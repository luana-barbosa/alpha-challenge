package com.luanabarbosa.starswars.films.ui

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
import com.luanabarbosa.starswars.databinding.FilmsFragmentBinding
import com.luanabarbosa.starswars.films.data.model.FilmsListModel
import com.luanabarbosa.starswars.films.ui.adapter.FilmsAdapter
import com.luanabarbosa.starswars.utils.extensions.gone
import com.luanabarbosa.starswars.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsFragment : Fragment() {
    private val filmsViewModel: FilmsViewModel by viewModel()
    private val filmsAdapter: FilmsAdapter by lazy {
        FilmsAdapter { item, position -> navigateToFilmsDetails(item, position) }
    }
    private val binding: FilmsFragmentBinding by lazy {
        FilmsFragmentBinding.inflate(
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
        setupListeners()
    }

    private fun getUsersList() {
        with(binding) {
            loading.visible()
            containerSuccess.visible()
            filmsActionHeader.gone()
            errorContainer.gone()
            filmsViewModel.getFilms()
        }
    }

    private fun setupObserver() {
        filmsViewModel.observerCards(this) {
            setupAdapter()
            filmsAdapter.submitList(it)
            adapterPagination()
            searchListDisplay(it)
        }

        filmsViewModel.isError.observe(viewLifecycleOwner) {
            with(binding) {
                containerSuccess.gone()
                errorContainer.visible()
                txtTryAgain.setOnClickListener { filmsViewModel.getFilms() }
            }
        }
    }

    private fun setupAdapter() {
        with(binding) {
            rvList.visible()
            filmsActionHeader.visible()
            loading.gone()
            rvList.adapter = filmsAdapter
        }
    }

    private fun setupListeners() {
        with(binding.filmsActionHeader) {
            onLeftButton().setOnClickListener {
                findNavController().navigate(R.id.homeFragment)
            }
        }
    }

    private fun navigateToFilmsDetails(film: FilmsListModel, position: Int?) {
        findNavController().navigate(
            R.id.filmsDetailsFragment,
            bundleOf(
                "FILMS" to film,
                "POSITION" to position
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
                        filmsViewModel.loadNextPage()
                    }
                }
            }
        })
    }

    fun RecyclerView.getLastVisibleItemPosition(): Int {
        val lastVisibleChild = this.getChildAt(this.childCount - FOUR)
        return this.getChildAdapterPosition(lastVisibleChild)
    }

    private fun searchListDisplay(list: List<FilmsListModel>) {
        with(binding.filmsActionHeader) {
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

    private fun resultListSearch(search: String, list: List<FilmsListModel>) {
        val listResultSearch: MutableList<FilmsListModel> = arrayListOf()
        for (element in list) {
            if (element.title.startsWith(search, ignoreCase = true)) {
                listResultSearch.add(element)
            }
            if (listResultSearch.isEmpty()) {
                binding.errorSearch.visible()
            } else {
                binding.errorSearch.gone()
            }
        }
        filmsAdapter.submitList(listResultSearch)
    }

    companion object {
        const val ZERO = 0
        private const val FOUR = 4
        const val ONE = 1
        const val KEY_FILMS = "FILMS"
        const val POSITION_FILMS = "POSITION"

    }
}