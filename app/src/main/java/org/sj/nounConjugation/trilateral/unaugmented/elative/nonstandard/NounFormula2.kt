package org.sj.nounConjugation.trilateral.unaugmented.elative.nonstandard

import org.sj.nounConjugation.INounSuffixContainer
import org.sj.nounConjugation.NounFormula
import org.sj.nounConjugation.trilateral.unaugmented.elative.ElativeSuffixContainer
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
class NounFormula2 : NounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String) {
        this.root = root
        this.suffixNo = suffixNo.toInt() + 1
        suffix = ElativeSuffixContainer.Companion.instance.get(this.suffixNo - 1)
            .replace(" ".toRegex(), "")
    }

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        return root.c1.toString() + ArabCharUtil.DAMMA + root.c2 + ArabCharUtil.SKOON + root.c3 + suffix
    }

    override fun getFormulaName(): String {
        return "فُعْلَى"
    }

    override fun getNounSuffixContainer(): INounSuffixContainer {
        return ElativeSuffixContainer.Companion.instance
    }
}