package com.example.uielements_pt_2

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import android.content.Intent as Intent1


val musicSongs = arrayListOf<String>()


class MainActivity : AppCompatActivity() {



    private val songsArray = arrayOf("Remember When", "Heaven", "Lucky",
            "My Love", "Beautiful in White", "The Prayer" , "Im yours", "Beauty and the Beast",
            "Someone Like You", "Always" , "Country Road", "Original Sin" ,
            )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songsArray)
        val queuedSongsListView = findViewById<ListView>(R.id.songsQueueListView)
        queuedSongsListView.adapter = adapter

        registerForContextMenu(queuedSongsListView)

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
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
            R.id.go_to_albums -> {

                startActivity(Intent1(this, AlbumsActivity::class.java))
                true
            }
            R.id.go_to_queues -> {

                startActivity(Intent1(this, QueuedSongsActivity::class.java))
                true



            }



            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {

            R.id.add_to_queue -> {
                val info = item.menuInfo as AdapterContextMenuInfo
                musicSongs.add(songsArray[info.position])
                true
                val snackbar = Snackbar.make(this.findViewById(R.id.songsQueueListView),
                        "Navigate To Queue", Snackbar.LENGTH_LONG)
                snackbar.setAction("Go", View.OnClickListener {
                    startActivity(Intent1(this, QueuedSongsActivity::class.java))
                })
                snackbar.show()


                true
            }
            else-> super.onContextItemSelected(item)

        }
    }
}

class QueuedSongsActivity {

}
