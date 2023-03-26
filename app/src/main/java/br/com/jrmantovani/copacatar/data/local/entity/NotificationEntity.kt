package br.com.jrmantovani.copacatar.data.local.entity

import android.app.Notification
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val idNotification: Long,
    val identificador :String,
    val status: String
)


