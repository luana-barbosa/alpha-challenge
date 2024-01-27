package com.luanabarbosa.starswars.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.SplashFragmentBinding
import com.luanabarbosa.starswars.utils.extensions.visible

class SplashFragment : Fragment() {

    private val handler = Handler(Looper.getMainLooper())
    private val binding: SplashFragmentBinding by lazy {
        SplashFragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeStatusBarColor()
        onAnimationStart()
    }

    private fun onAnimationStart() {
        with(binding) {
            animation.visible()
            animation.playAnimation()
            handler.postDelayed({
                view?.post {
                    onAnimationEnd()
                }
            }, ANIMATION_DELAY)
        }
    }

    private fun onAnimationEnd() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }

    private fun changeStatusBarColor() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(
            requireContext(), R.color.black
        )
    }

    companion object {
        private const val ANIMATION_DELAY = 3000L
    }

}