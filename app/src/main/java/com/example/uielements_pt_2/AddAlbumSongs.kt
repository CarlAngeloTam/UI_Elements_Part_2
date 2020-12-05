package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.AlbumSong

class AddAlbumSongs : AppCompatActivity() {
    lateinit var confirmButton: Button
    lateinit var etTitle: EditText
    lateinit var etArtist: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album_songs)

        val handlerDatabase = DatabaseHandler(this)
        etTitle = findViewById(R.id.songTitle_album)
        etArtist = findViewById(R.id.songArtist_album)
        confirmButton = findViewById(R.id.btn_confirm_add_song_main_album)

        confirmButton.setOnClickListener {
            val pamagat = etTitle.text.toString()
            val artist = etArtist.text.toString()

            if(title.isNotEmpty() && artist.isNotEmpty() ) {
                val albumMusic = AlbumSong(title = pamagat, artist = artist)
                handlerDatabase.albumSongCreate(albumMusic)
                Toast.makeText(this,"Song added to album successfully", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Wrong credentials. Please try again", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, AlbumsActivity::class.java))
    }
    fun clearFields(){
        etTitle.text.clear()
        etArtist.text.clear()
    }
}