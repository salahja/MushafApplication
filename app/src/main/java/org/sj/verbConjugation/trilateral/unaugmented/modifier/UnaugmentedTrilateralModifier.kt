package org.sj.verbConjugation.trilateral.unaugmented.modifier

import HamzaModifier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.VerbLamAlefModifier

/**
 *
 * Title: Sarf Program
 * المعالجة
 *
 * Description: يقوم بفحص واجراء التعديلات المناسبة على الأفعال الثلاثية المجردة
 * بما فيها الاعلال والابدال والهمزة
 * حسب الصيغة ماضي أو مضارع او أمر
 *
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
class UnaugmentedTrilateralModifier private constructor() {
    private val geminator = Geminator()
    private val vocalizer = Vocalizer()
    private val hamzaModifier = HamzaModifier()
    private val postHamzaModifier = PostHamzaModifier()
  


  
    /**
     * اخراج قائمة الأفعال بعد التعديلات
     * البدء بالادغام
     *
     * @param root!!         UnaugmentedTrilateralRoot
     * @param kov          int
     * @param conjugations List
     * @param tense        String (From SystemConstans class the values are stored)  ماضي أو مضارع او أمر
     * @return ConjugationResult
     */
    @JvmOverloads
    fun build(
        root: UnaugmentedTrilateralRoot,
        kov: Int,
        conjugations: MutableList<*>,
        tense: String,
        active: Boolean,
        applyGemination: Boolean = true
    ): ConjugationResult {
        val conjResult = ConjugationResult(kov, root!!, conjugations  )
        if (applyGemination) geminator.apply(tense, active, conjResult)
        vocalizer.apply(tense, active, conjResult)
        hamzaModifier.apply(tense, active, conjResult)
        //خصيصاُ للفعل أثأ
        postHamzaModifier.apply(tense, active, conjResult)

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

    companion object {
        val instance = UnaugmentedTrilateralModifier()
    }
}