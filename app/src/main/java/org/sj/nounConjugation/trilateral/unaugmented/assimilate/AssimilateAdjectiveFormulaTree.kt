package org.sj.nounConjugation.trilateral.unaugmented.assimilate

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
class AssimilateAdjectiveFormulaTree {
    private val formulas: MutableList<AssimilateAdjectiveFormula> = LinkedList()
    fun addFormula(formula: AssimilateAdjectiveFormula) {
        formulas.add(formula)
    }

    val formulaList: List<AssimilateAdjectiveFormula>
        get() = formulas
}