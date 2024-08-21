package org.sj.nounConjugation.trilateral.unaugmented.elative

import org.sj.nounConjugation.IUnaugmentedTrilateralNounConjugator
import org.sj.nounConjugation.NounFormula
import org.sj.nounConjugation.trilateral.unaugmented.elative.nonstandard.GenericElativeNounFormula
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
class ElativeNounConjugator private constructor() : IUnaugmentedTrilateralNounConjugator {
    override fun createNounList(root: UnaugmentedTrilateralRoot, formulaName: String): List<*> {
        val result: MutableList<NounFormula> = LinkedList()
        for (i in 0..17) {
            val noun: NounFormula = GenericElativeNounFormula(root, i.toString() + "")
            result.add(noun)
        }
        return result
    }

    override fun getAppliedFormulaList(root: UnaugmentedTrilateralRoot): List<*>? {
        //todo
  /*      val formulaTree = null
            ?: return null //  DatabaseManager.getInstance().getElativeNounFormulaTree(root.getC1());
        val iter: Iterator<ElativeNounFormula?> = formulaTree.formulaList.iterator()
        while (iter.hasNext()) {
            val formula = iter.next()
            if (formula.getC2() == root.c2 && formula.getC3() == root.c3) {
                return formulas
            }
        }
        return LinkedList<Any?>()*/
        return null;
    }

    companion object {
        val instance = ElativeNounConjugator()
        var formulas: MutableList<String> = ArrayList(1)

        init {
            formulas.add("أَفْعَل")
        }
    }
}