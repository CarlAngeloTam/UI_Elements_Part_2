package com.example.uielements_pt_2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.uielements_pt_2.models.Album
import com.example.uielements_pt_2.models.AlbumSong
import com.example.uielements_pt_2.models.Song
class DatabaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "songs_database"

        //song
        private val TABLE_NAME = "songs"
        private val COL_ID = "id"
        private val COL_TITLE = "title"
        private val COL_ARTIST = "artist"
        private val COL_ALBUM = "album"

        //album
        private val TABLE_ALBUM = "albums"
        private val ID_ALBUM = "id"
        private val ALBUM_TITLE = "title"
        private val ALBUM_RELEASE_DATE = "releaseDate"

        //album song
        private val TABLE_ALBUM_SONG = "albumSongs"
        private val ID_ALBUM_SONG = "id"
        private val ALBUM_SONG_TITLE = "title"
        private val ALBUM_SONG_ARTIST = "artist"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //song
        val queryTam =
            "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_TITLE TEXT, $COL_ARTIST TEXT, $COL_ALBUM TEXT)"
        db?.execSQL(queryTam)

        //album
        val queryTam2 =
            "CREATE TABLE $TABLE_ALBUM ($ID_ALBUM INTEGER PRIMARY KEY, $ALBUM_TITLE TEXT, $ALBUM_RELEASE_DATE TEXT)"
        db?.execSQL(queryTam2)

        //album song
        val queryTam3 =
            "CREATE TABLE $TABLE_ALBUM_SONG ($ID_ALBUM_SONG INTEGER PRIMARY KEY, $ALBUM_SONG_TITLE TEXT, $ALBUM_SONG_ARTIST TEXT)"
        db?.execSQL(queryTam3)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //song
        db!!.execSQL("DROP TABLE IF EXIST" + TABLE_NAME)

        //album
        db.execSQL("DROP TABLE IF EXIST" + TABLE_ALBUM)

        //album song
        db.execSQL("DROP TABLE IF EXIST" + TABLE_ALBUM_SONG)

        onCreate(db)
    }

    //song
    fun create(song: Song): Boolean {

        val database1 = this.writableDatabase
        val contentValues1 = ContentValues()
        contentValues1.put(COL_TITLE, song.title)
        contentValues1.put(COL_ARTIST, song.artist)
        contentValues1.put(COL_ALBUM, song.album)

        val result1 = database1.insert(TABLE_NAME, null, contentValues1)

        if (result1 == (0).toLong()) {
            return false
        }
        return true
    }

    fun update(song: Song): Boolean {
        val database2 = this.writableDatabase
        val contentValues2 = ContentValues()
        contentValues2.put(COL_TITLE, song.title)
        contentValues2.put(COL_ARTIST, song.artist)
        contentValues2.put(COL_ALBUM, song.album)

        val result2 = database2.update(TABLE_NAME, contentValues2, "id=" + song.id, null)

        if (result2 == 0) {
            return false
        }
        return true
    }

    fun delete(song: Song): Boolean {
        val database3 = this.writableDatabase
        val result3 = database3.delete(TABLE_NAME, "id=${song.id}", null)
        if (result3 == 0) {
            return false
        }
        return true
    }

    fun read(): MutableList<Song> {
        val musicsList: MutableList<Song> = ArrayList<Song>()
        val query1 = "SELECT * FROM $TABLE_NAME"
        val database1 = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database1.rawQuery(query1, null)
        } catch (e: SQLiteException) {
            return musicsList
        }

        var id: Int
        var title: String
        var artists: String
        var albums: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
                artists = cursor.getString(cursor.getColumnIndex(COL_ARTIST))
                albums = cursor.getString(cursor.getColumnIndex(COL_ALBUM))
                val music = Song(id, title, artists, albums)
                musicsList.add(music)
            } while (cursor.moveToNext())
        }
        return musicsList
    }

    fun readOne(song_id: Int): Song {
        var music: Song = Song(0, "", "", "")
        val query2 = "SELECT * FROM $TABLE_NAME WHERE id = $song_id"
        val database2 = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database2.rawQuery(query2, null)
        } catch (e: SQLiteException) {
            return music
        }

        var id: Int
        var title: String
        var artists: String
        var albums: String
        if (cursor.moveToFirst()) {

            id = cursor.getInt(cursor.getColumnIndex(COL_ID))
            title = cursor.getString(cursor.getColumnIndex(COL_TITLE))
            artists = cursor.getString(cursor.getColumnIndex(COL_ARTIST))
            albums = cursor.getString(cursor.getColumnIndex(COL_ALBUM))
            music = Song(id, title, artists, albums)
        }
        return music
    }

    //album
    fun albumCreate(album: Album): Boolean {

        val database3 = this.writableDatabase
        val contentValues3 = ContentValues()
        contentValues3.put(ALBUM_TITLE, album.title)
        contentValues3.put(ALBUM_RELEASE_DATE, album.releaseDate)

        val result3 = database3.insert(TABLE_ALBUM, null, contentValues3)

        if (result3 == (0).toLong()) {
            return false
        }
        return true
    }

    fun albumUpdate(album: Album): Boolean {
        val database4 = this.writableDatabase
        val contentValues4 = ContentValues()
        contentValues4.put(ALBUM_TITLE, album.title)
        contentValues4.put(ALBUM_RELEASE_DATE, album.releaseDate)

        val result4 = database4.update(TABLE_ALBUM, contentValues4, "id=" + album.id, null)

        if (result4 == 0) {
            return false
        }
        return true
    }

    fun albumDelete(album: Album): Boolean {
        val database5 = this.writableDatabase
        val result5 = database5.delete(TABLE_ALBUM, "id=${album.id}", null)
        if (result5 == 0) {
            return false
        }
        return true
    }

    fun albumRead(): MutableList<Album> {
        val musicList: MutableList<Album> = ArrayList<Album>()
        val query1 = "SELECT * FROM " + TABLE_ALBUM
        val database2 = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database2.rawQuery(query1, null)
        } catch (e: SQLiteException) {
            return musicList
        }

        var id: Int
        var title: String
        var releaseDate: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM))
                title = cursor.getString(cursor.getColumnIndex(ALBUM_TITLE))
                releaseDate = cursor.getString(cursor.getColumnIndex(ALBUM_RELEASE_DATE))

                val album = Album(id, title, releaseDate)
                musicList.add(album)
            } while (cursor.moveToNext())
        }
        return musicList
    }

    fun albumReadOne(album_id: Int): Album {
        var albumTwo: Album = Album(0, "", "")
        val query2 = "SELECT * FROM $TABLE_ALBUM WHERE id = $album_id"
        val database2 = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database2.rawQuery(query2, null)
        } catch (e: SQLiteException) {
            return albumTwo
        }

        var id: Int
        var title: String
        var releaseDate: String

        if (cursor.moveToFirst()) {

            id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM))
            title = cursor.getString(cursor.getColumnIndex(ALBUM_TITLE))
            releaseDate = cursor.getString(cursor.getColumnIndex(ALBUM_RELEASE_DATE))

            albumTwo = Album(id, title, releaseDate)
        }
        return albumTwo
    }

    //album song
    fun albumSongCreate(albumSong: AlbumSong): Boolean {

        val database3 = this.writableDatabase
        val contentValues3 = ContentValues()
        contentValues3.put(ALBUM_SONG_TITLE, albumSong.title)
        contentValues3.put(ALBUM_SONG_ARTIST, albumSong.artist)

        val result3 = database3.insert(TABLE_ALBUM_SONG, null, contentValues3)

        if (result3 == (0).toLong()) {
            return false
        }
        return true
    }

    fun albumSongUpdate(albumSong: AlbumSong): Boolean {
        val database4 = this.writableDatabase
        val contentValues4 = ContentValues()
        contentValues4.put(ALBUM_SONG_TITLE, albumSong.title)
        contentValues4.put(ALBUM_SONG_ARTIST, albumSong.artist)

        val result4 = database4.update(TABLE_ALBUM_SONG, contentValues4, "id=" + albumSong.id, null)

        if (result4 == 0) {
            return false
        }
        return true
    }

    fun albumSongDelete(albumSong: AlbumSong): Boolean {
        val database5 = this.writableDatabase
        val result5 = database5.delete(TABLE_ALBUM_SONG, "id=${albumSong.id}", null)
        if (result5 == 0) {
            return false
        }
        return true
    }

    fun albumSongRead(): MutableList<AlbumSong> {
        val albumList2: MutableList<AlbumSong> = ArrayList<AlbumSong>()
        val query1 = "SELECT * FROM " + TABLE_ALBUM_SONG
        val database1 = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database1.rawQuery(query1, null)
        } catch (e: SQLiteException) {
            return albumList2
        }

        var id: Int
        var title: String
        var artist: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM_SONG))
                title = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_TITLE))
                artist = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_ARTIST))

                val music = AlbumSong(id, title, artist)
                albumList2.add(music)
            } while (cursor.moveToNext())
        }
        return albumList2
    }

    fun albumSongReadOne(album_id: Int): AlbumSong {
        var albumTwo: AlbumSong = AlbumSong(0, "", "")
        val query1 = "SELECT * FROM $TABLE_ALBUM_SONG WHERE id = $album_id"
        val database2 = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database2.rawQuery(query1, null)
        } catch (e: SQLiteException) {
            return albumTwo
        }

        var id: Int
        var title: String
        var artist: String

        if (cursor.moveToFirst()) {

            id = cursor.getInt(cursor.getColumnIndex(ID_ALBUM_SONG))
            title = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_TITLE))
            artist = cursor.getString(cursor.getColumnIndex(ALBUM_SONG_ARTIST))

            albumTwo = AlbumSong(id, title, artist)
        }
        return albumTwo
    }
}