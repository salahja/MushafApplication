package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.AbstractFaaMahmouz
import java.util.LinkedList

class PresentMahmouz : AbstractFaaMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("أُءْ", "أُو")) // EX: (أُوثَرُ، أوْتَمَرُ، )
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (يُتَآكَلُ، )
        substitutions.add(InfixSubstitution("ْءَا", "ْآ")) // EX: (يُنآدُ )
        substitutions.add(InfixSubstitution("ُءْ", "ُؤْ")) // EX: (يُؤْثَرُ، يُؤْتَمَرُ، )
        substitutions.add(InfixSubstitution("ُءَ", "ُؤَ")) // EX: (يُؤَثَّرُ، يُؤَاجَرُ، )
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (يُنْأَطَرُ، )
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (يُتَأكَّدُ، )
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (يُسْتأمَرُ، )
    }


}