package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("ِيُ", "ِي")) // EX: (يُحْيِي، يحايِي، يزديي، يستحيي)
        substitutions.add(
            SuffixSubstitution(
                "ِيْ",
                "ِ"
            )
        ) // EX: (لم يُحْيِ، لم يُحايِ، لم يَزدَيِ، لم يَسْتَحِي)
        substitutions.add(
            InfixSubstitution(
                "ِيِي",
                "ِي"
            )
        ) // EX: (أنتِ تُحْيِينَ، تُحايِينَ، تزديين، تستحيين)
        substitutions.add(
            InfixSubstitution(
                "ِيُ",
                "ُ"
            )
        ) // EX: (أنتم تُحْيُون، تُحايُون، تزديون، تستحيون)
        substitutions.add(
            InfixSubstitution(
                "ِيْ",
                "ِي"
            )
        ) // EX: (أنتن تُحْيِينَ، تُحايِين، تزديين، تستحيين)
    }



    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        if (root!!.c2 == root!!.c3 && root!!.c3 == 'ي' && kov == 28) {
            when (formulaNo) {
                1, 3, 5, 9 -> return true
            }
        }
        return false
    }
}