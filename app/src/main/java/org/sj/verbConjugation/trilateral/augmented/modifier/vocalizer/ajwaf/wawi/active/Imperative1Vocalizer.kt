package org.sj.verbConjugation.trilateral.augmented.modifier.vocalizer.ajwaf.wawi.active

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.augmented.modifier.IAugmentedTrilateralModifier
import java.util.LinkedList

class Imperative1Vocalizer : SubstitutionsApplier(), IAugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (أنتم أقيموا، استديروا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوِC3ِ",
                "ِيC3ِ"
            )
        ) // EX: (أنتِ أقيمي، استديري)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوِC3َ",
                "ِيC3َ"
            )
        ) // EX: (أنتما أقيما، استديرا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوِC3ْ",
                "ِC3ْ"
            )
        ) // EX: (أنتن أقِمْنَ، استدرْنَ)
    }

   

    override fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean {
        val kov = mazeedConjugationResult.kov
        val formulaNo = mazeedConjugationResult.formulaNo
        return (kov == 16 || kov == 17) && formulaNo == 1 || kov == 15 || kov == 16 || kov == 17 && formulaNo == 9
    }
}