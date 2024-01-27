package com.luanabarbosa.starswars.people.ui

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanabarbosa.starswars.people.data.model.PeopleListModel
import com.luanabarbosa.starswars.people.domain.usecase.PeopleUseCase
import com.luanabarbosa.starswars.people.ui.PeopleFragment.Companion.KEY_PEOPLE
import com.luanabarbosa.starswars.people.ui.PeopleFragment.Companion.ONE
import com.luanabarbosa.starswars.people.ui.PeopleFragment.Companion.POSITION_PEOPLE
import com.luanabarbosa.starswars.utils.extensions.serializable
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PeopleViewModel(
    private val peopleUseCase: PeopleUseCase

) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading as LiveData<Boolean>

    private val _position = MutableLiveData<Int>()
    val position = _position as LiveData<Int>

    private val _details = MutableLiveData<PeopleListModel>()
    val details = _details as LiveData<PeopleListModel>

    private val _people = MutableLiveData<List<PeopleListModel>>()
    val people = _people as LiveData<List<PeopleListModel>>

    private val _isError = MutableLiveData<Throwable>()
    val isError = _isError as LiveData<Throwable>

    private var currentPage = ONE
    private var job: Job? = null
    var hasNext: Boolean? = false

    fun getPeople() {
        job = viewModelScope.launch {
            try {
                _isLoading.value = true
                peopleUseCase(currentPage).onSuccess {
                    _isLoading.value = false
                    _people.postValue(_people.value.orEmpty() + it.result)
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
        getPeople()
    }

    fun observerPeople(
        lifecycleOwner: LifecycleOwner,
        action: (List<PeopleListModel>) -> Unit,
    ) = _people.observe(lifecycleOwner) {
        action(it)
    }

    fun handleArguments(bundle: Bundle?) {
        bundle?.run {
            serializable<PeopleListModel>(KEY_PEOPLE)?.apply {
                _details.value = this
            }
            getInt(POSITION_PEOPLE).apply { _position.value = this }
        }
    }

}
