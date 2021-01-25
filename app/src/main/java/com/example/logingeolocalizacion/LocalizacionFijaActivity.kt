package com.example.logingeolocalizacion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocalizacionFijaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_localizacion_fija)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val myHome = LatLng(39.473532690617795, -0.4099243505967721)

        mMap.addMarker(MarkerOptions().position(myHome).title("My House").icon(BitmapDescriptorFactory.fromResource(R.drawable.casa)))

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(myHome))
        // Hacemos zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myHome, 15F))
        // insertamos control zoom
        mMap.uiSettings.isZoomControlsEnabled =true
    }
}