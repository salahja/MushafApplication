package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple

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
   override    var substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC3ِC3",
                "اC3ّ"
            )
        ) // EX: (مادٌّ، مادًّا، مادٍّ، مادَّةٌ، مادُّون، مادِّينَ)
    }


}