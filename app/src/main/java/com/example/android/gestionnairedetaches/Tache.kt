package com.example.android.gestionnairedetaches

class Tache {
    var name: String = ""
    var date: String = ""

    constructor() {}

    constructor(name: String, date: String) {
        this.name = name
        this.date = date
    }
}