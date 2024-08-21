package org.sj.verbConjugation.trilateral.augmented.imperative.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.imperative.AugmentedImperativeVerb
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedImperativeVerb7(
    root:AugmentedTrilateralRoot,
    lastDim: String?,
    connectedPronoun: String?
) : AugmentedImperativeVerb(root!!, lastDim, connectedPronoun) {
    /**
     * form
     *
     * @return String
     * @todo Implement this sarf.trilingual.augmented.imperative.AugmentedImperativeVerb
     * method
     */
    override fun form(): String {
        //  return "�"+ArabCharUtil.FATHA+root!!.getC1()+ArabCharUtil.FATHA+"�"+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDim+connectedPronoun;
        return "ت" + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.FATHA + "ا" + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDim + connectedPronoun
    }
}