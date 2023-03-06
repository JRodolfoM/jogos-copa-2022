package br.com.jrmantovani.copacatar.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.jrmantovani.copacatar.presentation.viewmodel.MainViewModel
import br.com.jrmantovani.copacatar.ui.theme.CopaCatarTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservable()
        setContent {
            CopaCatarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    fun initObservable(){
        mainViewModel.mathsLiveData.observe(this){listMatch->
            var textResultado =""
           listMatch.forEach { match ->
               textResultado += "${match.name} - ${match.team1} - ${match.team2}\n\n"
           }
            Log.i("info_copa", textResultado)


        }
    }


}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CopaCatarTheme {
        Greeting("Android")
    }
}