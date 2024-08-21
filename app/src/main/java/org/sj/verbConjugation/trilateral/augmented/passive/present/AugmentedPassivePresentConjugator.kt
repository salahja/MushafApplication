package org.sj.verbConjugation.trilateral.augmented.passive.present

import org.sj.verbConjugation.util.PresentConjugationDataContainer

class AugmentedPassivePresentConjugator private constructor() {
    val nominativeConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.instance.nominativeLastDprList,
        PresentConjugationDataContainer.instance.nominativeConnectedPronounList
    )
    val accusativeConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.instance.accusativeLastDprList,
        PresentConjugationDataContainer.instance.accusativeConnectedPronounList
    )
    val jussiveConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.instance.jussiveLastDprList,
        PresentConjugationDataContainer.instance.jussiveConnectedPronounList
    )
    val emphasizedConjugator = AbstractAugmentedPresentConjugator(
        PresentConjugationDataContainer.instance.emphasizedLastDprList,
        PresentConjugationDataContainer.instance.emphasizedConnectedPronounList
    )

    companion object {
        val instance = AugmentedPassivePresentConjugator()
    }
}