package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.yaei

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf.AbstractAjwafYaeiListedVocalizer
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
class ImperativeAjwafYaeiListedVocalizer : AbstractAjwafYaeiListedVocalizer() {
   override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    init {
        substitutions.add(ExpressionInfixSubstitution("اC1ْيَC3ُ", "C1َاC3ُ"))
        substitutions.add(ExpressionInfixSubstitution("اC1ْيَC3ِ", "C1َاC3ِ"))
        substitutions.add(ExpressionInfixSubstitution("اC1ْيَC3َ", "C1َاC3َ"))
        substitutions.add(ExpressionInfixSubstitution("اC1ْيَC3ْ", "C1َC3ْ"))
    }

   
}