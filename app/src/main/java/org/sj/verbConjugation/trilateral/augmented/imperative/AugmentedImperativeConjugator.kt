package org.sj.verbConjugation.trilateral.augmented.imperative

import org.sj.verbConjugation.util.ImperativeConjugationDataContainer

class AugmentedImperativeConjugator private constructor() {
    val notEmphsizedConjugator = AbstractAugmentedImperativeConjugator(
        ImperativeConjugationDataContainer.instance.lastDimList,
        ImperativeConjugationDataContainer.instance.connectedPronounList
    )
    val emphsizedConjugator = AbstractAugmentedImperativeConjugator(
        ImperativeConjugationDataContainer.instance.emphasizedLastDimList,
        ImperativeConjugationDataContainer.instance.emphasizedConnectedPronounList
    )

    companion object {
        val instance = AugmentedImperativeConjugator()
    }
}