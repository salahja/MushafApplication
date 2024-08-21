package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.AbstractFaaMahmouz
import java.util.LinkedList

class ImperativeMahmouz : AbstractFaaMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("أَءْ", "آ")) // EX: (آثِرْ،  )
        substitutions.add(InfixSubstitution("ءَا", "آ")) // EX: (آجِرْ، تآكَلْ، )
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (انْأطِرْ، )
        substitutions.add(InfixSubstitution("اءْ", "ائْ")) // EX: (ائْتَمِرْ، )
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (تَأَكَّدْ، )
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (اسْتَأْمِرْ، )
        substitutions.add(InfixSubstitution("َءِ", "َئِ")) // EX: (اسْتَئِمَّ، أَئِسْ)
        substitutions.add(InfixSubstitution("ءَ", "أَ")) // EX: (أثِّرْ، )
    }


}