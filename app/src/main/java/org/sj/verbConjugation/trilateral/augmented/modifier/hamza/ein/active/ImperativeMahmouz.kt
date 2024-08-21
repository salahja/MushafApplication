package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.AbstractEinMahmouz
import java.util.LinkedList

class ImperativeMahmouz : AbstractEinMahmouz() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْءُ", "وْءُ")) // EX: (اسْتَوْءُوا)
        substitutions.add(SuffixSubstitution("اءِ", "اءِ")) // EX: (رَاءِ)
        substitutions.add(SuffixSubstitution("َءِ", "َئِ")) // EX: (انْفَئِ، اشْتَئِ)
        substitutions.add(SuffixSubstitution("ْءِ", "ْئِ")) // EX: (أنْئِ)
        substitutions.add(SuffixSubstitution("َءِّ", "َئِّ")) // EX: (رَئِّ)
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (أسْئِمْ، اسْتَرْئِفْ، أوْئِبْ، انْذَئِجْ، ابْتَئِسْ، اتَّئِدْ، لائِمْ)
        substitutions.add(InfixSubstitution("َءِّ", "َئِّ")) // EX: (رَئِّسْ،  )
        substitutions.add(InfixSubstitution("َءَّ", "َأَّ")) // EX: (ترَأَّفْ، )
        substitutions.add(InfixSubstitution("َءُّ", "َؤُّ")) // EX: (رَؤُّوا،  )
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (انْفَؤُوا، ارْتَؤُوا)
        substitutions.add(InfixSubstitution("ْءُ", "ْؤُ")) // EX: (انْؤُوا، )
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (اجْأَلَّ، يَجْأوِّي، )
        substitutions.add(InfixSubstitution("اءَ", "اءَ")) // EX: (تَفاءَلْ، )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (راؤُوا، )
    }

   
}