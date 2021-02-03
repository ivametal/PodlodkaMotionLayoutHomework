package com.example.podlodkamotionlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import kotlin.math.abs

class MainActivity : AppCompatActivity(), MotionLayout.TransitionListener {

    private var fragment : Fragment? = null
    private var lastProgress = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout)
        if (savedInstanceState == null) {
            fragment = FirstFragment().also {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it)
                    .commitNow()
            }
        }
        motionLayout.setTransitionListener(this)
    }

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        println("On transition started")
    }

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        if (p3 - lastProgress > 0) {
            val atEnd = abs(p3 - 1f) < 0.1f
            if (atEnd && fragment is FirstFragment) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction
                    .setCustomAnimations(R.animator.show, 0)
                fragment = SecondFragment().also {
                    transaction
                        .setCustomAnimations(R.animator.show, 0)
                        .replace(R.id.container, it)
                        .commitNow()
                }
            }
        } else {
            val atStart = p3 < 0.9f
            if (atStart && fragment is SecondFragment) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction
                    .setCustomAnimations(0, R.animator.hide)
                fragment = FirstFragment().also {
                    transaction
                        .replace(R.id.container, it)
                        .commitNow()
                }
            }
        }
        lastProgress = p3
    }

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
        println("On transition complete")
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
        println("On transition trigger")
    }
}