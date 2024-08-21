package org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
 * القوانين الآتية خاصة بالضمائر (أنا)، و(أنتَ)، و(أنتِ)، و(أنتما)، و(أنتم)، و(أنتنَّ) فقط [أي الأرقام
 * 1 و 3 و 4 و 5 و 6 و 7  في جدول الأفعال]
 * يستعمل نفسه من أجل كل الصيغ الماضي والمضارع والأمر
 * للمعلوم وللمجهول
 *
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
class TGeminator : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
      override val substitutions: MutableList<InfixSubstitution> = LinkedList()
      override val appliedPronounsIndecies: MutableList<String> = ArrayList(6)

    init {
        substitutions.add(InfixSubstitution("تْتُ", "تُّ"))
        substitutions.add(InfixSubstitution("تْتَ", "تَّ"))
        substitutions.add(InfixSubstitution("تْتِ", "تِّ"))
        appliedPronounsIndecies.add("1")
        appliedPronounsIndecies.add("3")
        appliedPronounsIndecies.add("4")
        appliedPronounsIndecies.add("5")
        appliedPronounsIndecies.add("6")
        appliedPronounsIndecies.add("7")
    }

     


    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        return conjugationResult.root!!.c3 == 'ت' && (kov == 1 || kov == 2 || kov == 3 || kov == 5 || kov == 6 || kov == 11 || kov == 17 || kov == 20)
    }
//TODO possible
    fun apply(tense: String?, active: Boolean, conjResult: ConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
    }
}