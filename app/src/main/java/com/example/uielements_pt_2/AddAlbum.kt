package com.example.uielements_pt_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uielements_pt_2.models.Album

class AddAlbum : AppCompatActivity() {
    lateinit var albumTitle: EditText
    lateinit var albumRelease: EditText
    lateinit var buttonConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_album)
        val handlerDatabase = DatabaseHandler(this)

        albumTitle = findViewById(R.id.albumTitle)
        albumRelease = findViewById(R.id.releaseDate)
        buttonConfirm = findViewById(R.id.btn_confirm_add)
        buttonConfirm.setOnClickListener {

            val pamagatAlbum = albumTitle.text.toString()
            val dateGiven = albumRelease.text.toString()

            if(pamagatAlbum.isNotEmpty() && dateGiven.isNotEmpty()) {
                val albumPamagat = Album(title = pamagatAlbum, releaseDate = dateGiven)
                handlerDatabase.albumCreate(albumPamagat)
                Toast.makeText(this,"Album added Success", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Wrong Credentials, Try Again", Toast.LENGTH_SHORT).show()
            clearFields()
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, AlbumsActivity::class.java))
    }

    fun clearFields(){
        albumTitle.text.clear()
        albumRelease.text.clear()
    }
}
