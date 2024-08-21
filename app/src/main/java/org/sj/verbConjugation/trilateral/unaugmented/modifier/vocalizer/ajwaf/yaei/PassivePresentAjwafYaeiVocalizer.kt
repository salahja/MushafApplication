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
class PassivePresentAjwafYaeiVocalizer : AbstractAjwafYaeiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3ُ",
                "َاC3ُ"
            )
        ) // EX: (يُباع، يُبات، لن تُباتوا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3ِ",
                "َاC3ِ"
            )
        ) // EX: (تُباعِينَ، تُباتين، لن تُباتي )
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3َ",
                "َاC3َ"
            )
        ) // EX: (تُباعان، تُباتان، لن يُباتَ، يُباتَنَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3ْ",
                "َC3ْ"
            )
        ) // EX: (يُبَعْنَ، يُبَتْنَ، لم تُبَتْ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيَC3ّ",
                "َC3ّ"
            )
        ) // EX: (أنتن تُلَنَّ، هن يُلَنَّ)
    }


}