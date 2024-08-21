package org.sj.verbConjugation.trilateral.unaugmented.passive

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.PresentConjugationDataContainer
import java.util.LinkedList

/**
 *
 * Title: Sarf
 *
 *
 * Description: تصريف الأفعال في المضارع المبني للمجهول
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
class PassivePresentConjugator private constructor() {
    /**
     * إنشاء الفعل المضارع بغض النظر عن حالته الإعرابية
     *
     * @param pronounIndex         int
     * @param root!!                 UnaugmentedTrilateralRoot
     * @param lastDprList          List
     * @param connectedPronounList List
     * @return PassivePresentVerb
     */
    private fun createVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot,
        lastDprList: List<*>,
        connectedPronounList: List<*>
    ): PassivePresentVerb? {
        //	اظهار مع هو وهي فقط للمجهول اللازم
        if (root!!.verbtype == "ل" && pronounIndex != 7 && pronounIndex != 8) {
            return null
        }
        val cp = PresentConjugationDataContainer.instance.getCp(pronounIndex)
        val lastDpr = lastDprList[pronounIndex] as String
        val connectedPronoun = connectedPronounList[pronounIndex] as String
        return PassivePresentVerb(root!!, cp, lastDpr, connectedPronoun)
    }

    /**
     * إنشاء الفعل المضارع في حالة الرفع
     *
     * @param pronounIndex int
     * @param root!!         TrilateralVerb
     * @return PresentConjugation
     */
    fun createNominativeVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot
    ): PassivePresentVerb? {
        return createVerb(
            pronounIndex,
            root!!,
            PresentConjugationDataContainer.instance.nominativeLastDprList,
            PresentConjugationDataContainer.instance.nominativeConnectedPronounList
        )
    }

    /**
     * إنشاء الفعل المضارع في حالة النصب
     *
     * @param pronounIndex int
     * @param root!!         TrilateralVerb
     * @return PresentConjugation
     */
    fun createAccusativeVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot
    ): PassivePresentVerb? {
        return createVerb(
            pronounIndex,
            root!!,
            PresentConjugationDataContainer.instance.accusativeLastDprList,
            PresentConjugationDataContainer.instance.accusativeConnectedPronounList
        )
    }

    /**
     * إنشاء الفعل المضارع في حالة الجزم
     *
     * @param pronounIndex int
     * @param root!!         TrilateralVerb
     * @return PresentConjugation
     */
    fun createJussiveVerb(pronounIndex: Int, root: UnaugmentedTrilateralRoot): PassivePresentVerb? {
        return createVerb(
            pronounIndex,
            root!!,
            PresentConjugationDataContainer.instance.jussiveLastDprList,
            PresentConjugationDataContainer.instance.jussiveConnectedPronounList
        )
    }

    /**
     * إنشاء الفعل المضارع في حالة التأكيد
     *
     * @param pronounIndex int
     * @param root!!         TrilateralVerb
     * @return PresentConjugation
     */
    fun createEmphasizedVerb(
        pronounIndex: Int,
        root: UnaugmentedTrilateralRoot
    ): PassivePresentVerb? {
        return createVerb(
            pronounIndex,
            root!!,
            PresentConjugationDataContainer.instance.emphasizedLastDprList,
            PresentConjugationDataContainer.instance.emphasizedConnectedPronounList
        )
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة الرفع
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createNominativeVerbList(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..12) {
            result.add(createNominativeVerb(i, root!!))
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة النصب
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createAccusativeVerbList(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..12) {
            result.add(createAccusativeVerb(i, root!!))
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة الجزم
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createJussiveVerbList(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..12) {
            result.add(createJussiveVerb(i, root!!))
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي الأفعال حسب الضمائر
     * في حالة التأكيد
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createEmphasizedVerbList(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..12) {
            result.add(createEmphasizedVerb(i, root!!))
        }
        return result
    }

    //sarf sagheer
    fun createNominativeVerbHua(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..0) {
            result.add(createNominativeVerb(i, root!!))
        }
        return result
    }

    fun createAccusativeVerbHua(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..0) {
            result.add(createAccusativeVerb(i, root!!))
        }
        return result
    }

    fun createJussiveVerbHua(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..0) {
            result.add(createJussiveVerb(i, root!!))
        }
        return result
    }

    fun createEmphasizedVerbHua(root: UnaugmentedTrilateralRoot): List<PassivePresentVerb?> {
        val result: MutableList<PassivePresentVerb?> = LinkedList()
        for (i in 0..0) {
            result.add(createEmphasizedVerb(i, root!!))
        }
        return result
    }

    companion object {
        val instance = PassivePresentConjugator()
    }
}