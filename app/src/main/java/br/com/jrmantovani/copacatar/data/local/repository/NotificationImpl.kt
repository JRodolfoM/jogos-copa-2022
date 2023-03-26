package br.com.jrmantovani.copacatar.data.local.repository

import br.com.jrmantovani.copacatar.data.local.dao.NotificationDAO
import br.com.jrmantovani.copacatar.data.local.entity.NotificationEntity
import br.com.jrmantovani.copacatar.domain.result.ResultOperation
import javax.inject.Inject

class NotificationImpl @Inject constructor(
    private val notificationDAO: NotificationDAO
) {

    suspend fun salve(notification: NotificationEntity): ResultOperation {
        val idNotification = notificationDAO.salve(notification)

        return if (idNotification > 0) {
            ResultOperation(true, "Sucesso ao salvar Notificação")
        } else {
            ResultOperation(true, "Erro ao salvar Notificação")
        }

    }


    suspend fun update(notification: NotificationEntity): ResultOperation {

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

    suspend fun listNotification(): List<NotificationEntity>{
        return notificationDAO.listNotification()
    }
}