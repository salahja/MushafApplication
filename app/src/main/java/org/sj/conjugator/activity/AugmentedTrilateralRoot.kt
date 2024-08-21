package org.sj.conjugator.activity

import org.sj.verbConjugation.trilateral.TrilateralRoot

class AugmentedTrilateralRoot : TrilateralRoot {
    //قائمة صيغ المزيد الممكنة لهذا الجذر
    private val augmentations: Map<*, *> = HashMap<Any?, Any?>()
    private var c1 = 0.toChar()
    private var c2 = 0.toChar()
    private var c3 = 0.toChar()

    constructor() {}
    constructor(rootText: String) {
        val chars = rootText.toCharArray()
        c1 = chars[0]
        c2 = chars[1]
        c3 = chars[2]
    }

    override fun getC1(): Char {
        return c1
    }

    fun setC1(c1: Char) {
        this.c1 = c1
    }

    override fun getC2(): Char {
        return c2
    }

    fun setC2(c2: Char) {
        this.c2 = c2
    }

    override fun getC3(): Char {
        return c3
    }

    fun setC3(c3: Char) {
        this.c3 = c3
    }

    val augmentationList: Collection<*>
        get() = augmentations.values
}