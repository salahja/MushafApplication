package org.sj.nounConjugation.trilateral.unaugmented.assimilate.nonstandard

import org.sj.nounConjugation.INounSuffixContainer
import org.sj.nounConjugation.NounFormula
import org.sj.nounConjugation.trilateral.unaugmented.assimilate.AssimilateFormulaCSuffixContainer
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
class NounFormulaC : NounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String) {
        this.root = root!!
        this.suffixNo = suffixNo.toInt() + 1
        suffix = AssimilateFormulaCSuffixContainer.Companion.instance.get(this.suffixNo - 1)
            .replace(" ".toRegex(), "")
    }

    //to be used in refection getting the formula name
    constructor() {}

    override fun getNounSuffixContainer(): INounSuffixContainer {
        return AssimilateFormulaCSuffixContainer.Companion.instance
    }

    override fun form(): String {
        when (suffixNo) {
            1, 3, 7, 9, 13, 15 -> return "أ" + ArabCharUtil.FATHA + root!!.c1 + ArabCharUtil.SKOON + root!!.c2 + ArabCharUtil.FATHA + root!!.c3 + suffix
            2, 4, 8, 10, 14, 16 -> return root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SKOON + root!!.c3 + suffix
            5, 6, 11, 12, 17, 18 -> return root!!.c1.toString() + ArabCharUtil.DAMMA + root!!.c2 + ArabCharUtil.SKOON + root!!.c3 + suffix
        }
        return ""
    }

    override fun getFormulaName(): String {
        return "أَفْعَل"
    }
}