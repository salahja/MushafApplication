package org.sj.nounConjugation.trilateral.unaugmented.timeandplace

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
class TimeAndPlaceConjugator private constructor() : IUnaugmentedTrilateralNounConjugator {
    private val formulaClassNamesMap: MutableMap<String, Class<NonStandardTimeAndPlaceNounFormula>> =
        HashMap()

    //map <symbol,formulaName>
    private val formulaSymbolsNamesMap: MutableMap<String?, String> = HashMap()

    init {
        for (i in 1..3) {
            val formulaClassName = javaClass.getPackage().name + ".nonstandard.NounFormula" + i
            try {
                val formulaClass: Class<NonStandardTimeAndPlaceNounFormula> =
                    Class.forName(formulaClassName) as Class<NonStandardTimeAndPlaceNounFormula>
                val nounFormula = formulaClass.newInstance()
                formulaClassNamesMap[nounFormula.formulaName] = formulaClass
                formulaSymbolsNamesMap[nounFormula.symbol] = nounFormula.formulaName
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

    /**
     * إعادة الصيغ الممكنة للجذر
     *
     * @param root!! UnaugmentedTrilateralRoot
     * @return List
     */
    override fun getAppliedFormulaList(root: UnaugmentedTrilateralRoot): List<*>? {
        //todo
        //  XmlTimeAndPlaceNounFormulaTree formulaTree =  DatabaseManager.getInstance().getTimeAndPlaceNounFormulaTree(root!!.getC1());
    /*    val formulaTree = null ?: return null
        val result: MutableList<String?> = LinkedList()
        val iter: Iterator<XmlTimeAndPlaceNounFormula?> = formulaTree.formulaList.iterator()
        while (iter.hasNext()) {
            val formula = iter.next()
            if (formula.getNoc() == root!!.conjugation && formula.getC2() == root!!.c2 && formula.getC3() == root!!.c3) {
                if (formula.getForm1() != null && formula.getForm1() !== "") //add the formula pattern insteaed of the symbol (form1)
                    result.add(formulaSymbolsNamesMap[formula.getForm1()])
                //may the verb has two forms of instumentals
                if (formula.getForm2() != null && formula.getForm2() !== "") //add the formula pattern insteaed of the symbol (form2)
                    result.add(formulaSymbolsNamesMap[formula.getForm2()])
            }
        }
        return result*/
        return null
    }

    companion object {
        val instance = TimeAndPlaceConjugator()
    }
}