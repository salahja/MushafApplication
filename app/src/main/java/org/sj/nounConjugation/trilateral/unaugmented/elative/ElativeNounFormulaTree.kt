package org.sj.nounConjugation.trilateral.unaugmented.elative

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
class ElativeNounFormulaTree {
    private val formulas: MutableList<ElativeNounFormula> = LinkedList()
    fun addFormula(formula: ElativeNounFormula) {
        formulas.add(formula)
    }

    val formulaList: List<ElativeNounFormula>
        get() = formulas
}