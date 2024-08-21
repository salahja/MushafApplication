package org.sj.verbConjugation.trilateral.augmented.imperative.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.imperative.AugmentedImperativeVerb
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedImperativeVerb2(
    root:AugmentedTrilateralRoot,
    lastDim: String?,
    connectedPronoun: String?
) : AugmentedImperativeVerb(root!!, lastDim, connectedPronoun) {
    override fun form(): String {
        return root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SHADDA + ArabCharUtil.KASRA + root!!.c3 + lastDim + connectedPronoun
    }
}