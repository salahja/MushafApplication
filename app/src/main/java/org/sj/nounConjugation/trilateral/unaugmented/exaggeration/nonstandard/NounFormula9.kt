package org.sj.nounConjugation.trilateral.unaugmented.exaggeration.nonstandard

import org.sj.nounConjugation.trilateral.unaugmented.exaggeration.NonStandardExaggerationNounFormula
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
class NounFormula9 : NonStandardExaggerationNounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root, suffixNo) {}

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        return ArabCharUtil.MEEM + ArabCharUtil.KASRA + root.c1 + ArabCharUtil.SKOON + root.c2 + ArabCharUtil.KASRA + ArabCharUtil.YA + root.c3 + suffix
    }

    override fun getFormulaName(): String {
        return "مِفْعِيل"
    }

    override val symbol: String
        get() = "K"
}