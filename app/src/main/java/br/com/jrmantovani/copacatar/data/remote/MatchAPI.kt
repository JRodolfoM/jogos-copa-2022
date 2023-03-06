package br.com.jrmantovani.copacatar.data.remote

import br.com.jrmantovani.copacatar.data.dto.MatchDTO
import retrofit2.Response
import retrofit2.http.GET

interface MatchAPI {

    @GET("api.json")
    suspend fun getMatch():Response<List<MatchDTO>>

}