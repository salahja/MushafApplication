package org.sj.nounConjugation.trilateral.unaugmented.exaggeration

import org.sj.nounConjugation.IUnaugmentedTrilateralNounConjugator
import org.sj.nounConjugation.NounFormula
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
@Suppress("UNREACHABLE_CODE")
class NonStandardExaggerationConjugator private constructor() :
    IUnaugmentedTrilateralNounConjugator {
    private val formulaClassNamesMap: MutableMap<String, Class<NonStandardExaggerationNounFormula>> =
        HashMap()

    //map <symbol,formulaName>
    private val formulaSymbolsNamesMap: MutableMap<String?, String> = HashMap()

    init {
        for (i in 1..10) {
            val formulaClassName = javaClass.getPackage().name + ".nonstandard.NounFormula" + i
            try {
                val formulaClass: Class<NonStandardExaggerationNounFormula> =
                    Class.forName(formulaClassName) as Class<NonStandardExaggerationNounFormula>
                val nonStandardExaggerationNounFormula = formulaClass.newInstance()
                formulaClassNamesMap[nonStandardExaggerationNounFormula.formulaName] = formulaClass
                formulaSymbolsNamesMap[nonStandardExaggerationNounFormula.symbol] =
                    nonStandardExaggerationNounFormula.formulaName
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun createNoun(
        root: UnaugmentedTrilateralRoot,
        suffixNo: Int,
        formulaName: String
    ): NounFormula? {
        val parameters = arrayOf(root!!, suffixNo.toString() + "")
        try {
            val formulaClass =
                formulaClassNamesMap[formulaName]!!
            return formulaClass.constructors[1].newInstance(*parameters) as NounFormula
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    override fun createNounList(root: UnaugmentedTrilateralRoot, formulaName: String): List<*> {
        val result: MutableList<NounFormula?> = LinkedList()
        for (i in 0..17) {
            val noun = createNoun(root!!, i, formulaName)
            result.add(noun)
        }
        return result
    }

    override fun getAppliedFormulaList(root: UnaugmentedTrilateralRoot): List<*>? {
        //todo xml
        //  XmExaggerationNounFormulaTree formulaTree =  DatabaseManager.getInstance().getExaggerationNounFormulaTree(root!!.getC1());
      /*  val formulaTree = null ?: return null
        val result: MutableList<String?> = LinkedList()
        val iter: Iterator<XmExaggerationNounFormula> =XmExaggerationNounFormulaTree.getFormulaList().iterator()

        while (iter.hasNext()) {
            val formula = iter.next()
            if (formula.c2 == root!!.c2 && formula.c3() == root!!.c3) {
                if (formula.form1 != null && formula.form1 !== "") //add the formula pattern insteaed of the symbol (form1)
                    result.add(formulaSymbolsNamesMap[formula.form1])
                //may the verb has two forms of instumentals
                if (formula.form2    != null && formula.form2() !== "") //add the formula pattern insteaed of the symbol (form2)
                    result.add(formulaSymbolsNamesMap[formula.form2])
                //may the verb has two forms of instumentals
                if (formula.form3 != null && formula.form3 !== "") //add the formula pattern insteaed of the symbol (form3)
                    result.add(formulaSymbolsNamesMap[formula.form3])
            }
        }
        return result*/
        return null
    }

    companion object {
        val instance = NonStandardExaggerationConjugator()
    }
}