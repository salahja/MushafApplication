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
class NounFormula13 : NonStandardInstrumentalNounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root, suffixNo) {}

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        when (suffixNo) {
            2, 4, 8, 10, 14, 16 -> return "أ" + ArabCharUtil.DAMMA + root.c1 + ArabCharUtil.SKOON + root.c2 + ArabCharUtil.DAMMA + ArabCharUtil.WAW + root.c3 + suffix
        }
        return ""
    }

    override fun getFormulaName(): String {
        return "أُفْعُولَة"
    }

    override val symbol: String
        get() = "Q"
}