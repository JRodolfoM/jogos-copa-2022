package br.com.jrmantovani.copacatar.domain.repository

import br.com.jrmantovani.copacatar.domain.model.Match

interface MatchRepository {
    suspend fun getMachs(): List<Match>
}