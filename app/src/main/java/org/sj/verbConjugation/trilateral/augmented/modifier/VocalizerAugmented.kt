package org.sj.verbConjugation.trilateral.augmented.modifier

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active.Imperative1Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active.Imperative2Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active.Past1Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active.Past2Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active.Present1Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active.Present2Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Imperative4Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Imperative5Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Imperative6Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Past3Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present4Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present5Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present6Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present7Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present8Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present9Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.passive.WawiPastVocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.passive.YaeiPastVocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.mithal.WawiVocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.mithal.YaeiVocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active.Imperative3Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active.PastVocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active.Present3Vocalizer
import org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive.PresentVocalizer
import org.sj.verbConjugation.util.SystemConstants
import java.util.LinkedList

class VocalizerAugmented {
    private val vocalizerMap: MutableMap<String, List<IAugmentedTrilateralModifier>> = HashMap()

    init {
        val activePastList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val passivePastList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val activePresentList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val passivePresentList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val imperativeList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        vocalizerMap[SystemConstants.PAST_TENSE + "true"] = activePastList
        vocalizerMap[SystemConstants.PRESENT_TENSE + "true"] = activePresentList
        vocalizerMap[SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE + "true"] = imperativeList
        vocalizerMap[SystemConstants.EMPHASIZED_IMPERATIVE_TENSE + "true"] = imperativeList
        vocalizerMap[SystemConstants.PAST_TENSE + "false"] = passivePastList
        vocalizerMap[SystemConstants.PRESENT_TENSE + "false"] = passivePresentList
        activePastList.add(Past1Vocalizer())
        activePastList.add(Past2Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active.Past1Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active.Past2Vocalizer())
        activePastList.add(PastVocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active.PastVocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Past1Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Past2Vocalizer())
        activePastList.add(Past3Vocalizer())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active.PastVocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.passive.PastVocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.passive.PastVocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive.Past1Vocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive.Past2Vocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive.Past3Vocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.passive.PastVocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive.Past1Vocalizer())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive.Past2Vocalizer())
        passivePastList.add(WawiPastVocalizer())
        passivePastList.add(YaeiPastVocalizer())
        passivePastList.add(YaeiVocalizer())
        passivePastList.add(WawiVocalizer())
        activePresentList.add(Present1Vocalizer())
        activePresentList.add(Present2Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active.Present1Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active.Present2Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active.Present1Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active.Present2Vocalizer())
        activePresentList.add(Present3Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active.Present1Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active.Present2Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active.Present3Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present1Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present2Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Present3Vocalizer())
        activePresentList.add(Present4Vocalizer())
        activePresentList.add(Present5Vocalizer())
        activePresentList.add(Present6Vocalizer())
        activePresentList.add(Present7Vocalizer())
        activePresentList.add(Present8Vocalizer())
        activePresentList.add(Present9Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active.Present1Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active.Present2Vocalizer())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active.Present3Vocalizer())
        activePresentList.add(YaeiVocalizer())
        activePresentList.add(WawiVocalizer())

        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.passive.Present1Vocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.passive.Present2Vocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.passive.Present1Vocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.passive.Present2Vocalizer())
        passivePresentList.add(PresentVocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.passive.PresentVocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive.Present1Vocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive.Present2Vocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive.Present3Vocalizer())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.passive.PresentVocalizer())
        passivePresentList.add(YaeiVocalizer())
        passivePresentList.add(WawiVocalizer())
        imperativeList.add(Imperative1Vocalizer())
        imperativeList.add(Imperative2Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active.Imperative1Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.yaei.active.Imperative2Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active.Imperative1Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.active.Imperative2Vocalizer())
        imperativeList.add(Imperative3Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active.Imperative1Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active.Imperative2Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.yaei.active.Imperative3Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Imperative1Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Imperative2Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active.Imperative3Vocalizer())
        imperativeList.add(Imperative4Vocalizer())
        imperativeList.add(Imperative5Vocalizer())
        imperativeList.add(Imperative6Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active.Imperative1Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active.Imperative2Vocalizer())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.separated.active.Imperative3Vocalizer())
    }

    fun apply(tense: String, active: Boolean, conjResult: MazeedConjugationResult) {
        val vocalizers = vocalizerMap[tense + active]!!
        val iter = vocalizers.iterator()
        while (iter.hasNext()) {
            val vocalizer = iter.next()
            if (vocalizer.isApplied(conjResult)) {
                (vocalizer as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}