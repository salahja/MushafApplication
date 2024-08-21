package org.sj.nounConjugation.trilateral.augmented.passiveparticiple

import org.sj.nounConjugation.trilateral.augmented.AugmentedTrilateralNoun
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class NounFormula10(root:AugmentedTrilateralRoot, suffix: String?) :
    AugmentedTrilateralNoun(root!!, suffix) {
    /**
     * form
     *
     * @return String
     * @todo Implement this sarf.noun.trilateral.TrilateralNoun method
     */
    override fun form(): String {
        return ArabCharUtil.MEEM + ArabCharUtil.DAMMA + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.FATHA + ArabCharUtil.WAW + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + suffix
    }
}