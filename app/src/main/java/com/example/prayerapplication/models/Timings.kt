package com.example.prayerapplication.models

data class Timings(
    var Fajr       : String,
    var Sunrise    : String,
    var Dhuhr      : String,
    var Asr        : String,
    var Sunset     : String,
    var Maghrib    : String,
    var Isha       : String,
    var Imsak      : String,
    var Midnight   : String,
    var Firstthird : String,
    var Lastthird  : String
)
{
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    ) {
        // Handle any exceptions or validation here
        try {
            // Perform any necessary operations
        } catch (e: Exception) {
            // Handle the error case, e.g., log an error message or provide a default value
        }
    }
}