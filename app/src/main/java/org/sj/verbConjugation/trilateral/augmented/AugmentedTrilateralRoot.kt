package org.sj.verbConjugation.trilateral.augmented

import org.sj.verbConjugation.trilateral.TrilateralRoot
import org.sj.verbConjugation.util.AugmentationFormula

class AugmentedTrilateralRoot : TrilateralRoot {
    private val augmentations: MutableMap<String, AugmentationFormula?> = HashMap()
    private var c1 = 0.toChar()
    private var c2 = 0.toChar()
    private var c3 = 0.toChar()
    var form: String? = null
    var verbtype: String? = null
    var babname: String? = null
    fun addAugmentationFormula(formula: AugmentationFormula) {
        augmentations[formula.formulaNo.toString() + ""] = formula
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

    val augmentationList: Collection<AugmentationFormula?>
        get() = augmentations.values

    fun getAugmentationFormula(formulaNo: Int): AugmentationFormula? {
        return augmentations[formulaNo.toString() + ""]
    }
}