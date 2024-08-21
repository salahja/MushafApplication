package org.sj.nounConjugation.trilateral.unaugmented.instrumental.standard

import org.sj.nounConjugation.NounFormula
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
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
class NounFormula2(root: UnaugmentedTrilateralRoot, suffix: String?) : NounFormula(root!!, suffix) {
    override fun form(): String {
        when (suffixNo) {
            2, 4, 8, 10, 14, 16 -> return ArabCharUtil.MEEM + ArabCharUtil.KASRA + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + suffix
        }
        return ""
    }

    override fun getFormulaName(): String {
        return "مِفْعَلَة"
    }
}