package org.sj.nounConjugation.trilateral.unaugmented.elative.nonstandard

import org.sj.nounConjugation.INounSuffixContainer
import org.sj.nounConjugation.NounFormula
import org.sj.nounConjugation.trilateral.unaugmented.elative.ElativeSuffixContainer
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot

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
class GenericElativeNounFormula : NounFormula {
    private var appliedNounFormula: NounFormula? = null

    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String) {
        appliedNounFormula =
            if ((suffixNo.toInt() + 1) % 2 == 0 && (ElativeSuffixContainer.Companion.instance
                    .isDefinite || ElativeSuffixContainer.Companion.instance.isAnnexed)
            ) {
                NounFormula2(
                    root!!,
                    suffixNo
                )
            } else {
                NounFormula1(
                    root!!,
                    suffixNo
                )
            }
    }

    //to be used in refection getting the formula name
    constructor() {}

    override fun form(): String {
        return appliedNounFormula!!.form()
    }

    override fun getFormulaName(): String {
        return "أَفْعَل"
    }

    override fun getNounSuffixContainer(): INounSuffixContainer {
        return ElativeSuffixContainer.Companion.instance
    }
}