package org.sj.verbConjugation.trilateral.unaugmented.passive

import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot
import org.sj.verbConjugation.util.ArabCharUtil

/**
 * يمثل هذا الصف الفعل في صيغة المضارع المبني للمجهول متضمناً الأحرف الثلاثة
 * وحركاتها مع ضمير الرفع المنفصل ومع الأحرف الأخيرة المضافة للفعل حسب الضمير
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
class PassivePresentVerb(
    val root: UnaugmentedTrilateralRoot, //حرف المضارع
    val cp: String, //حركة لام الفعل حسب الضمير
    private val lastDpr: String, //الأحرف المضافة لنهاية الفعل حسب الضمير
    val connectedPronoun: String
) {

    fun getdpr2(): String {
        return dpr2
    }

    fun getlastDpr(): String {
        return lastDpr
    }

    override fun toString(): String {
        return cp + vcp + root!!.c1 + dpr1 + root!!.c2 + dpr2 + root!!.c3 + lastDpr + connectedPronoun
    }

    companion object {
        //حركة حرف المضارع وهي دائماًً ضمة
        private const val vcp = ArabCharUtil.DAMMA

        //حركة فاء الفعل وهي دائماً ثابتة سكون
        private const val dpr1 = ArabCharUtil.SKOON

        //حركة فاء الفعل وهي دائماً ثابتة فتحة
        private const val dpr2 = ArabCharUtil.FATHA
    }
}