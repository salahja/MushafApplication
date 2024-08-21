package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractLamMahmouz
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
class LamMahmouz : AbstractLamMahmouz() {
   override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("ِء", "ِئ")) // EX: (ظَامِئ)
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (باؤون )
        substitutions.add(InfixSubstitution("ِآ", "ِئَا")) // EX: (آثِئان)
    }

   
}