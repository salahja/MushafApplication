package org.sj.nounConjugation.trilateral.unaugmented.modifier.activeparticiple.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractEinMahmouz
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
class EinMahmouz : AbstractEinMahmouz() {
   override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("اءِ", "ائِ")) // EX: (سائِل)
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (ناؤُونَ)
    }

   
}