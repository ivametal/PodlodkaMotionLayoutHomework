package com.example.podlodkamotionlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.motion.widget.MotionHelper


class AnimateOut : MotionHelper {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setProgress(view: View, progress: Float) {
        view.rotation = (1f - progress) * 360
    }
}