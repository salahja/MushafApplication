package org.sj.verbConjugation.trilateral.augmented.modifier

interface AugmentedTrilateralModifierListener {
    //if there are two states for the verb with vocaliztion and without, listners will be notified
    //about this situation to take a decision
    fun doSelectVocalization(): Boolean
}