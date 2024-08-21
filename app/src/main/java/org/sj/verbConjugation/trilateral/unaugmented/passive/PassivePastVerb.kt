package org.sj.verbConjugation.trilateral.unaugmented.passive

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

/**
 * يمثل هذا الصف الفعل في صيغة الماضي المبني للمجهول متضمناً الأحرف الثلاثة
 * وحركاتها مع الأحرف الأخيرة المضافة للفعل حسب الضمير
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
class PassivePastVerb(
    val root: UnaugmentedTrilateralRoot, //حركة لام الفعل حسب الضمير
    val lastDpa: String, //الأحرف المضافة لنهاية الفعل حسب الضمير
    val connectedPronoun: String
) {

    override fun toString(): String {
        return root!!.c1.toString() + dpa1 + root!!.c2 + dpa2 + root!!.c3 + lastDpa + connectedPronoun
    }

    companion object {
        //حركة فاء الفعل وهي دائماً  ضمة
        private const val dpa1 = ArabCharUtil.DAMMA

        //حركة عين الفعل وهي دائماً كسرة
        private const val dpa2 = ArabCharUtil.KASRA
    }
}