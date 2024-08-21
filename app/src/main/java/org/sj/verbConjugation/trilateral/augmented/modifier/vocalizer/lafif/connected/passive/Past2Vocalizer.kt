package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past2Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "ِوُ",
                "ُ"
            )
        ) // EX: (اُسْوُوا، سُووُوا، انغُوُوا، استُوُوا، تُسُووُوا، استُغْوُوا)
        substitutions.add(InfixSubstitution("ِّوُ", "ُّ")) // EX: (سُوُّوا، تُسُوُّوا، احْوُوُّوا)
        substitutions.add(
            InfixSubstitution(
                "وَ",
                "يَ"
            )
        ) // EX: (أُسْوِيَ، سُوِّيَ أُسْوِيَتْ، أُسْوِيَا، سُووِيَ، انغُوِيَ، استُوِيَ، تُسُووِيَ، استُغْوِيَ احْوُوِّيَ)
        substitutions.add(
            InfixSubstitution(
                "وْ",
                "ي"
            )
        ) // EX: (أُسْوِيتُ، سُوِّيتُ سُووِيتُ، انغُوِيتُ، استُوِيتُ، تُسُووِيتُ، استُغْوِيتُ)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if (root!!.c2 == root!!.c3 && root!!.c3 == 'و' && kov == 28) {
            when (formulaNo) {
                1, 2, 3, 4, 5, 7, 8, 9, 11 -> return true
            }
        }
        return false
    }
}