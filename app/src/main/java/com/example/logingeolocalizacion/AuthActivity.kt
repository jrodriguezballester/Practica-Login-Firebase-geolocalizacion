package com.example.logingeolocalizacion


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        title = "Autentificacion"
        setup()
    }

    private fun setup() {
        registerButton.setOnClickListener {
            var myEmail = emailEditText.text.toString()
            var myPassword = passWordEditText.text.toString()
            var recursos = Recursos()
            if (recursos.isValidEmail(myEmail) && recursos.isValidPassword(myPassword)) {
                //registrar
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(myEmail, myPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Registro satisfactorio continuar app
                            Toast.makeText(
                                this,
                                "Enhorabuena, registro satisfactorio",
                                Toast.LENGTH_SHORT
                            ).show();

                            continuarApp()
                        } else {
                            // no se ha registrado
                            recursos.mostrarAviso1(
                                this,
                                "Error de Autentificacion",
                                "No has podido registrarte en la aplicacion, puede que ya estes registrado"
                            )
                        }
                    }
            } else {
                // Correo o Contraseña no valido mostrar ventana
                recursos.mostrarAviso1(this, "Aviso", "El correo o la contraseña son incorrentos")
            }
        }
        loginButton.setOnClickListener {
            var myEmail = emailEditText.text.toString()
            var myPassword = passWordEditText.text.toString()
            var recursos = Recursos()
            if (recursos.isValidEmail(myEmail) && recursos.isValidPassword(myPassword)) {
                //registrar
                FirebaseAuth.getInstance().signInWithEmailAndPassword(myEmail, myPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // login satisfactorio continuar app
                            continuarApp()
                        } else {
                            // no se ha registrado
                            recursos.mostrarAviso1(
                                this,
                                "Error de Login",
                                "No has podido ingresar en la aplicacion, Comprueba tu email y contraseña"
                            )
                        }
                    }
            } else {
                // Correo o Contraseña no valido mostrar ventana
                recursos.mostrarAviso1(this, "Aviso", "El correo o la contraseña son incorrentos")
            }
        }
// Pruebas
        /*
               loginButton.setOnClickListener {
                   continuarApp()
               }
         */
    }

    private fun continuarApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}