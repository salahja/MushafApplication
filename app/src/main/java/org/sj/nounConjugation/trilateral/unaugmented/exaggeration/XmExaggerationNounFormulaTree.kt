package org.sj.nounConjugation.trilateral.unaugmented.exaggeration

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
class XmExaggerationNounFormulaTree {
    private val formulas: MutableList<XmExaggerationNounFormula> = LinkedList()
    fun addFormula(formula: XmExaggerationNounFormula) {
        formulas.add(formula)
    }

    fun getFormulaList(): List<XmExaggerationNounFormula> {
        return formulas
    }
}
