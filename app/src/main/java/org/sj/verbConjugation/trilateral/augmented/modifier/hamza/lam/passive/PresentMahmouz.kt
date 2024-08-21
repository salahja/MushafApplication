package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.passive

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.lam.AbstractLamMahmouz
import java.util.LinkedList

class PresentMahmouz : AbstractLamMahmouz() {
    override val substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("اءُ", "اءُ")) // EX: (يُسْتَاءُ، يُساءُ)
        substitutions.add(
            SuffixSubstitution(
                "َءُ",
                "َأُ"
            )
        ) // EX: (يُجْزَأُ، يُكافَأُ، يُنْفَقَأُ، يُبْتَدَأُ، يُتَدارَأُ، يُسْتَهْزَأُ، يُحْزَوْزَأُ، )
        substitutions.add(SuffixSubstitution("َّءُ", "َّأُ")) // EX: (يُجَزَّأُ، يُخَبَّأُ، )
        substitutions.add(InfixSubstitution("اءُ", "اؤُ")) // EX: (يُسْتَاؤُونَ، يُساؤُونَ)
        substitutions.add(InfixSubstitution("اءِ", "ائِ")) // EX: (تُسْتَائِينَ،  تُسائِينَ)
        substitutions.add(
            InfixSubstitution(
                "َءْ",
                "َأْ"
            )
        ) // EX: (يُجْزَأْنَ، لم يُجْزَأْ، يُكافَأْنَ، يُنْفَقَأْنَ، يُبْتَدَأْنَ، يُتَدَارَأْنَ، يُسْتَهْزَأْنَ، يُحْزَوْزَأْنَ)
        substitutions.add(
            InfixSubstitution(
                "َءَ",
                "َأَ"
            )
        ) // EX: ( يُجْزَأَانِ، لن يُجْزَأَ، يُكافَأَانِ، يُنْفَقَأَانِ، يُبْتَدَأَانِ، يُتَدَارَأَانِ، يُسْتَهْزَأَانِ، يُحْزَوْزَأَانِ، )
        substitutions.add(
            InfixSubstitution(
                "َءُ",
                "َؤُ"
            )
        ) // EX: (يُجْزَؤُونَ، يُكافَؤُونَ، يُنْفَقَؤُونَ، يُبْتَدَؤُونَ، يُتَدَارَؤُونَ، يُسْتَهْزَؤُونَ، يُحْزَوْزَؤُونَ، )
        substitutions.add(
            InfixSubstitution(
                "َّءْ",
                "َّأْ"
            )
        ) // EX: (يُجَزَّأْنَ، لم يُجَزَّأْ، يُخَبَّأْنَ، )
        substitutions.add(
            InfixSubstitution(
                "َّءَ",
                "َّأَ"
            )
        ) // EX: ( يُجَزَّأَانِ، لن يُجَزَّأَ، يُخَبَّأَانِ، )
        substitutions.add(InfixSubstitution("َّءُ", "َّؤُ")) // EX: (يُجَزَّؤُونَ، يُخَبَّؤُونَ، )
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (تُجْزَئِينَ، تُجَزَّئِينَ، تُكافَئِينَ، تُنْفَقَئِينَ، تُبْتَدَئِينَ، تُتَدارَئِينَ، تُخَبَّئِينَ، تُسْتَهْزَئِينَ، تُحْزَوْزَئِينَ، )
    }

   
}