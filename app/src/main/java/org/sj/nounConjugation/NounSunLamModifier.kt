package org.sj.nounConjugation

import org.sj.verbConjugation.trilateral.Substitution.Substitution
import org.sj.verbConjugation.trilateral.Substitution.SubstitutionsApplier
import org.sj.verbConjugation.trilateral.TrilateralRoot
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
class NounSunLamModifier private constructor() : SubstitutionsApplier() {

    override  var substitutions: MutableList<ListedInfixSubstitution> = LinkedList()
    init {
        val sunLetters: MutableList<Any> = LinkedList()
        sunLetters.add("ت")
        sunLetters.add("ث")
        sunLetters.add("د")
        sunLetters.add("ذ")
        sunLetters.add("ر")
        sunLetters.add("ز")
        sunLetters.add("س")
        sunLetters.add("ش")
        sunLetters.add("ص")
        sunLetters.add("ض")
        sunLetters.add("ط")
        sunLetters.add("ظ")
        sunLetters.add("ل")
        sunLetters.add("ن")
        substitutions.add(ListedInfixSubstitution(sunLetters, "الSLَ", "الSLَّ"))
        substitutions.add(ListedInfixSubstitution(sunLetters, "الSLُ", "الSLُّ"))
        substitutions.add(ListedInfixSubstitution(sunLetters, "الSLِ", "الSLِّ"))
    }



    fun apply(conjResult: ConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, null)
    }

    fun apply(conjResult: MazeedConjugationResult) {
        apply(conjResult.finalResult as MutableList<Any>, null)
    }


    //todo
    /*

  public void apply(org.sj.verb.quadriliteral.ConjugationResult conjResult) {
    apply(conjResult.getFinalResult(), null);
  }

 */
  /*   fun getSubstitutions(): List<*> {
        return substitutions
    }
*/
    override val appliedPronounsIndecies: List<*>
        protected get() = appliedProunounsIndecies

    inner class ListedInfixSubstitution(
        private val probableChars: List<*>,
        segment: String?,
        result: String?
    ) : Substitution(
        segment!!, result!!
    ) {
        /**
         * @param word String
         * @return String
         */
        override fun apply(word: String, root: TrilateralRoot): String? {
            val iter = probableChars.iterator()
            while (iter.hasNext()) {
                val sl = iter.next() as String
                val appliedResut = apply(word, sl)
                if (appliedResut != null) {
                    return appliedResut
                }
            }
            return null
        }

        fun apply(word: String, sl: String?): String? {
            val wordSegment = segment.replace("SL".toRegex(), sl!!)
            if (word.indexOf(wordSegment) == -1) {
                return null
            }
            val replacedResult = result.replace("SL".toRegex(), sl)
            return word.replace(wordSegment.toRegex(), replacedResult)
        }
    }

    companion object {
        val instance = NounSunLamModifier()
        protected var appliedProunounsIndecies: MutableList<String> = ArrayList(18)

        init {
            for (i in 0..17) {
                appliedProunounsIndecies.add((i + 1).toString() + "")
            }
        }
    }
}