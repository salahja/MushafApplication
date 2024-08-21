package org.sj.verbConjugation.trilateral.augmented.modifier

import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.FormulaApplyingChecker
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.IFormulaApplyingChecker
import org.sj.verbConjugation.util.VerbLamAlefModifier

class AugmentedTrilateralModifier private constructor() {
    private val substituter = Substituter()
    private val mazeedGeminator = MazeedGeminator()
    private val vocalizerAugmented = VocalizerAugmented()
    private val preVocalizer = PreVocalizer()
    private val sarfHamzaModifier = SarfHamzaModifier()
    fun build(
        root: AugmentedTrilateralRoot,
        kov: Int,
        formulaNo: Int,
        conjugations: List<*>,
        tense: String,
        active: Boolean,
        applyGemination: Boolean,
        listener: Boolean
    ): MazeedConjugationResult {
        val conjResult = MazeedConjugationResult(kov, formulaNo, root!!, conjugations)
        substituter.apply(tense, active, conjResult)
        if (applyGemination) {
            mazeedGeminator.apply(tense, active, conjResult)
        }
        var applyVocalization = true
        val result: Int = FormulaApplyingChecker.Companion.instance.check(root!!, formulaNo)
        if (result == IFormulaApplyingChecker.Companion.NOT_VOCALIZED) {
            applyVocalization = false
        } else if (result == IFormulaApplyingChecker.Companion.TWO_STATE) {
            //asking the listener to apply or not the vocaliztion
            //    applyVocalization = listener.doSelectVocalization();
            applyVocalization = listener
        }
        if (applyVocalization) {
            preVocalizer.apply(tense, active, conjResult)
            vocalizerAugmented.apply(tense, active, conjResult)
        }
        sarfHamzaModifier.apply(tense, active, conjResult)
        VerbLamAlefModifier.instance.apply(conjResult)
        val filterNotNull = conjResult.finalResult.filterNotNull()


        if(filterNotNull.size==5){


            conjResult.amrandnahi.anta= filterNotNull[0].toString()
            conjResult.amrandnahi.antuma= filterNotNull[2].toString()
            conjResult.amrandnahi.antum= filterNotNull[3].toString()
            conjResult.amrandnahi.anti= filterNotNull[1].toString()
            conjResult.amrandnahi.antumaf= filterNotNull[2].toString()
            conjResult.amrandnahi.antunna= filterNotNull[4].toString()

        }

        else  if( conjResult.finalResult.size==13) {
            val toString =  conjResult.finalResult.toString()
            val split = toString.split(",")




            conjResult.madhiMudharay.hua = split[0]
            conjResult.madhiMudharay.huma = split[1]
            conjResult.madhiMudharay.hum = split[2]
            conjResult.madhiMudharay.hia = split[3]
            conjResult.madhiMudharay.humaf = split[4]
            conjResult.madhiMudharay.hunna = split[5]
            conjResult.madhiMudharay.anta = split[6]
            conjResult.madhiMudharay.antuma = split[7]
            conjResult.madhiMudharay.antum = split[8]
            conjResult.madhiMudharay.anti = split[9]
            conjResult.madhiMudharay.antumaf = split[7]
            conjResult.madhiMudharay.antunna = split[10]
            conjResult.madhiMudharay.ana = split[11]
            conjResult.madhiMudharay.nahnu = split[12]









        }
        return conjResult
    }

    fun build(
        root: AugmentedTrilateralRoot,
        kov: Int,
        formulaNo: Int,
        conjugations: List<*>,
        tense: String,
        active: Boolean,
        listener: Boolean
    ): MazeedConjugationResult {
        return build(root!!, kov, formulaNo, conjugations, tense, active, true, listener)
    }

    companion object {
        val instance = AugmentedTrilateralModifier()
    }
}