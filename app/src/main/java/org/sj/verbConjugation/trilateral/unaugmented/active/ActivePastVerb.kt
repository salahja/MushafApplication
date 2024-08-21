package org.sj.verbConjugation.trilateral.unaugmented.active

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

/**
 * الفعل الماضي
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
class ActivePastVerb(
    val root: UnaugmentedTrilateralRoot, //حركة عين الفعل حسب باب التصريف
    val dpa2: String, //حركة لام الفعل حسب الضمير
    val lastDpa: String, //ضمير الرفع المتصل
    val connectedPronoun: String
) {

    override fun toString(): String {
        ////System.out.printf(ss);
        return root!!.c1.toString() + dpa1 + root!!.c2 + dpa2 + root!!.c3 + lastDpa + connectedPronoun
    }

    companion object {
        //حركة فاء الفعل وهي الفتحة دائماً
        private const val dpa1 = ArabCharUtil.FATHA
    }
}