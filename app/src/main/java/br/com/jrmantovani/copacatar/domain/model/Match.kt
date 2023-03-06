package br.com.jrmantovani.copacatar.domain.model

data class Match(
    val date: String,
    val name: String,
    val stadium: Stadium,
    val team1: String,
    val team2: String
)