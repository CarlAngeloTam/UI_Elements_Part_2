package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.Album

class EditAlbum : AppCompatActivity() {
    lateinit var albumTitle: EditText
    lateinit var releaseAlbum: EditText
    lateinit var buttonConfirm: Button
    lateinit var album: Album

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_album)
        val album_id = intent.getIntExtra("album_id", 0)
        val handlerDatabase = DatabaseHandler(this)

        album = handlerDatabase.albumReadOne(album_id)
        albumTitle = findViewById(R.id.edit_Title)
        releaseAlbum = findViewById(R.id.edit_releaseDate)
        buttonConfirm = findViewById(R.id.edit_btn_confirm_add_album_main)
        albumTitle.setText(album.title)
        releaseAlbum.setText(album.releaseDate)

        buttonConfirm.setOnClickListener {
            val pamagat = albumTitle.text.toString()
            val dateGiven = releaseAlbum.text.toString()

            if(pamagat.isNotEmpty() && dateGiven.isNotEmpty()) {
                val updateAlbum1 = Album(id = album.id, title = pamagat, releaseDate = dateGiven)
                handlerDatabase.albumUpdate(updateAlbum1)
                Toast.makeText(this,"Edited Success", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Invalid credentials. Try again", Toast.LENGTH_SHORT).show()
        }

    }
    override fun onBackPressed() {
        startActivity(Intent(this, AlbumsActivity::class.java))
    }
}