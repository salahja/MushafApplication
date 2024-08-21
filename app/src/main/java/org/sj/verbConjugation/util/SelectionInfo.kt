package org.sj.verbConjugation.util

class SelectionInfo(
    var root: Any,
    var isTrilateral: Boolean,
    var isAugmented: Boolean,
    val kov: Int
) {
    var isActive = false
    var augmentationFormulaNo = 0
    var formulaText: String? = null
    var verbText: String? = null

}