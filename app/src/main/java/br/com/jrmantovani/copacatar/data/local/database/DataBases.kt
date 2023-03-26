package br.com.jrmantovani.copacatar.data.local.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.jrmantovani.copacatar.data.local.dao.NotificationDAO
import br.com.jrmantovani.copacatar.data.local.entity.NotificationEntity


@Database(
    entities = [NotificationEntity::class],
    version = 1
)
abstract class DataBases: RoomDatabase() {

    abstract val notificationDAO: NotificationDAO
    companion object {
        fun getInstance(context: Context) : DataBases{
            return Room.databaseBuilder(
                context ,
                DataBases::class.java,
               "BD.db"
            ).build()
        }
    }

}