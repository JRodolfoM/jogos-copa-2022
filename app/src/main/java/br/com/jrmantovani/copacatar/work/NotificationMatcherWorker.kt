package br.com.jrmantovani.copacatar.work

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.*
import br.com.jrmantovani.copacatar.domain.model.Match
import br.com.jrmantovani.copacatar.work.extensions.showNotification
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val NOTIFICATION_TITLE_KEY = "NOTIFICATION_TITLE_KEY"
private const val NOTIFICATION_CONTENT_KEY = "NOTIFICATION_CONTENT_KEY"

class NotificationMatcherWorker(
    private val context: Context,
    workerParams: WorkerParameters,
) : Worker(context, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        val title = inputData.getString(NOTIFICATION_TITLE_KEY)
            ?: throw IllegalArgumentException("title is required")
        val content = inputData.getString(NOTIFICATION_CONTENT_KEY)
            ?: throw IllegalArgumentException("content is required")

        context.showNotification(title, content)

        return Result.success()
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun start(context: Context, match: Match) {


            try {
                val (id, matchDate, _, _, team1, team2) = match

                val strDateTimeWithoutZ: String = matchDate.substring(0, matchDate.length - 1)

                val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

                val dateTime = LocalDateTime.parse(strDateTimeWithoutZ, formatter)

                val initialDelay = Duration.between(LocalDateTime.now(),dateTime).minusMinutes(2)



                val inputData = workDataOf(
                    NOTIFICATION_TITLE_KEY to "Se prepare que o jogo vai começar",
                    NOTIFICATION_CONTENT_KEY to "Hoje tem ${team1.flag} vs ${team2.flag}",
                )

                WorkManager.getInstance(context)
                    .enqueueUniqueWork(
                        id,
                        ExistingWorkPolicy.KEEP,
                        createRequest(initialDelay, inputData)
                    )


            }catch (e:Exception){
                Log.e("info_", "start: $e")
                return
            }





        }

        fun cancel(context: Context, match: Match) {
            WorkManager.getInstance(context)
                .cancelUniqueWork(match.id)
        }

        private fun createRequest(initialDelay: Duration, inputData: Data): OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<NotificationMatcherWorker>()
                .setInitialDelay(initialDelay)
                .setInputData(inputData)
                .build()
    }
}