package br.com.jrmantovani.copacatar.data.remote.repository

import android.util.Log
import br.com.jrmantovani.copacatar.data.local.dao.NotificationDAO
import br.com.jrmantovani.copacatar.data.local.entity.NotificationEntity
import br.com.jrmantovani.copacatar.data.remote.api.MatchAPI
import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.domain.model.Notification
import br.com.jrmantovani.copacatar.domain.repository.MatchRepository
import br.com.jrmantovani.copacatar.domain.result.ResultOperation
import br.com.jrmantovani.copacatar.extensions.toMach
import br.com.jrmantovani.copacatar.extensions.toNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(

    private val api: MatchAPI,
    private val notificationDAO: NotificationDAO
) : MatchRepository {


    override suspend fun getMachs(): List<Match> {
        val listResponse = api.getMatch()

        if (listResponse.isSuccessful) {
            val listMatchsDto = listResponse.body()
            if (listMatchsDto != null) {
                return listMatchsDto.map { it.toMach() }
            }
        }
        return emptyList()
    }

    override suspend fun enableNotificationFor(id: String) {
        CoroutineScope(Dispatchers.IO).launch {

            Log.i("info_copa", id + " Ativou")
            val isNotification : NotificationEntity? = notificationDAO.isNotification(id)



            if (isNotification?.identificador !== null) {

                update(
                    NotificationEntity(
                        isNotification.idNotification,
                        isNotification.identificador,
                        "S"
                    )
                )

            } else {

                salve(NotificationEntity(0, id, "S"))

            }


        }


    }

    override suspend fun disableNotificationFor(id: String) {
        CoroutineScope(Dispatchers.IO).launch {

            Log.i("info_copa", id + " Desativou")
            val isNotification : NotificationEntity? = notificationDAO.isNotification(id)



            if (isNotification?.identificador !== null) {

                update(
                    NotificationEntity(
                        isNotification.idNotification,
                        isNotification.identificador,
                        "N"
                    )
                )

            } else {

                salve(NotificationEntity(0, id, "N"))

            }


        }
    }


    fun salve(notification: NotificationEntity): ResultOperation {
        val idNotification = notificationDAO.salve(notification)

        return if (idNotification > 0) {
            ResultOperation(true, "Sucesso ao salvar Notificação")
        } else {
            ResultOperation(true, "Erro ao salvar Notificação")
        }

    }


     fun update(notification: NotificationEntity): ResultOperation {

        val idNotification = notificationDAO.update(notification)
        if (idNotification > 0) {
            return ResultOperation(
                true,
                "Sucesso ao atualizar Anotação"
            )
        }

        return ResultOperation(
            false,
            "Erro ao atualizar Anotação"
        )

    }

    override suspend fun listNotification(): List<Notification> {
        return notificationDAO.listNotification().map { it.toNotification()}
    }

}