package org.sj.verbConjugation.util

import org.sj.conjugator.utilities.ArabicLiterals
import org.sj.verbConjugation.trilateral.unaugmented.UnaugmentedTrilateralRoot

/**
 * يحتوي على  المعلومات  الصرفية المطلوبة لتصريف الأفعال  في المضارع
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
class PresentConjugationDataContainer private constructor() {
    //أحرف المضارعة حسب الضمير
    private val cpList: MutableList<String> = ArrayList(13)

    //قائمة حركات عين الفعل حسب باب التصريف
    private val dpr2List: MutableList<String> = ArrayList(6)

    //قائمة  حركات لام الفعل حسب ضمير الرفع
    //مرفوع
    val nominativeLastDprList: MutableList<String> = ArrayList(13)

    //منصوب
    val accusativeLastDprList: MutableList<String> = ArrayList(13)

    //مجزوم
    val jussiveLastDprList: MutableList<String?> = ArrayList(13)

    //مؤكد
    val emphasizedLastDprList: MutableList<String> = ArrayList(13)

    //قائمة ضمائر الرفع المتصلة
    //مرفوع
    val nominativeConnectedPronounList: MutableList<String> = ArrayList(13)

    //منصوب
    val accusativeConnectedPronounList: MutableList<String> = ArrayList(13)

    //مجزوم
    val jussiveConnectedPronounList: MutableList<String> = ArrayList(13)

    //مؤكد
    val emphasizedConnectedPronounList: MutableList<String> = ArrayList(13)

    init {
        //تهيئة القيم
        /*
    dpr2List.add(ArabCharUtil.DAMMA);
    dpr2List.add(ArabCharUtil.KASRA);
    dpr2List.add(ArabCharUtil.FATHA);
    dpr2List.add(ArabCharUtil.FATHA);
    dpr2List.add(ArabCharUtil.DAMMA);
    dpr2List.add(ArabCharUtil.KASRA);

     */
        //تهيئة القيم
        //   dpr2List.add(ArabCharUtil.DAMMA);
        //  dpr2List.add(ArabCharUtil.KASRA);
        // dpr2List.add(ArabCharUtil.FATHA);
        // dpr2List.add(ArabCharUtil.FATHA);
        // dpr2List.add(ArabCharUtil.DAMMA);
        // dpr2List.add(ArabCharUtil.KASRA);
        dpr2List.add(ArabicLiterals.Damma.trim { it <= ' ' }) //NASARA
        dpr2List.add(ArabicLiterals.Kasra.trim { it <= ' ' }) //ZARABA
        dpr2List.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //FATHA
        dpr2List.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //SAMIA
        dpr2List.add(ArabicLiterals.Damma.trim { it <= ' ' }) //KARUMA
        dpr2List.add(ArabicLiterals.Kasra.trim { it <= ' ' }) //HASIBA
        cpList.add("ي")
        cpList.add("ي")
        cpList.add("ي")
        cpList.add("ت")
        cpList.add("ت")
        cpList.add("ي")
        cpList.add("ت")
        cpList.add("ت")
        cpList.add("ت")
        cpList.add("ت")
        cpList.add("ت")
        cpList.add("أ")
        cpList.add("ن")
        nominativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Sukun.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Kasra.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Sukun.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        nominativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        nominativeConnectedPronounList.add("")
        nominativeConnectedPronounList.add("انِ")
        nominativeConnectedPronounList.add("ونَ")
        nominativeConnectedPronounList.add("")
        nominativeConnectedPronounList.add("انِ")
        nominativeConnectedPronounList.add("نَ")
        nominativeConnectedPronounList.add("")
        nominativeConnectedPronounList.add("انِ")
        nominativeConnectedPronounList.add("ونَ")
        nominativeConnectedPronounList.add("ينَ")
        nominativeConnectedPronounList.add("نَ")
        nominativeConnectedPronounList.add("")
        nominativeConnectedPronounList.add("")
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //hua
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //huma-male
        accusativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' }) //hum
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //hiya
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //humafemale
        accusativeLastDprList.add(ArabicLiterals.Sukun.trim { it <= ' ' }) //.hunna
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //anta
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //;antuma
        accusativeLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' }) //antum
        accusativeLastDprList.add(ArabicLiterals.Kasra.trim { it <= ' ' }) //anti
        accusativeLastDprList.add(ArabicLiterals.Sukun.trim { it <= ' ' }) //antuna
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //ana
        accusativeLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' }) //nahnu
        accusativeConnectedPronounList.add("") //huva
        accusativeConnectedPronounList.add("ا") //huma-ma
        accusativeConnectedPronounList.add("وا") //hum/
        accusativeConnectedPronounList.add("") //hiya
        accusativeConnectedPronounList.add("ا") //huma-female
        accusativeConnectedPronounList.add("نَ") //hunna
        accusativeConnectedPronounList.add("") //anta
        accusativeConnectedPronounList.add("ا") //antuma
        accusativeConnectedPronounList.add("وا") //antum
        accusativeConnectedPronounList.add("ي") //anti
        accusativeConnectedPronounList.add("نَ") //antunna
        accusativeConnectedPronounList.add("") //ana
        accusativeConnectedPronounList.add("") //nanhu
        jussiveLastDprList.add(ArabCharUtil.SKOON)
        jussiveLastDprList.add(ArabCharUtil.FATHA)
        jussiveLastDprList.add(ArabCharUtil.DAMMA)
        jussiveLastDprList.add(ArabCharUtil.SKOON)
        jussiveLastDprList.add(ArabCharUtil.FATHA)
        jussiveLastDprList.add(ArabCharUtil.SKOON)
        jussiveLastDprList.add(ArabCharUtil.SKOON)
        jussiveLastDprList.add(ArabCharUtil.FATHA)
        jussiveLastDprList.add(ArabCharUtil.DAMMA)
        jussiveLastDprList.add(ArabCharUtil.KASRA)
        jussiveLastDprList.add(ArabCharUtil.SKOON)
        jussiveLastDprList.add(ArabCharUtil.SKOON)
        jussiveLastDprList.add(ArabCharUtil.SKOON)
        jussiveConnectedPronounList.add("")
        jussiveConnectedPronounList.add("ا")
        jussiveConnectedPronounList.add("وا")
        jussiveConnectedPronounList.add("")
        jussiveConnectedPronounList.add("ا")
        jussiveConnectedPronounList.add("نَ")
        jussiveConnectedPronounList.add("")
        jussiveConnectedPronounList.add("ا")
        jussiveConnectedPronounList.add("وا")
        jussiveConnectedPronounList.add("ي")
        jussiveConnectedPronounList.add("نَ")
        jussiveConnectedPronounList.add("")
        jussiveConnectedPronounList.add("")
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Kasra.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Sukun.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Fatha.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Damma.trim { it <= ' ' })
        emphasizedLastDprList.add(ArabicLiterals.Sukun.trim { it <= ' ' })
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("انِّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَانِّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("انِّ")
        emphasizedConnectedPronounList.add("انِّ")
        emphasizedConnectedPronounList.add("نَّ")
        emphasizedConnectedPronounList.add("نَانِّ")
    }

 /*   fun getNominativeLastDprList(): List<String> {
        return nominativeLastDprList
    }

    fun getNominativeConnectedPronounList(): List<String> {
        return nominativeConnectedPronounList
    }

    fun getEmphasizedLastDprList(): List<String> {
        return emphasizedLastDprList
    }

    fun getEmphasizedConnectedPronounList(): List<String> {
        return emphasizedConnectedPronounList
    }

    fun getJussiveLastDprList(): List<String?> {
        return jussiveLastDprList
    }

    fun getJussiveConnectedPronounList(): List<String> {
        return jussiveConnectedPronounList
    }

    fun getAccusativeLastDprList(): List<String> {
        return accusativeLastDprList
    }

    fun getAccusativeConnectedPronounList(): List<String> {
        return accusativeConnectedPronounList
    }*/

    /**
     * الحصول  على حركة عين الفعل حسب باب تصريف الفعل
     *
     * @param root!! TripleVerb
     * @return String
     */
    fun getDpr2(root: UnaugmentedTrilateralRoot): String {
        //بسبب أن ترقيم الباب التصريفي يبدأ من الواحد على حين أن القائمة تبدأ من الصفر جرى طرح العدد واحد
        return dpr2List[root!!.conjugation!!.toInt() - 1]
    }

    /**
     * الحصول  على حرف المضارع حسب الضمير
     *
     * @param pronounIndex int
     * @return String
     */
    fun getCp(pronounIndex: Int): String {
        return cpList[pronounIndex]
    }

    companion object {
        val instance = PresentConjugationDataContainer()
    }
}