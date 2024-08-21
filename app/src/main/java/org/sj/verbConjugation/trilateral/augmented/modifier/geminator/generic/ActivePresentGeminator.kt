package org.sj.verbConjugation.trilateral.augmented.modifier.geminator.generic

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.ExpressionSuffixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import java.util.LinkedList

class ActivePresentGeminator : SubstitutionsApplier() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْC3ِC3ُ", "ِC3ُّ")) // EX: (يُتِمُّ)
        substitutions.add(ExpressionInfixSubstitution("ْC3ِC3َ", "ِC3َّ")) // EX: (يُتِمَّان)
        substitutions.add(ExpressionInfixSubstitution("ْC3ِC3ِ", "ِC3ِّ")) // EX: (تُتِمِّينَ)
        substitutions.add(ExpressionSuffixSubstitution("ْC3ِC3ْ", "ِC3َّ")) // EX: (لم يُتِمَّ)
        substitutions.add(ExpressionInfixSubstitution("َC3ِC3ُ", "َC3ُّ")) // EX: (يأتَمّ)
        substitutions.add(ExpressionInfixSubstitution("َC3ِC3َ", "َC3َّ")) // EX: (يأتَمّان)
        substitutions.add(ExpressionInfixSubstitution("َC3ِC3ِ", "َC3ِّ")) // EX: (تأتَمِّينَ)
        substitutions.add(ExpressionSuffixSubstitution("َC3ِC3ْ", "َC3َّ")) // EX: (لم يأتَمّ)
        substitutions.add(ExpressionInfixSubstitution("اC3َC3ُ", "اC3ُّ")) // EX: (يَتَصَامُّ،)
        substitutions.add(ExpressionInfixSubstitution("اC3َC3َ", "اC3َّ")) // EX: (يَتَصَامَّا،)
        substitutions.add(ExpressionInfixSubstitution("اC3َC3ِ", "اC3ِّ")) // EX: (تَتَصَامِّي،)
        substitutions.add(ExpressionSuffixSubstitution("اC3َC3ْ", "اC3َّ")) // EX: (لم يَتَصَامَّ،)
        substitutions.add(ExpressionInfixSubstitution("اC3ِC3ُ", "اC3ُّ")) // EX: (يُحاجّ)
        substitutions.add(ExpressionInfixSubstitution("اC3ِC3َ", "اC3َّ")) // EX: (يُحاجّا)
        substitutions.add(ExpressionInfixSubstitution("اC3ِC3ِ", "اC3ِّ")) // EX: (تُحاجِّي)
        substitutions.add(ExpressionSuffixSubstitution("اC3ِC3ْ", "اC3َّ")) // EX: (لم  ي Kُحاجَّ)
    }


}