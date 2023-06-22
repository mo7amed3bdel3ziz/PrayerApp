package com.example.prayerapplication.models

data class Meta(
    var latitude                 : Double ,
    var longitude                : Double ,
    var timezone                 : String ,
    var method                   : Method = Method(),
    var latitudeAdjustmentMethod : String  ,
    var midnightMode             : String  ,
    var school                   : String  ,
    var offset                   : Offset = Offset()
)
{
    constructor() : this(
        0.0,
        0.0,
        "",
        Method(),
        "",
        "",
        "",
        Offset()
    ) {
        // Handle any exceptions or validation here
        try {
            // Perform any necessary operations
        } catch (e: Exception) {
            // Handle the error case, e.g., log an error message or provide a default value
        }
    }
}