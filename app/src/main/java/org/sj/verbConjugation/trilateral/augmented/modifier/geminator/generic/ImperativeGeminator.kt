package org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.ExpressionSuffixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import java.util.LinkedList

class ImperativeGeminator : SubstitutionsApplier() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("C1ْC3ِC3ُ", "C1ِC3ُّ")) // EX: (أَتِمُّوا)
        substitutions.add(ExpressionInfixSubstitution("C1ْC3ِC3َ", "C1ِC3َّ")) // EX: (أَتِمَّا)
        substitutions.add(ExpressionInfixSubstitution("C1ْC3ِC3ِ", "C1ِC3ِّ")) // EX: (أَتِمِّي)
        substitutions.add(ExpressionSuffixSubstitution("C1ْC3ِC3ْ", "C1ِC3َّ")) // EX: (أَتِمَّ)
        substitutions.add(ExpressionInfixSubstitution("C3ِC3ُ", "C3ُّ")) // EX: (انقَضُّوا، حاجُّوا)
        substitutions.add(ExpressionInfixSubstitution("C3ِC3َ", "C3َّ")) // EX: (انْقَضَّا، حاجَّا)
        substitutions.add(ExpressionInfixSubstitution("C3ِC3ِ", "C3ِّ")) // EX: (انْقَضِّي، حاجِّي)
        substitutions.add(ExpressionSuffixSubstitution("C3ِC3ْ", "C3َّ")) // EX: (انْقَضَّ، حاجَّ)
        substitutions.add(ExpressionSuffixSubstitution("اC3َC3ْ", "اC3َّ")) // EX: (تآجَّ)
        substitutions.add(ExpressionInfixSubstitution("اC3َC3ْن", "اC3َC3ْن")) // EX: (تآجَجْنَ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC3َC3",
                "اC3ّ"
            )
        ) // EX: (تآجُّوا، تآجَّا، تآجِّي)
    }


}