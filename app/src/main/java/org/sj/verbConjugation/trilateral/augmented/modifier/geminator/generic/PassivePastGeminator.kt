package org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import java.util.LinkedList

class PassivePastGeminator : SubstitutionsApplier() {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْC3ِC3َ", "ِC3َّ")) // EX: (أُحِبَّ،)
        substitutions.add(ExpressionInfixSubstitution("ْC3ِC3ُ", "ِC3ُّ")) // EX: (أُحِبُّوا،)
        substitutions.add(ExpressionInfixSubstitution("ُC3ِC3َ", "ُC3َّ")) // EX: (انْقُضَّ)
        substitutions.add(ExpressionInfixSubstitution("ُC3ِC3ُ", "ُC3ُّ")) // EX: (انْقُضُّوا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "وC3ِC3َ",
                "وC3َّ"
            )
        ) // EX: (حُوجَّ، احْمُورَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "وC3ِC3ُ",
                "وC3ُّ"
            )
        ) // EX: (حُوجُّوا، احْمُورُّوا)
    }


}