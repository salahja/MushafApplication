package org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.ExpressionSuffixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import java.util.LinkedList

class PassivePresentGeminator : SubstitutionsApplier() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3ُ", "َC3ُّ")) // EX: (يُتَمُّ،)
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3َ", "َC3َّ")) // EX: (يُتَمَّان،)
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3ِ", "َC3ِّ")) // EX: (تُتَمِّي،)
        substitutions.add(ExpressionSuffixSubstitution("ْC3َC3ْ", "َC3َّ")) // EX: (لم يُتَمَّ،)
        substitutions.add(
            ExpressionSuffixSubstitution(
                "َC3َC3ْ",
                "َC3َّ"
            )
        ) // EX: (لم يُنْقَضَّ، يُشْتَدَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC3َC3ُ",
                "اC3ُّ"
            )
        ) // EX: (يُتَصَامُّ، يُحاجّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC3َC3َ",
                "اC3َّ"
            )
        ) // EX: (يُتَصَامَّا، يحاجّا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC3َC3ِ",
                "اC3ِّ"
            )
        ) // EX: (تُتَصَامِّي، تحاجِي)
        substitutions.add(
            ExpressionSuffixSubstitution(
                "اC3َC3ْ",
                "اC3َّ"
            )
        ) // EX: (لم يُتَصَامَّ، يحاجَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َC3َC3ُ",
                "َC3ُّ"
            )
        ) // EX: (يُحْمَرُّ، يُقْتَضُّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َC3َC3َ",
                "َC3َّ"
            )
        ) // EX: (لن يُحْمَرَّ، لن يُنْقَضَّ، لن يُشتدَّ)
        substitutions.add(ExpressionInfixSubstitution("َC3َC3ِ", "َC3ِّ")) // EX: (تُنقَضِّين)
    }


}