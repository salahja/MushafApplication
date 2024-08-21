package org.sj.verbConjugation.trilateral.augmented.passive.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class AugmentedPastVerb7(
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
        return "ت" + ArabCharUtil.DAMMA + root!!.c1 + ArabCharUtil.DAMMA + "و" + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + lastDpa + connectedPronoun
    }
}