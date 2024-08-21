package org.sj.verbConjugation.trilateral.augmented.active.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb5(
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
        var s = ""
        //  return "�"+root!!.getC1()+ArabCharUtil.SKOON+"�"+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpa+connectedPronoun;
        val c1 = root!!.c1
        s = if (c1.toString() == "ء") {
            "اِ" + "ت" + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpa + connectedPronoun
        } else {
            "ا" + root!!.c1 + ArabCharUtil.SKOON + "ت" + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpa + connectedPronoun
        }
        return s
    }
}