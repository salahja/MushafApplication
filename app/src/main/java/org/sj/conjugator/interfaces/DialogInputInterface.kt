package org.sj.conjugator.interfaces

import android.view.View

interface DialogInputInterface {
    // onBuildDialog() is called when the dialog   is ready to accept a view to insert
    // into the dialog
    fun onBuildDialog(): View?

    // onCancel() is called when the user clicks on 'Cancel'
    fun onCancel()

    // onResult(View v) is called when the user clicks on 'Ok'
    fun onResult(v: View?)
}