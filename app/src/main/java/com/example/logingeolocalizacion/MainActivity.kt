package com.example.logingeolocalizacion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            // onBackPressed()  Mas seguro sustituido por startActivity
            val intent = Intent(this, AuthActivity::class.java);
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK;
            startActivity(intent);
        }

    }
}
