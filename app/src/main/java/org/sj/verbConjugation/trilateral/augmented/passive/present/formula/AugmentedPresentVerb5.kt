package org.sj.verbConjugation.trilateral.augmented.passive.present.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPresentVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPresentVerb5(
    root:AugmentedTrilateralRoot,
    cp: String?,
    lastDpr: String?,
    connectedPronoun: String?
) : AugmentedPresentVerb(root!!, cp, lastDpr, connectedPronoun) {
    override fun form(): String {
        // return cp+ArabCharUtil.DAMMA+root!!.getC1()+ArabCharUtil.SKOON+"�"+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpr+connectedPronoun;
        var s = ""
        //  return "�"+root!!.getC1()+ArabCharUtil.SKOON+"�"+ArabCharUtil.FATHA+root!!.getC2()+ArabCharUtil.FATHA+root!!.getC3()+lastDpa+connectedPronoun;
        val c1 = root!!.c1
        s = if (c1.toString() == "ء") {
            cp + ArabCharUtil.DAMMA + "ت" + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpr + connectedPronoun
        } else {
            cp + ArabCharUtil.DAMMA + root!!.c1 + ArabCharUtil.SKOON + "ت" + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + lastDpr + connectedPronoun
        }
        return s
    }
}