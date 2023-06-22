package com.example.prayerapplication.models

import android.location.Location

data class Method(
    var id       : Int ,
    var name     : String,
    var params   : Params   = Params(),
    var location : com.example.prayerapplication.models.Location = com.example.prayerapplication.models.Location()
)
{
    constructor() : this(
        0,
        "",
        Params(),
        Location()
    ) {
        // Handle any exceptions or validation here
        try {
            // Perform any necessary operations
        } catch (e: Exception) {
            // Handle the error case, e.g., log an error message or provide a default value
        }
    }
}