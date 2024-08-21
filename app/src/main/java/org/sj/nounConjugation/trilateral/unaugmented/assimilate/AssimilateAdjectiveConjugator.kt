package org.sj.nounConjugation.trilateral.unaugmented.assimilate

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

//TOTDO POSSIBLE WRONG CAST
class AssimilateAdjectiveConjugator private constructor() :
    IUnaugmentedTrilateralNounConjugator {
    private val formulaNamesMap: MutableMap<String, String> = HashMap()
    private val formulaIDsMap: MutableMap<String, String> = HashMap()

    init {
        loadFormulaName("A")
        loadFormulaName("B")
        loadFormulaName("C")
        loadFormulaName("D")
        //تم تفريق هذه الصيغة إلى صيغتين
        loadFormulaName("E1")
        loadFormulaName("E2")
    }

    private fun loadFormulaName(formulaID: String) {
        val formulaClassName = javaClass.getPackage().name + ".nonstandard.NounFormula" + formulaID
        try {
            val formulaClass = Class.forName(formulaClassName) as Class<NounFormula>
            val formulaName = formulaClass.newInstance().formulaName
            formulaNamesMap[formulaID] = formulaName
            formulaIDsMap[formulaName] = formulaID
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun createNoun(
        root: UnaugmentedTrilateralRoot,
        suffixNo: Int,
        formulaID: String?
    ): NounFormula? {
        val parameters = arrayOf(root!!, suffixNo.toString() + "")
        try {
            val formulaClassName =
                javaClass.getPackage().name + ".nonstandard.NounFormula" + formulaID
            val formulaClass = Class.forName(formulaClassName)
            return formulaClass.constructors[1].newInstance(*parameters) as NounFormula
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    override fun createNounList(root: UnaugmentedTrilateralRoot, formulaName: String): List<*> {
        val formulaID = formulaIDsMap[formulaName]
        val result: MutableList<NounFormula?> = LinkedList()
        for (i in 0..17) {
            val noun = createNoun(root!!, i, formulaID)
            result.add(noun)
        }
        return result
    }

    private fun addAdjectiveResult(result: MutableList<String?>, adj: String?) {
        if (adj == null || adj.length == 0) return
        if (adj == "E") {
            result.add(formulaNamesMap["E1"])
            result.add(formulaNamesMap["E2"])
        } else result.add(formulaNamesMap[adj])
    }

    override fun getAppliedFormulaList(root: UnaugmentedTrilateralRoot): List<*>? {
        //    AssimilateAdjectiveFormulaTree formulaTree = DatabaseManager.getInstance().getAssimilateAdjectiveFormulaTree(root!!.getC1());
        //   AssimilateAdjectiveFormulaTree formulaTree = DatabaseManager.getInstance().getAssimilateAdjectiveFormulaTree(root!!.getC1());
        val formulaTree = null
            ?: return null //= DatabaseManager.getInstance().getAssimilateAdjectiveFormulaTree(root!!.getC1());
        val result: MutableList<String?> = LinkedList()
      //  val iter = formulaTree.formulaList.iterator()
       /**/
        /*while (iter.hasNext()) {
            val formula = iter.next()
            if (formula.conjugation == root!!.conjugation && formula.c2 == root!!.c2 && formula.c3 == root!!.c3) {
                addAdjectiveResult(result, formula.adj1)
                addAdjectiveResult(result, formula.adj2)
                addAdjectiveResult(result, formula.adj3)
            }
        }*/
        return result
    }

    companion object {
        val instance = AssimilateAdjectiveConjugator()
    }
}
