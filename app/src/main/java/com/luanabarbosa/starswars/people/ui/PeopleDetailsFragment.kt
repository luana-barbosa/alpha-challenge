package com.luanabarbosa.starswars.people.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.DetailsFragmentBinding
import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import com.luanabarbosa.starswars.utils.extensions.ImageHelper
import com.luanabarbosa.starswars.utils.extensions.gone
import com.luanabarbosa.starswars.utils.extensions.load
import com.luanabarbosa.starswars.utils.extensions.setValueOrDefault
import com.luanabarbosa.starswars.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleDetailsFragment : Fragment() {
    private val peopleViewModel: PeopleViewModel by viewModel()
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
        peopleViewModel.handleArguments(arguments)
        setupObserver()
    }

    private fun setupOnLoading() {
        with(binding) {
            containerSuccess.gone()
            loading.visible()
        }
    }

    private fun setupObserver() {
        peopleViewModel.details.observe(viewLifecycleOwner) { setupInfoProfile(it) }
        peopleViewModel.position.observe(viewLifecycleOwner) { setupImage(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun setupInfoProfile(people: PeopleListModel) {
        with(binding) {
            containerSuccess.visible()
            loading.gone()
            txtTitle.setValueOrDefault(people.name, UNKNOWN)
            txtInfoOne.setValueOrDefault(
                getString(R.string.txt_birth, people.birthYear),
                getString(R.string.txt_birth, UNKNOWN)
            )

            txtInfoTwo.setValueOrDefault(
                getString(R.string.txt_height, people.height),
                getString(R.string.txt_height, UNKNOWN)
            )
            txtInfoThree.setValueOrDefault(
                getString(R.string.txt_mass, people.mass),
                getString(R.string.txt_mass, UNKNOWN)
            )
            txtInfoFour.setValueOrDefault(
                getString(R.string.txt_gender, people.gender),
                getString(R.string.txt_gender, UNKNOWN)
            )
            txtInfoFive.setValueOrDefault(
                getString(R.string.txt_hairColor, people.hairColor),
                getString(R.string.txt_hairColor, UNKNOWN)
            )
            txtInfoSix.setValueOrDefault(
                getString(R.string.txt_skinColor, people.skinColor),
                getString(R.string.txt_skinColor, UNKNOWN)
            )
        }
    }

    private fun setupImage(position: Int) {
        with(binding) {
            imgDetails.load(ImageHelper.getPeopleImage(position + 1))
        }
    }

    private fun setupToolbar() {
        with(binding.detailsActionHeader) {
            onTextMenu().text = getString(R.string.people_details_tit_toolbar)
            invisibleRightButton()
            onLeftButton().setOnClickListener {
                findNavController().navigate(R.id.peopleFragment)
            }
        }
    }

    companion object {
        private const val UNKNOWN = "Unknown"
    }
}
