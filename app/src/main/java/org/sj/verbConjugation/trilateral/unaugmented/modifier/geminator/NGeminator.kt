package org.sj.verbConjugation.trilateral.unaugmented.modifier.geminator

import org.sj.verbConjugation.trilateral.Substitution.ExpressionInfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.modifier.IUnaugmentedTrilateralModifier
import org.sj.verbConjugation.util.SystemConstants
import java.util.LinkedList

//import org.sj.verb.trilateral.unaugmented.modifier.geminator.ngeminator.*;
/**
 *
 * Title: Sarf Program
 *
 *
 * Description:
 * الادغام للمنتهي بالنون هو نفسه من أجل كل الصيغ للمعلوم وللمجهول
 * ولكن تختلف فيما بينها بأرقام الضمائر
 * فهو الصف الأب للثلاث الصفوف في الماضي والمضارع والأمر
 * A composite pattern is applied here
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
class NGeminator : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
    override val substitutions: MutableList<ExpressionInfixSubstitution> = LinkedList()

    //أرقام الضمائر التي يمكن التطبيق من أجلها حسب الصيغةة  ماضي أو مضارع أو أمر
    private val indeciesMap: MutableMap<String, List<String>> = HashMap()

    init {
        substitutions.add(ExpressionInfixSubstitution("نْنَ", "نَّ"))
        var indecies: MutableList<String> = ArrayList(2)
        indecies.add("2")
        indecies.add("13")
        indeciesMap[SystemConstants.PAST_TENSE] = indecies
        indecies = ArrayList(2)
        indecies.add("7")
        indecies.add("13")
        indeciesMap[SystemConstants.PRESENT_TENSE] = indecies
        indecies = ArrayList(1)
        indecies.add("7")
        indeciesMap[SystemConstants.NOT_EMPHASIZED_IMPERATIVE_TENSE] = indecies
        indeciesMap[SystemConstants.EMPHASIZED_IMPERATIVE_TENSE] = indecies
    }



    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        return (conjugationResult.root!!.c3 == 'ن') && ((kov == 1) || (kov == 2) || (kov == 3) || (kov == 5) || (kov == 6) || (kov == 11) || (kov == 14) || (kov == 15) || (kov == 17) || (kov == 18) || (kov == 20))
    }

    //todo possible
    fun apply(tense: String?, active: Boolean, conjResult: ConjugationResult) {
        //it is the same active or passive
        apply(conjResult.finalResult as MutableList<Any>, conjResult.root!!)
    } /*protected List getAppliedPronounsIndecies(String tense) {
        return (List) indeciesMap.get(tense);
    }*/
}