package com.example.utility

import android.content.Context
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.mushafconsolidated.R

object AnimationUtility {
    /**
     * handles all subclasses of View : TextView, Button, ImageView etc..
     * given the component's id in their layout file
     */
    fun AnimateArrow(active: Float, myImageView: ImageView) {
        val todegrees = 90.0f
        //     ImageView myImageView = (ImageView) findViewById(R.id.dropDownIconView);
        val animSet = AnimationSet(true)
        animSet.interpolator = DecelerateInterpolator()
        animSet.fillAfter = true
        animSet.isFillEnabled = true
        val animRotate = RotateAnimation(
            0.0f, active,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        animRotate.duration = 500
        animRotate.fillAfter = true
        animSet.addAnimation(animRotate)
        myImageView.startAnimation(animSet)
    }

    fun runAlphaAnimation(act: AppCompatActivity, viewId: Int) {
        // load animation XML resource under res/anim
        val animation = AnimationUtils.loadAnimation(act, R.anim.fade)
            ?: return  // here, we don't care
        // reset initialization state
        animation.reset()
        // find View by its id attribute in the XML
        val v = act.findViewById<View>(viewId)
        // cancel any pending animation and start this one
        if (v != null) {
            v.clearAnimation()
            v.startAnimation(animation)
        }
    }

    fun slide_down(ctx: Context?, v: View?) {
        val a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down)
        if (a != null) {
            a.reset()
            if (v != null) {
                v.clearAnimation()
                v.startAnimation(a)
            }
        }
    }
}