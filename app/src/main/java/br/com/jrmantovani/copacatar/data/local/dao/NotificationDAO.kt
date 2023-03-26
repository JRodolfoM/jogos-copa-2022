package br.com.jrmantovani.copacatar.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.jrmantovani.copacatar.data.local.entity.NotificationEntity

@Dao
interface NotificationDAO {

    @Insert
    fun salve(notification: NotificationEntity):Long

    @Update
    fun update(notification: NotificationEntity):Int

    @Query("SELECT * FROM notification WHERE status = 'S'")
    fun listNotification(): List<NotificationEntity>

    @Query("SELECT * FROM notification WHERE identificador = :id")
    fun isNotification(id:String): NotificationEntity
}