package org.sj.verbConjugation.trilateral.unaugmented.modifier

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractLamMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.ActivePastMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.ActivePresentMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.ImperativeMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.PassivePastMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.PassivePresentMahmouz
import org.sj.verbConjugation.util.SystemConstants

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: يطبق خصيصاً لفعل أثأ لمعالجة حالة مهموز الفاء واللام
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
class PostHamzaModifier {
    private val modifiersMap: HashMap<String, AbstractLamMahmouz> = HashMap()

    init {
        //خمس أنواع  أساسية  للمهموز للمعلوم والمبني لمجهول في الماضي والمضارع والأمر
        modifiersMap[SystemConstants.PAST_TENSE + "true"] =
            ActivePastMahmouz()
        modifiersMap[SystemConstants.PRESENT_TENSE + "true"] =
            ActivePresentMahmouz()
        modifiersMap[SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            ImperativeMahmouz()
        modifiersMap[SystemConstants.EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            ImperativeMahmouz()
        modifiersMap[SystemConstants.PAST_TENSE + "false"] =
            PassivePastMahmouz()
        modifiersMap[SystemConstants.PRESENT_TENSE + "false"] =
            PassivePresentMahmouz()
    }

    /**
     * تطبيق تغيير  الهمزة حسب الصيغة ماضي أو مضارع أو أمر للمعلوم أو لمجهول
     * قد لا يطبق أي نوع
     *
     * @param tense      String
     * @param active     boolean
     * @param conjResult ConjugationResult
     */
    fun apply(tense: String, active: Boolean, conjResult: ConjugationResult) {
        if (conjResult.kov != 4) return
        val modifier: IUnaugmentedTrilateralModifier? = modifiersMap[tense + active]
        if (modifier!!.isApplied(conjResult)) {
            (modifier as SubstitutionsApplier?)!!.apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
        }
    }
}