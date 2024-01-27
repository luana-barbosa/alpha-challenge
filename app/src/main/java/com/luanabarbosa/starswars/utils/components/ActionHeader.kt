package com.luanabarbosa.starswars.utils.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.luanabarbosa.starswars.R
import com.luanabarbosa.starswars.databinding.ActionHeaderBinding

class ActionHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val CENTER_TITLE = 1
    }

    private val binding = ActionHeaderBinding
        .inflate(LayoutInflater.from(context), this, true)

    private var leftButtonMenu: Int? = null
    private var rightButtonMenu: Int? = null
    private var headerTitle: String? = null
    private var viewState: AppHeaderViewState = AppHeaderViewState.CenterTitle
        set(value) {
            field = value
            refreshState()
        }

    init {
        setAppHeaderLayout(attrs)
        refreshState()
    }

    private fun setAppHeaderLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(
                attributeSet,
                R.styleable.ActionHeader
            )

            val headerLeftButtonResId =
                attributes.getResourceId(R.styleable.ActionHeader_action_header_left_button, 0)
            val headerRightButtonResId =
                attributes.getResourceId(R.styleable.ActionHeader_action_header_right_button, 0)
            val headerTitleResId =
                attributes.getString((R.styleable.ActionHeader_action_header_title))

            if (headerLeftButtonResId != 0) leftButtonMenu = headerLeftButtonResId
            if (headerRightButtonResId != 0) rightButtonMenu = headerRightButtonResId
            if (headerTitleResId != null) headerTitle = headerTitleResId.toString()

            attributes.recycle()
        }
    }

    fun onTextMenu() = binding.textTitleMenu

    fun onLeftButton() = binding.buttonLeftMenu

    fun onRightButton() = binding.buttonRightSearch

    fun invisibleRightButton(){binding.buttonRightSearch.visibility = View.INVISIBLE}

    private fun refreshState() {
        when (viewState) {
            AppHeaderViewState.CenterTitle -> {
                headerTitle?.let { binding.textTitleMenu.text = it }
                leftButtonMenu?.let { binding.buttonLeftMenu.setImageResource(it) }
                rightButtonMenu?.let { binding.buttonRightSearch}
                binding.apply {
                    textTitleMenu.isVisible = true
                    buttonRightSearch.isVisible = true
                }
            }

            else -> {}
        }
    }

    sealed class AppHeaderViewState {
        object CenterTitle : AppHeaderViewState()
    }
}
