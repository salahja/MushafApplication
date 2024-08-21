package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot

abstract class IFormulaApplyingChecker {
    fun check(root: AugmentedTrilateralRoot): Int {
        val rootString = root!!.c1.toString() + "" + root!!.c2 + "" + root!!.c3
        if (notVocalizedList.contains(rootString)) return NOT_VOCALIZED
        return if (twoStateList.contains(rootString)) TWO_STATE else NO_THING
    }

    abstract val notVocalizedList: List<String>
    abstract val twoStateList: List<String>

    companion object {
        const val TWO_STATE = 1
        const val NOT_VOCALIZED = 2
        const val NO_THING = 0
    }
}