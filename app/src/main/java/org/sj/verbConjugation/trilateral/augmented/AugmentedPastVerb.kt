package org.sj.verbConjugation.trilateral.augmented

abstract class AugmentedPastVerb(
    var root:AugmentedTrilateralRoot,
    var lastDpa: String?,
    var connectedPronoun: String?
) {
    abstract fun form(): String
    override fun toString(): String {
        return form()
    }
}