package br.com.jrmantovani.copacatar.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Notification
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.model.Team
import br.com.jrmantovani.copacatar.extensions.getDate
import br.com.jrmantovani.copacatar.ui.theme.Shapes
import coil.compose.AsyncImage

@Composable
fun MainScreen(matches: List<Match>) {
//    val lista = remember {mutableStateOf(emptyList<Match>())
//
//    }
//    lista.value = matches

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(matches) { match ->
                MatchInfo(match)
            }
        }
    }
}

@Composable
fun MatchInfo(match: Match) {
    Card(
        shape = Shapes.large,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            AsyncImage(
                model = match.stadium.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
//                Notification(match, onNotificationClick)
                Title(match)
                Teams(match)
            }
        }
    }
}


//
//@Composable
//fun Notification(match: Match, ) {
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
//        val drawable = R.drawable.ic_notifications_active
//        else R.drawable.ic_notifications
//
//        Image(
//            painter = painterResource(id = drawable),
//            modifier = Modifier.clickable {
//                onClick(match)
//            },
//            contentDescription = null
//        )
//    }
//}

@Composable
fun Title(match: Match) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${match.date.getDate()} - ${match.name}",
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )
    }
}

@Composable
fun Teams(match: Match) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TeamItem(team = match.team1)

        Text(
            text = "X",
            modifier = Modifier.padding(end = 16.dp, start = 16.dp),
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )

        TeamItem(team = match.team2)
    }
}

@Composable
fun TeamItem(team: Team) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = team.flag,
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.h3.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            text = team.displayName,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )
    }
}
