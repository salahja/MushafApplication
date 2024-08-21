package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractEinMahmouz

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
class PassivePastMahmouz : AbstractEinMahmouz() {
     override val substitutions: MutableList<InfixSubstitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("ُءِ", "ُئِ")) // EX: (سُئِلْتُ)
        substitutions.add(InfixSubstitution("ُءُ", "ُؤُ")) // EX: (رُؤُوا)
    }


}