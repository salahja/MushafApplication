package org.sj.verbConjugation.trilateral.augmented

import java.util.LinkedList

/**
 * Title:
 *
 *
 * Description:
 *
 *
 * Copyright: Copyright (c) 2006
 *
 *
 * Company:
 *
 * @author not attributable
 * @version 1.0
 */
class AugmentedTrilateralRootTree {
    private val roots: MutableList<AugmentedTrilateralRoot> = LinkedList()
    fun addRoot(root: AugmentedTrilateralRoot) {
        roots.add(root!!)
    }

    fun getRoots(): List<AugmentedTrilateralRoot> {
        return roots
    }
}