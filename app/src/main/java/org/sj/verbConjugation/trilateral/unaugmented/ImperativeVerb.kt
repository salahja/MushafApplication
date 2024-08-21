package org.sj.verbConjugation.trilateral.unaugmented

import org.sj.verbConjugation.util.ArabCharUtil

/**
 * فعل الأمر الثلاثي المجرد
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
class ImperativeVerb(
    val root: UnaugmentedTrilateralRoot, //حركة عين الفعل حسب باب التصريف
    //وهي نفسها في المضارع
    private val dpr2: String, //حركة لام الفعل حسب الضمير
    private val lastDim: String?, //ضمير الرفع المتصل
    val connectedPronoun: String
) {

    fun getdpr2(): String {
        return dpr2
    }

    fun getlastDim(): String {
        return lastDim!!
    }

    val ci: String
        get() = Companion.ci

    override fun toString(): String {
        return Companion.ci + root!!.c1 + dim1 + root!!.c2 + dpr2 + root!!.c3 + lastDim + connectedPronoun
    }

    companion object {
        //حرف الأمر
        private const val ci = ArabCharUtil.Aleph

        //حركة فاء الفعل وهي السكون دائماً
        private const val dim1 = ArabCharUtil.SKOON
    }
}