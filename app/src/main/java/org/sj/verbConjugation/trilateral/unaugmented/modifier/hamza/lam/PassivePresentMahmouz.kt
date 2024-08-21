package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.lam

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractLamMahmouz

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class PassivePresentMahmouz : AbstractLamMahmouz() {
      override val substitutions: MutableList<Substitution> = ArrayList()

    init {
        substitutions.add(SuffixSubstitution("َءُ", "َأُ")) // EX: (هو يُوطَأُ)
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (هنَّ يُبدأْنَ، لم يُبْدَأْ)
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (هما يُبدأان، لن يُبدأ)
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (هم يُبدؤون)
        substitutions.add(
            InfixSubstitution(
                "ءِ",
                "ئِ"
            )
        ) // EX: (أنتِ تُبدئين، تُبائين، تُدائين، تشائين)
        substitutions.add(InfixSubstitution("اءُو", "اؤُو")) // EX: (هم يُباؤُون،  يُداؤون، يُشاؤون)
        substitutions.add(InfixSubstitution("اءُن", "اؤُن")) // EX: (هم يساؤُنَّ، يجاؤنَّ)
    }

   
}