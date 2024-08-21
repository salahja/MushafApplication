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
class PassivePresentAjwafWawiVocalizer : AbstractAjwafWawiVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        //نفس القائمة في listed
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3ُ",
                "َاC3ُ"
            )
        ) // EX: (يُقام، يُخاف، لن تُخافُوا، يُداء)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3ِ",
                "َاC3ِ"
            )
        ) // EX: (تُقامين، تُخافين، لن تُخافي، )
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3َ",
                "َاC3َ"
            )
        ) // EX: (تُقامان، تُخافان، لن يُخافَ، يُخافَنَّ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3ْ",
                "َC3ْ"
            )
        ) // EX: (تُقَمْنَ، تُخَفْنَ، لم تُخَفْ)
        substitutions.add(
            ExpressionInfixSubstitution(
                "ْوَC3ّ",
                "َC3ّ"
            )
        ) // EX: (أنتن تُصَنَّ، هن يُصَنَّ)
    }


}