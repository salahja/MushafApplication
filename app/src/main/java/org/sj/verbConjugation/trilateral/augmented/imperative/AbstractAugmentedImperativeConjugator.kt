package org.sj.verbConjugation.trilateral.augmented.imperative

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.util.AugmentationFormula
import java.util.LinkedList

class AbstractAugmentedImperativeConjugator(
    private val lastDimList: List<*>,
    private val connectedPronounList: List<*>
) {
    fun createVerb(
        root: AugmentedTrilateralRoot,
        pronounIndex: Int,
        formulaNo: Int
    ): AugmentedImperativeVerb? {
        val lastDim = lastDimList[pronounIndex] as String
        val connectedPronoun = connectedPronounList[pronounIndex] as String
        val formulaClassName =
            javaClass.getPackage().name + ".formula." + "AugmentedImperativeVerb" + formulaNo
        val parameters = arrayOf(root!!, lastDim, connectedPronoun)
        try {
            return Class.forName(formulaClassName).constructors[0]
                .newInstance(*parameters) as AugmentedImperativeVerb
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    fun createVerbList(
        root: AugmentedTrilateralRoot,
        formulaNo: Int
    ): List<AugmentedImperativeVerb?> {
        val result: MutableList<AugmentedImperativeVerb?> = LinkedList()
        result.add(null)
        result.add(null)
        //that indexing because the pronouns is existed only for that indecis
        for (i in 2..6) {
            val verb = createVerb(root!!, i, formulaNo)
            result.add(verb)
        }
        result.add(null)
        result.add(null)
        result.add(null)
        result.add(null)
        result.add(null)
        result.add(null)
        return result
    }

    fun createAllVerbList(root: AugmentedTrilateralRoot): Map<String, List<AugmentedImperativeVerb?>> {
        val result: MutableMap<String, List<AugmentedImperativeVerb?>> = HashMap()
        val iter: Iterator<*> = root!!.augmentationList.iterator()
        while (iter.hasNext()) {
            val formula = iter.next() as AugmentationFormula
            val formulaVerbList = createVerbList(root!!, formula.formulaNo)
            result[formula.formulaNo.toString() + ""] = formulaVerbList
        }
        return result
    }
}