package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.AbstractLamMahmouz
import java.util.LinkedList

class PresentMahmouz : AbstractLamMahmouz() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("يءُ", "يءُ")) // EX: (يُسِيءُ، )
        substitutions.add(SuffixSubstitution("يءَ", "يءَ")) // EX: (لن يُسِيءَ، )
        substitutions.add(InfixSubstitution("يء", "يئ")) // EX: (يُسِيئَانِ، يُسِيئُونِ)
        substitutions.add(SuffixSubstitution("َءُ", "َأُ")) // EX: (يَتَدارَأُ، )
        substitutions.add(SuffixSubstitution("َّءُ", "َّأُ")) // EX: (يَتَخَبَّأُ، )
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (لن يَتَدارَأَ، يَتَدارَأَانِ، )
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (تَتَدارَؤُونَ، )
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (لم يَتَدارَأْ، تَتَدارَأْنَ، )
        substitutions.add(InfixSubstitution("َّءَ", "َّأَ")) // EX: (لن يتخبَّأَ، يَتَخَبَّأَانِ، )
        substitutions.add(InfixSubstitution("َّءُ", "َّؤُ")) // EX: (تتخبَّؤُونَ، )
        substitutions.add(InfixSubstitution("َّءْ", "َّأْ")) // EX: (لم يتخبَّأْ، تتخبَّأْنَ، )
        substitutions.add(SuffixSubstitution("اءُ", "اءُ")) // EX: (يَسْتاءُ، )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (يَسْتاؤُونَ، )
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (تَسْتائِينَ، تُسِيئِينَ، تتدارَئِينَ، تتخبَّئِينَ، )
        substitutions.add(
            InfixSubstitution(
                "ِء",
                "ِئ"
            )
        ) // EX: (يُجْزِئُ، لن يُجْزِئَ، لم يُجْزِئْ، يكافِئُ، يَنْفقِئُ، يَبْتدِئُ، يستهزِئُ، يَحْزَوْزِئُ، )
        substitutions.add(
            InfixSubstitution(
                "ِّء",
                "ِّئ"
            )
        ) // EX: (يُجْزِّئُ، لن يُجْزِّئَ، لم يُجْزِّئْ، )
    }

   
}