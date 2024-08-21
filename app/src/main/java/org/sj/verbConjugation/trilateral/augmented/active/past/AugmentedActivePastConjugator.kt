package org.sj.verbConjugation.trilateral.augmented.active.past

import org.sj.verbConjugation.trilateral.augmented.AugmentedPastVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.AugmentationFormula
import org.sj.verbConjugation.util.PastConjugationDataContainer
import java.util.LinkedList

class AugmentedActivePastConjugator private constructor() {
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
        val result: MutableList<AugmentedPastVerb?> = LinkedList()
        for (i in 0..12) {
            val verb = createVerb(root!!, i, formulaNo)
            result.add(verb)
        }
        return result
    }

    fun createAllVerbList(root: AugmentedTrilateralRoot): Map<String, List<AugmentedPastVerb?>> {
        val result: MutableMap<String, List<AugmentedPastVerb?>> = HashMap()
        val iter: Iterator<*> = root!!.augmentationList.iterator()
        while (iter.hasNext()) {
            val formula = iter.next() as AugmentationFormula
            val formulaVerbList = createVerbList(root!!, formula.formulaNo)
            result[formula.formulaNo.toString() + ""] = formulaVerbList
        }
        return result
    }

    companion object {
        val instance = AugmentedActivePastConjugator()
    }
}