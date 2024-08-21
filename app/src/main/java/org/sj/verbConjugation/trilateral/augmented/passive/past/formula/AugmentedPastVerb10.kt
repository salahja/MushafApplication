package org.sj.verbConjugation.trilateral.augmented.passive.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb10(
    root:AugmentedTrilateralRoot,
    lastDpa: String?,
    connectedPronoun: String?
) : AugmentedPastVerb(root!!, lastDpa, connectedPronoun) {
    override fun form(): String {
        return "ا" + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.DAMMA + "و" + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + lastDpa + connectedPronoun
    }
}