package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractFaaMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.PrefixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
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
class FaaMahmouz : AbstractFaaMahmouz() {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ِء", "ِئ")) // EX: (مِئْزَر، مِئزرة، مِئزار)
        substitutions.add(PrefixSubstitution("ءَ", "أَ")) // EX: (أزَّارة، )
        substitutions.add(InfixSubstitution("الءَ", "الأَ")) // EX: (أتَّاءَة، أثّاءة)
        substitutions.add(InfixSubstitution("ءِ", "إِ")) // EX: (إزار، )
        substitutions.add(InfixSubstitution("ءُ", "أُ")) // EX: (أُخْذَة، )
    }

   
}