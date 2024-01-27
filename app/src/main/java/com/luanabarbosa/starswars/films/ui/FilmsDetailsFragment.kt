package com.luanabarbosa.starswars.films.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.DetailsFragmentBinding
import com.luanabarbosa.starswars.films.data.model.FilmsListModel
import com.luanabarbosa.starswars.utils.extensions.ImageHelper
import com.luanabarbosa.starswars.utils.extensions.gone
import com.luanabarbosa.starswars.utils.extensions.load
import com.luanabarbosa.starswars.utils.extensions.setValueOrDefault
import com.luanabarbosa.starswars.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmsDetailsFragment : Fragment() {
    private val filmsViewModel: FilmsViewModel by viewModel()
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
        filmsViewModel.handleArguments(arguments)
        setupObserver()
    }

    private fun setupOnLoading() {
        with(binding) {
            containerSuccess.gone()
            loading.visible()
        }
    }

    private fun setupObserver() {
        filmsViewModel.details.observe(viewLifecycleOwner) { setupInfoProfile(it) }
        filmsViewModel.position.observe(viewLifecycleOwner) { setupImage(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun setupInfoProfile(films: FilmsListModel) {
        with(binding) {
            containerSuccess.visible()
            loading.gone()
            txtTitle.setValueOrDefault(films.title, UNKNOWN)
            txtInfoOne.setValueOrDefault(
                getString(R.string.txt_created, films.releaseDate),
                getString(R.string.txt_created, UNKNOWN)
            )

            txtInfoTwo.setValueOrDefault(
                getString(R.string.txt_director, films.director),
                getString(R.string.txt_director, UNKNOWN)
            )
            txtInfoThree.setValueOrDefault(
                getString(R.string.txt_producer, films.producer),
                getString(R.string.txt_producer, UNKNOWN)
            )
            txtInfoFour.setValueOrDefault(
                getString(R.string.txt_opening_crawl, films.openingCrawl),
                getString(R.string.txt_opening_crawl, UNKNOWN)
            )
            txtInfoFive.gone()
            txtInfoSix.gone()
        }
    }

    private fun setupImage(position: Int) {
        with(binding) {
            imgDetails.load(ImageHelper.getFilmsImage(position + 1))
        }
    }

    private fun setupToolbar() {
        with(binding.detailsActionHeader) {
            onTextMenu().text = getString(R.string.films_details_tit_toolbar)
            invisibleRightButton()
            onLeftButton().setOnClickListener {
                findNavController().navigate(R.id.filmsFragment)
            }
        }
    }

    companion object {
        private const val UNKNOWN = "Unknown"
    }
}
