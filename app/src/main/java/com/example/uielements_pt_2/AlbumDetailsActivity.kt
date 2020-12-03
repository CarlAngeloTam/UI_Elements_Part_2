package com.example.uielements_pt_2

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.util.Log
import android.widget.*

class AlbumDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        var albumItems: AlbumItem = intent.getSerializableExtra("data") as AlbumItem
        var viewImage = findViewById<ImageView>(R.id.icon_details)
        var viewText = findViewById<TextView>(R.id.icon_name)

        if(albumItems.icons == R.drawable.tree_songs) {
            viewImage.setImageResource(albumItems.icons!!)

            val songsQueueArray = mutableListOf("Beautiful in White", "The Prayer", "Im yours", "Beauty and the Beast")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter

            albumSongs.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialogAlert = AlertDialog.Builder(this)
                dialogAlert.setMessage("Do you want to remove this song from list?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener() { dialog, which ->
                            val removeSong = songsQueueArray[position]
                            songsQueueArray.remove(removeSong)
                            adapter.notifyDataSetChanged()

                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, which ->
                            dialog.cancel()
                        })
                val alert = dialogAlert.create()
                alert.setTitle("Alert! Deleting Song")
                alert.show()
            }// item listener
        }

        else if(albumItems.icons == R.drawable.grace){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Someone Like You"

            val songsQueueArray = mutableListOf("Someone Like You", "Always", "Country Road", "Original Sin")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter

            albumSongs.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialogAlert = AlertDialog.Builder(this)
                dialogAlert.setMessage("Do you want to remove this song from list?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener() { dialog, which ->
                            val removeSong = songsQueueArray[position]
                            songsQueueArray.remove(removeSong)
                            adapter.notifyDataSetChanged()

                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, which ->
                            dialog.cancel()
                        })
                val alert = dialogAlert.create()
                alert.setTitle("Alert! Deleting Song")
                alert.show()
            }// item listener
        }
        else if(albumItems.icons == R.drawable.country_songs){
            viewImage.setImageResource(albumItems.icons!!)
            viewText.text = "Remember When"

            val songsQueueArray = mutableListOf("Remember When", "Heaven", "Lucky", "My Love")
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsQueueArray)
            val albumSongs = findViewById<ListView>(R.id.album_songs)
            albumSongs.adapter = adapter

            albumSongs.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val dialogAlert = AlertDialog.Builder(this)
                dialogAlert.setMessage("Do you want to remove this song from list?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener() { dialog, which ->
                            val removeSong = songsQueueArray[position]
                            songsQueueArray.remove(removeSong)
                            adapter.notifyDataSetChanged()

                        })
                        .setNegativeButton("No", DialogInterface.OnClickListener {
                            dialog, which ->
                            dialog.cancel()
                        })
                val alert = dialogAlert.create()
                alert.setTitle("Alert! Deleting Song")
                alert.show()
            }// item listener
        }
    }
}