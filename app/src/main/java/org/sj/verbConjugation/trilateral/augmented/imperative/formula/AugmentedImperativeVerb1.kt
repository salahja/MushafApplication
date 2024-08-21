package org.sj.verbConjugation.trilateral.augmented.imperative.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot


import org.sj.verbConjugation.trilateral.augmented.imperative.AugmentedImperativeVerb
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedImperativeVerb1(
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
        return "أ" + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 +
                lastDim + connectedPronoun
    }
}