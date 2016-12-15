package de.mglaubitz.sillyracer

import android.app.Activity
import android.os.Bundle
import android.view.Window

class SillyRacerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SillyRacerView(this))
    }

}