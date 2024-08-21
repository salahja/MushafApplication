package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.mithal

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
class ActivePrenentVocalizer : SubstitutionsApplier(), IUnaugmentedTrilateralModifier {
   override val substitutions: MutableList<InfixSubstitution> = LinkedList()
    private val acceptList: MutableList<String> = LinkedList()
    private val declineList: MutableList<String> = LinkedList()

    init {
        acceptList.add("وذر")
        acceptList.add("وسع")
        acceptList.add("وطء")
        declineList.add("وبء")
        declineList.add("وبه")
        declineList.add("وجع")
        declineList.add("وسع")
        declineList.add("وهل")
        substitutions.add(InfixSubstitution("َوْ", "َ"))
    }



    /**
     * فحص أحد ثلاثة احتمالات
     *
     * @param conjugationResult ConjugationResult
     * @return boolean
     */
    override fun isApplied(conjugationResult: ConjugationResult): Boolean {
        val kov = conjugationResult.kov
        val noc = conjugationResult.root!!.conjugation!!.toInt()
        return kov == 9 || kov == 10 || kov == 11 &&
                (noc == 2 || noc == 6 //احمال1
                        || isApplied1(conjugationResult) //احتمال2
                        || isApplied2(conjugationResult)) // احتمال 3
    }

    private fun isApplied1(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root!!
        //فحص الباب التصريفي أولاً
        if (root!!.conjugation != "4") return false
        val iter: Iterator<String> = acceptList.iterator()
        while (iter.hasNext()) {
            val appliedRoot = iter.next()
            val c1 = appliedRoot[0]
            val c2 = appliedRoot[1]
            val c3 = appliedRoot[2]
            if (c1 == root!!.c1 && c2 == root!!.c2 && root!!.c3 == c3) return true
        }
        return false
    }

    private fun isApplied2(conjugationResult: ConjugationResult): Boolean {
        val root = conjugationResult.root!!
        //فحص الباب التصريفي أولاً
        if (root!!.conjugation != "3") return false
        val iter: Iterator<String> = declineList.iterator()
        while (iter.hasNext()) {
            val appliedRoot = iter.next()
            val c1 = appliedRoot[0]
            val c2 = appliedRoot[1]
            val c3 = appliedRoot[2]
            if (c1 == root!!.c1 && c2 == root!!.c2 && root!!.c3 == c3) return false
        }
        return true
    }
}