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
class NounFormula1 : NounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String) {
        this.root = root
        this.suffixNo = suffixNo.toInt() + 1
        suffix = ElativeSuffixContainer.Companion.instance.get(this.suffixNo - 1)
            .replace(" ".toRegex(), "")
    }

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        return "أ" + ArabCharUtil.FATHA + root.c1 + ArabCharUtil.SKOON + root.c2 + ArabCharUtil.FATHA + root.c3 + suffix
    }

    override fun getFormulaName(): String {
        return "أَفْعَل"
    }

    override fun getNounSuffixContainer(): INounSuffixContainer {
        return ElativeSuffixContainer.Companion.instance
    }
}