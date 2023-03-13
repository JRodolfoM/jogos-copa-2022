package br.com.jrmantovani.copacatar.data.dto

import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.model.Stadium
import br.com.jrmantovani.copacatar.extensions.toTeam

data class MatchDTO(
    val date: String,
    val name: String,
    val stadium: StadiumDTO,
    val team1: String,
    val team2: String
)

fun MatchDTO.toMach():Match{

    return Match(
        date,
        name,
        stadium= Stadium( this.stadium.image, this.stadium.name),
        team1.toTeam(),
        team2.toTeam()
    )

}