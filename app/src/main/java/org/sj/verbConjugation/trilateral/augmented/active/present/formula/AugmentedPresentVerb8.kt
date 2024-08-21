package org.sj.verbConjugation.trilateral.augmented.active.present.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPresentVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPresentVerb8(
    root:AugmentedTrilateralRoot,
    cp: String?,
    lastDpr: String?,
    connectedPronoun: String?
) : AugmentedPresentVerb(root!!, cp, lastDpr, connectedPronoun) {
    override fun form(): String {
        //  return cp+ArabCharUtil.FATHA+"�"+ArabCharUtil.FATHA+root!!.getC1()+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.SHADDA+ArabCharUtil.FATHA+root!!.getC3()+lastDpr+connectedPronoun;
        return cp + ArabCharUtil.FATHA + "ت" + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + root!!.c3 + lastDpr + connectedPronoun
    }
}