package org.sj.verbConjugation.trilateral.unaugmented.active

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.PresentConjugationDataContainer
import java.util.LinkedList

/**
 *
 * Title: Sarf
 *
 *
 * Description: تصريف الأفعال في المضارع
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company:
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class ActivePresentConjugator private constructor() {
    /**
     * *إنشاء الفعل المضارع بغض النظر عن حالته الإعرابية
     *
     * @param pronounIndex         int
     * @param root                 UnaugmentedTrilateralRoot
     * @param lastDprList          List
     * @param connectedPronounList List
     * @return ActivePresentVerb
     */
    private fun createVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot,
        lastDprList: List<*>,
        connectedPronounList: List<*>
    ): ActivePresentVerb {
        val cp = PresentConjugationDataContainer.Companion.instance.getCp(pronounIndex)
        val dpr2 = PresentConjugationDataContainer.instance.getDpr2(root)
        val lastDpr = lastDprList[pronounIndex] as String
        val connectedPronoun = connectedPronounList[pronounIndex] as String
        return ActivePresentVerb(root, cp, dpr2, lastDpr, connectedPronoun)
    }

    /**
     * إنشاء الفعل المضارع في حالة الرفع
     *
     * @param pronounIndex int
     * @param root         TrilateralVerb
     * @return PresentConjugation
     */
    fun createNominativeVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot
    ): ActivePresentVerb {
        return createVerb(
            pronounIndex,
            root,
            PresentConjugationDataContainer.instance.nominativeLastDprList,
            PresentConjugationDataContainer.instance.nominativeConnectedPronounList
        )
    }

    /**
     * إنشاء الفعل المضارع في حالة النصب
     *
     * @param pronounIndex int
     * @param root         TrilateralVerb
     * @return PresentConjugation
     */
    fun createAccusativeVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot
    ): ActivePresentVerb {
        return createVerb(
            pronounIndex,
            root,
            PresentConjugationDataContainer.instance.accusativeLastDprList,
            PresentConjugationDataContainer.instance.accusativeConnectedPronounList
        )
    }

    /**
     * إنشاء الفعل المضارع في حالة الجزم
     *
     * @param pronounIndex int
     * @param root         TrilateralVerb
     * @return PresentConjugation
     */
    fun createJussiveVerb(pronounIndex: Int, root: UnaugmentedTrilateralRoot): ActivePresentVerb {
        return createVerb(
            pronounIndex,
            root,
            PresentConjugationDataContainer.instance.jussiveLastDprList,
            PresentConjugationDataContainer.instance.jussiveConnectedPronounList
        )
    }

    /**
     * إنشاء الفعل المضارع في حالة التأكيد
     *
     * @param pronounIndex int
     * @param root         TrilateralVerb
     * @return PresentConjugation
     */
    fun createEmphasizedVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot
    ): ActivePresentVerb {
        return createVerb(
            pronounIndex,
            root,
            PresentConjugationDataContainer.instance.emphasizedLastDprList,
            PresentConjugationDataContainer.instance.emphasizedConnectedPronounList
        )
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة الرفع
     *
     * @param root TripleVerb
     * @return List
     */
    fun createNominativeVerbList(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..12) {
            result.add(createNominativeVerb(i, root))
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة النصب
     *
     * @param root TripleVerb
     * @return List
     */
    fun createAccusativeVerbList(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..12) {
            result.add(createAccusativeVerb(i, root))
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة الجزم
     *
     * @param root TripleVerb
     * @return List
     */
    fun createJussiveVerbList(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..12) {
            result.add(createJussiveVerb(i, root))
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة التأكيد
     *
     * @param root TripleVerb
     * @return List
     */
    fun createEmphasizedVerbList(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..12) {
            result.add(createEmphasizedVerb(i, root))
        }
        return result
    }

    fun createNominativeVerbHua(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..0) {
            result.add(createNominativeVerb(i, root))
        }
        return result
    }

    fun createAccusativeVerbHua(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..0) {
            result.add(createAccusativeVerb(i, root))
        }
        return result
    }

    fun createJussiveVerbHua(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..0) {
            result.add(createJussiveVerb(i, root))
        }
        return result
    }

    fun createEmphasizedVerbHua(root: UnaugmentedTrilateralRoot): List<ActivePresentVerb> {
        val result: MutableList<ActivePresentVerb> = LinkedList()
        for (i in 0..0) {
            result.add(createEmphasizedVerb(i, root))
        }
        return result
    }

    companion object {
        val instance = ActivePresentConjugator()
    }
}