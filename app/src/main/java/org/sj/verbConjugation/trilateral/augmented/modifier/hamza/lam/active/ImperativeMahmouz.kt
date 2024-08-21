package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.AbstractLamMahmouz
import java.util.LinkedList

class ImperativeMahmouz : AbstractLamMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("يءَ", "يئَ")) // EX: (أَسِيئَا، )
        substitutions.add(InfixSubstitution("يءُ", "يئُ")) // EX: (أَسِيئُوا، )
        substitutions.add(
            InfixSubstitution(
                "ِء",
                "ِئ"
            )
        ) // EX: (أُجْزِئْ، كافِئْ، انْفَقِئْ، ابتَدِئْ، استهزِئْ، احْزَوْزِئْ، أَسِئْ، )
        substitutions.add(InfixSubstitution("ءِ", "ئِ")) // EX: (تكافئي )
        substitutions.add(InfixSubstitution("ِّء", "ِّئ")) // EX: (جَزِّئْ، )
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (تدارَأْ، تدارَأْنَ، اسْتَأْ)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (تدارَأَا، )
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (تدارَؤُوا، )
        substitutions.add(InfixSubstitution("َّءْ", "َّأْ")) // EX: (تَخَبَّأْ، تَخَبَّأْنَ، )
        substitutions.add(InfixSubstitution("َّءَ", "َّأَ")) // EX: (تَخَبَّأَا، )
        substitutions.add(InfixSubstitution("َّءُ", "َّؤُ")) // EX: (تَخَبَّؤُوا، )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (اسْتاؤُوا، )
    }


}