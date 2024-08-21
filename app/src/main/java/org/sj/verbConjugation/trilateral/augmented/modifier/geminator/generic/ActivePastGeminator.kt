package org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import java.util.LinkedList

class ActivePastGeminator : SubstitutionsApplier() {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3َ", "َC3َّ")) // EX: (أَمَدَّ،)
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3ُ", "َC3ُّ")) // EX: (أَمَدُّوا،)
        substitutions.add(ExpressionInfixSubstitution("اC3َC3َ", "اC3َّ")) // EX: (حاجَّ، احمارَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC3َC3ُ",
                "اC3ُّ"
            )
        ) // EX: (حاجُّوا، احمارُّوا)
        substitutions.add(ExpressionInfixSubstitution("َC3َC3َ", "َC3َّ")) // EX: (احمرَّ)
        substitutions.add(ExpressionInfixSubstitution("َC3َC3ُ", "َC3ُّ")) // EX: (احمرُّوا)
    }

   
}