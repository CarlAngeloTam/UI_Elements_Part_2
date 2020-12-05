package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.Song

class AddSongs : AppCompatActivity() {
    lateinit var buttonConfirm: Button
    lateinit var etTitle: EditText
    lateinit var etArtist: EditText
    lateinit var etAlbum:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_songs)

        val handlerDatabase = DatabaseHandler(this)

        etTitle = findViewById(R.id.songTitle)
        etArtist = findViewById(R.id.songArtist)
        etAlbum = findViewById(R.id.songAlbum)
        buttonConfirm = findViewById(R.id.btn_confirm_add_song_main)
        buttonConfirm.setOnClickListener {
            val pamagat = etTitle.text.toString()
            val artistic2 = etArtist.text.toString()
            val albums2 = etAlbum.text.toString()

            if(title.isNotEmpty() && artistic2.isNotEmpty() && albums2.isNotEmpty()) {
                val music = Song(title = pamagat, artist = artistic2, album = albums2)
                handlerDatabase.create(music)
                Toast.makeText(this, "Song Added Success", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this, "Need to fill up the credentials", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun clearFields(){
        etTitle.text.clear()
        etArtist.text.clear()
        etAlbum.text.clear()
    }
}