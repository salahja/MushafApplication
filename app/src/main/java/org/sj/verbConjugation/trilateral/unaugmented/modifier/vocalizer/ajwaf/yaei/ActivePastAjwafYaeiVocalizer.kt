package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.AbstractAjwafYaeiVocalizer
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
class ActivePastAjwafYaeiVocalizer : AbstractAjwafYaeiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيَC3ْ",
                "ِC3ْ"
            )
        ) // EX: (بِعْتُ، قِئتُ، إِنْتُ،)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيَC3ّ",
                "ِC3ّ"
            )
        ) // EX: (نحن لِنَّا، هن لِنَّ، أنا لِتُّ)
        substitutions.add(ExpressionInfixSubstitution("َيَC3َ", "َاC3َ")) // EX: (باع، قاء، آن،)
        substitutions.add(
            ExpressionInfixSubstitution(
                "َيَC3ُ",
                "َاC3ُ"
            )
        ) // EX: (باعوا، قاؤوا، آنوا،)
    }

    /**
     * @return List
     * @todo Implement this
     * org.sj.verb.trilateral.Substitution.SubstitutionsApplier method
     */

}