package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractLamMahmouz
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
class ActivePastMahmouz : AbstractLamMahmouz() {
     override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (بَدأتُ، وجأتُ)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (بَدَأَ، وَجَأَ)
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (بَدَؤُوا،)
        substitutions.add(
            InfixSubstitution(
                "ِء",
                "ِئ"
            )
        ) // EX: (ظَمِئَ، ظَمِئْتُ، ظَمِئُوا، وَطِئَ، هَوِئَ، قِئْتُ)
        substitutions.add(
            InfixSubstitution(
                "ُء",
                "ُؤ"
            )
        ) // EX: (جَرُؤَ، جَرُؤْتُ، جَرُؤُوا، وَضُؤَ، بُؤْتُ، )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (باؤوا، قاؤوا )
    }


}