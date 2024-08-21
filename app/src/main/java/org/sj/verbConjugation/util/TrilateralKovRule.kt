package org.sj.verbConjugation.util

class TrilateralKovRule {
    private var c1: String? = null
    private var c2: String? = null
    private var c3: String? = null
    var kov = 0
    var desc: String? = null
    var example: String? = null
    fun setC3(c3: String?) {
        this.c3 = c3
    }

    fun setC2(c2: String?) {
        this.c2 = c2
    }

    fun setC1(c1: String?) {
        this.c1 = c1
    }

    fun check(verbC1: Char, verbC2: Char, verbC3: Char): Boolean {
        val b1 = c1 == "?" || c1 == "null" || c1 == verbC1.toString() + ""
        var b2 = false
        var b3 = false
        if (c2.equals("c3", ignoreCase = true) && c3.equals("c2", ignoreCase = true)) {
            b3 = verbC2 == verbC3
            b2 = b3
        } else {
            b2 = c2 == "?" || c2 == "null" || c2 == verbC2.toString() + ""
            b3 = c3 == "?" || c3 == "null" || c3 == verbC3.toString() + ""
        }
        return b1 && b2 && b3
    }
}