package org.sj.nounConjugation.trilateral.unaugmented.exaggeration.standard

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
class NounFormula1(root: UnaugmentedTrilateralRoot, suffixNo: String?) :
    NounFormula(root, suffixNo) {
    override fun form(): String {
        return root.c1.toString() + ArabCharUtil.FATHA + root.c2 + ArabCharUtil.SHADDA + ArabCharUtil.FATHA + ArabCharUtil.Aleph + root.c3 + suffix
    }

    override fun getFormulaName(): String {
        return "فَعَّال"
    }
}