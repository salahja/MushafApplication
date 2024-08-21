package org.sj.nounConjugation.trilateral.unaugmented.instrumental.nonstandard

import org.sj.nounConjugation.trilateral.unaugmented.instrumental.NonStandardInstrumentalNounFormula
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
class NounFormula12 : NonStandardInstrumentalNounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root, suffixNo) {}

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        return if (suffixNo % 2 == 0) root.c1.toString() + ArabCharUtil.DAMMA + root.c2 + ArabCharUtil.FATHA + ArabCharUtil.Aleph + root.c3 + suffix else ""
    }

    override fun getFormulaName(): String {
        return "فُعَالَة"
    }

    override val symbol: String
        get() = "P"
}