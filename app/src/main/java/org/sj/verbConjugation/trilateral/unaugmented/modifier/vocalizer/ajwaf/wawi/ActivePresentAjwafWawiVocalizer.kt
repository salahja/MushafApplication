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
class ActivePresentAjwafWawiVocalizer : AbstractAjwafWawiVocalizer() {
      override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوُC3ُ",
                "ُوC3ُ"
            )
        ) // EX: (يَقُومُ، يبوء، يؤوب)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوُC3ِ",
                "ُوC3ِ"
            )
        ) // EX: (تقومين، لن تقومِي، تبوئين، تؤوبين)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوُC3َ",
                "ُوC3َ"
            )
        ) // EX: (تقومان، لن يقومَ، تبوءان، تؤوبان)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوُC3ْ",
                "ُC3ْ"
            )
        ) // EX: (تَقُمْنَ، لم يَقُمْ، يَبُؤْ، يَؤُبْ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوُC3ّ",
                "ُC3ّ"
            )
        ) // EX: (أنتن تَصُنَّ، هنّ صُنَّ)
    }


}