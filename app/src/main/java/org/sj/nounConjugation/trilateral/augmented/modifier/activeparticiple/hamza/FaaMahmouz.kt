package org.sj.nounConjugation.trilateral.augmented.modifier.activeparticiple.hamza

import org.sj.nounConjugation.trilateral.augmented.modifier.AbstractFaaMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
      override  var substitutions: MutableList<InfixSubstitution?> = LinkedList<InfixSubstitution?>()

    init {
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (مُتآكِلٌ)
        substitutions.add(InfixSubstitution("ْءَا", "ْآ")) // EX: (مُنْآد)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (مُتأكِّد)
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (مُستَأْمِر)
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (مُنْأَطِرٌ)
        substitutions.add(InfixSubstitution("ءِ", "ئِ")) // EX: (مُئِيسٌ)
        substitutions.add(InfixSubstitution("ُء", "ُؤ")) // EX: (مُؤْثِرٌ، مُؤَثِّرٌ، مُؤَاجِرٌ)
    }


}