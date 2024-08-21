package org.sj.verbConjugation.trilateral.augmented.active.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb4(
    root:AugmentedTrilateralRoot,
    lastDpa: String?,
    connectedPronoun: String?
) : AugmentedPastVerb(root!!, lastDpa, connectedPronoun) {
    override fun form(): String {
        //   return "��"+ArabCharUtil.SKOON+ root!!.getC1()+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpa+connectedPronoun;
        return "ان" + ArabCharUtil.SKOON + root!!.c1 + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpa + connectedPronoun
    }
}