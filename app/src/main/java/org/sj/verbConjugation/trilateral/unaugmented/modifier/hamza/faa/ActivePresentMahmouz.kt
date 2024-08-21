package org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.faa

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.hamza.AbstractFaaMahmouz

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
class ActivePresentMahmouz : AbstractFaaMahmouz() {
    override val substitutions: MutableList<InfixSubstitution> = ArrayList()

    init {
        substitutions.add(InfixSubstitution("أَءْ", "آ")) // EX: (آكُل)
        substitutions.add(InfixSubstitution("َءْ", "َأْ")) // EX: (يَأْكَلَ)
        substitutions.add(InfixSubstitution("َءُ", "َؤُ")) // EX: (يَؤُوب، يَؤُمُّ)
        substitutions.add(InfixSubstitution("َءِ", "َئِ")) // EX: (يَئِين، يَئِنُّ)
    }


}