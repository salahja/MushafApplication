package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.AbstractEinMahmouz
import java.util.LinkedList

class PastMahmouz : AbstractEinMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (أُسْئِمَ، لُوئِمَ، انْذُئِجَ، ابتُئِسَ، تُسُوئِلَ، استُرئِفَ، اتُّئِدَ، )
        substitutions.add(InfixSubstitution("ْءُ", "ْؤُ")) // EX: (اجْؤُلَّ، )
        substitutions.add(InfixSubstitution("ُءُ", "ُؤُ")) // EX: (ارتُؤُوا، )
        substitutions.add(InfixSubstitution("ُءِّ", "ُئِّ")) // EX: (رُئِّسَ، تُرُئِّفَ، )
        substitutions.add(InfixSubstitution("ُءُّ", "ُؤُّ")) // EX: (رُؤُّوا، )
    }

   
}