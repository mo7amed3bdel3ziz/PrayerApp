package com.example.prayerapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.prayerapplication.databinding.ActivityMainBinding
import com.example.prayerapplication.getLocation.MyLocation
import com.example.prayerapplication.network.State
import com.example.prayerapplication.ui.AladhanViewModel
import com.example.prayerapplication.ui.MapsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.Math.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
   private lateinit var aladhanViewModel: AladhanViewModel

    private lateinit var locationManager: LocationManager
   // val aladhanViewModel: AladhanViewModel by  viewModels()

    var loc: Location? = null
     var latitude =0.0
    var longitude=0.0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
      aladhanViewModel = ViewModelProvider(this).get(AladhanViewModel::class.java)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val requestCode = 1 // You can use any value here

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, so request it
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            // Permission is already granted
            // Start using location services
        }
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start using location services
            } else {
                // Permission denied, handle the error or provide an alternative
            }
        }


        val locationResult: MyLocation.LocationResult = object : MyLocation.LocationResult() {
            override fun gotLocation(location: Location) {
                loc = location

                latitude =loc!!.latitude
                latitude =loc!!.longitude
                Log.d("AddLocationPointToUser" ,latitude.toString())

            }
        }

        val myLocation = MyLocation()
        myLocation.getLocation(this, locationResult)

        val currentHour: Int = LocalTime.now().hour
         lifecycleScope.launch {

             aladhanViewModel.AddLocationPointToUser(latitude,latitude,"2").collect {

                 when (it) {
                     is State.Loading -> {}
                     is State.Success -> {
                         Log.d("AddLocationPointToUser" ,"Success")
                         Log.d("AddLocationPointToUser" ,it.data.data.get(0).date.hijri.year.toString())



                         binding.isha.setText(it.data.data.get(0).timings.Fajr.replace("(EET)",""))
                         binding.maghrib.setText(it.data.data.get(0).timings.Dhuhr.replace("(EET)",""))
                         binding.asr.setText(it.data.data.get(0).timings.Asr.replace("(EET)",""))
                         binding.duhur.setText(it.data.data.get(0).timings.Maghrib.replace("(EET)",""))
                         binding.fajr.setText(it.data.data.get(0).timings.Isha.replace("(EET)",""))
                         binding.dateID.setText(it.data.data.get(1).date.readable.replace("(EET)",""))
                         binding.adressId.setText(it.data.data.get(2).meta.timezone.replace("(EET)",""))

                       if ( currentHour <4) {

                             binding.nextPrayerId.setText("Fajr")
                           val timeString = it.data.data.get(0).timings.Fajr.replace("(EET)","")


                           val timeComponents = timeString.split(":")
                           // Extract the hour and minute components
                           val hour = timeComponents[0].toInt()

                           val minute = 12
                           val currentTime = LocalDateTime.now()
                           val specificTime = LocalTime.of(hour, minute)
                           val duration = Duration.between(specificTime, currentTime)
                           val hours = duration.toHours()
                           val minutes = duration.toMinutes() % 60
                           binding.leftHoursTXT.setText(hours.toString()+"hr"+minutes.toString()+"min")
                         }
                    else if ( currentHour <13) {
                             binding.nextPrayerId.setText("Dhuhr")
                           val timeString = it.data.data.get(0).timings.Dhuhr.replace("(EET)","")


                           val timeComponents = timeString.split(":")
                           // Extract the hour and minute components
                           val hour = timeComponents[0].toInt()

                           val minute = 12
                           val currentTime = LocalDateTime.now()
                           val specificTime = LocalTime.of(hour, minute)
                           val duration = Duration.between(specificTime, currentTime)
                           val hours = duration.toHours()
                           val minutes = duration.toMinutes() % 60
                           binding.leftHoursTXT.setText(hours.toString()+"hr"+minutes.toString()+"min")
                         }
                    else if ( currentHour <4) {
                             binding.nextPrayerId.setText("Asr")
                           val timeString = it.data.data.get(0).timings.Asr.replace("(EET)","")


                           val timeComponents = timeString.split(":")
                           // Extract the hour and minute components
                           val hour = timeComponents[0].toInt()

                           val minute = 12
                           val currentTime = LocalDateTime.now()
                           val specificTime = LocalTime.of(hour, minute)
                           val duration = Duration.between(specificTime, currentTime)
                           val hours = duration.toHours()
                           val minutes = duration.toMinutes() % 60
                           binding.leftHoursTXT.setText(hours.toString()+"hr"+minutes.toString()+"min")
                         }
                         else if ( currentHour <20) {
                             binding.nextPrayerId.setText("Maghrib")
                           val timeString = it.data.data.get(0).timings.Maghrib.replace("(EET)","")


                           val timeComponents = timeString.split(":")
                           // Extract the hour and minute components
                           val hour = timeComponents[0].toInt()

                           val minute = 12
                           val currentTime = LocalDateTime.now()
                           val specificTime = LocalTime.of(hour, minute)
                           val duration = Duration.between(specificTime, currentTime)
                           val hours = duration.toHours()
                           val minutes = duration.toMinutes() % 60
                           binding.leftHoursTXT.setText(hours.toString()+"hr"+minutes.toString()+"min")
                         }
                    else   {
                             binding.nextPrayerId.setText("Isha")
                           val timeString = it.data.data.get(0).timings.Isha.replace("(EET)","")


                           val timeComponents = timeString.split(":")
                           // Extract the hour and minute components
                           val hour = timeComponents[0].toInt()

                           val minute = 12
                           val currentTime = LocalDateTime.now()
                           val specificTime = LocalTime.of(hour, minute)
                           val duration = Duration.between(specificTime, currentTime)
                           val hours = duration.toHours()
                           val minutes = duration.toMinutes() % 60
                           binding.leftHoursTXT.setText(hours.toString()+"hr"+minutes.toString()+"min")
                         }

                     }
                     is State.Error -> {
                         Log.d("AddLocationPointToUser" ,it.messag)

                     }
                 }
             }
         }


        lifecycleScope.launch {

            aladhanViewModel.GetQibla().collect {

                when (it) {
                    is State.Loading -> {}
                    is State.Success -> {
                        Log.d("GetQibla" ,"Success")

                    }
                    is State.Error -> {
                        Log.d("GetQibla" ,it.messag)

                    }
                }
            }
        }

        binding.QiblaBTN.setOnClickListener {


            val intent = Intent(this, MapsActivity::class.java).apply {

            }
            startActivity(intent)

        }
    }


}
