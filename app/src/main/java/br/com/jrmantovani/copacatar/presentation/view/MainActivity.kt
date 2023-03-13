package br.com.jrmantovani.copacatar.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.livedata.observeAsState

import br.com.jrmantovani.copacatar.domain.model.Match

import br.com.jrmantovani.copacatar.presentation.viewmodel.MainViewModel
import br.com.jrmantovani.copacatar.ui.theme.CopaCatarTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservable()
        setContent {
            CopaCatarTheme {
                 val listaPartidas =  mainViewModel.matchsLiveData.observeAsState(emptyList())
                MainScreen(matches = listaPartidas.value)
            }
        }


    }

    fun initObservable() {
//        mainViewModel.matchsLiveData.observe(this) { listMatch ->
//
//
//
//
//            Log.i("info_copa", listMatch.toString())
//
//
//
//        }


    }


    override fun onStart() {
        super.onStart()
        mainViewModel.getMatchs()
    }



}


