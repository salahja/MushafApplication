package org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ajwaf

import org.sj.verbConjugation.trilateral.unaugmented.modifier.vocalizer.ListedVocalizer
import java.util.LinkedList

/**
 *
 * Title: Sarf Program
 *
 *
 * Description:فحص الأجوف حسب قائمة
 * وهي نفس القائمة للأجوف اليائي أي نفس الفحص وتختلف بالاستبدالات
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
abstract class AbstractAjwafYaeiListedVocalizer : ListedVocalizer() {
   final override val appliedRoots: MutableList<String> = LinkedList()

    init {
        appliedRoots.add("بيت")
        appliedRoots.add("حير")
        appliedRoots.add("خيل")
        appliedRoots.add("زيم")
        appliedRoots.add("شيء")
        appliedRoots.add("طيط")
        appliedRoots.add("عيف")
        appliedRoots.add("عيم")
        appliedRoots.add("غير")
        appliedRoots.add("نيل")
        appliedRoots.add("نيه")
        appliedRoots.add("هيء")
        appliedRoots.add("هيب")
        appliedRoots.add("هيع")
    }

       

    protected override val noc: Int
        protected get() = 4
}