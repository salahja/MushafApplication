package org.sj.verbConjugation.trilateral.augmented.imperative.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.imperative.AugmentedImperativeVerb
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedImperativeVerb8(
    root:AugmentedTrilateralRoot,
    lastDim: String?,
    connectedPronoun: String?
) : AugmentedImperativeVerb(root!!, lastDim, connectedPronoun) {
    override fun form(): String {
        //  return "�"+ArabCharUtil.FATHA+root!!.getC1()+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.SHADDA+ArabCharUtil.FATHA+root!!.getC3()+lastDim+connectedPronoun;
        return "ت" + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + root!!.c3 + lastDim + connectedPronoun
    }
}