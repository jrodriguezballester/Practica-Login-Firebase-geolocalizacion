package com.example.logingeolocalizacion

import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setup()
    }

    private fun setup() {
        registerButton.setOnClickListener {
            var myEmail = emailEditText.text.toString()
            var myPassword = passWordEditText.text.toString()

            if (isValidEmail(myEmail) && isValidPassword(myPassword)) {
                //registrar
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(myEmail, myPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Registro satisfactorio continuar app
                            mostrarAviso1("Enhorabuena","Registro Satisfactorio")
                        } else {
                            // no se ha registrado
                            mostrarAviso1(
                                "Error de Autentificacion",
                                "No has podido registrarte en la aplicacion, puede que ya estes registrado"
                            )
                        }
                    }
            } else {
                // Correo o Contraseña no valido mostrar ventana
                mostrarAviso1("Aviso", "El correo o la contraseña son incorrentos")
            }
        }
    }

    // validaciones
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //^ # inicio-de-cadena
    //(? =. * [0-9]) # un dígito debe aparecer al menos una vez
    //(? =. * [a-z]) # una letra minúscula debe aparecer al menos una vez
    //(? =. * [A-Z]) # una letra mayúscula debe aparecer al menos una vez
    //(? =. * [@ # $% ^ & + =]) # un carácter especial debe aparecer al menos una vez que pueda reemplazarlo con sus caracteres especiales
    // (? = \\ S + $) # no se permiten espacios en blanco en toda la cadena
    //. {4,} # . cualquier caracter,{n} n lugares,{n,m} n=min, m=max
    //$ # final de cadena
    private fun isValidPassword(password: String): Boolean {
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"
        val pattern = Regex(PASSWORD_PATTERN)
        return pattern.containsMatchIn(password)
    }

    private fun mostrarAviso2() {

        AlertDialog.Builder(this)
            .setTitle("Titulo del diálogo")
            .setMessage("Contenido del diálogo.")
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                })
            .setNegativeButton(android.R.string.cancel,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón cancel pulsado
                })
            .show()

    }

    private fun mostrarAviso1(titulo: String, mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle(titulo)
            .setMessage(mensaje)
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialog, which ->
                    //botón OK pulsado
                })
            .show()
    }
}