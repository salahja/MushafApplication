package org.sj.nounConjugation.trilateral.unaugmented.modifier.passiveparticiple.hamza

import org.sj.nounConjugation.trilateral.unaugmented.modifier.AbstractEinMahmouz
import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
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
class EinMahmouz : AbstractEinMahmouz() {
   override  var substitutions: MutableList<InfixSubstitution> = LinkedList()

    init {
        substitutions.add(InfixSubstitution("وْءُ", "وْءُ")) // EX: (مَوْءُود)
        substitutions.add(InfixSubstitution("يْءُ", "يْئُ")) // EX: (مَيْئُوس)
        substitutions.add(InfixSubstitution("ْءُ", "ْؤُ")) // EX: (مَسْؤُول)
        substitutions.add(InfixSubstitution("ْءِ", "ْئِ")) // EX: (مَرْئِيّ)
    }


}