package com.example.android_mysql1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class RegistroActivity : AppCompatActivity() {
    private lateinit var etNombre:EditText
    private lateinit var etEmail:EditText
    private lateinit var etTelefono:EditText
    private lateinit var etPass:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etNombre = findViewById(R.id.et_RNombre)
        etEmail = findViewById(R.id.et_REmail)
        etTelefono = findViewById(R.id.et_Rtelefono)
        etPass = findViewById(R.id.et_RPass)

        val id:String? = intent.getStringExtra("id").toString()

        val procesoEnCola: RequestQueue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.3/android_mysql1/registro.php?id=$id"
        val consulta = JsonObjectRequest(Request.Method.GET,url,null,
            Response.Listener { Respuesta->
                etNombre.setText(Respuesta.getString("nombre"))
                etEmail.setText(Respuesta.getString("email"))
                etTelefono.setText(Respuesta.getString("telefono"))
                etPass.setText(Respuesta.getString("pass"))
                Toast.makeText(this,"Datos obtenidos",Toast.LENGTH_LONG).show()
            }, Response.ErrorListener { error->
                Toast.makeText(this,"Datos obtenidos",Toast.LENGTH_LONG).show()
            }

            )
        procesoEnCola.add(consulta)
    }

    fun regresar(Vista: View){
        var ventana: Intent = Intent(this,MainActivity::class.java)
        startActivity(ventana)
    }
}