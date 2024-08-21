package org.sj.verbConjugation.trilateral.Substitution

import org.sj.verbConjugation.trilateral.TrilateralRoot

abstract class Substitution(protected var segment: String, protected var result: String) {
    /**
     * check wheather this Substitution cn be applied for this root!! and word (which generated form this root!!)
     * if ok the result will be used
     *
     * @param word String
     * @return String
     */
    abstract fun apply(word: String, root: TrilateralRoot): String?
}