package org.sj.verbConjugation.trilateral.unaugmented

import org.sj.verbConjugation.Gerund
import org.sj.verbConjugation.trilateral.TrilateralRoot
import org.sj.verbConjugation.util.OrderedMap

/**
 *
 * Title:
 *
 *
 * Description: الجذر الثلاثي المجرد
 * متضمنا الأحرف الثلاثة وباب التصريف  واللزوم أو التعدية مع رموز المصادر
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company:
 *
 * @author not attributable
 * @version 1.0
 */
class UnaugmentedTrilateralRoot : TrilateralRoot {
    private var c1 = 0.toChar()
    private var c2 = 0.toChar()
    private var c3 = 0.toChar()
    var conjugation: String? = null
    var conjugationname: String? = null
    var rulename: String? = null
    var verbtype: String? = null
    var verb: String? = null
    private val gerundes = OrderedMap()
    override fun getC1(): Char {
        return c1
    }

    fun setC1(c1: Char) {
        this.c1 = c1
    }

    override fun getC2(): Char {
        return c2
    }

    fun setC2(c2: Char) {
        this.c2 = c2
    }

    override fun getC3(): Char {
        return c3
    }

    fun setC3(c3: Char) {
        this.c3 = c3
    }

    val gerundsSymbols: Collection<*>
        get() = gerundes.orderedKeys

    fun getGerund(symbol: String): Gerund? {
        return gerundes[symbol] as Gerund?
    }

    fun addGerund(gerund: Gerund) {
        gerundes[gerund.symbol] = gerund
    }

    override fun equals(obj: Any?): Boolean {
        val root = obj as UnaugmentedTrilateralRoot?
        return root!!.c1 == c1 && root!!.c2 == c2 && root!!.c3 == c3
    }
}