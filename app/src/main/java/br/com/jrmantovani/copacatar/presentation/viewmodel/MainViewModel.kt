package br.com.jrmantovani.copacatar.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.model.Notification
import br.com.jrmantovani.copacatar.domain.model.Stadium
import br.com.jrmantovani.copacatar.domain.model.Team
import br.com.jrmantovani.copacatar.domain.usecase.DisableNotificationUseCase
import br.com.jrmantovani.copacatar.domain.usecase.EnableNotificationUseCase
import br.com.jrmantovani.copacatar.domain.usecase.GetMachUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val matchsUsecase: GetMachUseCase,
    private val disableNotificationUseCase: DisableNotificationUseCase,
    private val enableNotificationUseCase: EnableNotificationUseCase,
) : ViewModel() {

    private val _mathsLiveData = MutableLiveData<List<Match>>()

    val matchsLiveData: LiveData<List<Match>>
        get() = _mathsLiveData

    private val _action = MutableLiveData<Match>()

    val action: LiveData<Match>
        get() = _action

    init {
        getMatchs()
    }

    fun getMatchs() {
        viewModelScope.launch(Dispatchers.IO) {
            val listMatchs = matchsUsecase.getMaths()

            val listNotificationStatus = matchsUsecase.getMathsStatus()

            val listMatchsFinal = listMatchs.map { match ->


                Match(
                    match.id,
                    match.date,
                    match.name,
                    match.stadium,
                    match.team1,
                    match.team2,
                    notificationEnabled = compare(match.id, listNotificationStatus)

                )

            }


            _mathsLiveData.postValue(listMatchsFinal)


        }
    }


    fun toggleNotification(match: Match) {
        viewModelScope.launch {

            withContext(Dispatchers.Main) {
             val actionNotification =   if (match.notificationEnabled) {

                    disableNotificationUseCase(match.id)
                    match
                } else {
                    enableNotificationUseCase(match.id)
                     match

                }


                _action.postValue(actionNotification)


            }

        }
    }

    private fun compare(id: String, listNotification: List<Notification>): Boolean {

        return if (listNotification.isNotEmpty()) {
            val notification =
                listNotification.filter { notification -> notification.identificador == id }
            if (notification.isEmpty())
                false
            else notification[0].status == "S"
        } else {
            false
        }
    }


}