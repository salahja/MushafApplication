package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present7Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(
            SuffixSubstitution(
                "وُ",
                "ي"
            )
        ) // EX: (يُسْوِي، يُساوِي، تنغوي، يستوي، يستغوي)
        substitutions.add(
            SuffixSubstitution(
                "وْ",
                ""
            )
        ) // EX: (لم يُسْوِ، لم يُساوِ، لم ينغوِ، لم يَستوِ، لم يستغوِ)
        substitutions.add(
            InfixSubstitution(
                "وِوِ",
                "وِ"
            )
        ) // EX: (أنتِ تُسْوِينَ، تُساوِينَ، تنغوين، تستوين، تستغوين)
        substitutions.add(
            InfixSubstitution(
                "ِوَ",
                "ِيَ"
            )
        ) // EX: (أنتما تُسْوِيَانِ، تُساوِيانِ، تنغويان، تستويان، تستغويان)
        substitutions.add(
            InfixSubstitution(
                "ِوُ",
                "ُ"
            )
        ) // EX: (أنتم تُسْوُونَ، تُساوُونَ، تنغوُونَ، تستوُونَ، تستغوون)
        substitutions.add(
            InfixSubstitution(
                "ِوْ",
                "ِي"
            )
        ) // EX: (أنتن تُسْوِينَ، تُساوِينَ، تنغوين، تستوين، تستغوين)
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