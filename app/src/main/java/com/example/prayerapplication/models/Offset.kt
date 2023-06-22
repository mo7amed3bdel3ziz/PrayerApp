package com.example.prayerapplication.models

data class Offset(

    var Imsak    : Int,
    var Fajr     : Int,
    var Sunrise  : Int,
    var Dhuhr    : Int,
    var Asr      : Int,
    var Maghrib  : Int,
    var Sunset   : Int,
    var Isha     : Int,
    var Midnight : Int

)
{
    constructor() : this(
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
    ) {
        // Handle any exceptions or validation here
        try {
            // Perform any necessary operations
        } catch (e: Exception) {
            // Handle the error case, e.g., log an error message or provide a default value
        }
    }
}