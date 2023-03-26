package br.com.jrmantovani.copacatar.domain.repository

import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.model.Notification

interface MatchRepository {
    suspend fun getMachs(): List<Match>
    suspend fun enableNotificationFor(id: String)
    suspend fun disableNotificationFor(id: String)

    suspend fun listNotification():List<Notification>
}