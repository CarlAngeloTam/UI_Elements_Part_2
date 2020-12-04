package com.example.uielements_pt_2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService



lateinit var notificationManager: NotificationManager
lateinit var notificationChannel: NotificationChannel
lateinit var builder: Notification.Builder
private val channel_id="com.example.uielements2"
private val description="Notification"
class SongsQueueActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val Intent = Intent(this, SongsQueueActivity::class.java)
        val pendingIntent =
                PendingIntent.getActivity(this, 0, Intent, PendingIntent.FLAG_UPDATE_CURRENT)

        var list: List<String>? = musicSongs

        if (list.orEmpty().isEmpty() && (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)) {
            notificationChannel =
                    NotificationChannel(channel_id, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channel_id)
                    .setContentTitle("2nd Part of UI elements")
                    .setContentText("The Songs is Empty")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)

        } else {
            builder = Notification.Builder(this)
                    .setContentTitle("test")
                    .setContentText("Notification")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                    .setContentIntent(pendingIntent)
        }
        notificationManager.notify(1234, builder.build())




        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musicSongs)
        val musicSongs = findViewById<ListView>(R.id.queue_songs)
        musicSongs.adapter = adapter
        registerForContextMenu(musicSongs)


    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.remove_item, menu)
    }



    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {

            R.id.remove_song -> {
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                musicSongs.removeAt(info.position)
                true
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
                Toast.makeText(baseContext, "Song Removed" , Toast.LENGTH_SHORT ).show()
                true

                return true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


