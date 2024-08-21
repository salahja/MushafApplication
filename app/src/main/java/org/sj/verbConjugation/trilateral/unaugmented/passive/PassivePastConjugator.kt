package org.sj.verbConjugation.trilateral.unaugmented.passive

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.PastConjugationDataContainer
import java.util.LinkedList

/**
 *
 * Title: Sarf
 *
 *
 * Description: تصريف الأفعال في الماضي المبني للمجهول
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
class PassivePastConjugator private constructor() {
    /**
     * إنشاء الفعل حسب الضمير
     *
     * @param pronounIndex int
     * @param root!!         TripleVerb
     * @return PassivePastVerb
     */
    fun createVerb(pronounIndex: Int, root: UnaugmentedTrilateralRoot): PassivePastVerb? {
        //	اظهار مع هو وهي فقط للمجهول اللازم
        if (root!!.verbtype == "ل" && pronounIndex != 7 && pronounIndex != 8) return null
        val lastDpa = PastConjugationDataContainer.getInstance().getLastDpa(pronounIndex)
        val connectedPronoun =
            PastConjugationDataContainer.getInstance().getConnectedPronoun(pronounIndex)
        return PassivePastVerb(root!!, lastDpa, connectedPronoun)
    }

    /**
     * إنشاء  قائمة تحتوي الأفعال مع الضمائر الثلاثة عشر
     *
     * @param root!! TripleVerb
     * @return List
     */
    fun createVerbList(root: UnaugmentedTrilateralRoot): List<PassivePastVerb?> {
        val result: MutableList<PassivePastVerb?> = LinkedList()
        for (i in 0..12) {
            result.add(createVerb(i, root!!))
        }
        return result
    }

    fun createVerbHua(root: UnaugmentedTrilateralRoot): List<PassivePastVerb?> {
        val result: MutableList<PassivePastVerb?> = LinkedList()
        for (i in 0..0) {
            result.add(createVerb(i, root!!))
        }
        return result
    }

    companion object {
        val instance = PassivePastConjugator()
    }
}