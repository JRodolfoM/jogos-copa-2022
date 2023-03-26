package br.com.jrmantovani.copacatar.presentation.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.livedata.observeAsState

import br.com.jrmantovani.copacatar.domain.model.Match

import br.com.jrmantovani.copacatar.presentation.viewmodel.MainViewModel
import br.com.jrmantovani.copacatar.ui.theme.CopaCatarTheme
import br.com.jrmantovani.copacatar.work.NotificationMatcherWorker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservable()
        setContent {
            CopaCatarTheme {
                 val listaPartidas =  mainViewModel.matchsLiveData.observeAsState(emptyList())
                MainScreen(matches = listaPartidas.value) {match->
                    mainViewModel.toggleNotification(match)
                }
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initObservable() {
        mainViewModel.action.observe(this) { match ->

          if (match.notificationEnabled){
              NotificationMatcherWorker.cancel(applicationContext, match)
              Log.i("info_", "desabilitou  $match")

          }else{
              NotificationMatcherWorker.start(applicationContext, match)
              Log.i("info_", "abilitou ")
          }

            mainViewModel.getMatchs()





        }


    }


    override fun onStart() {
        super.onStart()
       /// mainViewModel.getMatchs()
    }



}


