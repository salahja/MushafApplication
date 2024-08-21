package org.sj.verbConjugation.trilateral.unaugmented.modifier

import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.GenericGeminator
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.NGeminator
import org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.TGeminator

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: This is the facade for gemination subsystem
 * هو الصف المسؤول عن عملية الادغام بكل حالاتها
 * حيث يتخاطب مع الصفوف الموجودة في حزمة الادغام
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
class Geminator {
    //ادغام العام
    private val genericGeminator = GenericGeminator()

    //ادغام المنتهي بحرف النون
    private val nGeminator = NGeminator()

    //ادغام المنتهي بحرف التاء
    private val tGeminator = TGeminator()

    /**
     * تطبيق الادغام حسب الصيغة ماضي أو مضارع أو أمر للمعلوم أو لمجهول
     * قد لا يطبق أي نوع من الادغام
     *
     * @param tense      String
     * @param active     boolean
     * @param conjResult ConjugationResult
     */
    fun apply(tense: String, active: Boolean, conjResult: ConjugationResult) {
        if (genericGeminator.isApplied(conjResult))
            genericGeminator.apply(
            tense,
            active,
            conjResult
        )
        //else
        if (tGeminator.isApplied(conjResult))
            tGeminator.apply(tense, active, conjResult)
        //else
        if (nGeminator.isApplied(conjResult))
            nGeminator.apply(tense, active, conjResult)
    }
}