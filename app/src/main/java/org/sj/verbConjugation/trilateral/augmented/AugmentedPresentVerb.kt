package org.sj.verbConjugation.trilateral.augmented

abstract class AugmentedPresentVerb(
    var root:AugmentedTrilateralRoot,
    var cp: String?,
    var lastDpr: String?,
    var connectedPronoun: String?
) {
    abstract fun form(): String
    override fun toString(): String {
        return form()
    }
}