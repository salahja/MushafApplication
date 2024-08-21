package org.sj.nounConjugation.trilateral.unaugmented.elative

import org.sj.nounConjugation.INounSuffixContainer
import org.sj.verbConjugation.util.ArabCharUtil

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
 * @author Haytham Mohta\u00E6sseb Billah
 * @version 1.0
 */
class ElativeSuffixContainer private constructor() : INounSuffixContainer {
    //حالة المعرفة
    private val definiteSuffixList = ArrayList<String>(18)

    //جدول تصريف اسم التفضيل المضاف إلى معرفة
    private val annexedDefiniteSuffixList = ArrayList<String>(18)

    // جدول تصريف اسم التفضيل المضاف إلى نكرة
    private val annexedIndefiniteSuffixList = ArrayList<String>(18)

    //جدول تصريف اسم التفضيل غير المضاف
    //notAnnexedSuffixList is same as annexedIndefiniteSuffixList
    private val notAnnexedSuffixList = ArrayList<String>(18)

    //تكون لها قيمة عندما تكون الحالة هي معرفة
    private var prefix = ""

    //يمثل القائمة المختارة تبعاً للحالة
    private var currentSuffixList = annexedIndefiniteSuffixList

    init {
        annexedIndefiniteSuffixList.add(ArabCharUtil.DAMMA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.DAMMA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.DAMMA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.DAMMA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.DAMMA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.DAMMA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.FATHA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.FATHA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.FATHA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.FATHA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.FATHA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.FATHA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.KASRA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.KASRA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.KASRA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.KASRA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.KASRA)
        annexedIndefiniteSuffixList.add(ArabCharUtil.KASRA)
        definiteSuffixList.add("ُ")
        definiteSuffixList.add("َى")
        definiteSuffixList.add("َانِ")
        definiteSuffixList.add("َيَانِ")
        definiteSuffixList.add("ُونَ")
        definiteSuffixList.add("َيَاتُ")
        definiteSuffixList.add("َ")
        definiteSuffixList.add("َى")
        definiteSuffixList.add("َيْنِ")
        definiteSuffixList.add("َيَيْنِ")
        definiteSuffixList.add("ِينَ")
        definiteSuffixList.add("َيَاتِ")
        definiteSuffixList.add("ِ")
        definiteSuffixList.add("َى")
        definiteSuffixList.add("َيْنِ")
        definiteSuffixList.add("َيَيْنِ")
        definiteSuffixList.add("ِينَ")
        definiteSuffixList.add("َيَاتِ")
        annexedDefiniteSuffixList.add(ArabCharUtil.DAMMA)
        annexedDefiniteSuffixList.add("َى")
        annexedDefiniteSuffixList.add("َا")
        annexedDefiniteSuffixList.add("َيَا")
        annexedDefiniteSuffixList.add("ُو")
        annexedDefiniteSuffixList.add("َيَاتُ")
        annexedDefiniteSuffixList.add(ArabCharUtil.FATHA)
        annexedDefiniteSuffixList.add("َى")
        annexedDefiniteSuffixList.add("َيْ")
        annexedDefiniteSuffixList.add("َيَيْ")
        annexedDefiniteSuffixList.add("ِي")
        annexedDefiniteSuffixList.add("َيَاتِ")
        annexedDefiniteSuffixList.add(ArabCharUtil.KASRA)
        annexedDefiniteSuffixList.add("َى")
        annexedDefiniteSuffixList.add("َيْ")
        annexedDefiniteSuffixList.add("َيَيْ")
        annexedDefiniteSuffixList.add("ِي")
        annexedDefiniteSuffixList.add("َيَاتِ")
        notAnnexedSuffixList.add(ArabCharUtil.DAMMA)
        notAnnexedSuffixList.add(ArabCharUtil.DAMMA)
        notAnnexedSuffixList.add(ArabCharUtil.DAMMA)
        notAnnexedSuffixList.add(ArabCharUtil.DAMMA)
        notAnnexedSuffixList.add(ArabCharUtil.DAMMA)
        notAnnexedSuffixList.add(ArabCharUtil.DAMMA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
        notAnnexedSuffixList.add(ArabCharUtil.FATHA)
    }

    /**
     * اختيار المعرف بأل
     */
    override fun selectDefiniteMode() {
        prefix = "ال"
        currentSuffixList = definiteSuffixList
    }

    /**
     * اختيار المضاف إلى نكرة
     */
    override fun selectInDefiniteMode() {
        prefix = ""
        currentSuffixList = annexedIndefiniteSuffixList
    }

    /**
     * اختيار الغير مضاف
     */
    fun selectNotAnnexedMode() {
        prefix = ""
        currentSuffixList = notAnnexedSuffixList
    }

    /**
     * اختيار المضاف إلى معرفة
     */
    override fun selectAnnexedMode() {
        prefix = ""
        currentSuffixList = annexedDefiniteSuffixList
    }

    override fun getPrefix(): String {
        return prefix
    }

    override fun get(index: Int): String {
        return currentSuffixList[index]
    }

    val isAnnexed: Boolean
        get() = currentSuffixList === annexedDefiniteSuffixList
    val isDefinite: Boolean
        get() = currentSuffixList === definiteSuffixList
    val isIndefinite: Boolean
        get() = currentSuffixList === annexedIndefiniteSuffixList
    val isNotAnnexed: Boolean
        get() = currentSuffixList === notAnnexedSuffixList

    companion object {
        val instance = ElativeSuffixContainer()
    }
}