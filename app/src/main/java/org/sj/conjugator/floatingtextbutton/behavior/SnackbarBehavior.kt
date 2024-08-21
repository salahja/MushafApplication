package org.sj.conjugator.floatingtextbutton.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Interpolator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class SnackbarBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<FloatingTextButton>(context, attrs) {
    private var animation: ViewPropertyAnimatorCompat? = null
    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: FloatingTextButton,
        dependency: View
    ): Boolean {
        return dependency is SnackbarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: FloatingTextButton,
        dependency: View
    ): Boolean {
        if (child.translationY > 0) {
            return true
        }
        if (animation != null) {
            animation!!.cancel()
            animation = null
        }
        child.translationY = Math.min(0f, dependency.translationY - dependency.height)
        return true
    }

    override fun onDependentViewRemoved(
        parent: CoordinatorLayout,
        child: FloatingTextButton,
        dependency: View
    ) {
        if (dependency is SnackbarLayout) {
            animation = ViewCompat.animate(child)
                .translationY(0f)
                .setInterpolator(HIDE_INTERPOLATOR)
                .setDuration(HIDE_DURATION)
            animation!!.start()
        }
        super.onDependentViewRemoved(parent, child, dependency)
    }

    companion object {
        private val HIDE_INTERPOLATOR: Interpolator = FastOutSlowInInterpolator()
        private const val HIDE_DURATION = 250L
    }
}