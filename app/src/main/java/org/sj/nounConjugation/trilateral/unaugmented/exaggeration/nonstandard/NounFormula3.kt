package org.sj.nounConjugation.trilateral.unaugmented.exaggeration.nonstandard

import org.sj.nounConjugation.GenericNounSuffixContainer
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
class NounFormula3 : NonStandardExaggerationNounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root, suffixNo) {
        if (this.suffixNo == 7 && GenericNounSuffixContainer.getInstance().isInDefiniteMode) {
            suffix = "ً"
        }
    }

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        when (suffixNo) {
            1, 7, 13 -> return root.c1.toString() + ArabCharUtil.DAMMA + root.c2 + ArabCharUtil.FATHA + root.c3 + ArabCharUtil.FATHA + "ة" + suffix
        }
        return ""
    }

    override fun getFormulaName(): String {
        return "فُعَلَة"
    }

    override val symbol: String
        get() = "D"
}