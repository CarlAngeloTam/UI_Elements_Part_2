package com.example.uielements_pt_2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import com.example.uielements_pt_2.models.Album

class AlbumsActivity : AppCompatActivity() {


    lateinit var albumItems: MutableList<Album>
    lateinit var albumTableHandler: DatabaseHandler
    lateinit var adapter: ArrayAdapter<Album>
    lateinit var gridView: GridView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        gridView = findViewById<GridView>(R.id.grid_view)
        albumTableHandler = DatabaseHandler(this)
        albumItems = albumTableHandler.albumRead()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumItems)
        gridView.adapter = adapter
        registerForContextMenu(gridView)

        gridView.setOnItemClickListener { parent, view, position, id ->
            val album_id = albumItems[position].id

            val intent1 = Intent(applicationContext, AlbumDetailsActivity::class.java)
            intent.putExtra("album_id", album_id)

            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater2 = menuInflater
        inflater2.inflate(R.menu.album_edit_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info1 = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val musicList = info1.position

        return when (item.itemId) {
            R.id.go_to_edit_album -> {
                val album_id = albumItems[musicList].id

                val intent = Intent(applicationContext, EditAlbum::class.java)
                intent.putExtra("album_id", album_id)

                startActivity(intent)
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
            R.id.go_to_add_album -> {
                startActivity(Intent(this, AddAlbum::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
