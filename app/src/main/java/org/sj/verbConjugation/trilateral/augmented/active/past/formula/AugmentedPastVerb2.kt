package org.sj.verbConjugation.trilateral.augmented.active.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb2(
    root:AugmentedTrilateralRoot,
    lastDpa: String?,
    connectedPronoun: String?
) : AugmentedPastVerb(root!!, lastDpa, connectedPronoun) {
    override fun form(): String {
        ////System.out.println(s);
        return root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + root!!.c3 + lastDpa + connectedPronoun
    }
}