package org.sj.verbConjugation.trilateral.augmented.modifier

import org.sj.verbConjugation.trilateral.augmented.MazeedConjugationResult

/**
 * Title: Sarf Program
 *
 *
 * Description: abstract interface to be implemented from the child classes
 * which will modify the verbs
 *
 *
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
interface IAugmentedTrilateralModifier {
    fun isApplied(mazeedConjugationResult: MazeedConjugationResult): Boolean
}