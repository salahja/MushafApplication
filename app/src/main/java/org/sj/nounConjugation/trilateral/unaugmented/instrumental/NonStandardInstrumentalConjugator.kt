package org.sj.nounConjugation.trilateral.unaugmented.instrumental

import org.sj.nounConjugation.IUnaugmentedTrilateralNounConjugator
import org.sj.nounConjugation.NounFormula
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil
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
//TODO CASTING
class NonStandardInstrumentalConjugator private constructor() :
    IUnaugmentedTrilateralNounConjugator {
    private val formulaClassNamesMap: MutableMap<String, Class<NonStandardInstrumentalNounFormula>> =
        HashMap()

    //map <symbol,formulaName>
    private val formulaSymbolsNamesMap: MutableMap<String?, String> = HashMap()

    init {
        for (i in 1..15) {
            val formulaClassName = javaClass.getPackage().name + ".nonstandard.NounFormula" + i
            try {
                val formulaClass =
                    Class.forName(formulaClassName) as Class<NonStandardInstrumentalNounFormula>
                val instrumentalNounFormula = formulaClass.newInstance()
                formulaClassNamesMap[instrumentalNounFormula.formulaName] = formulaClass
                formulaSymbolsNamesMap[instrumentalNounFormula.symbol] =
                    instrumentalNounFormula.formulaName
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
        //فقط للفعل المتعدي
        if (root!!.verbtype != ArabCharUtil.MEEM && root!!.verbtype != "ك") {
            return null
        }
        //todo
        //    XmlNonStandardInstrumentalNounFormulaTree formulaTree = DatabaseManager.getInstance().getInstrumentalNounFormulaTree(root!!.getC1());
      /*  val formulaTree = null ?: return null
        val result: MutableList<String?> = LinkedList()
        val iter: Iterator<XmlNonStandardInstrumentalNounFormula?> =
            formulaTree.formulaList.iterator()
        while (iter.hasNext()) {
            val formula = iter.next()
            if (formula.getC2() == root!!.c2 && formula.getC3() == root!!.c3) {
                if (formula.getForm1() != null && formula.getForm1() !== "") {
                    //add the formula pattern insteaed of the symbol (form1)
                    result.add(formulaSymbolsNamesMap[formula.getForm1()])
                }
                //may the verb has two forms of instumentals
                if (formula.getForm2() != null && formula.getForm2() !== "") {
                    //add the formula pattern insteaed of the symbol (form2)
                    result.add(formulaSymbolsNamesMap[formula.getForm2()])
                }
            }
        }
        return result*/
        return null
    }

    companion object {
        val instance = NonStandardInstrumentalConjugator()
    }
}