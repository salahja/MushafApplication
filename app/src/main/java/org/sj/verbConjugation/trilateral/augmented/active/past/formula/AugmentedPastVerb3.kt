package org.sj.verbConjugation.trilateral.augmented.active.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb3(
    root:AugmentedTrilateralRoot,
    lastDpa: String?,
    connectedPronoun: String?
) : AugmentedPastVerb(root!!, lastDpa, connectedPronoun) {
    /**
     * form
     *
     * @return String
     * @todo Implement this sarf.trilingual.augmented.past.AugmentedPastVerb
     * method
     */
    override fun form(): String {
        //    return root!!.getC1()+ArabCharUtil.FATHA+"�"+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpa+connectedPronoun;
        return root!!.c1.toString() + ArabCharUtil.FATHA + "ا" + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpa + connectedPronoun
    }
}