package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractGeminator
import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
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
class Geminator : AbstractGeminator() {
   override  var substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3", "َC3ّ")) // EX: (مِجَنّ)
        substitutions.add(ExpressionInfixSubstitution("ْC3ُC3", "ُC3ّ")) // EX: (مُدُقّ)
        substitutions.add(ExpressionInfixSubstitution("َC3ْC3", "َC3ّ")) // EX: (دَفَّة)
        substitutions.add(ExpressionInfixSubstitution("ُC3ْC3", "ُC3ّ")) // EX: (جُرَّة)
        substitutions.add(ExpressionInfixSubstitution("ِC3ْC3", "ِC3ّ")) // EX: (سِكَّة)
        substitutions.add(ExpressionInfixSubstitution("اC3ِC3", "اC3ّ")) // EX: (مَاصَّة)
    }


}