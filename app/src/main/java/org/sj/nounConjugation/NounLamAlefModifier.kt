package org.sj.nounConjugation

import org.sj.verbConjugation.trilateral.Substitution.InfixSubstitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
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
class NounLamAlefModifier private constructor() : SubstitutionsApplier() {
 //   override var substitutions: MutableList<*> = LinkedList<Any?>()
 override  var substitutions: MutableList<InfixSubstitution> = LinkedList()
    init {
        substitutions.add(InfixSubstitution("لَا", "لا")) // EX: (قالا)
        substitutions.add(InfixSubstitution("لَّا", "لاَّ")) // EX: (انْشَلاَّ)
        substitutions.add(InfixSubstitution("لَأ", "لأ")) // EX: (مَلأَ، مَلأْتُ)
        substitutions.add(InfixSubstitution("لًا", "لاً")) // EX: (حملاً)
    }


    fun apply(conjResult: ConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, null)
        //قد يوجد لام ألف أخرى تتطابق مع قانون أخر
        apply(conjResult.finalResult as MutableList<Any>, null)
    }

    fun apply(conjResult: MazeedConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, null)
        //قد يوجد لام ألف أخرى تتطابق مع قانون أخر
        apply(conjResult.finalResult as MutableList<Any>, null)
    }



    //todo quadri
    /*

  public void apply(org.sj.verb.quadriliteral.ConjugationResult conjResult) {
    apply(conjResult.getFinalResult(), null);
    //قد يوجد لام ألف أخرى تتطابق مع قانون أخر
    apply(conjResult.getFinalResult(), null);
  }
 */
/*
    override fun getSubstitutions(): List<*> {
        return substitutions
    }
*/

    override val appliedPronounsIndecies: List<*>
        protected get() = appliedProunounsIndecies

    companion object {
        val instance = NounLamAlefModifier()
        protected var appliedProunounsIndecies: MutableList<String> = ArrayList(18)

        init {
            for (i in 0..17) {
                appliedProunounsIndecies.add((i + 1).toString() + "")
            }
        }
    }
}