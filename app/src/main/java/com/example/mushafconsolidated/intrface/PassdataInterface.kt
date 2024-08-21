package com.example.mushafconsolidated.intrfaceimport


open interface PassdataInterface {
    fun ondatarecevied(
        chapterno: Int,
        partname: String?,
        totalverses: Int,
        rukucount: Int,
        makkimadani: Int
    )
}