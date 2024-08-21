package org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator.generic

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.ExpressionSuffixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class ImperativeGeminator : SubstitutionsApplier() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3ُC3ُ", "C1ُC3ُّ")) // EX: (مُدُّوا)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3ُC3َ", "C1ُC3َّ")) // EX: (مُدَّا)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3ُC3ِ", "C1ُC3ِّ")) // EX: (مُدِّي)
        substitutions.add(ExpressionSuffixSubstitution("اC1ْC3ُC3ْ", "C1ُC3َّ")) // EX: (مُدَّ)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3ِC3ُ", "C1ِC3ُّ")) // EX: (تِمُّوا)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3ِC3َ", "C1ِC3َّ")) // EX: (تِمَّا)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3ِC3ِ", "C1ِC3ِّ")) // EX: (تِمِّي)
        substitutions.add(ExpressionSuffixSubstitution("اC1ْC3ِC3ْ", "C1ِC3َّ")) // EX: (تِمَّ)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3َC3ُ", "C1َC3ُّ")) // EX: (عَضُّوا)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3َC3َ", "C1َC3َّ")) // EX: (عَضَّا)
        substitutions.add(ExpressionInfixSubstitution("اC1ْC3َC3ِ", "C1َC3ِّ")) // EX: (عَضِّي)
        substitutions.add(ExpressionSuffixSubstitution("اC1ْC3َC3ْ", "C1َC3َّ")) // EX: (عَضَّ)
    }

   
}