package org.sj.verbConjugation.trilateral.augmented.active.present.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPresentVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPresentVerb9(
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
        //  return cp+ArabCharUtil.FATHA+"�"+ArabCharUtil.SKOON+ "�"+ArabCharUtil.FATHA+root!!.getC1()+ArabCharUtil.SKOON+root!!.getC2()+ArabCharUtil.KASRA+root!!.getC3()+lastDpr+connectedPronoun;
        return cp + ArabCharUtil.FATHA + "س" + ArabCharUtil.SKOON + "ت" + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + lastDpr + connectedPronoun
    }
}