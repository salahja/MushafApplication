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
class AugmentedTrilateralPassiveParticipleConjugator private constructor() {
    fun createNoun(
        root: AugmentedTrilateralRoot,
        suffixIndex: Int,
        formulaNo: Int
    ): AugmentedTrilateralNoun? {
        val suffix = GenericNounSuffixContainer.getInstance()[suffixIndex]
        val formulaClassName =
            javaClass.getPackage().name + ".passiveparticiple." + "NounFormula" + formulaNo
        val parameters = arrayOf(root!!, suffix)
        try {
            return Class.forName(formulaClassName).constructors[0]
                .newInstance(*parameters) as AugmentedTrilateralNoun
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun createNounList(
        root: AugmentedTrilateralRoot,
        formulaNo: Int
    ): List<AugmentedTrilateralNoun?> {
        val result: MutableList<AugmentedTrilateralNoun?> = LinkedList()
        for (i in 0..17) {
            val noun = createNoun(root!!, i, formulaNo)
            result.add(noun)
        }
        return result
    }

    //������ �� ��� ������ ������� ������� ������
    private fun createNounList(
        root: AugmentedTrilateralRoot,
        formulaNo: Int,
        indecies: List<String>
    ): List<Any?> {
        val result: MutableList<Any?> = LinkedList()
        for (i in 0..17) {
            result.add("")
        }
        for (i in indecies.indices) {
            val index = indecies[i].toInt()
            val noun = createNoun(root!!, index, formulaNo)
            result[index] = noun
        }
        return result
    }

    fun createTimeAndPlaceNounList(root: AugmentedTrilateralRoot, formulaNo: Int): List<Any?> {
        return createNounList(root!!, formulaNo, timeAndPlaceIndeciesList)
    }

    fun createMeemGerundNounList(root: AugmentedTrilateralRoot, formulaNo: Int): List<Any?> {
        return createNounList(root!!, formulaNo, meemGerundIndeciesList)
    }

    companion object {
        val instance = AugmentedTrilateralPassiveParticipleConjugator()
        var timeAndPlaceIndeciesList: MutableList<String> = LinkedList()
        var meemGerundIndeciesList: MutableList<String> = LinkedList()

        init {
            //��� ������ ������
            timeAndPlaceIndeciesList.add("0")
            timeAndPlaceIndeciesList.add("2")
            timeAndPlaceIndeciesList.add("6")
            timeAndPlaceIndeciesList.add("8")
            timeAndPlaceIndeciesList.add("12")
            timeAndPlaceIndeciesList.add("14")
        }

        init {
            //������ ������
            meemGerundIndeciesList.add("0")
            meemGerundIndeciesList.add("6")
            meemGerundIndeciesList.add("12")
        }
    }
}
