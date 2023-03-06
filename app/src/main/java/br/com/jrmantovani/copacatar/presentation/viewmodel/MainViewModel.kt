package br.com.jrmantovani.copacatar.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.usecase.GetMachUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchsUsecase: GetMachUseCase
): ViewModel(){

    private val _mathsLiveData = MutableLiveData<List<Match>>()

    val mathsLiveData : LiveData<List<Match>>
    get() = _mathsLiveData

    init {
        getMatchs()
    }

    fun getMatchs(){
        viewModelScope.launch {
            val listMatchs = matchsUsecase.getMaths()
            _mathsLiveData.postValue(listMatchs)
        }
    }

}