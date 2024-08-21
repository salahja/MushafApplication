package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class PassivePresentMahmouz : AbstractFaaMahmouz() {
      override val substitutions: MutableList<InfixSubstitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("أُءْ", "أُو")) // EX: (أُوكَل)
        substitutions.add(InfixSubstitution("ُءْ", "ُؤْ")) // EX: (يُؤْكَل)
        substitutions.add(InfixSubstitution("ُءَ", "ُؤَ")) // EX: (يُؤَاب، يُؤَان)
    }


}