package br.com.jrmantovani.copacatar.extensions

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import br.com.jrmantovani.copacatar.domain.model.Team
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.getDate(): String {
    val formatoEntrada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    formatoEntrada.timeZone = TimeZone.getTimeZone("UTC")
    val data = formatoEntrada.parse(this)
    val formatoSaida = SimpleDateFormat("dd/MM HH:mm")
    return formatoSaida.format(data)
}

 fun String.toTeam(): Team {
    return Team(
        flag = getTeamFlag(this),
        displayName = Locale("", this).isO3Country
    )
}


private fun getTeamFlag(team: String): String {
    return team.map {
        String(Character.toChars(it.code + 127397))
    }.joinToString("")
}