package com.example.prayerapplication.models

data class Weekday(

    var en : String,
    var ar : String
)
{
    constructor() : this(
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