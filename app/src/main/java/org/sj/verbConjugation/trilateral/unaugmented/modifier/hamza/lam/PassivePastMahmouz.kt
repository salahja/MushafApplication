package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractLamMahmouz

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
class PassivePastMahmouz : AbstractLamMahmouz() {
      override val substitutions: MutableList<Substitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("ِء", "ِئ")) // EX: (بُدِئَ، ظُمِئَ، جُرِئَ، هُوِئَ)
        substitutions.add(InfixSubstitution("يءُو", "يئُو")) // EX: (بِيئُوا، قِيئُوا، شِيئُوا)
        substitutions.add(SuffixSubstitution("يءَ", "يءَ")) // EX: (بِيءَ، قِيءَ، شِيءَ، سِيءَ)
        substitutions.add(InfixSubstitution("يءَ", "يئَ")) // EX: (بِيئَا، قِيئَا، شِيئَا، سِيئَتْ)
        substitutions.add(InfixSubstitution("ُءْ", "ُؤْ")) // EX: (جُؤْتُ)
    }

   
}