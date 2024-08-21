package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.wawi

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.AbstractAjwafWawiListedVocalizer
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:فحص الأجوف حسب قائمة
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
class ImperativeAjwafWawiListedVocalizer : AbstractAjwafWawiListedVocalizer() {
      override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("اC1ْوَC3ُ", "C1َاC3ُ"))
        substitutions.add(ExpressionInfixSubstitution("اC1ْوَC3ِ", "C1َاC3ِ"))
        substitutions.add(ExpressionInfixSubstitution("اC1ْوَC3َ", "C1َاC3َ"))
        substitutions.add(ExpressionInfixSubstitution("اC1ْوَC3ْ", "C1َC3ْ"))
    }


}