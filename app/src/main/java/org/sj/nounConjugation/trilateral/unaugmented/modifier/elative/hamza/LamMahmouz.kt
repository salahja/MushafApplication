package org.sj.nounConjugation.trilateral.unaugmented.modifier.elative.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractLamMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import java.util.LinkedList

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
class LamMahmouz : AbstractLamMahmouz() {
   override  var substitutions: MutableList<Substitution> = LinkedList()

    init {
        substitutions.add(SuffixSubstitution("َءُ", "َأُ")) // EX: (هو الأجرأُ، )
        substitutions.add(SuffixSubstitution("َءِ", "َأِ")) // EX: (كالأجرأِ، )
        substitutions.add(InfixSubstitution("وْءَ", "وءَ")) // EX: (السُّوءَى، )
        substitutions.add(InfixSubstitution("َءَا", "َآ")) // EX: (الأجرآن، )
        substitutions.add(InfixSubstitution("َءُو", "َؤُو")) // EX: (الأجرؤون، )
        substitutions.add(InfixSubstitution("َءَ", "َأَ")) // EX: (رأيتُ الأجرأَ، )
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (الجُرْأَى، )
        substitutions.add(InfixSubstitution("َءِ", "َئِ")) // EX: (الأجرئِينَ، )
    }

   
}