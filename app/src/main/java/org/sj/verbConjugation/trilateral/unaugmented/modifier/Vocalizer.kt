package org.sj.verbConjugation.trilateral.unaugmented.modifier

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.ActivePastAjwafWawiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.ActivePastAjwafWawiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.ActivePresentAjwafWawiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.ActivePresentAjwafWawiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.ImperativeAjwafWawiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.ImperativeAjwafWawiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.PassivePastAjwafWawiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.PassivePastAjwafWawiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.PassivePresentAjwafWawiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi.PassivePresentAjwafWawiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.ActivePastAjwafYaeiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.ActivePastAjwafYaeiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.ActivePresentAjwafYaeiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.ActivePresentAjwafYaeiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.ImperativeAjwafYaeiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.ImperativeAjwafYaeiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.PassivePastAjwafYaeiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.PassivePastAjwafYaeiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.PassivePresentAjwafYaeiListedVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei.PassivePresentAjwafYaeiVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected.ActivePast1Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected.ActivePast2Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected.ActivePresentVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected.PassivePastVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected.PassivePresentVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.ActivePresent1Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.ActivePresent2Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.ActivePresent3Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.Imperative1Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.Imperative2Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.Imperative3Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.WawiPassivePresentVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.YaeiPassivePresentVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal.ActivePrenentVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active.ImperativeVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active.Past1Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active.Past2Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active.Past3Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active.Past4Vocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.active.PresentVocalizer
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.passive.PastVocalizer
import org.sj.verbConjugation.util.SystemConstants
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
class Vocalizer {
    //المعلوم  و  المجهول تحتوي قائمة بالأنواع الخمسة لاعلال لماضي والمضارع والأمر حسب
    private val vocalizerMap: MutableMap<String, List<SubstitutionsApplier>> = HashMap()

    init {
        val activePastList: MutableList<SubstitutionsApplier> = LinkedList()
        val passivePastList: MutableList<SubstitutionsApplier> = LinkedList()
        val activePresentList: MutableList<SubstitutionsApplier> = LinkedList()
        val passivePresentList: MutableList<SubstitutionsApplier> = LinkedList()
        val imperativeList: MutableList<SubstitutionsApplier> = LinkedList()

        //خمس أنواع  أساسية  للاعلال للمعلوم والمبني لمجهول في الماضي والمضارع والأمر
        vocalizerMap[SystemConstants.PAST_TENSE + "true"] = activePastList
        vocalizerMap[SystemConstants.PRESENT_TENSE + "true"] =
            activePresentList
        vocalizerMap[SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            imperativeList
        vocalizerMap[SystemConstants.EMPHASIZED_IMPERATIVE_TENSE + "true"] = imperativeList
        vocalizerMap[SystemConstants.PAST_TENSE + "false"] = passivePastList
        vocalizerMap[SystemConstants.PRESENT_TENSE + "false"] = passivePresentList

        //قائمة الماضي المبني لمعلوم
        activePastList.add(ActivePastAjwafWawiListedVocalizer())
        activePastList.add(ActivePastAjwafWawiVocalizer())
        activePastList.add(ActivePastAjwafYaeiListedVocalizer())
        activePastList.add(ActivePastAjwafYaeiVocalizer())
        activePastList.add(Past1Vocalizer())
        activePastList.add(Past2Vocalizer())
        activePastList.add(Past3Vocalizer())
        activePastList.add(Past4Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.active.Past1Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.active.Past2Vocalizer())
        activePastList.add(ActivePast1Vocalizer())
        activePastList.add(ActivePast2Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.ActivePast1Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.ActivePast2Vocalizer())

        //قائمة الماضي المبني لمجهول
        passivePastList.add(PassivePastAjwafWawiListedVocalizer())
        passivePastList.add(PassivePastAjwafWawiVocalizer())
        passivePastList.add(PassivePastAjwafYaeiListedVocalizer())
        passivePastList.add(PassivePastAjwafYaeiVocalizer())
        passivePastList.add(PastVocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.passive.PastVocalizer())
        passivePastList.add(PassivePastVocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.separeted.PassivePastVocalizer())

        //قائمة المضارع المبني لمعلوم
        activePresentList.add(ActivePresentAjwafWawiListedVocalizer())
        activePresentList.add(ActivePresentAjwafWawiVocalizer())
        activePresentList.add(ActivePresentAjwafYaeiListedVocalizer())
        activePresentList.add(ActivePresentAjwafYaeiVocalizer())
        activePresentList.add(PresentVocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.active.PresentVocalizer())
        activePresentList.add(ActivePresentVocalizer())
        activePresentList.add(ActivePresent1Vocalizer())
        activePresentList.add(ActivePresent2Vocalizer())
        activePresentList.add(ActivePresent3Vocalizer())
        activePresentList.add(ActivePrenentVocalizer())

        //قائمة المضارع المبني لمجهول
        passivePresentList.add(PassivePresentAjwafWawiListedVocalizer())
        passivePresentList.add(PassivePresentAjwafWawiVocalizer())
        passivePresentList.add(PassivePresentAjwafYaeiListedVocalizer())
        passivePresentList.add(PassivePresentAjwafYaeiVocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.wawi.passive.PresentVocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.passive.PresentVocalizer())
        passivePresentList.add(PassivePresentVocalizer())
        passivePresentList.add(WawiPassivePresentVocalizer())
        passivePresentList.add(YaeiPassivePresentVocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal.WawiPassivePresentVocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal.YaeiPassivePresentVocalizer())

        //قائمة الأمر
        imperativeList.add(ImperativeAjwafWawiListedVocalizer())
        imperativeList.add(ImperativeAjwafWawiVocalizer())
        imperativeList.add(ImperativeAjwafYaeiListedVocalizer())
        imperativeList.add(ImperativeAjwafYaeiVocalizer())
        imperativeList.add(ImperativeVocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.nakes.yaei.active.ImperativeVocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.lafif.connected.ImperativeVocalizer())
        imperativeList.add(Imperative1Vocalizer())
        imperativeList.add(Imperative2Vocalizer())
        imperativeList.add(Imperative3Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal.Imperative1Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal.Imperative2Vocalizer())

        //        imperativeList.add(new org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal.Imperative2Vocalizer());


    }

    /**
     * تطبيق الاعلال حسب الصيغة ماضي أو مضارع أو أمر للمعلوم أو لمجهول
     * قد لا يطبق أي نوع من الاعلال
     *
     * @param tense      String
     * @param active     boolean
     * @param conjResult ConjugationResult
     */
    fun apply(tense: String, active: Boolean, conjResult: ConjugationResult) {
        val vocalizers = vocalizerMap[tense + active]!!
        val iter = vocalizers.iterator()
        while (iter.hasNext()) {
            val vocalizer = iter.next() as IUnaugmentedTrilateralModifier
            if (vocalizer.isApplied(conjResult)) {
                (vocalizer as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }

            
        }
    }
}