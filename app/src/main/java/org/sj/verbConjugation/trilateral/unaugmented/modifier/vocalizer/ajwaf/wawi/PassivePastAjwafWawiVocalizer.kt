package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.AbstractAjwafWawiVocalizer
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
class PassivePastAjwafWawiVocalizer : AbstractAjwafWawiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُوِC3ْ",
                "ِC3ْ"
            )
        ) // EX: (قِمْتُ، بِئْتُ، إِبْتُ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُوِC3ّ",
                "ِC3ّ"
            )
        ) // EX: (نحن صِنَّا، هن صِنَّ، أنا فِتُّ)
        substitutions.add(ExpressionInfixSubstitution("ُوِC3َ", "ِيC3َ")) // EX: (قِيمَ، بِيء، إيبَ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُوِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (قِيموا، بِيئوا، إيبوا)
    }

    /**
     * @return List
     * @todo Implement this
     * org.sj.verb.trilateral.Substitution.SubstitutionsApplier method
     */
   
}