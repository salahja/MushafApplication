package org.sj.verbConjugation.trilateral.augmented.active.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb8(
    root:AugmentedTrilateralRoot,
    lastDpa: String?,
    connectedPronoun: String?
) : AugmentedPastVerb(root!!, lastDpa, connectedPronoun) {
    override fun form(): String {
        //  return "�"+ArabCharUtil.FATHA+root!!.getC1()+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.SHADDA+ArabCharUtil.FATHA+root!!.getC3()+lastDpa+connectedPronoun;
        return "ت" + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + root!!.c3 + lastDpa + connectedPronoun
    }
}