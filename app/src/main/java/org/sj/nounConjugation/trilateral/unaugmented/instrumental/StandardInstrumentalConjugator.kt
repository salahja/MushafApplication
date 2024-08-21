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
class StandardInstrumentalConjugator private constructor() : IUnaugmentedTrilateralNounConjugator {
    fun createNoun(root: UnaugmentedTrilateralRoot, suffixNo: Int, formulaNo: Int): NounFormula? {
        val formulaClassName = javaClass.getPackage().name + ".standard.NounFormula" + formulaNo
        val parameters = arrayOf(root!!, suffixNo.toString() + "")
        try {
            return Class.forName(formulaClassName).constructors[0]
                .newInstance(*parameters) as NounFormula
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun createNounList(root: UnaugmentedTrilateralRoot, formulaNo: Int): List<NounFormula?> {
        val result: MutableList<NounFormula?> = LinkedList()
        for (i in 0..17) {
            val noun = createNoun(root!!, i, formulaNo)
            result.add(noun)
        }
        return result
    }

    override fun createNounList(root: UnaugmentedTrilateralRoot, formulaName: String): List<*> {
        return createNounList(root!!, formulas.indexOf(formulaName) + 1)
    }

    override fun getAppliedFormulaList(root: UnaugmentedTrilateralRoot): List<*> {
        //فقط للفعل المتعدي
        return if (root!!.verbtype == ArabCharUtil.MEEM || root!!.verbtype == "ك") formulas else LinkedList<Any?>()
    }

    companion object {
        val instance = StandardInstrumentalConjugator()
        private val formulas: MutableList<String> = LinkedList()

        init {
            formulas.add("مِفْعَل")
            formulas.add("مِفْعَلَة")
            formulas.add("مِفْعَال")
            formulas.add("فَعَّالَة")
        }
    }
}