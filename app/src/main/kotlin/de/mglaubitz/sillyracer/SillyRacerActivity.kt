package de.mglaubitz.sillyracer

import android.app.Activity
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class SillyRacerActivity : Activity() {

    private var contentView: SillyRacerView? = null

    private var timerHandle: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SillyRacerView(this).apply {
            contentView = this
        })
    }

    override fun onStop() {
        super.onStop()

        timerHandle?.dispose()
    }

    override fun onStart() {
        super.onStart()

        // 25 fps
        timerHandle = Observable.interval(DateUtils.SECOND_IN_MILLIS / 35, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                contentView?.requestRender()
            }


    }
}