
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.ActivePastMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.ActivePresentMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.ImperativeMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.PassivePastMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.PassivePresentMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.RaaImperativeMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.RaaPresentMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.SpecialEmphsizedImperativeMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein.SpecialImperativeMahmouz
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.SpecialEmphsizedImperativeMahmouz1
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.SpecialEmphsizedImperativeMahmouz2
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.SpecialImperativeMahmouz1
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.SpecialImperativeMahmouz2
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
class HamzaModifier {
    private val modifiersMap: MutableMap<String, List<SubstitutionsApplier>> = HashMap()

    init {
        val activePastList: LinkedList<SubstitutionsApplier> = LinkedList()
      //  val activePastList: MutableList<SubstitutionsApplier> = LinkedList()
        val passivePastList: LinkedList<SubstitutionsApplier> = LinkedList()
        val activePresentList: LinkedList<SubstitutionsApplier> = LinkedList()
        val passivePresentList: LinkedList<SubstitutionsApplier> = LinkedList()
        val imperativeList: LinkedList<SubstitutionsApplier> = LinkedList()
        val emphasizedImperativeList: LinkedList<SubstitutionsApplier> = LinkedList()

        //خمس أنواع  أساسية  للمهموز للمعلوم والمبني لمجهول في الماضي والمضارع والأمر
        modifiersMap[SystemConstants.PAST_TENSE + "true"] = activePastList
        modifiersMap[SystemConstants.PRESENT_TENSE + "true"] =
            activePresentList
        modifiersMap[SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE + "true"] = imperativeList
        modifiersMap[SystemConstants.EMPHASIZED_IMPERATIVE_TENSE + "true"] =
            emphasizedImperativeList
        modifiersMap[SystemConstants.PAST_TENSE + "false"] = passivePastList
        modifiersMap[SystemConstants.PRESENT_TENSE + "false"] = passivePresentList

        //قائمة الماضي المبني لمعلوم

        activePastList.add(ActivePastMahmouz())
        activePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.ActivePastMahmouz())
        activePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.ActivePastMahmouz())

        //قائمة الماضي المبني لمجهول
        passivePastList.add(PassivePastMahmouz())
        passivePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.PassivePastMahmouz())
        passivePastList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.PassivePastMahmouz())


        //قائمة المضارع المبني لمعلوم
        activePresentList.add(RaaPresentMahmouz())
        activePresentList.add(ActivePresentMahmouz())
        activePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.ActivePresentMahmouz())
        activePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.ActivePresentMahmouz())

        //قائمة المضارع المبني لمجهول
        passivePresentList.add(RaaPresentMahmouz())
        passivePresentList.add(PassivePresentMahmouz())
        passivePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.PassivePresentMahmouz())
        passivePresentList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.PassivePresentMahmouz())

        //قائمة الأمر
        //وضع الحالات الخاصة أولاً
        imperativeList.add(RaaImperativeMahmouz())
        imperativeList.add(SpecialImperativeMahmouz1())
        imperativeList.add(SpecialImperativeMahmouz2())
        imperativeList.add(SpecialImperativeMahmouz())
        imperativeList.add(ImperativeMahmouz())
        imperativeList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa.ImperativeMahmouz())
        imperativeList.add(org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam.ImperativeMahmouz())

        //قائمة الأمر المؤكد
        emphasizedImperativeList.add(imperativeList[0])
        emphasizedImperativeList.add(SpecialEmphsizedImperativeMahmouz1())
        emphasizedImperativeList.add(SpecialEmphsizedImperativeMahmouz2())
        emphasizedImperativeList.add(SpecialEmphsizedImperativeMahmouz())
        emphasizedImperativeList.add(imperativeList[4])
        emphasizedImperativeList.add(imperativeList[5])
        emphasizedImperativeList.add(imperativeList[6])
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
        val modifiers = modifiersMap[tense + active]!!
        val iter = modifiers.iterator()
        while (iter.hasNext()) {
            val modifier = iter.next() as IUnaugmentedTrilateralModifier
            if (modifier.isApplied(conjResult)) {
                (modifier as SubstitutionsApplier).apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
                break
            }
        }
    }
}