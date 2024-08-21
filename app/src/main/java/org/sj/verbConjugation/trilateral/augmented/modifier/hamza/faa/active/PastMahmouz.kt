package org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.active

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.augmented.modifier.hamza.faa.AbstractFaaMahmouz
import java.util.LinkedList

class PastMahmouz : AbstractFaaMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("أَءْ", "آ")) // EX: (آثَرَ، آتَى، آخَى، آوَى، )
        substitutions.add(
            InfixSubstitution(
                "ءَا",
                "آ"
            )
        ) // EX: (آضَّ، تَآجَّ، آجَرَ، تآكَلَ، آوَبَ، انْآدَ، تآوَبَ، آيَسَ، آيَدَ، آسَى، تآخَى، آسى، تآثَى، تآوَى، )
        substitutions.add(
            InfixSubstitution(
                "ءَ",
                "أَ"
            )
        ) // EX: (أمَّمَ، تأمَّمَ، اسْتأمَّ، أثَّر، انأطَرَ، تأكَّدَ، أوَّب، تأوَّبَ، أيَّدَ، تأيَّدَ، أبَّى، تأسَّى، أدَّى، تأتَّى، أوَّى، تأوَّى، )
        substitutions.add(
            InfixSubstitution(
                "اءْ",
                "ائْ"
            )
        ) // EX: (ائْتَمَّ، ائْتَثَأَ، ائْتَمَرَ، ائْتَالَ، ائْتامَ، ائْتَسَى، ائْتَرَى، ائْتَوَى، )
        substitutions.add(
            InfixSubstitution(
                "َءْ",
                "َأْ"
            )
        ) // EX: (اسْتَأْمَرَ، اسْتَأْوَرَ، استأيَكَ، اسْتَأمَى، استأنَى، اسْتَأْوَى، )
    }


}