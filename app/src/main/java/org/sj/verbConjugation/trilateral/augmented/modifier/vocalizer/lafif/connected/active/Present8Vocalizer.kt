package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.lafif.connected.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Present8Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("وُ", "ي")) // EX: (يُسَوِّي، يَحْوَوِّي)
        substitutions.add(SuffixSubstitution("وْ", "")) // EX: (لم يُسَوِّ، لم يَحْوَوِّ)
        substitutions.add(InfixSubstitution("وِّوِ", "وِّ")) // EX: (أنتِ تُسَوِّينَ، تَحْوَوِّينَ)
        substitutions.add(
            InfixSubstitution(
                "ِّوَ",
                "ِّيَ"
            )
        ) // EX: (أنتما تُسَوِّيانِ، تَحْوَوِّيانِ، لن يُسَوِّيَ، لن يَحْوَوِّيَ)
        substitutions.add(InfixSubstitution("ِّوُ", "ُّ")) // EX: (أنتم تُسَوُّونَ، تَحْوَوُّونَ)
        substitutions.add(InfixSubstitution("ِّوْ", "ِّي")) // EX: (أنتن تُسَوِّينَ، تَحْوَوِّينَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        val root = mazeedConjugationResult.root!!
        return root!!.c2 == root!!.c3 && root!!.c3 == 'و' && kov == 28 && (formulaNo == 2 || formulaNo == 11)
    }
}