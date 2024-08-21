package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.PrefixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractFaaMahmouz

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
class ImperativeMahmouz : AbstractFaaMahmouz() {
      override val substitutions: MutableList<Substitution> = ArrayList()

    init {
        substitutions.add(ExpressionInfixSubstitution("اءْC2َ", "ائْC2َ")) // EX: (ائْبَه)
        substitutions.add(ExpressionInfixSubstitution("اءْC2ِ", "ائْC2ِ")) // EX: (ائْسِر)
        substitutions.add(ExpressionInfixSubstitution("اءْC2ُ", "اؤْC2ُ")) // EX: (اؤْنُث)
        substitutions.add(ExpressionInfixSubstitution("ءُC3", "أُC3")) // EX: (أُبْ)
        substitutions.add(PrefixSubstitution("ءِ", "إِ")) // EX: (أنتَ إِنَّ، إمْ)
        substitutions.add(PrefixSubstitution("ءُ", "أُ")) // EX: (أنتِ أُولي)
    }


}