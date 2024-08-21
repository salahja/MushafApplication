package org.sj.nounConjugation.trilateral.unaugmented.exaggeration

import org.sj.nounConjugation.IUnaugmentedTrilateralNounConjugator
import org.sj.nounConjugation.NounFormula
import org.sj.nounConjugation.trilateral.unaugmented.exaggeration.standard.NounFormula1
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
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
class StandardExaggerationConjugator private constructor() : IUnaugmentedTrilateralNounConjugator {
    override fun createNounList(root: UnaugmentedTrilateralRoot, formulaName: String): List<*> {
        val result: MutableList<NounFormula> = LinkedList()
        for (i in 0..17) {
            val noun: NounFormula = NounFormula1(root!!, i.toString() + "")
            result.add(noun)
        }
        return result
    }

    override fun getAppliedFormulaList(root: UnaugmentedTrilateralRoot): List<*> {
        return formulas
    }

    companion object {
        val instance = StandardExaggerationConjugator()
        var formulas: MutableList<String> = ArrayList(1)

        init {
            formulas.add("فَعَّال")
        }
    }
}