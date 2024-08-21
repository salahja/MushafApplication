package org.sj.nounConjugation.trilateral.unaugmented.modifier.exaggeration.hamza

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
        substitutions.add(InfixSubstitution("اءًا", "اءً")) // EX: (بدّاءً،  )
        substitutions.add(SuffixSubstitution("اءُ", "اءُ")) // EX: (البدّاءُ )
        substitutions.add(SuffixSubstitution("اءِ", "اءِ")) // EX: (البدّاءِ )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (بدّاؤون )
        substitutions.add(InfixSubstitution("اءِ", "ائِ")) // EX: (بدّائِين )
        substitutions.add(InfixSubstitution("َءَة", "َأَة")) // EX: (هُزَأَة )
    }


}