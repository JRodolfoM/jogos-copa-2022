package br.com.jrmantovani.copacatar.domain.usecase

import br.com.jrmantovani.copacatar.domain.repository.MatchRepository
import javax.inject.Inject

class EnableNotificationUseCase @Inject constructor(
    private val repository: MatchRepository
) {
    suspend operator fun invoke(id: String) {
        repository.enableNotificationFor(id)
    }
}
