package com.example.a24012011083_mad_practical_3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        implicitIntent()
        ExplicitIntent()
    }
    fun implicitIntent(){
        findViewById<Button>(R.id.btn_Browse).setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse(findViewById<EditText>(R.id.editTextText).text.toString())).also {
                startActivity(it)
            }
        }

        val callButton = findViewById<Button>(R.id.btn_Call)

        callButton.setOnClickListener {
            val number = findViewById<EditText>(R.id.editTextText2).text.toString()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData("tel:$number".toUri())
            startActivity(intent) }

        findViewById<Button>(R.id.btn_CallLog).setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://call_log/calls"))
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_Gallery).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type= "image/*"
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_Camara).setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btn_Alarm).setOnClickListener {
            val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, 7)
                putExtra(AlarmClock.EXTRA_MINUTES, 0)
            }

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No Alarm app found", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun ExplicitIntent(){
        findViewById<Button>(R.id.btn_Login).setOnClickListener {
            val intent = Intent(this, LoginActivity :: class.java)
            startActivity(intent)
        }
    }
}