package com.luanabarbosa.starswars.planets.ui

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanabarbosa.starswars.planets.data.model.PlanetListModel
import com.luanabarbosa.starswars.planets.domain.usecase.PlanetUseCase
import com.luanabarbosa.starswars.planets.ui.PlanetFragment.Companion.KEY_PLANET
import com.luanabarbosa.starswars.planets.ui.PlanetFragment.Companion.ONE
import com.luanabarbosa.starswars.planets.ui.PlanetFragment.Companion.POSITION_PLANET
import com.luanabarbosa.starswars.utils.extensions.serializable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlanetViewModel(
    private val planetUseCase: PlanetUseCase

) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private val _position = MutableLiveData<Int>()
    val position = _position as LiveData<Int>

    private val _details = MutableLiveData<PlanetListModel>()
    val details = _details as LiveData<PlanetListModel>

    private val _planets = MutableLiveData<List<PlanetListModel>>()
    val planets = _planets as LiveData<List<PlanetListModel>>

    private val _isError = MutableLiveData<Throwable>()
    val isError = _isError as LiveData<Throwable>

    private var currentPage = ONE
    private var job: Job? = null
    var hasNext: Boolean? = false


    fun getPlanets() {
        job = viewModelScope.launch {
            try {
                _isLoading.value = true
                planetUseCase(currentPage).onSuccess {
                    _isLoading.value = false
                    _planets.postValue(planets.value.orEmpty() + it.result)
                    hasNext = it.next.isNotEmpty()
                }
                    .onFailure {
                        _isError.postValue(it)
                    }
            } catch (e: Throwable) {
                _isError.postValue(e)
            }
        }
    }

    fun loadNextPage() {
        if (job?.isActive == true) return
        if (hasNext == true) currentPage++
        getPlanets()
    }

    fun observerCards(
        lifecycleOwner: LifecycleOwner,
        action: (List<PlanetListModel>) -> Unit,
    ) = _planets.observe(lifecycleOwner) {
        action(it)
    }

    fun handleArguments(bundle: Bundle?) {
        bundle?.run {
            serializable<PlanetListModel>(KEY_PLANET)?.apply {
                _details.value = this
            }
            getInt(POSITION_PLANET).apply { _position.value = this }
        }
    }

}
