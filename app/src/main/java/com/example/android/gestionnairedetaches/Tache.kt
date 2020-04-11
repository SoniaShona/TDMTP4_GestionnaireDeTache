package com.example.android.gestionnairedetaches

import java.text.SimpleDateFormat
import java.util.*


class Tache :Comparable<Tache> {
    var name: String = ""
    var date: String = ""

    constructor() {}

    constructor(name: String, date: String) {
        this.name = name
        this.date = date
    }

    fun getDate():Calendar{
        val date1 = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        date1.setTime(sdf.parse(this.date))
        return date1
    }

    override fun compareTo(other:Tache):Int
    {
        return this.getDate().compareTo(other.getDate())
    }
}