package com.example.imad5111assignment2

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.ImageView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnWelcome= findViewById< Button >(R.id.btnWelcome)
        val pbHomeScreen = findViewById<ProgressBar>(R.id.pbWelCome)
        val txtWelc=findViewById<TextView>(R.id.txtMain)
        val brown = Color.rgb(70, 73, 77)
        val txtInfo=findViewById<TextView>(R.id.txtAction)

        showGIF()



        pbHomeScreen.progressTintList = ColorStateList.valueOf(Color.RED)

        btnWelcome.setOnClickListener {

            pbHomeScreen.progress = 0
            pbHomeScreen.max = 100

            var i = 0

            Thread {
                while (i <= 100) {
                    runOnUiThread {
                        pbHomeScreen.progress = i

                        when {
                            i < 10 -> {
                                pbHomeScreen.progressTintList = ColorStateList.valueOf(Color.GRAY)
                                txtInfo.text = "Pedro likes to play with Corgis and his favorite color is blue"
                            }
                            i < 40 -> {
                                pbHomeScreen.progressTintList = ColorStateList.valueOf(Color.WHITE)
                            }
                            i < 80 -> {
                                pbHomeScreen.progressTintList = ColorStateList.valueOf(brown)
                            }
                        }
                    }
                    try {
                        Thread.sleep(150)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    i++
                }

                val intent = Intent(this@MainActivity, IMAD5111Screen2::class.java)
                startActivity(intent)
            }.start()
            btnWelcome.isEnabled = false
        }
    }
    fun showGIF() {
        val imgGif: ImageView = findViewById(R.id.imgGif)

        Glide.with(this).load(R.drawable.homescreen_1).into(imgGif)

    }
}
