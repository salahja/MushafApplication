package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer

import java.util.LinkedList

class Formula5ApplyingChecker : IFormulaApplyingChecker() {
   override val twoStateList: MutableList<String> = LinkedList()
   override val notVocalizedList: MutableList<String> = LinkedList()

    init {
        twoStateList.add("حول")
        twoStateList.add("روح")
        twoStateList.add("شور")
        notVocalizedList.add("جور")
        notVocalizedList.add("حوش")
        notVocalizedList.add("زوج")
        notVocalizedList.add("سوط")
        notVocalizedList.add("عور")
        notVocalizedList.add("هور")
    }


}