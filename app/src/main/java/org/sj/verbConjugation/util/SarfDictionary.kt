package org.sj.verbConjugation.util


class SarfDictionary private constructor() {
/*    fun getAugmentedTrilateralRoot(rootText: String): AugmentedTrilateralRoot? {
        val c1 = rootText[0]
        val c2 = rootText[1]
        val c3 = rootText[2]
        val augmentedRootsTree: AugmentedTrilateralRootTree =
            DatabaseManager.getInstance().getAugmentedTrilateralRootTree(c1)
        val roots: List<*> = augmentedRootsTree.getRoots()
        val iter = roots.iterator()
        while (iter.hasNext()) {
            val aRoot: AugmentedTrilateralRoot = iter.next() as AugmentedTrilateralRoot
            if (aRoot.getC1() === c1 && aRoot.getC2() === c2 && aRoot.getC3() === c3) {
                return aRoot
            }
        }
        return null
    }

    fun getUnaugmentedTrilateralRoots(rootText: String): List<*> {
        val c1 = rootText[0]
        val c2 = rootText[1]
        val c3 = rootText[2]
        val unaugmentedRootsTree: UnaugmentedTrilateralRootTree =
            DatabaseManager.getInstance().getUnaugmentedTrilateralRootTree(c1)
        val roots: List<*> = unaugmentedRootsTree.getRoots()
        val result: MutableList<*> = LinkedList<Any?>()
        val iter = roots.iterator()
        while (iter.hasNext()) {
            val root = iter.next() as UnaugmentedTrilateralRoot
            if (root.c1 == c1 && root.c2 == c2 && root.c3 == c3) {
                result.add(root)
            }
        }
        return result
    }
 */
    companion object {
        val instance = SarfDictionary()
    }
}
