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
class ImperativeAjwafWawiVocalizer : AbstractAjwafWawiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْوُC3ُ",
                "C1ُوC3ُ"
            )
        ) // EX: (قُومُوا، قُومُنَّ، بوءوا، أوبوا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْوُC3ِ",
                "C1ُوC3ِ"
            )
        ) // EX: (قومِي، قومِنَّ، بوئي، أوبي)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْوُC3َ",
                "C1ُوC3َ"
            )
        ) // EX: (قوما، قومَنَّ، بوءا، أوبا)
        substitutions.add(
            ExpressionInfixSubstitution(
                "اC1ْوُC3ْ",
                "C1ُC3ْ"
            )
        ) // EX: (قُمْ، قُمْنَانِّ، بُؤْ، أُبْ)
        substitutions.add(ExpressionInfixSubstitution("اC1ْوُC3ّ", "C1ُC3ّ")) // EX: (أنتن صُنَّ)
    }

   
}