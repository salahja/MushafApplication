package org.sj.nounConjugation.trilateral.unaugmented.modifier.instrumental.hamza

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
        substitutions.add(InfixSubstitution("َءَّا", "َآَّ")) // EX: (سآَّلَة)
        substitutions.add(InfixSubstitution("ْءَا", "ْآ")) // EX: (مِذآب)
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (مِذْأَب، مِذْأبَة)
        substitutions.add(InfixSubstitution("ْءً", "ْأً")) // EX: (مِمْأًى)
        substitutions.add(InfixSubstitution("يءً", "يئً")) // EX: (مِيئًى)
        substitutions.add(InfixSubstitution("يءَ", "يئَ")) // EX: (مِيئَد، مِيئَدة، مِيئاد)
    }

   
}