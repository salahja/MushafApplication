package com.example.mushafconsolidatedimport

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.mushafconsolidated.R
import kotlin.math.hypot

class SearchView constructor(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private val open_search_button: View
    private val close_search_button: View
    private val search_input_text: TextView
    private val search_open_view: RelativeLayout

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.m_search_view, this, true)
        open_search_button = rootView.findViewById(R.id.open_search_button)
        close_search_button = rootView.findViewById(R.id.close_search_button)
        search_input_text = rootView.findViewById(R.id.search_input_text)
        search_open_view = rootView.findViewById(R.id.search_open_view)
        open_search_button.setOnClickListener { openSearch() }
        close_search_button.setOnClickListener { closeSearch() }
    }

    private fun closeSearch() {
        val cx: Int = search_open_view.width / 2
        val cy: Int = search_open_view.height / 2

        // get the initial radius for the clipping circle
        val initialRadius: Float = hypot(cx.toDouble(), cy.toDouble()).toFloat()

        // create the animation (the final radius is zero)
        val anim: Animator = ViewAnimationUtils.createCircularReveal(
            search_open_view, cx, cy, initialRadius, 0f
        )

        // make the view invisible when the animation is done
        anim.addListener(object : AnimatorListenerAdapter() {
        })

        // start the animation
        anim.start()
        /* Animator circularConceal = ViewAnimationUtils.createCircularReveal(
                search_open_view,
                (open_search_button.getRight() + open_search_button.getLeft()) / 2,
                (open_search_button.getTop() + open_search_button.getBottom()) / 2,
                0f, open_search_button.getWidth() );
        circularConceal.setDuration(300)  ;
        circularConceal.start();
        circularConceal.addListener(new Animator.AnimatorListener() {

         //   override fun onAnimationRepeat(animation: Animator?) = Unit
         //   override fun onAnimationCancel(animation: Animator?) = Unit
         //   override fun onAnimationStart(animation: Animator?) = Unit
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                search_open_view.setVisibility(INVISIBLE);
                search_input_text.setText("");

                circularConceal.removeAllListeners();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Animator Unit;
            //    Animator animator=Unit;

            }
        });*/
    }

    private fun openSearch() {
        search_input_text.text = ""
        search_open_view.visibility = VISIBLE
        val circularReveal: Animator = ViewAnimationUtils.createCircularReveal(
            search_open_view,
            (open_search_button.right + open_search_button.left) / 2,
            (open_search_button.top + open_search_button.bottom) / 2,
            0f, open_search_button.width.toFloat()
        )
        circularReveal.duration = 300
        circularReveal.start()
    }
}