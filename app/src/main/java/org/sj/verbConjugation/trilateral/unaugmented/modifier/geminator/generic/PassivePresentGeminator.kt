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
class PassivePresentGeminator : SubstitutionsApplier() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3ُ", "َC3ُّ"))
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3َ", "َC3َّ"))
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3ِ", "َC3ِّ"))
        substitutions.add(ExpressionSuffixSubstitution("ْC3َC3ْ", "َC3َّ"))
    }


}