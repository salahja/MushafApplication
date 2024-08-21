package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.AbstractLamMahmouz
import java.util.LinkedList

class PastMahmouz : AbstractLamMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            InfixSubstitution(
                "َءْ",
                "َأْ"
            )
        ) // EX: (أجْزَأْتُ، كافأْتُ، انفقَأْتُ، ابتدَأْتُ، تدارَأْتُ، استَهْزَأْتُ، احْزَوْزَأْتُ)
        substitutions.add(
            InfixSubstitution(
                "َءَ",
                "َأَ"
            )
        ) // EX: (أجْزَأَ، كافَأَ، انْفَقَأَ، ابتدَأَ، تدارَأَ، استَهْزَأَ، احْزَوْزَأَ، )
        substitutions.add(
            InfixSubstitution(
                "َءُ",
                "َؤُ"
            )
        ) // EX: (أجْزَؤُوا، كافَؤُوا، انفقَؤُوا، ابتدَؤُوا، تدارَؤُوا، استَهْزَؤُوا، احْزَوْزَؤُوا)
        substitutions.add(InfixSubstitution("َّءْ", "َّأْ")) // EX: (جَزَّأْتُ، تَخَبَّأْتُ، )
        substitutions.add(InfixSubstitution("َّءَ", "َّأَ")) // EX: (جَزَّأَ، تَخَبَّأَ،)
        substitutions.add(InfixSubstitution("َّءُ", "َّؤُ")) // EX: (جَزَّؤُوا، تَخَبَّؤُوا، )
        substitutions.add(InfixSubstitution("اءَ", "اءَ")) // EX: (أساءَ، استاءَ، )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (أساؤُوا، استاؤُوا، )
    }

   
}