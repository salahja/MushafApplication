package org.sj.nounConjugation.trilateral.unaugmented.timeandplace.nonstandard

import org.sj.nounConjugation.trilateral.unaugmented.timeandplace.NonStandardTimeAndPlaceNounFormula
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
class NounFormula2 : NonStandardTimeAndPlaceNounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root!!, suffixNo) {}

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        when (suffixNo) {
            1, 3, 7, 9, 13, 15 -> return ArabCharUtil.MEEM + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + suffix
        }
        return ""
    }

    override fun getFormulaName(): String {
        return "مَفْعِل"
    }

    override val symbol: String
        get() = "B"
}