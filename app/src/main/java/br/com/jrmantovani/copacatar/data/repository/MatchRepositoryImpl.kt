package br.com.jrmantovani.copacatar.data.repository

import br.com.jrmantovani.copacatar.data.dto.toMach
import br.com.jrmantovani.copacatar.data.remote.MatchAPI
import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.repository.MatchRepository
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
private val api: MatchAPI
): MatchRepository{



    override suspend fun getMachs(): List<Match> {
        val listResponse = api.getMatch()
        if (listResponse.isSuccessful){
            val listMatchsDto  = listResponse.body()
            if (listMatchsDto != null){
                return listMatchsDto.map { it.toMach() }
            }
        }
        return emptyList()
    }

}