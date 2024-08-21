package org.sj.nounConjugation.trilateral.unaugmented.assimilate.nonstandard

import AssimilateFormulaE1SuffixContainer
import org.sj.nounConjugation.INounSuffixContainer
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
class NounFormulaE1 : NounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String) {
        this.root = root!!
        this.suffixNo = suffixNo.toInt() + 1
        suffix = AssimilateFormulaE1SuffixContainer.Companion.instance.get(this.suffixNo - 1)
            .replace(" ".toRegex(), "")
    }

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        when (suffixNo) {
            1, 3, 7, 9, 13, 15, 2, 4, 8, 10, 14, 16 -> return root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SKOON + root!!.c3 + ArabCharUtil.FATHA + "ان" + suffix
        }
        return ""
    }

    override fun getFormulaName(): String {
        return "فَعْلان / فَعْلانة"
    }

    override fun getNounSuffixContainer(): INounSuffixContainer {
        return AssimilateFormulaE1SuffixContainer.Companion.instance
    }
}