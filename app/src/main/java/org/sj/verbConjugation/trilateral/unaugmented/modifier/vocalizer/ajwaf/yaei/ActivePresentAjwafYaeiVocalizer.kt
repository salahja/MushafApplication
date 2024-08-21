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
class ActivePresentAjwafYaeiVocalizer : AbstractAjwafYaeiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("ْيِC3ُ", "ِيC3ُ")) // EX: (يبيع، يقيء، يئين)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3ِ",
                "ِيC3ِ"
            )
        ) // EX: (تبيعين، لن تبيعي، تقيئين، تئينين)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3َ",
                "ِيC3َ"
            )
        ) // EX: (تبيعان، لن يبيعا، تقيئان، تئينان)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3ْ",
                "ِC3ْ"
            )
        ) // EX: (تَبِعْنَ، لم يَبِعْ، يَقِئْ، يَئِنْ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْيِC3ّ",
                "ِC3ّ"
            )
        ) // EX: (أنتن تَلِنَّ ، هن يَلِنَّ)
    }


}