package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.ein

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SuffixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractEinMahmouz

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
class ActivePresentMahmouz : AbstractEinMahmouz() {
     override val substitutions: MutableList<Substitution> = ArrayList()

    init {
        substitutions.add(SuffixSubstitution("ْءُ", "ْؤُ")) // EX: (لم يَمْؤُ)
        substitutions.add(InfixSubstitution("يْءَ", "يْئَ")) // EX: (يَيْئَس)
        substitutions.add(InfixSubstitution("ْءَ", "ْأَ")) // EX: (يَسْأَل)
        substitutions.add(InfixSubstitution("ءِ", "ئِ")) // EX: (يَنْئِمُ، يَئِدُ)
        substitutions.add(InfixSubstitution("ْءُ", "ْؤُ")) // EX: (يَضْؤُل)
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (أنتم تَؤُوا، هم يَؤُوا)
    }


}