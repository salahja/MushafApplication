package org.sj.verbConjugation.trilateral.augmented.imperative.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.imperative.AugmentedImperativeVerb
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedImperativeVerb10(
    root:AugmentedTrilateralRoot,
    lastDim: String?,
    connectedPronoun: String?
) : AugmentedImperativeVerb(root!!, lastDim, connectedPronoun) {
    override fun form(): String {
        //  return "�"+root!!.getC1()+ArabCharUtil.SKOON+root!!.getC2()+ArabCharUtil.FATHA+"�"+ArabCharUtil.SKOON+root!!.getC2()+ArabCharUtil.KASRA+root!!.getC3()+lastDim+connectedPronoun;
        return "ا" + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.FATHA + "و" + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + lastDim + connectedPronoun
    }
}