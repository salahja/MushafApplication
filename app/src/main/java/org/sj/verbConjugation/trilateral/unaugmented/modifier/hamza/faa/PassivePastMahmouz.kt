package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class PassivePastMahmouz : AbstractFaaMahmouz() {
      override val substitutions: MutableList<Substitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("ءِ", "إِ")) // EX: (إيبَ، إِبْتُ)
        substitutions.add(PrefixSubstitution("ءُ", "أُ")) // EX: (أُكِلَ)
    }


}