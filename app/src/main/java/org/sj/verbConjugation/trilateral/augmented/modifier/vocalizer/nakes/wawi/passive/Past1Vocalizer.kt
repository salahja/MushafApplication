package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.nakes.wawi.passive

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
                "وْ",
                "ي"
            )
        ) // EX: (أنا أُدْنِيتُ، حُوبِيتُ، انجُليت، ارتضيت، تُسُومِيتُ، استُرْضِيتُ، احْلُولِيتُ)
        substitutions.add(
            InfixSubstitution(
                "وَ",
                "يَ"
            )
        ) // EX: (هو أُدْنِيَ، حُوبِيَ، انجُلِيَ، ارتضيَ، تُسُومِيَ، استُرْضِيَ، احْلُولِيَ)
        substitutions.add(
            InfixSubstitution(
                "ِوُ",
                "ُ"
            )
        ) // EX: (هم أُدْنُوا، حُوبُوا، انجُلُوا، ارتضُوا، تُسُومُوا، استُرْضُوا، احْلُولُوا)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        when (kov) {
            21 -> {
                when (formulaNo) {
                    1, 3, 5, 7, 9 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 7 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 6, 7, 9, 10 -> return true
                }
            }

            22 -> {
                when (formulaNo) {
                    1, 3, 4, 5, 7 -> return true
                }
                when (formulaNo) {
                    1, 3, 4, 5, 6, 7, 9, 10 -> return true
                }
            }

            23 -> when (formulaNo) {
                1, 3, 4, 5, 6, 7, 9, 10 -> return true
            }
        }
        return false
    }
}