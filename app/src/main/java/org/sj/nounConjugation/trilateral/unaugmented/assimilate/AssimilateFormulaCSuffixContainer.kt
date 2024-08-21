package org.sj.nounConjugation.trilateral.unaugmented.assimilate

import org.sj.nounConjugation.INounSuffixContainer

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
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class AssimilateFormulaCSuffixContainer private constructor() : INounSuffixContainer {
    //حالة النكرة
    private val indefiniteSuffixList = ArrayList<String>(18)

    //حالة المعرفة
    private val definiteSuffixList = ArrayList<String>(18)

    //حالة الاضافة
    private val annexedSuffixList = ArrayList<String>(18)

    //تكون لها قيمة عندما تكون الحالة هي معرفة
    private var prefix = ""

    //يمثل القائمة المختارة تبعاً للحالة
    private var currentSuffixList = indefiniteSuffixList

    init {
        indefiniteSuffixList.add("ُ")
        indefiniteSuffixList.add("َاءُ")
        indefiniteSuffixList.add("َانِ")
        indefiniteSuffixList.add("َاوَانِ")
        indefiniteSuffixList.add("ٌ")
        indefiniteSuffixList.add("ٌ")
        indefiniteSuffixList.add("َ")
        indefiniteSuffixList.add("َاءَ")
        indefiniteSuffixList.add("َيْنِ")
        indefiniteSuffixList.add("َاوَيْنِ")
        indefiniteSuffixList.add("ًا")
        indefiniteSuffixList.add("ًا")
        indefiniteSuffixList.add("َ")
        indefiniteSuffixList.add("َاءَ")
        indefiniteSuffixList.add("َيْنِ")
        indefiniteSuffixList.add("َاوَيْنِ")
        indefiniteSuffixList.add("ٍ")
        indefiniteSuffixList.add("ٍ")
        definiteSuffixList.add("ُ")
        definiteSuffixList.add("َاءُ")
        definiteSuffixList.add("َانِ")
        definiteSuffixList.add("َاوَانِ")
        definiteSuffixList.add("ُ")
        definiteSuffixList.add("ُ")
        definiteSuffixList.add("َ")
        definiteSuffixList.add("َاءَ")
        definiteSuffixList.add("َيْنِ")
        definiteSuffixList.add("َاوَيْنِ")
        definiteSuffixList.add("َ")
        definiteSuffixList.add("َ")
        definiteSuffixList.add("ِ")
        definiteSuffixList.add("َاءِ")
        definiteSuffixList.add("َيْنِ")
        definiteSuffixList.add("َاوَيْنِ")
        definiteSuffixList.add("ِ")
        definiteSuffixList.add("ِ")
        annexedSuffixList.add("ُ")
        annexedSuffixList.add("َاءُ")
        annexedSuffixList.add("َا")
        annexedSuffixList.add("َاوَا")
        annexedSuffixList.add("ُ")
        annexedSuffixList.add("ُ")
        annexedSuffixList.add("َ")
        annexedSuffixList.add("َاءَ")
        annexedSuffixList.add("َيْ")
        annexedSuffixList.add("َاوَيْ")
        annexedSuffixList.add("َ")
        annexedSuffixList.add("َ")
        annexedSuffixList.add("ِ")
        annexedSuffixList.add("َاءِ")
        annexedSuffixList.add("َيْ")
        annexedSuffixList.add("َاوَيْ")
        annexedSuffixList.add("ِ")
        annexedSuffixList.add("ِ")
    }

    override fun selectDefiniteMode() {
        prefix = "ال"
        currentSuffixList = definiteSuffixList
    }

    override fun selectInDefiniteMode() {
        prefix = ""
        currentSuffixList = indefiniteSuffixList
    }

    override fun selectAnnexedMode() {
        prefix = ""
        currentSuffixList = annexedSuffixList
    }

    override fun getPrefix(): String {
        return prefix
    }

    override fun get(index: Int): String {
        return currentSuffixList[index]
    }

    companion object {
        val instance = AssimilateFormulaCSuffixContainer()
    }
}