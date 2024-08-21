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
class AssimilateFormulaE2SuffixContainer private constructor() : INounSuffixContainer {
    //حالة النكرة
    val indefiniteSuffixList = ArrayList<String>(18)

    //حالة المعرفة
    val definiteSuffixList = ArrayList<String>(18)

    //حالة الاضافة
    val annexedSuffixList = ArrayList<String>(18)

    //تكون لها قيمة عندما تكون الحالة هي معرفة
    private var prefix = ""

    //يمثل القائمة المختارة تبعاً للحالة
    var currentSuffixList = indefiniteSuffixList
        private set

    init {
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ ى")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ يَانِ")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ ى")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ يَيْنِ")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ ى")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ يَيْنِ")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        definiteSuffixList.add("")
        definiteSuffixList.add("َ ى")
        definiteSuffixList.add("")
        definiteSuffixList.add("َ يَانِ")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        definiteSuffixList.add("َ ى")
        definiteSuffixList.add("")
        definiteSuffixList.add("َ يَيْنِ")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        definiteSuffixList.add("َ ى")
        definiteSuffixList.add("")
        definiteSuffixList.add("َ يَيْنِ")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        annexedSuffixList.add("")
        annexedSuffixList.add("َ ى")
        annexedSuffixList.add("")
        annexedSuffixList.add("َ يَا")
        annexedSuffixList.add("")
        annexedSuffixList.add("")
        annexedSuffixList.add("")
        annexedSuffixList.add("َ ى")
        annexedSuffixList.add("")
        annexedSuffixList.add("َ يَيْ")
        annexedSuffixList.add("")
        annexedSuffixList.add("")
        annexedSuffixList.add("ِ")
        annexedSuffixList.add("َ ى")
        annexedSuffixList.add("")
        annexedSuffixList.add("َ يَيْ")
        annexedSuffixList.add("")
        annexedSuffixList.add("")
    }

    override fun selectDefiniteMode() {
        prefix = "ال"
        currentSuffixList = definiteSuffixList
        if (AssimilateFormulaE1SuffixContainer.Companion.instance
                .currentSuffixList !== AssimilateFormulaE1SuffixContainer.Companion.instance
                .definiteSuffixList
        ) AssimilateFormulaE1SuffixContainer.Companion.instance.selectDefiniteMode()
    }

    override fun selectInDefiniteMode() {
        prefix = ""
        currentSuffixList = indefiniteSuffixList
        if (AssimilateFormulaE1SuffixContainer.Companion.instance
                .currentSuffixList !== AssimilateFormulaE1SuffixContainer.Companion.instance
                .indefiniteSuffixList
        ) AssimilateFormulaE1SuffixContainer.Companion.instance.selectInDefiniteMode()
    }

    override fun selectAnnexedMode() {
        prefix = ""
        currentSuffixList = annexedSuffixList
        if (AssimilateFormulaE1SuffixContainer.Companion.instance
                .currentSuffixList !== AssimilateFormulaE1SuffixContainer.Companion.instance
                .annexedSuffixList
        ) AssimilateFormulaE1SuffixContainer.Companion.instance.selectAnnexedMode()
    }

    override fun getPrefix(): String {
        return prefix
    }

    override fun get(index: Int): String {
        return currentSuffixList[index]
    }

    companion object {
        val instance = AssimilateFormulaE2SuffixContainer()
    }
}
