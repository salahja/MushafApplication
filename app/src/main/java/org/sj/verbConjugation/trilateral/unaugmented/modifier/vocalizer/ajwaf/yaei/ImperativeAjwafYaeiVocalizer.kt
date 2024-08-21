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
class ImperativeAjwafYaeiVocalizer : AbstractAjwafYaeiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْيِC3ُ",
                "C1ِيC3ُ"
            )
        ) // EX: (بِيعُوا، بِيعُنَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْيِC3ِ",
                "C1ِيC3ِ"
            )
        ) // EX: (بيعي، بيعِنَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْيِC3َ",
                "C1ِيC3َ"
            )
        ) // EX: (بيعا، بيعانَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْيِC3ْ",
                "C1ِC3ْ"
            )
        ) // EX: (بِعْ، بِعْنانِّ)
        substitutions.add(ExpressionInfixSubstitution("اC1ْيِC3ّ", "C1ِC3ّ")) // EX: (أنتن لِنَّ)
    }


}