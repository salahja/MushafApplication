package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.ein.AbstractEinMahmouz
import java.util.LinkedList

class PresentMahmouz : AbstractEinMahmouz() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْءُ", "وْءُ")) // EX: (يَسْتَوْءُونَ)
        substitutions.add(InfixSubstitution("يْءَ", "يْئَ")) // EX: (يَيْئَسَ)
        substitutions.add(SuffixSubstitution("اءِ", "اءِ")) // EX: (لم يُراءِ،)
        substitutions.add(SuffixSubstitution("َءِ", "َئِ")) // EX: (لم يَنْفَئِ، لم يَشْتَئِ،)
        substitutions.add(SuffixSubstitution("َءِّ", "َئِّ")) // EX: (لم يُرَئِّ)
        substitutions.add(SuffixSubstitution("ْءِ", "ْئِ")) // EX: (لم يُنْئِ، )
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (يُسْئِم، يَسْتَرئِفُ، يُوئِبُ، يَنْذَئِجُ، يَبْتَئِسُ، يَتَّئِدُ، يُلائِمُ)
        substitutions.add(InfixSubstitution("ْءُ", "ْؤُ")) // EX: (يُنْؤُونَ، )
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (يَجْأَلُّ، يَجْأوِّي، )
        substitutions.add(InfixSubstitution("َءَّ", "َأَّ")) // EX: (يترَأَّفُ، )
        substitutions.add(InfixSubstitution("َءُّ", "َؤُّ")) // EX: (يُرَؤُّونَ، )
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (يَنْفَؤُونَ، يَرْتَؤُونَ)
        substitutions.add(InfixSubstitution("َءِّ", "َئِّ")) // EX: (يُرَئِّسُ، )
        substitutions.add(InfixSubstitution("اءَ", "اءَ")) // EX: (يَتَفاءَلُ، )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (يُراؤُونَ، )
    }


}