package com.example.imad5111assignment2


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class   IMAD5111Screen2 : AppCompatActivity() {

    private lateinit var progressBarFeed: ProgressBar
    private lateinit var progressBarClean: ProgressBar
    private lateinit var progressBarPlay: ProgressBar
    private lateinit var txtAction: TextView
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imad5111_screen2)

 progressBarFeed = findViewById(R.id.PogressFeed)        
 progressBarClean = findViewById(R.id.ProgrssClean)      
 progressBarPlay = findViewById(R.id.ProgressPlay)       
 txtAction = findViewById(R.id.txtAction)                
       val btnFeed = findViewById<Button>(R.id.btnFeed)
       val btnClean = findViewById<Button>(R.id.btnClean)
       val btnPlay = findViewById<Button>(R.id.btnPlay)


        showGIF()

        btnFeed.setOnClickListener {
             showGIFfeed()
            startProgressLoading(progressBarFeed)
            txtAction.setTextSize(22.0F)
            val intFeed = progressBarFeed.progress

            if (intFeed < 40) {
                txtAction.setTextColor(Color.RED)
                progressBarFeed.progressTintList = ColorStateList.valueOf(Color.WHITE)
                txtAction.text = "Pedro feels like he has not eaten for days"
            } else if (intFeed < 70) {
                txtAction.setTextColor(Color.YELLOW)
                txtAction.text = " Pedro wants more grapes"
                progressBarFeed.progressTintList = ColorStateList.valueOf(Color.LTGRAY)
            } else if (intFeed < 90) {
                txtAction.setTextColor(Color.GREEN)
                progressBarFeed.progressTintList = ColorStateList.valueOf(Color.BLUE)
                txtAction.text = "Pedro wants more grapes"
            } else if (intFeed <= 100) {
                txtAction.setTextColor(Color.BLUE)
                progressBarFeed.progressTintList = ColorStateList.valueOf(Color.CYAN)
                txtAction.text = "Pedro is full"

            }



        }

        btnClean.setOnClickListener {
            var intClean = progressBarClean.progress
            startProgressLoading(progressBarClean)
            txtAction.setTextSize(28.0F)

           
                if (intClean < 40) {
                    txtAction.setTextColor(Color.RED)
                    progressBarClean.progressTintList = ColorStateList.valueOf(Color.WHITE)
                    txtAction.text = "Pedro needs to bath more"
                } else if (intClean < 70) {
                    txtAction.setTextColor(Color.YELLOW)
                    txtAction.text = "Pedro missed a few spots"
                    progressBarClean.progressTintList = ColorStateList.valueOf(Color.LTGRAY)
                } else if (intClean < 90) {
                    txtAction.setTextColor(Color.GREEN)
                    progressBarClean.progressTintList = ColorStateList.valueOf(Color.BLUE)
                    txtAction.text = "Pedro has a slight smell"
                } else {
                    txtAction.setTextColor(Color.BLUE)
                    progressBarClean.progressTintList = ColorStateList.valueOf(Color.CYAN)
                    txtAction.text = "Pedro has a shiny coat and smells like grapes"
                }


            showGIFbath()
        }

        btnPlay.setOnClickListener {
            startProgressLoading(progressBarPlay)
            txtAction.setTextSize(22.0F)
            val intPlay = progressBarPlay.progress

            if (intPlay < 40) {
                txtAction.setTextColor(Color.RED)
                progressBarPlay.progressTintList = ColorStateList.valueOf(Color.WHITE)
                txtAction.text = "Pedro wants to play more"
            } else if (intPlay < 70) {
                txtAction.setTextColor(Color.YELLOW)
                txtAction.text = "Pedro still wants to play"
                progressBarPlay.progressTintList = ColorStateList.valueOf(Color.LTGRAY)
            } else if (intPlay < 90) {
                txtAction.setTextColor(Color.GREEN)
                progressBarPlay.progressTintList = ColorStateList.valueOf(Color.BLUE)
                txtAction.text = "Pedro still has some energy to run"
            } else if (intPlay <= 100) {
                txtAction.setTextColor(Color.BLUE)
                progressBarPlay.progressTintList = ColorStateList.valueOf(Color.CYAN)
                txtAction.text = "Pedro is now done playing and is happy"

            }


            showGIFplay()
        }

        startProgressDeductionTimer()
    }

    private fun startProgressLoading(progressBar: ProgressBar) {
        val btnFeed = findViewById<Button>(R.id.btnFeed)
        val btnClean = findViewById<Button>(R.id.btnClean)
        val btnPlay = findViewById<Button>(R.id.btnPlay)


        val imgAction = findViewById<ImageView>(R.id.GifAction)
        // Disable button to prevent multiple clicks during loading
        val button: Button = when (progressBar) {
            progressBarFeed -> findViewById(R.id.btnFeed)
            progressBarClean -> findViewById(R.id.btnClean)
            progressBarPlay -> findViewById(R.id.btnPlay)
            else -> return
        }
        // button.isEnabled = false
        btnClean.isEnabled = false
        btnFeed.isEnabled = false
        btnPlay.isEnabled = false

        // Show "Loading..." message


        // Start progress loading after 5 seconds delay
        handler.postDelayed({
            incrementProgress(progressBar)

            btnFeed.isEnabled = true
            btnClean.isEnabled = true
            btnPlay.isEnabled = true
            showGIF()

            txtAction.text = "Pedro"
            txtAction.setTextSize(40.0F)
            txtAction.setTextColor(Color.CYAN)
        }, 5000)
    }

    private fun incrementProgress(progressBar: ProgressBar) {
        // Calculate 10% of the maximum progress value
        val increment = (progressBar.max * 0.1).toInt()


        val newProgress = progressBar.progress + increment


        progressBar.progress = if (newProgress <= progressBar.max) newProgress else progressBar.max


        if (progressBar.progress >= progressBar.max) {

            handler.postDelayed({

            }, 5000)
        }
    }

    private fun startProgressDeductionTimer() {
        handler.postDelayed(progressDeductionRunnable, 30000)
    }

    private val progressDeductionRunnable = object : Runnable {
        override fun run() {
            // Deduct 2% from each progress bar
            progressBarFeed.progress -= (progressBarFeed.max * 0.02).toInt()
            progressBarClean.progress -= (progressBarClean.max * 0.05).toInt()
            progressBarPlay.progress -= (progressBarPlay.max * 0.05).toInt()


            handler.postDelayed(this, 35000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove callbacks to avoid memory leaks
        handler.removeCallbacks(progressDeductionRunnable)
    }

    fun showGIF() {
        val gifSec: ImageView = findViewById(R.id.GifAction)

        Glide.with(this).load(R.drawable.second).into(gifSec)

    }

    fun showGIFfeed() {
        val gifFeed: ImageView = findViewById(R.id.GifAction)

        Glide.with(this).load(R.drawable.eat_1).into(gifFeed)

    }

    fun showGIFplay() {
        val gifPlay: ImageView = findViewById(R.id.GifAction)

        Glide.with(this).load(R.drawable.play).into(gifPlay)

    }

    fun showGIFbath() {
        val gifBath: ImageView = findViewById(R.id.GifAction)

        Glide.with(this).load(R.drawable.bath_1).into(gifBath)

    }
}

