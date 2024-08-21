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
class NounFormula4(root: UnaugmentedTrilateralRoot, suffix: String?) : NounFormula(root!!, suffix) {
    override fun form(): String {
        return if (suffixNo % 2 == 0) root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + ArabCharUtil.Aleph + root!!.c3 + suffix else ""
    }

    override fun getFormulaName(): String {
        return "فَعَّالَة"
    }
}