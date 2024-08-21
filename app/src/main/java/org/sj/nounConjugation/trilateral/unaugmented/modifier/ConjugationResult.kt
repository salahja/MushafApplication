package org.sj.nounConjugation.trilateral.unaugmented.modifier

import org.sj.verbConjugation.trilateral.unaugmented.ConjugationResult
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot

/**
 *
 * Title: Sarf Program
 *
 *
 * Description: يمثل نتيجة التصريف مع الجذر ونوع الجذر
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
class ConjugationResult(
    kov: Int,
    root: UnaugmentedTrilateralRoot,
    originalResult:List<*>,
    val nounFormula: String
) : ConjugationResult(kov, root!!, originalResult )