package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present4Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "ِيُ",
                "ِي"
            )
        ) // EX: (يُذْوِي، يداوي، ينزوي، يحتوي، يستهوي)
        substitutions.add(
            SuffixSubstitution(
                "يْ",
                ""
            )
        ) // EX: (لم يُذْوِ، لم يداوِ، لم يَنْزَوِ، لم يَحْتَوِ، لم يَسْتَهْوِ)
        substitutions.add(
            InfixSubstitution(
                "ِيِ",
                "ِ"
            )
        ) // EX: (أنتِ تُذْوِينَ، تُداوِينَ، تنْزوين، تحتوين، تستهوين)
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (أنتم تُذْوُونَ، تُداوُونَ، تنْزوون، تحتوون، تستهوون)
        substitutions.add(
            InfixSubstitution(
                "ِيْ",
                "ِي"
            )
        ) // EX: (أنتن تُذْوِينَ، تداوين، تنْزوين، تحتوين، تستهوين)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if (root!!.c2 == 'و' && root!!.c3 == 'ي') {
            when (kov) {
                27 -> {
                    when (formulaNo) {
                        1, 5, 9 -> return true
                    }
                    when (formulaNo) {
                        1, 3, 4, 5, 9 -> return true
                    }
                }

                28 -> when (formulaNo) {
                    1, 3, 4, 5, 9 -> return true
                }
            }
        }
        return false
    }
}