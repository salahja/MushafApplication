package org.sj.verbConjugation.util

class TrilateralKovRuleList {
    private val rules: MutableList<TrilateralKovRule> = ArrayList(33)
    fun addRule(rule: TrilateralKovRule) {
        rules.add(rule)
    }

    fun getRules(): List<TrilateralKovRule> {
        return rules
    }
}