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
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class QueuedSongsActivity : AppCompatActivity() {

    lateinit var myMusic: ListView

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel:NotificationChannel
    lateinit var builder: Notification.Builder
    private val channel_Id ="com.example.uielements_pt_2"
    private val description = "Notification"
    lateinit var queuedSongs: MutableList<String>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

            // notification when the queue is empty
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val Intent1 = Intent(this, QueuedSongsActivity::class.java)
            val pendingIntent2 = PendingIntent.getActivity(this, 0, Intent1, PendingIntent.FLAG_UPDATE_CURRENT)
            var list3: List<String>? = queuedSongs

            if (list3.orEmpty().isEmpty() && (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)) {

                notificationChannel = NotificationChannel((channel_Id), description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.BLUE
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channel_Id)
                        .setContentTitle("Part 2 Notification of UI Elements")
                        .setContentText("Queue is Empty")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                        .setContentIntent(pendingIntent2)

            } else {
                builder = Notification.Builder(this)
                        .setContentTitle("test")
                        .setContentText("Notification")
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent2)
            }

            notificationManager.notify(1234,builder.build())


            val adapter3 = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, queuedSongs)
            myMusic = findViewById<ListView>(R.id.queue_songs)
            myMusic.adapter = adapter3
            registerForContextMenu(myMusic)

    }

            override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
            {
            super.onCreateContextMenu(menu, v, menuInfo)
            val inflater4 = menuInflater
            inflater4.inflate(R.menu.remove_item, menu)
            }

            override fun onContextItemSelected(item: MenuItem): Boolean
            {
                return when (item.itemId) {
                    R.id.remove_song -> {
                        val info1 = item.menuInfo as AdapterView.AdapterContextMenuInfo
                        queuedSongs.removeAt(info1.position)
                        true
                        finish()
                        overridePendingTransition(0, 0)
                        startActivity(getIntent())
                        overridePendingTransition(0, 0)
                        val toast = Toast.makeText(applicationContext, "Song removed", Toast.LENGTH_LONG)
                        toast.show()
                        true
                    }
                else -> super.onContextItemSelected(item)
                }
            }

            override fun onCreateOptionsMenu(menu: Menu?): Boolean
            {
                val inflater4 = menuInflater
                inflater4.inflate(R.menu.main_menu, menu)
                return true
            }


}

