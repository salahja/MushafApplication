package org.sj.verbConjugation.trilateral.augmented.passive.present.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPresentVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPresentVerb3(
    root:AugmentedTrilateralRoot,
    cp: String?,
    lastDpr: String?,
    connectedPronoun: String?
) : AugmentedPresentVerb(root!!, cp, lastDpr, connectedPronoun) {
    override fun form(): String {
        //    return cp+ArabCharUtil.DAMMA+root!!.getC1()+ArabCharUtil.FATHA+"�"+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpr+connectedPronoun;
        return cp + ArabCharUtil.DAMMA + root!!.c1 + ArabCharUtil.FATHA + "ا" + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpr + connectedPronoun
    }
}