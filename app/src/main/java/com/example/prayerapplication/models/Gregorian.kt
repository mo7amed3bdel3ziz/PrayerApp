package com.example.prayerapplication.models

data class Gregorian(
   var date        : String ,
   var format      : String ,
   var day         : String ,
   var weekday     : Weekday    = Weekday(),
   var month       : Month       = Month(),
   var year        : String     ,
   var designation : Designation = Designation()
)
{
   constructor() : this(
      "",
      "",
      "",
      Weekday(),
      Month(),
      "",
      Designation()
   ) {
      // Handle any exceptions or validation here
      try {
         // Perform any necessary operations
      } catch (e: Exception) {
         // Handle the error case, e.g., log an error message or provide a default value
      }
   }
}