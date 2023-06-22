package com.example.prayerapplication.models

data class Date(

    var readable  : String ,
    var timestamp : String ,
    var gregorian : Gregorian = Gregorian(),
    var hijri     : Hijri     = Hijri()


)
{
    constructor() : this(
        "",
        "",
        Gregorian(),
        Hijri()
    ) {
        // Handle any exceptions or validation here
        try {
            // Perform any necessary operations
        } catch (e: Exception) {
            // Handle the error case, e.g., log an error message or provide a default value
        }
    }
}