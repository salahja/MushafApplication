package org.sj.nounConjugation.trilateral.augmented

import org.sj.nounConjugation.GenericNounSuffixContainer
import org.sj.verbConjugation.trilateral.augmented.AugmentedTrilateralRoot

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: ���� ������� ������� ������� �� ���� �����
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company: ALEXO
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
abstract class AugmentedTrilateralNoun(
    protected var root: AugmentedTrilateralRoot,
    protected var suffix: String?
) {
    abstract fun form(): String
    override fun toString(): String {
        return GenericNounSuffixContainer.getInstance().prefix + form()
    }
}