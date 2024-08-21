package org.sj.verbConjugation.trilateral.augmented.active.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb10(
    root:AugmentedTrilateralRoot,
    lastDpa: String?,
    connectedPronoun: String?
) : AugmentedPastVerb(root!!, lastDpa, connectedPronoun) {
    override fun form(): String {
        // return "�"+root!!.getC1()+ArabCharUtil.SKOON+root!!.getC2()+ArabCharUtil.FATHA+"�"+ArabCharUtil.SKOON+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpa+connectedPronoun;
        return "ا" + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.FATHA + "و" + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpa + connectedPronoun
    }
}