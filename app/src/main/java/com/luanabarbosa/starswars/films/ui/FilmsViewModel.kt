package com.luanabarbosa.starswars.films.ui

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanabarbosa.starswars.films.data.model.FilmsListModel
import com.luanabarbosa.starswars.films.domain.usecase.FilmsUseCase
import com.luanabarbosa.starswars.films.ui.FilmsFragment.Companion.KEY_FILMS
import com.luanabarbosa.starswars.films.ui.FilmsFragment.Companion.ONE
import com.luanabarbosa.starswars.films.ui.FilmsFragment.Companion.POSITION_FILMS
import com.luanabarbosa.starswars.utils.extensions.serializable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val filmsUseCase: FilmsUseCase

) : ViewModel() {
    private val _position = MutableLiveData<Int>()
    val position = _position as LiveData<Int>

    private val _details = MutableLiveData<FilmsListModel>()
    val details = _details as LiveData<FilmsListModel>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private val _films = MutableLiveData<List<FilmsListModel>>()
    val films = _films as LiveData<List<FilmsListModel>>

    private val _isError = MutableLiveData<Throwable>()
    val isError = _isError as LiveData<Throwable>

    private var currentPage = ONE
    private var job: Job? = null
    var hasNext: Boolean? = false

    fun getFilms() {
         job = viewModelScope.launch {
            try {
                _isLoading.value = true
                filmsUseCase(currentPage).onSuccess {
                    _isLoading.value = false
                    _films.postValue(films.value.orEmpty() + it.result)
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
        getFilms()
    }

    fun observerCards(
        lifecycleOwner: LifecycleOwner,
        action: (List<FilmsListModel>) -> Unit,
    ) = _films.observe(lifecycleOwner) {
        action(it)
    }

    fun handleArguments(bundle: Bundle?) {
        bundle?.run {
            serializable<FilmsListModel>(KEY_FILMS)?.apply {
                _details.value = this
            }
            getInt(POSITION_FILMS).apply { _position.value = this }
        }
    }
}
