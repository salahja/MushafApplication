package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (أُحْيُوا، أُذْوُوا، دُووُوا، حُويُوا، انزُوُوا، احتُوُوا، تُدُووُوا، استُهْوُوا)
        substitutions.add(
            InfixSubstitution(
                "ِّيُ",
                "ُّ"
            )
        ) // EX: (حُيُّوا، قُوُّوا، تُقُوُّوا، تُحُيُّوا)
        substitutions.add(
            InfixSubstitution(
                "يْ",
                "ي"
            )
        ) // EX: (أُحْيِيتُ، حُيِّيتُ قُوِّيتُ دُوِيتُ، انزويت، احتويتُ، تُحُيِّيتُ تدويت، استُحْيِيتُ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if ((root!!.c2 == 'و' || root!!.c2 == 'ي') && root!!.c3 == 'ي') {
            when (kov) {
                27 -> {
                    when (formulaNo) {
                        1, 2, 5, 7, 8, 9 -> return true
                    }
                    when (formulaNo) {
                        1, 2, 3, 4, 5, 7, 8, 9 -> return true
                    }
                }

                28 -> when (formulaNo) {
                    1, 2, 3, 4, 5, 7, 8, 9 -> return true
                }
            }
        }
        return false
    }
}