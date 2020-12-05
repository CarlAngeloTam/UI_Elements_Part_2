package com.example.uielements_pt_2

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.uielements_pt_2.models.Album
import com.example.uielements_pt_2.models.AlbumSong

class AlbumDetailsActivity : AppCompatActivity() {
    lateinit var album: Album
    lateinit var albumTitle: TextView
    lateinit var musicAlbumTableHandler: DatabaseHandler
    lateinit var albumMusicItem: MutableList<AlbumSong>
    lateinit var adapter: ArrayAdapter<AlbumSong>
    lateinit var albumMusicListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        albumMusicListView = findViewById<ListView>(R.id.album_songs)
        val album_id = intent.getIntExtra("album_id", 0)
        musicAlbumTableHandler = DatabaseHandler(this)
        album = musicAlbumTableHandler.albumReadOne(album_id)
        albumTitle = findViewById(R.id.icon_name)
        albumTitle.text = album.title
        albumMusicItem = musicAlbumTableHandler.albumSongRead()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumMusicItem)
        albumMusicListView.adapter = adapter
        registerForContextMenu(albumMusicListView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater1 = menuInflater
        inflater1.inflate(R.menu.album_details_remove_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info1 = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val musicList = info1.position

        return when (item.itemId) {
            R.id.go_to_remove_album_song -> {
                val albumMusic = albumMusicItem[musicList]
                if(musicAlbumTableHandler.albumSongDelete(albumMusic)){
                    albumMusicItem.removeAt(musicList)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this,"Song deleted successfully", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Song deletion failed", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater2 = menuInflater
        inflater2.inflate(R.menu.album_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_a_song_album -> {
                startActivity(Intent(this, AddAlbumSongs::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}