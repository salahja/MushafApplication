package org.sj.verbConjugation.trilateral.augmented.modifier

import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active.ImperativeMahmouz
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active.PastMahmouz
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active.PresentMahmouz
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active.RaaImperativeMahmouz
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active.RaaPastMahmouz
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active.RaaPresentMahmouz
import org.sj.verbConjugation.util.SystemConstants
import java.util.LinkedList

class SarfHamzaModifier {
    private val modifiersMap: MutableMap<String, List<IAugmentedTrilateralModifier>> = HashMap()

    init {
        val activePastList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val passivePastList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val activePresentList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val passivePresentList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        val imperativeList: MutableList<IAugmentedTrilateralModifier> = LinkedList()
        modifiersMap[SystemConstants.PAST_TENSE + "true"] = activePastList
        modifiersMap[SystemConstants.PRESENT_TENSE + "true"] = activePresentList
        modifiersMap[SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            imperativeList
        modifiersMap[SystemConstants.EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            imperativeList
        modifiersMap[SystemConstants.PAST_TENSE + "false"] = passivePastList
        modifiersMap[SystemConstants.PRESENT_TENSE + "false"] = passivePresentList
        activePastList.add(RaaPastMahmouz())
        activePastList.add(PastMahmouz())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.active.PastMahmouz())
        activePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.active.PastMahmouz())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive.RaaPastMahmouz())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive.PastMahmouz())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.passive.PastMahmouz())
        passivePastList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.passive.PastMahmouz())
        activePresentList.add(RaaPresentMahmouz())
        activePresentList.add(PresentMahmouz())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.active.PresentMahmouz())
        activePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.active.PresentMahmouz())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive.RaaPresentMahmouz())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive.PresentMahmouz())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.passive.PresentMahmouz())
        passivePresentList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.passive.PresentMahmouz())
        imperativeList.add(RaaImperativeMahmouz())
        imperativeList.add(ImperativeMahmouz())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.active.ImperativeMahmouz())
        imperativeList.add(org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.active.ImperativeMahmouz())
    }

    /**
     * ����� �����  ������ ��� ������ ���� �� ����� �� ��� ������� �� ������
     * �� �� ���� �� ���
     *
     * @param tense      String
     * @param active     boolean
     * @param conjResult ConjugationResult
     */
    fun apply(tense: String, active: Boolean, conjResult: MazeedConjugationResult) {
        val modifiers = modifiersMap[tense + active]!!
        val iter = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next()
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}