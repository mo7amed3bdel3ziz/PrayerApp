package com.example.prayerapplication.models

data class Hijri(
    var date        : String,
    var format      : String,
    var day         : String,
    var weekday     : Weekday          = Weekday(),
    var month       : Month?            = Month(),
    var year        : String?           = null,
    var designation : Designation?      = Designation(),
    var holidays    : ArrayList<String> = arrayListOf()

)
{
    constructor() : this(
        "",
        "",
        "",
        Weekday(),
        Month(),
        null,
        Designation(),
        arrayListOf()
    ) {
        // Handle any exceptions or validation here
        try {
            // Perform any necessary operations
        } catch (e: Exception) {
            // Handle the error case, e.g., log an error message or provide a default value
        }
    }
}