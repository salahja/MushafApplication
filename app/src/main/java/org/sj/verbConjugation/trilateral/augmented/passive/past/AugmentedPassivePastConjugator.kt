package org.sj.verbConjugation.trilateral.augmented.passive.past

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.PastConjugationDataContainer
import java.util.LinkedList

class AugmentedPassivePastConjugator private constructor() {
    fun createVerb(
        root: AugmentedTrilateralRoot,
        pronounIndex: Int,
        formulaNo: Int
    ): AugmentedPastVerb? {
        val lastDpa = PastConjugationDataContainer.getInstance().getLastDpa(pronounIndex)
        val connectedPronoun =
            PastConjugationDataContainer.getInstance().getConnectedPronoun(pronounIndex)
        val formulaClassName =
            javaClass.getPackage().name + ".formula." + "AugmentedPastVerb" + formulaNo
        val parameters = arrayOf(root!!, lastDpa, connectedPronoun)
        try {
            return Class.forName(formulaClassName).constructors[0]
                .newInstance(*parameters) as AugmentedPastVerb
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun createVerbList(root: AugmentedTrilateralRoot, formulaNo: Int): List<AugmentedPastVerb?> {
        val augmentationFormula = root!!.getAugmentationFormula(formulaNo)
        //todo correct
        return if (formulaNo == 20) {
            // if (augmentationFormula.getTransitive() == 'ل') {
            createLazzemVerbList(root!!, formulaNo)
        } else {
            val result: MutableList<AugmentedPastVerb?> =
                LinkedList()
            for (i in 0..12) {
                val verb = createVerb(root!!, i, formulaNo)
                result.add(verb)
            }
            result
        }
    }

    //������ ������ ������ ��� �� �� �� ��
    fun createLazzemVerbList(
        root: AugmentedTrilateralRoot,
        formulaNo: Int
    ): List<AugmentedPastVerb?> {
        val result: MutableList<AugmentedPastVerb?> = LinkedList()
        for (i in 0..12) {
            if (i == 7 || i == 8) {
                val verb = createVerb(root!!, i, formulaNo)
                result.add(verb)
            } else {
                result.add(null)
            }
        }
        return result
    }

    companion object {
        val instance = AugmentedPassivePastConjugator()
    }
}