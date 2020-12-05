
package com.example.uielements_pt_2

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.uielements_pt_2.models.Song
import com.google.android.material.snackbar.Snackbar

val musicList = arrayListOf<String>()

class MainActivity : AppCompatActivity() {

    lateinit var queuedSongArray: MutableList<Song>
    lateinit var musicsTableHandler: DatabaseHandler
    lateinit var adapter: ArrayAdapter<Song>
    lateinit var queuedSongListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queuedSongListView = findViewById<ListView>(R.id.songsQueueListView)
        musicsTableHandler = DatabaseHandler(this)
        queuedSongArray = musicsTableHandler.read()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, queuedSongArray)
        queuedSongListView.adapter = adapter
        registerForContextMenu(queuedSongListView)
        queuedSongListView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->  }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater1= menuInflater
        inflater1.inflate(R.menu.songs_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        val info2 = item.menuInfo as AdapterContextMenuInfo
        val jacketList= info2.position

        return when (item.itemId) {
            R.id.add_to_queue -> {
                val info2 = item.menuInfo as AdapterContextMenuInfo
                musicList.add(queuedSongArray[info2.position].toString())
                true
                val snackbar: Snackbar = Snackbar.make(this.findViewById(R.id.songsQueueListView),
                    "Navigate to Queue", Snackbar.LENGTH_LONG)
                snackbar.setAction("Go", View.OnClickListener {
                    startActivity(Intent(this, QueuedSongsActivity::class.java))
                })
                snackbar.show()
                true
            }
            R.id.go_to_edit_songs -> {
                val song_id = queuedSongArray[jacketList].id
                val intent = Intent(applicationContext, EditSongs::class.java)
                intent.putExtra("song_id", song_id)

                startActivity(intent)
                true
            }
            R.id.go_to_delete_songs -> {
                val music = queuedSongArray[jacketList]
                if (musicsTableHandler.delete(music)) {
                    queuedSongArray.removeAt(jacketList)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "Song Deleted Success.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Song Deletion Failed.", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_to_songs -> {
                true
            }
            R.id.go_to_add_songs -> {
                startActivity(Intent(this, AddSongs::class.java))
                true
            }
            R.id.go_to_albums -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            R.id.go_to_queues -> {
                startActivity(Intent(this, QueuedSongsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

