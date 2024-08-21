package com.example.mushafconsolidated.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mushafconsolidated.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment




 



 
 
 
  
 
 
 

 


class CustomBottomSheetDialogFragment constructor() : BottomSheetDialogFragment() {
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.bottom_app_player, container, false)
        return v
    }
}