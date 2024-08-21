package org.sj.verbConjugation.trilateral.unaugmented

import org.sj.verbConjugation.util.ImperativeConjugationDataContainer
import org.sj.verbConjugation.util.PresentConjugationDataContainer
import java.util.LinkedList

/**
 * يقوم هذا الصف بتصريف الأفعال الثلاثية المجردة في صيغة الأمر
 *
 * Title: Sarf
 *
 * Description: برنامج التصريف
 *
 * Copyright: Copyright (c) 2006
 *
 * Company:
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class UnaugmentedImperativeConjugator private constructor() {
    /**
     * إنشاء صيغة الفعل الأمر غير المؤكد
     *
     * @param pronounIndex int
     * @param root!!         TrilateralVerb
     * @return PresentConjugation
     */
    fun createVerb(pronounIndex: Int, root: UnaugmentedTrilateralRoot): ImperativeVerb? {
        val dpr2 = PresentConjugationDataContainer.instance.getDpr2(root!!)
        val lastDim = ImperativeConjugationDataContainer.instance.getLastDim(pronounIndex)
        val connectedPronoun =
            ImperativeConjugationDataContainer.instance.getConnectedPronoun(pronounIndex)
        return if (lastDim === "" && connectedPronoun === "") null else ImperativeVerb(
            root!!,
            dpr2,
            lastDim,
            connectedPronoun
        )
    }

    /**
     * إنشاء صيغة الفعل الأمر  المؤكد
     *
     * @param pronounIndex int
     * @param root!!         TrilateralVerb
     * @return PresentConjugation
     */
    fun createEmphasizedVerb(pronounIndex: Int, root: UnaugmentedTrilateralRoot): ImperativeVerb? {
        val dpr2 = PresentConjugationDataContainer.instance.getDpr2(root!!)
        val lastDim =
            ImperativeConjugationDataContainer.instance.getEmphasizedLastDim(pronounIndex)
        val connectedPronoun = ImperativeConjugationDataContainer.instance
            .getEmphasizedConnectedPronoun(pronounIndex)
        return if (lastDim === "" && connectedPronoun === "") null else ImperativeVerb(
            root!!,
            dpr2,
            lastDim,
            connectedPronoun
        )
    }

    /**
     * إنشاء قائمة تحتوي على صيغ تصريف الفعل حسب الضمائر
     * الأمر غير المؤكد
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createVerbList(root: UnaugmentedTrilateralRoot): List<ImperativeVerb?> {
        val result: MutableList<ImperativeVerb?> = LinkedList()
        for (i in 0..12) {
            val conj = createVerb(i, root!!)
            result.add(conj)
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي على صيغ تصريف الفعل حسب الضمائر
     * الأمر  المؤكد
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createEmphasizedVerbList(root: UnaugmentedTrilateralRoot): List<ImperativeVerb?> {
        val result: MutableList<ImperativeVerb?> = LinkedList()
        for (i in 0..12) {
            val conj = createEmphasizedVerb(i, root!!)
            result.add(conj)
        }
        return result
    }

    fun createVerbHua(root: UnaugmentedTrilateralRoot): List<ImperativeVerb?> {
        val result: MutableList<ImperativeVerb?> = LinkedList()
        for (i in 0..0) {
            val conj = createVerb(i, root!!)
            result.add(conj)
        }
        return result
    }

    /**
     * إنشاء قائمة تحتوي على صيغ تصريف الفعل حسب الضمائر
     * الأمر  المؤكد
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createEmphasizedVerbHua(root: UnaugmentedTrilateralRoot): List<ImperativeVerb?> {
        val result: MutableList<ImperativeVerb?> = LinkedList()
        for (i in 0..0) {
            val conj = createEmphasizedVerb(i, root!!)
            result.add(conj)
        }
        return result
    }

    companion object {
        val instance = UnaugmentedImperativeConjugator()
    }
}