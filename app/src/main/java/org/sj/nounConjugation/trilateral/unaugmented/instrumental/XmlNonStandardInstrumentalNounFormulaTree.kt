package org.sj.nounConjugation.trilateral.unaugmented.instrumental

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
class XmlNonStandardInstrumentalNounFormulaTree {
    private val formulas: MutableList<XmlNonStandardInstrumentalNounFormula> = LinkedList()
    fun addFormula(formula: XmlNonStandardInstrumentalNounFormula) {
        formulas.add(formula)
    }

    val formulaList: List<XmlNonStandardInstrumentalNounFormula>
        get() = formulas
}