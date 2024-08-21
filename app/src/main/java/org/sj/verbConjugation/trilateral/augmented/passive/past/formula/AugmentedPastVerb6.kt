package org.sj.verbConjugation.trilateral.augmented.passive.past.formula

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

/**
 * Title: Sarf
 *
 *
 * Description: ������ �������
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company:
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class AugmentedPastVerb6(
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
        return "ا" + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.DAMMA + root!!.c3 + ArabCharUtil.KASRA + root!!.c3 + lastDpa + connectedPronoun
    }
}