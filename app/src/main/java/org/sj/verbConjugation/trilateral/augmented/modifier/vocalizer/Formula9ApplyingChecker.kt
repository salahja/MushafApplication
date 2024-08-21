package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer

import java.util.LinkedList

class Formula9ApplyingChecker : IFormulaApplyingChecker() {
   override val twoStateList: MutableList<String> = LinkedList()
   override val notVocalizedList: MutableList<String> = LinkedList()

    init {
        twoStateList.add("جوب")
        twoStateList.add("جوف")
        twoStateList.add("خول")
        twoStateList.add("روح")
        twoStateList.add("روض")
        twoStateList.add("صوب")
        notVocalizedList.add("ءور")
        notVocalizedList.add("تيس")
        notVocalizedList.add("حوذ")
        notVocalizedList.add("حوس")
        notVocalizedList.add("حوض")
        notVocalizedList.add("فيل")
        notVocalizedList.add("قوس")
        notVocalizedList.add("ليث")
        notVocalizedList.add("نوق")
        notVocalizedList.add("نوك")
    }


}