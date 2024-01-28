package com.example.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var mediaPlayer: MediaPlayer

    var totaltime: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        mediaPlayer.setVolume(2f, 1f)
        totaltime = mediaPlayer.duration


        val play = findViewById<ImageView>(R.id.playView)
        val pause = findViewById<ImageView>(R.id.pauseView2)
        val stop = findViewById<ImageView>(R.id.stopView3)
        val seekbar = findViewById<SeekBar>(R.id.seekbar)

        play.setOnClickListener {
            mediaPlayer.start()
        }
        pause.setOnClickListener {
            mediaPlayer.pause()
        }
        stop.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer.release();
            mediaPlayer.release()
        }
        seekbar.max = totaltime
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mediaPlayer.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {


                    seekbar.progress = mediaPlayer.currentPosition
                    handler.postDelayed(this, 1000)
                }catch(e:java.lang.Exception){
                    seekbar.progress=0
                }
            }

        }, 0)

    }
}