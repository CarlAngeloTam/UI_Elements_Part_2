package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.Song

class EditSongs : AppCompatActivity() {
    lateinit var editConfirmButton: Button
    lateinit var etTitle: EditText
    lateinit var etArtist: EditText
    lateinit var etAlbum: EditText
    lateinit var music: Song
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_songs)

        val song_id = intent.getIntExtra("song_id", 0)

        val handlerDatabase = DatabaseHandler(this)
        music= handlerDatabase.readOne(song_id)

        etTitle = findViewById(R.id.et_songTitle)
        etArtist = findViewById(R.id.et_songArtist)
        etAlbum = findViewById(R.id.et_songAlbum)
        editConfirmButton = findViewById(R.id.et_btn_confirm_add_song_main)

        etTitle.setText(music.title)
        etArtist.setText(music.artist)
        etAlbum.setText(music.album)

        editConfirmButton.setOnClickListener {
            val pamagat = etTitle.text.toString()
            val artitst1 = etArtist.text.toString()
            val album2 = etAlbum.text.toString()

            if(title.isNotEmpty() && artitst1.isNotEmpty() && album2.isNotEmpty()) {
                val musicUpdate = Song(id = music.id, title = pamagat, artist = artitst1, album = album2)
                handlerDatabase.update(musicUpdate)
                Toast.makeText(this,"Song edited Success", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Invalid credentials. Try again", Toast.LENGTH_SHORT).show()

        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}