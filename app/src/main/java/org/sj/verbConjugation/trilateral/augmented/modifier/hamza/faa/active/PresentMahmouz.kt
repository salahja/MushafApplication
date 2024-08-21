package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.AbstractFaaMahmouz
import java.util.LinkedList

class PresentMahmouz : AbstractFaaMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("أُءْ", "أُو")) // EX: (أنا أُوثِرُ، )
        substitutions.add(InfixSubstitution("أَءْ", "آ")) // EX: (أنا آتَمِرُ، )
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (يَتَآكَلُ)
        substitutions.add(InfixSubstitution("ْءَا", "ْآ")) // EX: (يَنْآدُ)
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (يَأْتَمِرُ، يسْتَأْمِرُ)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (يَتَأَكَّدُ، )
        substitutions.add(InfixSubstitution("َءِ", "َئِ")) // EX: (يَسْتَئِمُّ، )
        substitutions.add(InfixSubstitution("ُءِ", "ُئِ")) // EX: (يُئِيسُ، )
        substitutions.add(InfixSubstitution("ُءْ", "ُؤْ")) // EX: (يُؤْثِرُ، )
        substitutions.add(InfixSubstitution("ُءَ", "ُؤَ")) // EX: (يُؤَثِّرُ، يُؤَاجَرُ، )
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (يَنْأطِرُ، )
    }


}