package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.AbstractEinMahmouz
import java.util.LinkedList

class PresentMahmouz : AbstractEinMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("يْءَ", "يْئَ")) // EX: (يُسْتَيْئَسُ، )
        substitutions.add(InfixSubstitution("وْءَ", "وْءَ")) // EX: (يُسْتَوْءَلُ، يُسْتَوْءَى، )
        substitutions.add(InfixSubstitution("وءَ", "وْءَ")) // EX: (يُوءَبُ، )
        substitutions.add(
            InfixSubstitution(
                "ْءَ",
                "ْأَ"
            )
        ) // EX: (يُسْأَمُ، يُجْأَلُّ، يُسْتَرْأَفُ، )
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (يُنْذَأجُ، يُبْتَأَسُ، )
        substitutions.add(InfixSubstitution("َءَّ", "َأَّ")) // EX: (يُرَأَّسُ، يُتَرَأَّفُ، )
        substitutions.add(InfixSubstitution("َّءَ", "َّأَ")) // EX: (يُتَّأدُ، )
        substitutions.add(InfixSubstitution("اءَ", "اءَ")) // EX: (يُلاءَمُ، يُتَساءَلُ، )
    }

   
}