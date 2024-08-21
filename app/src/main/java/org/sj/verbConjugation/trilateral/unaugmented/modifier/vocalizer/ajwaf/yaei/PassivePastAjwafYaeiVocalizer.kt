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
class PassivePastAjwafYaeiVocalizer : AbstractAjwafYaeiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ُيِC3ّ", "ُC3ّ")) // EX: ( بُتُّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُيِC3ْ",
                "ُC3ْ"
            )
        ) // EX: (بُعْتُ، قُئتُ، أُنْتُ، شُئْتُ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُيِC3ّ",
                "ُC3ّ"
            )
        ) // EX: (نحن لُنَّا، هن لُنَّ، أنا لِتُّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُيِC3َ",
                "ِيC3َ"
            )
        ) // EX: (بِيع، قِيء، إين، شِيء، بِيت)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ُيِC3ُ",
                "ِيC3ُ"
            )
        ) // EX: (بِيعوا، قِيئوا، إينوا، شِيئوا، بِيتوا)
    }

    /**
     * @return List
     * @todo Implement this
     * org.sj.verb.trilateral.Substitution.SubstitutionsApplier method
     */
   
}