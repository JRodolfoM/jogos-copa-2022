package br.com.jrmantovani.copacatar.data.remote.api

import br.com.jrmantovani.copacatar.data.remote.dto.MatchDTO
import retrofit2.Response
import retrofit2.http.GET

interface MatchAPI {

    @GET("api.json")
    suspend fun getMatch():Response<List<MatchDTO>>

}