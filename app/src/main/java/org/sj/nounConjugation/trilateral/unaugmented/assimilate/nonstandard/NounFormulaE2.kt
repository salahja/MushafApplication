package org.sj.nounConjugation.trilateral.unaugmented.assimilate.nonstandard

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
class NounFormulaE2 : NounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root!!, suffixNo) {}

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        when (suffixNo) {
            1, 3, 7, 9, 13, 15 -> return form2()
            2, 4, 8, 10, 14, 16 -> return form1()
        }
        return ""
    }

    //فَعْلَى
    fun form1(): String {
        suffix = AssimilateFormulaE2SuffixContainer.Companion.instance.get(suffixNo - 1)
            .replace(" ".toRegex(), "")
        return root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SKOON + root!!.c3 + suffix
    }

    //فَعْلان
    fun form2(): String {
        suffix = AssimilateFormulaE1SuffixContainer.Companion.instance.get(suffixNo - 1)
            .replace(" ".toRegex(), "")
        return root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.SKOON + root!!.c3 + ArabCharUtil.FATHA + "ان" + suffix
    }

    override fun getFormulaName(): String {
        return "فَعْلان / فَعْلَى"
    }

    override fun getNounSuffixContainer(): INounSuffixContainer {
        when (suffixNo) {
            1, 3, 7, 9, 13, 15 -> return AssimilateFormulaE1SuffixContainer.Companion.instance
        }
        return AssimilateFormulaE2SuffixContainer.Companion.instance
    }
}