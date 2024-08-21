package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.AbstractEinMahmouz
import java.util.LinkedList

class PastMahmouz : AbstractEinMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْءَ", "وْءَ")) // EX: (اسْتَوْءَى)
        substitutions.add(InfixSubstitution("يْءَ", "يْئَ")) // EX: (ايْئَسَ)
        substitutions.add(
            InfixSubstitution(
                "ْءَ",
                "ْأَ"
            )
        ) // EX: (أسْأمَ، اجْأَلَّ، استرْأَفَ، اجْأَوَّى)
        substitutions.add(InfixSubstitution("َءَّ", "َأَّ")) // EX: (رأَّسَ، ترأَّفَ، )
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (انذَأَجَ، ابتَأَسَ، )
        substitutions.add(InfixSubstitution("َّءَ", "َّأَ")) // EX: (اتَّأَدَ، )
        substitutions.add(InfixSubstitution("اءَ", "اءَ")) // EX: (لاءَمَ، تفاءَلَ، )
    }


}