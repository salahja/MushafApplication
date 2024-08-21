package org.sj.verbConjugation.trilateral.unaugmented

import java.util.LinkedList

/**
 *
 * Title: قائمة الجذور الثلاثية المجردة
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
class UnaugmentedTrilateralRootTree {
    private val roots: MutableList<UnaugmentedTrilateralRoot> = LinkedList()
    fun addRoot(root: UnaugmentedTrilateralRoot) {
        roots.add(root!!)
    }

    fun getRoots(): List<UnaugmentedTrilateralRoot> {
        return roots
    }
}