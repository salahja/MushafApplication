package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf

import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ListedVocalizer
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:فحص الأجوف حسب قائمة
 * * وهي نفس القائمة للأجوف الواوي أي نفس الفحص وتختلف بالاستبدالات
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
abstract class AbstractAjwafWawiListedVocalizer : ListedVocalizer() {
   override val appliedRoots: MutableList<String> = LinkedList()

    init {
        appliedRoots.add("بوه")
        appliedRoots.add("خوف")
        appliedRoots.add("دوء")
        appliedRoots.add("دود")
        appliedRoots.add("دوم")
        appliedRoots.add("شوك")
        appliedRoots.add("صوت")
        appliedRoots.add("طوع")
        appliedRoots.add("كود")
        appliedRoots.add("مول")
        appliedRoots.add("نوم")
        appliedRoots.add("نوه")
        appliedRoots.add("هوع")
    }



    protected override val noc: Int
        protected get() = 4
}