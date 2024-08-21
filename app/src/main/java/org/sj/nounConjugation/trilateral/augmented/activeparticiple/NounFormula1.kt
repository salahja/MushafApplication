package org.sj.nounConjugation.trilateral.augmented.activeparticiple

import org.sj.nounConjugation.trilateral.augmented.AugmentedTrilateralNoun
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class NounFormula1(root: AugmentedTrilateralRoot, suffix: String?) :
    AugmentedTrilateralNoun(root!!, suffix) {
    override fun form(): String {
        return ArabCharUtil.MEEM + ArabCharUtil.DAMMA + root!!!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + suffix
    }
}