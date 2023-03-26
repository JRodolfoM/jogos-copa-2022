package br.com.jrmantovani.copacatar.extensions


import br.com.jrmantovani.copacatar.data.local.entity.NotificationEntity
import br.com.jrmantovani.copacatar.data.remote.dto.MatchDTO
import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.model.Notification
import br.com.jrmantovani.copacatar.domain.model.Stadium
import br.com.jrmantovani.copacatar.domain.model.Team
import java.text.SimpleDateFormat
import java.util.*

fun String.getDate(): String {
    val formatoEntrada = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
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

fun MatchDTO.toMach(): Match {

    return Match(
        id = "$team1-$team2",
        date,
        name,
        stadium = Stadium(this.stadium.image, this.stadium.name),
        team1.toTeam(),
        team2.toTeam()

    )

}

fun NotificationEntity.toNotification() : Notification {

    return Notification(
        idNotification = idNotification,
        identificador= identificador,
        status = status
    )

}


private fun getTeamFlag(team: String): String {
    return team.map {
        String(Character.toChars(it.code + 127397))
    }.joinToString("")


}