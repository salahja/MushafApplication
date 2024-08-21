package org.sj.verbConjugation.util

/**
 * يحتوي على  المعلومات  الصرفية المطلوبة لتصريف الأفعال  في الأمر
 *
 * Title: Sarf
 *
 * Description: برنامج التصريف
 *
 * Copyright: Copyright (c) 2006
 *
 * Company:
 *
 * @author Haytham Mohtasseb Billah
 * @version 1.0
 */
class ImperativeConjugationDataContainer private constructor() {
    //قائمة حركات عين الفعل حسب باب التصريف
    //الأمر غير المؤكد
    val lastDimList: MutableList<String?> = ArrayList(13)

    //الأمر المؤكد
    val emphasizedLastDimList: MutableList<String?> = ArrayList(13)

    //قائمة ضمائر الرفع المتصلة
    //الأمر غير المؤكد
    val connectedPronounList: MutableList<String> = ArrayList(13)

    //الأمر المؤكد
    val emphasizedConnectedPronounList: MutableList<String> = ArrayList(13)

    init {
        lastDimList.add("")
        lastDimList.add("")
        lastDimList.add(ArabCharUtil.SKOON)
        lastDimList.add(ArabCharUtil.KASRA)
        lastDimList.add(ArabCharUtil.FATHA)
        lastDimList.add(ArabCharUtil.DAMMA)
        lastDimList.add(ArabCharUtil.SKOON)
        lastDimList.add("")
        lastDimList.add("")
        lastDimList.add("")
        lastDimList.add("")
        lastDimList.add("")
        lastDimList.add("")
        connectedPronounList.add("")
        connectedPronounList.add("")
        connectedPronounList.add("")
        connectedPronounList.add("ي")
        connectedPronounList.add("ا")
        connectedPronounList.add("وا")
        connectedPronounList.add("نَ")
        connectedPronounList.add("")
        connectedPronounList.add("")
        connectedPronounList.add("")
        connectedPronounList.add("")
        connectedPronounList.add("")
        connectedPronounList.add("")
        emphasizedLastDimList.add("")
        emphasizedLastDimList.add("")
        emphasizedLastDimList.add(ArabCharUtil.FATHA)
        emphasizedLastDimList.add(ArabCharUtil.KASRA)
        emphasizedLastDimList.add(ArabCharUtil.FATHA)
        emphasizedLastDimList.add(ArabCharUtil.DAMMA)
        emphasizedLastDimList.add(ArabCharUtil.SKOON)
        emphasizedLastDimList.add("")
        emphasizedLastDimList.add("")
        emphasizedLastDimList.add("")
        emphasizedLastDimList.add("")
        emphasizedLastDimList.add("")
        emphasizedLastDimList.add("")
        emphasizedConnectedPronounList.add("")
        emphasizedConnectedPronounList.add("")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("انِّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَانِّ")
        emphasizedConnectedPronounList.add("")
        emphasizedConnectedPronounList.add("")
        emphasizedConnectedPronounList.add("")
        emphasizedConnectedPronounList.add("")
        emphasizedConnectedPronounList.add("")
        emphasizedConnectedPronounList.add("")
    }
/*

    fun getEmphasizedConnectedPronounList(): List<String> {
        return emphasizedConnectedPronounList
    }

    fun getEmphasizedLastDimList(): List<String?> {
        return emphasizedLastDimList
    }

    fun getConnectedPronounList(): List<String> {
        return connectedPronounList
    }

    fun getLastDimList(): List<String?> {
        return lastDimList
    }
*/

    /**
     * الحصول  على  حركة لام الفعل حسب الضمير
     * الأمر غير المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    fun getLastDim(pronounIndex: Int): String? {
        return lastDimList[pronounIndex]
    }

    /**
     * الحصول  على  حركة لام الفعل حسب الضمير
     * الأمر  المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    fun getEmphasizedLastDim(pronounIndex: Int): String? {
        return emphasizedLastDimList[pronounIndex]
    }

    /**
     * الحصول على ضمائر الرفع المتصلة
     * الأمر غير المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    fun getConnectedPronoun(pronounIndex: Int): String {
        return connectedPronounList[pronounIndex]
    }

    /**
     * الحصول على ضمائر الرفع المتصلة
     * الأمر  المؤكد
     *
     * @param pronounIndex int
     * @return String
     */
    fun getEmphasizedConnectedPronoun(pronounIndex: Int): String {
        return emphasizedConnectedPronounList[pronounIndex]
    }

    companion object {
        val instance = ImperativeConjugationDataContainer()
    }
}