package org.sj.nounConjugation.trilateral.unaugmented

import org.sj.nounConjugation.GenericNounSuffixContainer
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

class UnaugmentedTrilateralActiveParticiple(
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
        //   return GenericNounSuffixContainer.getInstance().getPrefix()+root!!.getC1()+ArabCharUtil.FATHA+ArabCharUtil.Aleph+root!!.getC2()+ArabCharUtil.KASRA+root!!.getC3()+suffix;
        val str: String
        str =
            GenericNounSuffixContainer.getInstance().prefix + root!!.c1 + ArabCharUtil.FATHA + ArabCharUtil.Aleph + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + suffix
        return str
    }

    override fun toString(): String {
        return form()
    }
}