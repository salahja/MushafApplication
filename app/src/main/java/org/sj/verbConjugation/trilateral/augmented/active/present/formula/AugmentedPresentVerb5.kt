package org.sj.verbConjugation.trilateral.augmented.active.present.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPresentVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPresentVerb5(
    root:AugmentedTrilateralRoot,
    cp: String?,
    lastDpr: String?,
    connectedPronoun: String?
) : AugmentedPresentVerb(root!!, cp, lastDpr, connectedPronoun) {
    /**
     * form
     *
     * @return String
     * @todo Implement this sarf.trilingual.augmented.present.AugmentedPresentVerb
     * method
     */
    override fun form(): String {
        var s = ""
        //  return "�"+root!!.getC1()+ArabCharUtil.SKOON+"�"+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpa+connectedPronoun;
        val c1 = root!!.c1
        s = if (c1.toString() == "ء") {
            cp + "ت" + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + lastDpr + connectedPronoun
        } else {

            //  return cp+ArabCharUtil.FATHA+root!!.getC1()+ArabCharUtil.SKOON+"�"+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.KASRA+root!!.getC3()+lastDpr+connectedPronoun;
            cp + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.SKOON + "ت" + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + lastDpr + connectedPronoun
        }
        return s
    }
}