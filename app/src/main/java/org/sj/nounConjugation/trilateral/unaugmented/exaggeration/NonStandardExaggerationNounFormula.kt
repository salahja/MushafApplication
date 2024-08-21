package org.sj.nounConjugation.trilateral.unaugmented.exaggeration

import org.sj.nounConjugation.NounFormula
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
abstract class NonStandardExaggerationNounFormula : NounFormula {
    constructor() {}
    constructor(root: UnaugmentedTrilateralRoot, suffixNo: String?) : super(root!!, suffixNo) {}

    /**
     * Every non standard instrumental has a symbol to be represented by
     *
     * @return String
     */
    abstract val symbol: String
}