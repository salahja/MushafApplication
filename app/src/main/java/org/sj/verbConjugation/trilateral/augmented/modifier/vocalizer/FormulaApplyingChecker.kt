package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot

class FormulaApplyingChecker private constructor() {
    var formula1ApplyingChecker = Formula1ApplyingChecker()
    var formula5ApplyingChecker = Formula5ApplyingChecker()
    var formula9ApplyingChecker = Formula9ApplyingChecker()
    fun check(root: AugmentedTrilateralRoot, formulaNo: Int): Int {
        when (formulaNo) {
            1 -> return formula1ApplyingChecker.check(root!!)
            5 -> return formula5ApplyingChecker.check(root!!)
            9 -> return formula9ApplyingChecker.check(root!!)
        }
        return IFormulaApplyingChecker.Companion.NO_THING
    }

    companion object {
        val instance = FormulaApplyingChecker()
    }
}