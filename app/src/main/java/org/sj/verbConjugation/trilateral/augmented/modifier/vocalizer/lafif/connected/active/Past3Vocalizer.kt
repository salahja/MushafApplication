package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Past3Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "َوَ",
                "َى"
            )
        ) // EX: (أسوَى، سَوَّى ساوَى، انغوى، استوى، تساوى، تَسَوَّى استغوى احْوَوَّى)
        substitutions.add(
            InfixSubstitution(
                "وْ",
                "يْ"
            )
        ) // EX: (أسْوَيْتُ، سَوَّيْتُ ساوَيْتُ، انغويتُ، استوَيْتُ، تساويتُ، تسوَّيْتُ استغوَيْتُ احوَوَّيْتُ)
        substitutions.add(
            InfixSubstitution(
                "وَا",
                "يَا"
            )
        ) // EX: (أسْوَيَا، سَوَّيا ساوَيا، انغويا، استوَيا، تساوَيَا، تَسَوَّيَا، استغوَيَا، احْوَوَّيَا)
        substitutions.add(
            InfixSubstitution(
                "وُو",
                "وْ"
            )
        ) // EX: (أسْوَوْا، سَوَّوْا ساوَوْا، انغوَوْا، استوَوْا، تساوَوْا، تَسَوَّوْا، استغوَوْا، احْوَوَّوْا)
        substitutions.add(InfixSubstitution("وَوَ", "وَ")) // EX: (أسْوَتْ، )
        substitutions.add(InfixSubstitution("وَّوَ", "وَّ")) // EX: (سَوَّتْ، )
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