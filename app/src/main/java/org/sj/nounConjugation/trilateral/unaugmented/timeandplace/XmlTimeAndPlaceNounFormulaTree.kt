package org.sj.nounConjugation.trilateral.unaugmented.timeandplace

import java.util.LinkedList

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
class XmlTimeAndPlaceNounFormulaTree {
    private val formulas: MutableList<XmlTimeAndPlaceNounFormula> = LinkedList()
    fun addFormula(formula: XmlTimeAndPlaceNounFormula) {
        formulas.add(formula)
    }

    val formulaList: List<XmlTimeAndPlaceNounFormula>
        get() = formulas
}