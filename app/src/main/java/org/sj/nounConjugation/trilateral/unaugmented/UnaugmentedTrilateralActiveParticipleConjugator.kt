package org.sj.nounConjugation.trilateral.unaugmented

import org.sj.nounConjugation.GenericNounSuffixContainer
import org.sj.nounConjugation.IUnaugmentedTrilateralNounConjugator
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: ����� ��� ������ ������� ������
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
class UnaugmentedTrilateralActiveParticipleConjugator private constructor() :
    IUnaugmentedTrilateralNounConjugator {
    fun createNoun(
        root: UnaugmentedTrilateralRoot,
        suffixIndex: Int
    ): UnaugmentedTrilateralActiveParticiple {
        val suffix = GenericNounSuffixContainer.getInstance()[suffixIndex]
        return UnaugmentedTrilateralActiveParticiple(root!!, suffix!!)
    }

    override fun createNounList(root: UnaugmentedTrilateralRoot, formulaName: String): List<*> {
        val result: MutableList<UnaugmentedTrilateralActiveParticiple> = ArrayList(18)
        for (i in 0..17) result.add(createNoun(root!!, i))
        return result
    }

    override fun getAppliedFormulaList(root: UnaugmentedTrilateralRoot): List<*>? {
        return null
    }

    companion object {
        val instance = UnaugmentedTrilateralActiveParticipleConjugator()
    }
}