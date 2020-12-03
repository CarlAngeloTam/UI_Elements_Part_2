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

    lateinit var myList: ListView

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel:NotificationChannel
    lateinit var builder: Notification.Builder
    private val channel_Id ="com.example.uielements_pt_2"
    private val description = "Notification"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)

            // notification when the queue is empty
            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val Intent = Intent(this, QueuedSongsActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, Intent, PendingIntent.FLAG_UPDATE_CURRENT)
            var list: List<String>? = queuedMusics

            if (list.orEmpty().isEmpty() && (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)) {

                notificationChannel = NotificationChannel((channel_Id), description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channel_Id)
                        .setContentTitle("Notification from UI Elements Part 2!")
                        .setContentText("Songs Queue is Empty")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
                        .setContentIntent(pendingIntent)

            } else {
                builder = Notification.Builder(this)
                        .setContentTitle("test")
                        .setContentText("Notification")
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
            }

            notificationManager.notify(1234,builder.build())

            // listview
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, queuedMusics)
            myList = findViewById<ListView>(R.id.queue_songs)
            myList.adapter = adapter
            registerForContextMenu(myList)

    }

            override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?)
            {
            super.onCreateContextMenu(menu, v, menuInfo)
            val inflater = menuInflater
            inflater.inflate(R.menu.remove_item, menu)
            }

            override fun onContextItemSelected(item: MenuItem): Boolean
            {
                return when (item.itemId) {
                    R.id.remove_song -> {
                        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                        queuedMusics.removeAt(info.position)
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
                val inflater = menuInflater
                inflater.inflate(R.menu.main_menu, menu)
                return true
            }


}

