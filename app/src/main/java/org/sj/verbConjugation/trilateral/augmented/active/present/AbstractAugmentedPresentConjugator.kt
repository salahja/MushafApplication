package org.sj.verbConjugation.trilateral.augmented.active.present

import org.sj.verbConjugation.trilateral.augmented.AugmentedPresentVerb
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.AugmentationFormula
import org.sj.verbConjugation.util.PresentConjugationDataContainer
import java.util.LinkedList

class AbstractAugmentedPresentConjugator(
    private val lastDprList: List<*>,
    private val connectedPronounList: List<*>
) {
    fun createVerb(
        root: AugmentedTrilateralRoot,
        pronounIndex: Int,
        formulaNo: Int
    ): AugmentedPresentVerb? {
        val cp = PresentConjugationDataContainer.instance.getCp(pronounIndex)
        val lastDpr = lastDprList[pronounIndex] as String
        val connectedPronoun = connectedPronounList[pronounIndex] as String
        val formulaClassName =
            javaClass.getPackage().name + ".formula." + "AugmentedPresentVerb" + formulaNo
        val parameters = arrayOf(root!!, cp, lastDpr, connectedPronoun)
        try {
            return Class.forName(formulaClassName).constructors[0]
                .newInstance(*parameters) as AugmentedPresentVerb
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun createVerbList(root: AugmentedTrilateralRoot, formulaNo: Int): List<AugmentedPresentVerb?> {
        val result: MutableList<AugmentedPresentVerb?> = LinkedList()
        for (i in 0..12) {
            val verb = createVerb(root!!, i, formulaNo)
            result.add(verb)
        }
        return result
    }

    fun createAllVerbList(root: AugmentedTrilateralRoot): Map<String, List<AugmentedPresentVerb?>> {
        val result: MutableMap<String, List<AugmentedPresentVerb?>> = HashMap()
        val iter: Iterator<*> = root!!.augmentationList.iterator()
        while (iter.hasNext()) {
            val formula = iter.next() as AugmentationFormula
            val formulaVerbList = createVerbList(root!!, formula.formulaNo)
            result[formula.formulaNo.toString() + ""] = formulaVerbList
        }
        return result
    }
}