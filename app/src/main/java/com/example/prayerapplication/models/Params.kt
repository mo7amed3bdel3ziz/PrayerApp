package com.example.prayerapplication.models

data class Params(
   var Fajr : Int,
   var Isha : Int
)
{
   constructor() : this(
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