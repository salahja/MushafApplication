package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate

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
        substitutions.add(ExpressionInfixSubstitution("ْC3َC3", "َC3ّ")) // EX: (أصَمّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َC3ْC3",
                "َC3ّ"
            )
        ) // EX: (صَمَّاء، رَيَّان، رَثٌّ)
    }


}