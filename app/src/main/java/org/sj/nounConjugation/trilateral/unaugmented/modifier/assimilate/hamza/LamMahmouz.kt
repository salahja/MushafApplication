package org.sj.nounConjugation.trilateral.unaugmented.modifier.assimilate.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractLamMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
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
class LamMahmouz : AbstractLamMahmouz() {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يءُ", "يءُ")) // EX: (الجريءُ، )
        substitutions.add(SuffixSubstitution("يءَ", "يءَ")) // EX: (الجريءَ، )
        substitutions.add(SuffixSubstitution("يءِ", "يءِ")) // EX: (الجريءِ، )
        substitutions.add(InfixSubstitution("يءٌ", "يءٌ")) // EX: (جريءٌ، )
        substitutions.add(InfixSubstitution("يءٍ", "يءٍ")) // EX: (جريءٍ، )
        substitutions.add(InfixSubstitution("يء", "يئ")) // EX: (جَريئاً، جَرِيئَةً، جَرِيئان)
        substitutions.add(InfixSubstitution("ِء", "ِئ")) // EX: (ظَمِئٌ، )
        substitutions.add(InfixSubstitution("ْءَا", "ْآ")) // EX: (ظَمْآن، )
    }


}