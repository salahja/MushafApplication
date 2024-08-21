package org.sj.verbConjugation.trilateral.unaugmented.active

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.PastConjugationDataContainer
import java.util.LinkedList

/**
 *
 * Title: Sarf
 *
 *
 * Description: تصريف الأفعال في الماضي
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
class ActivePastConjugator private constructor() {
    /**
     * إنشاء الفعل حسب الضمير
     *
     * @param pronounIndex int
     * @param root!!         UnaugmentedTrilateralRoot
     * @return PastConjugation
     */
    fun createVerb(pronounIndex: Int, root: UnaugmentedTrilateralRoot): ActivePastVerb {
        val dpa2 = PastConjugationDataContainer.getInstance().getDpa2(root!!)
        val lastDpa = PastConjugationDataContainer.getInstance().getLastDpa(pronounIndex)
        val connectedPronoun =
            PastConjugationDataContainer.getInstance().getConnectedPronoun(pronounIndex)
        return ActivePastVerb(root!!, dpa2, lastDpa, connectedPronoun)
    }

    /**
     * إنشاء  قائمة تحتوي الأفعال مع الضمائر الثلاثة عشر
     *
     * @param root!! UnaugmentedTrilateralRoot
     * @return List
     */
    fun createVerbList(root: UnaugmentedTrilateralRoot): List<ActivePastVerb> {
        val result: MutableList<ActivePastVerb> = LinkedList()
        for (i in 0..12) {
            val add = result.add(createVerb(i, root!!))
        }
        ////System.out.println(result);
        return result
    }

    fun createVerbHua(root: UnaugmentedTrilateralRoot): List<ActivePastVerb> {
        val result: MutableList<ActivePastVerb> = LinkedList()
        for (i in 0..0) {
            val add = result.add(createVerb(i, root!!))
        }
        ////System.out.println(result);
        return result
    }

    companion object {
        val instance = ActivePastConjugator()
    }
}