package org.sj.nounConjugation.trilateral.unaugmented

import org.sj.nounConjugation.GenericNounSuffixContainer
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class UnaugmentedTrilateralPassiveParticiple(
    private val root: UnaugmentedTrilateralRoot,
    private val suffix: String
) {
    /**
     * form
     *
     * @return String
     * @todo Implement this org.sj.noun.Trilateral.TrilateralNoun method
     */
    fun form(): String {
        return GenericNounSuffixContainer.getInstance().prefix + ArabCharUtil.MEEM + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.DAMMA + ArabCharUtil.WAW + root!!.c3 + suffix
    }

    override fun toString(): String {
        return form()
    }
}