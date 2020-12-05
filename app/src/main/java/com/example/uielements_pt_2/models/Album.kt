package com.example.uielements_pt_2.models

class Album(var id: Int = 0, var title: String, var releaseDate: String) {
    override fun toString(): String {
        return "Title: ${title} \n"+
                "Release Date: ${releaseDate}"
    }
}