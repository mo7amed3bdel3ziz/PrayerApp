package com.example.prayerapplication.ui

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.prayerapplication.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import android.content.pm.PackageManager
import android.location.LocationListener
import java.lang.Math.*
import android.Manifest

class MapsActivity : AppCompatActivity() {
    private lateinit var locationManager: LocationManager
    private lateinit var qiblaDirectionTextView: TextView
    private lateinit var compassImageView: ImageView
    private var currentLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        qiblaDirectionTextView = findViewById(R.id.qiblaDirectionTextView)
        compassImageView = findViewById(R.id.compassImageView)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            startLocationUpdates()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
    }

    private fun startLocationUpdates() {
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                MIN_TIME_BETWEEN_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                locationListener
            )
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_TIME_BETWEEN_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                locationListener
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun stopLocationUpdates() {
        locationManager.removeUpdates(locationListener)
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            currentLocation = location
            calculateQiblaDirection()
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onProviderDisabled(provider: String) {}
    }

    private fun calculateQiblaDirection() {
        val qiblaLatitude = 21.4225
        val qiblaLongitude = 39.8262
        val userLatitude = currentLocation?.latitude ?: 0.0
        val userLongitude = currentLocation?.longitude ?: 0.0

        val deltaLongitude = qiblaLongitude - userLongitude

        val y = sin(deltaLongitude * DEGREE_TO_RADIAN)
        val x = cos(userLatitude * DEGREE_TO_RADIAN) * sin(qiblaLatitude * DEGREE_TO_RADIAN) -
                sin(userLatitude * DEGREE_TO_RADIAN) * cos(qiblaLatitude * DEGREE_TO_RADIAN) * cos(
            deltaLongitude * DEGREE_TO_RADIAN
        )

        val qiblaDirection = atan2(y, x) * RADIAN_TO_DEGREE
        val qiblaDirectionText = String.format("%.2f", qiblaDirection)

        qiblaDirectionTextView.text = "Qibla Direction: $qiblaDirectionText°"

        rotateCompassArrow(qiblaDirection)
    }

    private fun rotateCompassArrow(qiblaDirection: Double) {
        val fromDegree = currentDegree
        val toDegree = -qiblaDirection.toFloat()

        val rotateAnimation = RotateAnimation(
            fromDegree,
            toDegree,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation.duration = 200
        rotateAnimation.fillAfter = true

        compassImageView.startAnimation(rotateAnimation)
        currentDegree = toDegree
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
        private const val MIN_TIME_BETWEEN_UPDATES: Long = 5000
        private const val MIN_DISTANCE_CHANGE_FOR_UPDATES: Float = 10f
        private const val DEGREE_TO_RADIAN: Double = 0.01745329252
        private const val RADIAN_TO_DEGREE: Double = 57.2957795131
    }

    private var currentDegree: Float = 0f
}