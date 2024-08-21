package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "يْ",
                ""
            )
        ) // EX: (أحْيِ، أذْوِ، حايِ، داوِ، انزوِ، احتوِ، استحيِ، استهوِ)
        substitutions.add(
            InfixSubstitution(
                "ِيِ",
                "ِ"
            )
        ) // EX: (أنتِ أحْيِي، أذْوِي، حايِي، داوِي، انزوِي، احتوِي، استحيِي، استهوِي)
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (أنتم أحْيُوا، أذْوُوا، حايُوا، داوُوا، انزوُوا، احتوُوا، استحيُوا، استهوُوا)
        substitutions.add(
            InfixSubstitution(
                "ِيْ",
                "ِي"
            )
        ) // EX: (أنتن أحْيِينَ، أذْوِينَ، حايِينَ، داوِينَ، انزوِينَ، احتوِينَ، استحيِينَ، استهوِينَ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if ((root!!.c2 == 'و' || root!!.c2 == 'ي') && root!!.c3 == 'ي') {
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