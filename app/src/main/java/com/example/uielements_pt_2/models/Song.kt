package com.example.uielements_pt_2.models

import java.io.Serializable

class Song (var id: Int = 0, var title: String, var artist: String, var album: String){
    override fun toString(): String {
        return  "Album: ${album} \n"+
                "Song Title: ${title} \n"+
                "Artist: ${artist}"
    }
}