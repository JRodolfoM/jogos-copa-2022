package br.com.jrmantovani.copacatar.domain.usecase

import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.repository.MatchRepository
import javax.inject.Inject

class GetMachUseCase  @Inject constructor(
    private val repository: MatchRepository
){
    suspend fun getMaths():List<Match>{
        return try {
            repository.getMachs()

        }catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }
}