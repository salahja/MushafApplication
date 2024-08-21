package org.sj.nounConjugation.trilateral.augmented

import org.sj.nounConjugation.GenericNounSuffixContainer
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
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
class AugmentedTrilateralActiveParticipleConjugator private constructor() {
    fun createNoun(
        root: AugmentedTrilateralRoot,
        suffixIndex: Int,
        formulaNo: Int
    ): AugmentedTrilateralNoun? {
        val suffix = GenericNounSuffixContainer.getInstance()[suffixIndex]
        val formulaClassName =
            javaClass.getPackage().name + ".activeparticiple." + "NounFormula" + formulaNo
        val parameters = arrayOf(root!!, suffix)
        try {
            return Class.forName(formulaClassName).constructors[0]
                .newInstance(*parameters) as AugmentedTrilateralNoun
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun createNounList(root: AugmentedTrilateralRoot, formulaNo: Int): List<*> {
        val result = LinkedList<AugmentedTrilateralNoun?>()
        for (i in 0..17) {
            val noun = createNoun(root!!, i, formulaNo)
            result.add(noun)
        }
        return result
    }

    companion object {
        val instance = AugmentedTrilateralActiveParticipleConjugator()
    }
}
