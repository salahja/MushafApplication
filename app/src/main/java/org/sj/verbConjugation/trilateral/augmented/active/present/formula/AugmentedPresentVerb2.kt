package org.sj.verbConjugation.trilateral.augmented.active.present.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPresentVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPresentVerb2(
    root:AugmentedTrilateralRoot,
    cp: String?,
    lastDpr: String?,
    connectedPronoun: String?
) : AugmentedPresentVerb(root!!, cp, lastDpr, connectedPronoun) {
    override fun form(): String {
        return cp + ArabCharUtil.DAMMA + root!!.c1 + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SHADDA + ArabCharUtil.KASRA + root!!.c3 + lastDpr + connectedPronoun
    }
}