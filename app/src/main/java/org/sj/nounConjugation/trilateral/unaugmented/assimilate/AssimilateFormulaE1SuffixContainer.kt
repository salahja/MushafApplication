import org.sj.nounConjugation.INounSuffixContainer

class AssimilateFormulaE1SuffixContainer private constructor() : INounSuffixContainer {
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
        indefiniteSuffixList.add("ُ")
        indefiniteSuffixList.add("َ ةٌ")
        indefiniteSuffixList.add("َ انِ")
        indefiniteSuffixList.add("َ تَانِ")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ")
        indefiniteSuffixList.add("َ ةً")
        indefiniteSuffixList.add("َ يْنِ")
        indefiniteSuffixList.add("َ تَيْنِ")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("َ")
        indefiniteSuffixList.add("َ ةٍ")
        indefiniteSuffixList.add("َ يْنِ")
        indefiniteSuffixList.add("َ تَيْنِ")
        indefiniteSuffixList.add("")
        indefiniteSuffixList.add("")
        definiteSuffixList.add("ُ")
        definiteSuffixList.add("َ ةُ")
        definiteSuffixList.add("َ انِ")
        definiteSuffixList.add("َ تَانِ")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        definiteSuffixList.add("َ")
        definiteSuffixList.add("َ ةَ")
        definiteSuffixList.add("َ يْنِ")
        definiteSuffixList.add("َ تَيْنِ")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        definiteSuffixList.add("ِ")
        definiteSuffixList.add("َ ةِ")
        definiteSuffixList.add("َ يْنِ")
        definiteSuffixList.add("َ تَيْنِ")
        definiteSuffixList.add("")
        definiteSuffixList.add("")
        annexedSuffixList.add("ُ")
        annexedSuffixList.add("َ ةُ")
        annexedSuffixList.add("َ ا")
        annexedSuffixList.add("َ تَا")
        annexedSuffixList.add("")
        annexedSuffixList.add("")
        annexedSuffixList.add("َ")
        annexedSuffixList.add("َ ةَ")
        annexedSuffixList.add("َ يْ")
        annexedSuffixList.add("َ تَيْ")
        annexedSuffixList.add("")
        annexedSuffixList.add("")
        annexedSuffixList.add("ِ")
        annexedSuffixList.add("َ ةِ")
        annexedSuffixList.add("َ يْ")
        annexedSuffixList.add("َ تَيْ")
        annexedSuffixList.add("")
        annexedSuffixList.add("")
    }

    override fun selectDefiniteMode() {
        prefix = "ال"
        currentSuffixList = definiteSuffixList
        if (AssimilateFormulaE2SuffixContainer.Companion.instance
                .currentSuffixList !== AssimilateFormulaE2SuffixContainer.Companion.instance
                .definiteSuffixList
        ) AssimilateFormulaE2SuffixContainer.Companion.instance.selectDefiniteMode()
    }

    override fun selectInDefiniteMode() {
        prefix = ""
        currentSuffixList = indefiniteSuffixList
        if (AssimilateFormulaE2SuffixContainer.Companion.instance
                .currentSuffixList !== AssimilateFormulaE2SuffixContainer.Companion.instance
                .indefiniteSuffixList
        ) AssimilateFormulaE2SuffixContainer.Companion.instance.selectInDefiniteMode()
    }

    override fun selectAnnexedMode() {
        prefix = ""
        currentSuffixList = annexedSuffixList
        if (AssimilateFormulaE2SuffixContainer.Companion.instance
                .currentSuffixList !== AssimilateFormulaE2SuffixContainer.Companion.instance
                .annexedSuffixList
        ) AssimilateFormulaE2SuffixContainer.Companion.instance.selectAnnexedMode()
    }

    override fun getPrefix(): String {
        return prefix
    }

    override fun get(index: Int): String {
        return currentSuffixList[index]
    }

    companion object {
        val instance = AssimilateFormulaE1SuffixContainer()
    }
}
