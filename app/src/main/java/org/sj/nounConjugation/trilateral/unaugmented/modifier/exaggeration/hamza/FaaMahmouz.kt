package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza

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
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (مآنِيف،)
        substitutions.add(PrefixSubstitution("الءَ", "الأَ")) // EX: (الأَكَّال، الأكول،)
        substitutions.add(PrefixSubstitution("الءُ", "الأُ")) // EX: (الأُكَلَة،)
        substitutions.add(PrefixSubstitution("ءَ", "أَ")) // EX: (أَكَّال، أكول،)
        substitutions.add(PrefixSubstitution("ءُ", "أُ")) // EX: (أُكَلَة،)
        substitutions.add(InfixSubstitution("ِءْ", "ِئْ")) // EX: (مِئْناف،)
    }


}