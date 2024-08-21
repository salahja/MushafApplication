package org.sj.nounConjugation.trilateral.unaugmented.assimilate.nonstandard

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
class NounFormulaB : NounFormula {
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root!!, suffixNo) {}

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        return root!!.c1.toString() + ArabCharUtil.FATHA + root!!.c2 + ArabCharUtil.KASRA + root!!.c3 + suffix
    }

    override fun getFormulaName(): String {
        return "فَعِل"
    }
}