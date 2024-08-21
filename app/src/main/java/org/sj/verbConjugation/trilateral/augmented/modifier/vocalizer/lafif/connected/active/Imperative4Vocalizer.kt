package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative4Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "وْ",
                ""
            )
        ) // EX: (أسْوِ، ساوِ، انْغَوِ، استَوِ، استَغْوِ)
        substitutions.add(
            InfixSubstitution(
                "ِوِ",
                "ِ"
            )
        ) // EX: (أنتِ أسْوِي، ساوِي، انْغَوِي، استَوِي، استَغْوِي)
        substitutions.add(
            InfixSubstitution(
                "ِوَ",
                "ِيَ"
            )
        ) // EX: (أنتما ِ أسْوِيا، ساوِيا، انْغَوِيا، استَوِيا، استَغْوِيا)
        substitutions.add(
            InfixSubstitution(
                "ِوُ",
                "ُ"
            )
        ) // EX: (أنتم أسْوُوا، ساوُوا، انْغَوُوا، استَوُوا، استَغْوُوا)
        substitutions.add(
            InfixSubstitution(
                "ِوْ",
                "ِي"
            )
        ) // EX: (أنتن أسْوِينَ، ساوِينَ، انْغَوِينَ، استَوِينَ، استَغْوِينَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if (root!!.c2 == root!!.c3 && root!!.c3 == 'و' && kov == 28) {
            when (formulaNo) {
                1, 3, 4, 5, 9 -> return true
            }
        }
        return false
    }
}