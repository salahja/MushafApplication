package com.refactoring



import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.preference.PreferenceManager
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.Constant
import com.example.justJava.FrameSpan
import com.example.mushafconsolidated.Entities.NewMudhafEntity
import com.example.mushafconsolidated.Entities.NewShartEntity
import com.example.mushafconsolidated.Entities.SifaEntity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.model.CorpusAyahWord
import com.example.utility.QuranGrammarApplication
import java.util.regex.Pattern

class NewCorpusUtilityorig {
    // --Commented out by Inspection (15/08/23, 4:17 pm):final ArrayList<MousufSifa> NEWmousufSifaArrayList = new ArrayList<>();
    private val preferences: String?
    private var context: Context? = null
    var activity: Activity? = null


    constructor(context: Context?) {
        this.context = context
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        preferences = prefs.getString("theme", "dark")
        val preferences = prefs.getString("theme", "dark")
        dark = preferences == "dark" || preferences == "blue" || preferences == "green"
    }

    fun SetMousufSifaDB(corpusayahWordArrayList: ArrayList<CorpusAyahWord>, surah_id: Int) {
        val utils = Utils(QuranGrammarApplication.context!!)
        val surah = utils.getSifabySurah(surah_id)
        //  SpannableStringBuilder spannableverse = null;
        // SpannableString spannableString = null;
//todo 2 188 iza ahudu
        //todo 9;92 UNCERTAIN
        //TODO 9:94 JAWABHARMAHDOOF 9 95 JAWABHSARMAHODFF
        //TO 9;118 IZA IN THE MEANING OF HEENA AND 9 122 IZA AS HEENA
        for (sifaEntity in surah!!) {
            val indexstart = sifaEntity!!.startindex
            val indexend = sifaEntity.endindex
            //  sifaspans = new BackgroundColorSpan(WBURNTUMBER);
            SifaSpansSetup(corpusayahWordArrayList, sifaEntity, indexstart, indexend)
        }
    }

    private fun SifaSpansSetup(
        corpusayahWordArrayList: ArrayList<CorpusAyahWord>,
        sifaEntity: SifaEntity,
        indexstart: Int,
        indexend: Int
    ) {
        val spannableverse: SpannableString
        try {
            spannableverse = corpusayahWordArrayList[sifaEntity.ayah - 1].spannableverse!!
            //spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(sifaEntity.getAyah() - 1).getSpannableverse());
            try {
                if (indexstart == 0 || indexstart > 0) {
                    //   sifaspansDark = getSpancolor(preferences, false);
                    if (dark) {
                        Constant.sifaspansDark = BackgroundColorSpan(Constant.WBURNTUMBER)
                    } else {
                        Constant.sifaspansDark = BackgroundColorSpan(Constant.CYANLIGHTEST)
                    }
                    spannableverse.setSpan(
                        Constant.sifaspansDark,
                        indexstart,
                        indexend,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    //   spannableverse.setSpan(new UnderlineSpan(),indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } catch (e: IndexOutOfBoundsException) {
                //System.out.println(e.getMessage());
            }
        } catch (e: IndexOutOfBoundsException) {
            //System.out.println(e.getMessage());
        }
    }

    fun newnewHarfNasbDb(corpusayahWordArrayList: ArrayList<CorpusAyahWord>, surah_id: Int) {
        val utils = Utils(QuranGrammarApplication.context!!)
        val harfnasb = utils.getHarfNasbIndexesnew(surah_id)
        //TODO SURA10 7 INNA ISM INNALIZINA(0,5,6,9 AND KHABR IN 10;8 oolika(0,12,len33)
        if (surah_id == 2 || surah_id == 3 || surah_id == 4 || surah_id == 5 || surah_id == 6 || surah_id == 7 || surah_id == 8 || surah_id == 9 || surah_id == 10 || surah_id == 59 || surah_id == 60 || surah_id == 61 || surah_id == 62 || surah_id == 63 || surah_id == 64 || surah_id == 65 || surah_id == 66 || surah_id == 67 || surah_id == 68 || surah_id == 69 || surah_id == 70 || surah_id == 71 || surah_id == 72 || surah_id == 73 || surah_id == 74 || surah_id == 75 || surah_id == 76 || surah_id == 77 || surah_id == 78 || surah_id > 78 && surah_id <= 114) {
            var spannableverse: SpannableString
            val err = ArrayList<String>()
            for (nasb in harfnasb!!) {
                val indexstart = nasb!!.indexstart
                val indexend = nasb.indexend
                val ismstartindex = nasb.ismstart
                val ismendindex = nasb.ismend
                val khabarstart = nasb.khabarstart
                val khabarend = nasb.khabarend
                spannableverse = corpusayahWordArrayList[nasb.ayah - 1].spannableverse!!
                try {
                    if (dark) {
                        Constant.harfinnaspanDark = ForegroundColorSpan(Color.GREEN)
                    } else {
                        Constant.harfinnaspanDark = ForegroundColorSpan(Constant.KASHMIRIGREEN)
                    }
                    //  harfinnaspanDark=new ForegroundColorSpan(GREEN);
                    spannableverse.setSpan(
                        Constant.harfinnaspanDark,
                        indexstart,
                        indexend,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } catch (e: IndexOutOfBoundsException) {
                    //    System.out.println(nasb.getSurah() + ":" + nasb.getAyah());
                    err.add(nasb.surah.toString() + ":" + nasb.ayah)
                }
                try {
                    //    spannableverse.setSpan(new ForegroundColorSpan(GOLD), ismindexone, ismindexone + lenism1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    if (dark) {
                        Constant.harfismspanDark = ForegroundColorSpan(Constant.BCYAN)
                    } else {
                        Constant.harfismspanDark = ForegroundColorSpan(Constant.prussianblue)
                    }
                    spannableverse.setSpan(
                        Constant.harfismspanDark,
                        ismstartindex,
                        ismendindex,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } catch (e: IndexOutOfBoundsException) {
                    //     System.out.println(nasb.getSurah() + ":" + nasb.getAyah());
                    err.add(nasb.surah.toString() + ":" + nasb.ayah)
                }
                try {
                    if (dark) {
                        Constant.harfkhabarspanDark = ForegroundColorSpan(Color.YELLOW)
                    } else {
                        Constant.harfkhabarspanDark = ForegroundColorSpan(Constant.deepburnsienna)
                    }
                    spannableverse.setSpan(
                        Constant.harfkhabarspanDark,
                        khabarstart,
                        khabarend,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } catch (e: IndexOutOfBoundsException) {
                    //   System.out.println(nasb.getSurah() + ":" + nasb.getAyah());
                    err.add(nasb.surah.toString() + ":" + nasb.ayah)
                }
            }
        }
    }

    fun setMudhafFromDB(corpusayahWordArrayList: ArrayList<CorpusAyahWord>, surah_id: Int) {
        val utils = Utils(QuranGrammarApplication.context!!)
        val surah = utils.getMudhafSurahNew(surah_id)


//todo 2 188 iza ahudu
        //todo 9;92 UNCERTAIN
        //TODO 9:94 JAWABHARMAHDOOF 9 95 JAWABHSARMAHODFF
        //TO 9;118 IZA IN THE MEANING OF HEENA AND 9 122 IZA AS HEENA
        for (mudhafen in surah!!) {
            val indexstart = mudhafen!!.startindex
            val indexend = mudhafen.endindex
            val frameshartharf = FrameSpan(Color.GREEN, 2f, Constant.RECKT)
            MudhafSpansSetup(
                frameshartharf,
                corpusayahWordArrayList,
                mudhafen,
                indexstart,
                indexend
            )
        }
    }

    private fun MudhafSpansSetup(
        frameshartharf: FrameSpan,
        corpusayahWordArrayList: ArrayList<CorpusAyahWord>,
        mudhafen: NewMudhafEntity,
        indexstart: Int,
        indexend: Int
    ) {
        val spannableverse: SpannableString?
        //  SpannableString spannableString;
        try {
            val qtext = corpusayahWordArrayList!!.get(mudhafen.ayah)!!.spannableverse
            val     spannableverse=SpannableString(qtext)
            //  spannableverse = corpusayahWordArrayList!!!!?.get(mudhafen.ayah - 1)?.qurantext
            // spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(mudhafen.getAyah() - 1).getSpannableverse());
            try {
                if (indexstart == 0 || indexstart > 0) {
                    if (dark) {
                        Constant.mudhafspansDark = BackgroundColorSpan(Constant.MIDNIGHTBLUE)
                    } else {
                        Constant.mudhafspansDark = BackgroundColorSpan(Constant.GREENYELLOW)
                    }
                    spannableverse?.setSpan(
                        Constant.mudhafspansDark,
                        indexstart,
                        indexend,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    //   spannableverse.setSpan(new UnderlineSpan(),indexstart, indexend, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            } catch (e: IndexOutOfBoundsException) {
                //System.out.println(e.getMessage());
            }
        } catch (e: IndexOutOfBoundsException) {
            //System.out.println(e.getMessage());
        }
    }

    fun setShart(corpusayahWordArrayList: ArrayList<CorpusAyahWord>, surah_id: Int) {
        val utils = Utils(QuranGrammarApplication.context!!)
        val surah = utils.getShartSurahNew(surah_id)
        //  final ArrayList<ShartEntity> surah = utils.getShartSurah(surah_id);

        //TO 9;118 IZA IN THE MEANING OF HEENA AND 9 122 IZA AS HEENA
        if (surah_id > 1 && surah_id <= 10 || surah_id > 57 && surah_id <= 114) {
            for (shart in surah!!) {
                val indexstart = shart!!.indexstart
                val indexend = shart!!.indexend
                val shartsindex = shart.shartindexstart
                val sharteindex = shart.shartindexend
                val jawabstartindex = shart.jawabshartindexstart
                val jawabendindex = shart.jawabshartindexend
                try {
                } catch (e: ArrayIndexOutOfBoundsException) {
                    println(shart.surah.toString() + " " + shart.ayah)
                }

                //   spanIt(SpanType.BGCOLOR,spannableString, shart, indexstart, indexend, shartsindex, sharteindex, jawabstartindex, jawabendindex);
                ColoredShart(
                    corpusayahWordArrayList,
                    shart,
                    indexstart,
                    indexend,
                    shartsindex,
                    sharteindex,
                    jawabstartindex,
                    jawabendindex
                )
            }
        }
    }

    private fun ColoredShart(
        corpusayahWordArrayList: ArrayList<CorpusAyahWord>,
        shart: NewShartEntity,
        indexstart: Int,
        indexend: Int,
        shartsindex: Int,
        sharteindex: Int,
        jawabstartindex: Int,
        jawabendindex: Int
    ) {
        val spannableverse: SpannableString
        if (dark) {
            Constant.harfshartspanDark = ForegroundColorSpan(Constant.GOLD)
            Constant.shartspanDark = ForegroundColorSpan(Constant.ORANGE400)
            Constant.jawabshartspanDark = ForegroundColorSpan(Color.CYAN)
        } else {
            Constant.harfshartspanDark = ForegroundColorSpan(Constant.FORESTGREEN)
            Constant.shartspanDark = ForegroundColorSpan(Constant.KASHMIRIGREEN)
            Constant.jawabshartspanDark = ForegroundColorSpan(Constant.WHOTPINK)
        }
        try {
            spannableverse = corpusayahWordArrayList[shart.ayah - 1].spannableverse!!
            //   spannableString = SpannableString.valueOf(corpusayahWordArrayList.get(shart.getAyah() - 1).getSpannableverse());
            try {
                if (indexstart == 0 || indexstart > 0) {
                    spannableverse.setSpan(
                        Constant.harfshartspanDark,
                        indexstart,
                        indexend,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableverse.setSpan(
                        UnderlineSpan(),
                        indexstart,
                        indexend,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                if (shartsindex == 0 || shartsindex > 0) {
                    spannableverse.setSpan(
                        Constant.shartspanDark,
                        shartsindex,
                        sharteindex,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableverse.setSpan(
                        UnderlineSpan(),
                        shartsindex,
                        sharteindex,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                if (jawabstartindex == 0 || jawabstartindex > 0) {
                    val myDrawable =
                        AppCompatResources.getDrawable(context!!, R.drawable.oval_circle)!!
                    myDrawable.setBounds(
                        0,
                        0,
                        myDrawable.intrinsicWidth,
                        myDrawable.intrinsicHeight
                    )
                    spannableverse.setSpan(
                        Constant.jawabshartspanDark,
                        jawabstartindex,
                        jawabendindex,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableverse.setSpan(
                        UnderlineSpan(),
                        jawabstartindex,
                        jawabendindex,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            } catch (e: IndexOutOfBoundsException) {
                //System.out.println(e.getMessage());
            }
        } catch (e: IndexOutOfBoundsException) {
            //System.out.println(e.getMessage());
        }
    }

    fun setKana(corpusayahWordArrayList: ArrayList<CorpusAyahWord>, surah_id: Int) {
        val utils = Utils(
            context!!.applicationContext
        )
        val kanalist = utils.getKananew(surah_id)
        val harfkana: ForegroundColorSpan
        val kanaism: ForegroundColorSpan
        val kanakhbar: ForegroundColorSpan
        if (dark) {
            harfkana = ForegroundColorSpan(Constant.GOLD)
            kanaism = ForegroundColorSpan(Constant.ORANGE400)
            kanakhbar = ForegroundColorSpan(Color.CYAN)
        } else {
            harfkana = ForegroundColorSpan(Constant.FORESTGREEN)
            kanaism = ForegroundColorSpan(Constant.KASHMIRIGREEN)
            kanakhbar = ForegroundColorSpan(Constant.WHOTPINK)
        }
        if (surah_id > 1 && surah_id <= 10 || surah_id > 58 && surah_id <= 114) {
            for (kana in kanalist!!) {
                val spannableverse = corpusayahWordArrayList[kana!!.ayah - 1].spannableverse
                try {
                    if (spannableverse != null) {
                        spannableverse.setSpan(
                            harfkana,
                            kana!!.indexstart,
                            kana!!.indexend,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                    //    shart.setSpannedverse(spannableverse);
                    if (spannableverse != null) {
                        spannableverse.setSpan(
                            kanakhbar,
                            kana!!.khabarstart,
                            kana!!.khabarend,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                    //   shart.setSpannedverse(spannableverse);
                    if (spannableverse != null) {
                        spannableverse.setSpan(
                            kanaism,
                            kana!!.ismkanastart,
                            kana!!.ismkanaend,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }
                    //   shart.setSpannedverse(spannableverse);
                } catch (e: IndexOutOfBoundsException) {
                    //System.out.println(e.getMessage());
                }
            }
        }
    }

    companion object {
        private var dark = true
        @JvmStatic
        fun NewSetWordSpanTag(
            tagone: String,
            tagtwo: String,
            tagthree: String,
            tagfour: String,
            tagfive: String,
            arafive: String,
            arafour: String,
            arathree: String,
            aratwo: String,
            araone: String
        ): SpannableString? {
            var arafive = arafive
            var arafour = arafour
            var arathree = arathree
            var aratwo = aratwo
            var araone = araone
            var str: SpannableString? = null
            var tagcounter = 0
            val b = tagone.length > 0
            val bb = tagtwo.length > 0
            val bbb = tagthree.length > 0
            val bbbb = tagfour.length > 0
            val bbbbb = tagfive.length > 0
            if (b && !bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 1
            } else if (b && bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 2
            } else if (b && bb && bbb && !bbbb && !bbbbb) {
                tagcounter = 3
            } else if (b && bb && bbb && bbbb && !bbbbb) {
                tagcounter = 4
            } else if (b && bb && bbb && bbbb) {
                tagcounter = 5
            }
            araone = araone.trim { it <= ' ' }
            aratwo = aratwo.trim { it <= ' ' }
            arathree = arathree.trim { it <= ' ' }
            arafour = arafour.trim { it <= ' ' }
            arafive = arafive.trim { it <= ' ' }
            //
            val spanhash: Map<String?, ForegroundColorSpan>
            spanhash = stringForegroundColorSpanMap
            if (tagcounter == 1) {
                //   Set<String> strings = spanhash.keySet();
                str =
                    SpannableString(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else if (tagcounter == 2) {
                val strone = SpannableString(araone.trim { it <= ' ' })
                val strtwo = SpannableString(aratwo.trim { it <= ' ' })
                strtwo.setSpan(spanhash[tagtwo], 0, aratwo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                strone.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                val charSequence = TextUtils.concat(strtwo, strone)
                str = SpannableString(charSequence)
            } else if (tagcounter == 3) {
                spanhash[tagone]
                val strone = SpannableString(araone.trim { it <= ' ' })
                val strtwo = SpannableString(aratwo.trim { it <= ' ' })
                val strthree = SpannableString(arathree.trim { it <= ' ' })
                strthree.setSpan(
                    spanhash[tagthree],
                    0,
                    arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                strtwo.setSpan(spanhash[tagtwo], 0, aratwo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                strone.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                val charSequence = TextUtils.concat(strthree, strtwo, strone)
                str = SpannableString(charSequence)
            } else if (tagcounter == 4) {
                //  str = new SpannableString(arafour.trim() + arathree.trim() + aratwo.trim() + araone.trim());
                val strone = SpannableString(araone.trim { it <= ' ' })
                val strtwo = SpannableString(aratwo.trim { it <= ' ' })
                val strthree = SpannableString(arathree.trim { it <= ' ' })
                val strfour = SpannableString(arafour.trim { it <= ' ' })
                strfour.setSpan(
                    spanhash[tagfour],
                    0,
                    arafour.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                strthree.setSpan(
                    spanhash[tagthree],
                    0,
                    arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                strtwo.setSpan(spanhash[tagtwo], 0, aratwo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                strone.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                val charSequence = TextUtils.concat(strfour, strthree, strtwo, strone)
                str = SpannableString(charSequence)
            } else if (tagcounter == 5) {
                val strone = SpannableString(araone.trim { it <= ' ' })
                val strtwo = SpannableString(aratwo.trim { it <= ' ' })
                val strthree = SpannableString(arathree.trim { it <= ' ' })
                val strfour = SpannableString(arafour.trim { it <= ' ' })
                val strfive = SpannableString(arafive.trim { it <= ' ' })
                strfive.setSpan(
                    spanhash[tagone],
                    0,
                    arafive.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                strfour.setSpan(
                    spanhash[tagtwo],
                    0,
                    arafour.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                strthree.setSpan(
                    spanhash[tagthree],
                    0,
                    arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                strtwo.setSpan(
                    spanhash[tagfour],
                    0,
                    aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                strone.setSpan(
                    spanhash[tagfive],
                    0,
                    araone.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                val charSequence = TextUtils.concat(strone, strtwo, strthree, strfour, strfive)
                str = SpannableString(charSequence)
            }
            return str
        }

        @JvmStatic
        fun ColorizeRootword(
            tagone: String, tagtwo: String, tagthree: String, tagfour: String, tagfive: String,
            rootword: String
        ): SpannableString? {
            var str: SpannableString? = null
            var tagcounter = 0
            val b = tagone.length > 0
            val bb = tagtwo.length > 0
            val bbb = tagthree.length > 0
            val bbbb = tagfour.length > 0
            val bbbbb = tagfive.length > 0
            if (b && !bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 1
            } else if (b && bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 2
            } else if (b && bb && bbb && !bbbb && !bbbbb) {
                tagcounter = 3
            } else if (b && bb && bbb && bbbb && !bbbbb) {
                tagcounter = 4
            } else if (b && bb && bbb && bbbb) {
                tagcounter = 5
            }
            val spanhash: Map<String?, ForegroundColorSpan>
            //   SharedPreferences sharedPreferences =
            //      androidx.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.context!!);
            //   String isNightmode = sharedPreferences.getString("themepref", "dark" );
            //   if (isNightmode.equals("dark")||isNightmode.equals("blue")) {
            spanhash = stringForegroundColorSpanMap
            //  }else{
            //   spanhash = getColorSpanforPhrasesLight();
            //  }
            if (tagcounter == 1) {
                str = SpannableString(rootword.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, rootword.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else if (tagcounter == 2) {
                if (tagone == "N" || tagone == "ADJ" || tagone == "PN" || tagone == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagone],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else if (tagtwo == "N" || tagtwo == "ADJ" || tagtwo == "PN" || tagtwo == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagtwo],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            } else if (tagcounter == 3) {
                if (tagone == "N" || tagone == "ADJ" || tagone == "PN" || tagone == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagone],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else if (tagtwo == "N" || tagtwo == "ADJ" || tagtwo == "PN" || tagtwo == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagtwo],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else if (tagthree == "N" || tagthree == "ADJ" || tagthree == "PN" || tagthree == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagthree],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            } else if (tagcounter == 4) {
                if (tagone == "N" || tagone == "ADJ" || tagone == "PN" || tagone == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagone],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else if (tagtwo == "N" || tagtwo == "ADJ" || tagtwo == "PN" || tagtwo == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagtwo],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else if (tagthree == "N" || tagthree == "ADJ" || tagthree == "PN" || tagthree == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagthree],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else if (tagfour == "N" || tagfour == "ADJ" || tagfour == "PN" || tagfour == "V") {
                    str = SpannableString(rootword.trim { it <= ' ' })
                    str.setSpan(
                        spanhash[tagthree],
                        0,
                        rootword.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            }
            return str
        }

        @JvmStatic
        fun NewSetWordSpan(
            tagone: String?,
            tagtwo: String?,
            tagthree: String?,
            tagfour: String?,
            tagfive: String?,
            araone: String,
            aratwo: String,
            arathree: String,
            arafour: String,
            arafive: String
        ): SpannableString {
            var araone = araone
            var aratwo = aratwo
            var arathree = arathree
            var arafour = arafour
            var arafive = arafive
            var str: SpannableString
            val istagnull =
                tagone == null || tagtwo == null || tagthree == null || tagfour == null || tagfive == null
            if (istagnull) {
                return SpannableString(araone).also { str = it }
            }
            var tagcounter = 0
            val b = tagone!!.length > 0
            val bb = tagtwo!!.length > 0
            val bbb = tagthree!!.length > 0
            val bbbb = tagfour!!.length > 0
            val bbbbb = tagfive!!.length > 0
            if (b && !bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 1
            } else if (b && bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 2
            } else if (b && bb && bbb && !bbbb && !bbbbb) {
                tagcounter = 3
            } else if (b && bb && bbb && bbbb && !bbbbb) {
                tagcounter = 4
            } else if (b && bb && bbb && bbbb) {
                tagcounter = 5
            }
            araone = araone.trim { it <= ' ' }
            aratwo = aratwo.trim { it <= ' ' }
            arathree = arathree.trim { it <= ' ' }
            arafour = arafour.trim { it <= ' ' }
            arafive = arafive.trim { it <= ' ' }
            val spanhash: Map<String?, ForegroundColorSpan>
            //   SharedPreferences sharedPreferences =
            //      androidx.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.context!!);
            //   String isNightmode = sharedPreferences.getString("themepref", "dark" );
            //   if (isNightmode.equals("dark")||isNightmode.equals("blue")) {
            spanhash = stringForegroundColorSpanMap
            //  }else{
            //   spanhash = getColorSpanforPhrasesLight();
            //  }
            if (tagcounter == 1) {
                str = SpannableString(araone.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else if (tagcounter == 2) {
                str = SpannableString(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                str.setSpan(
                    spanhash[tagtwo],
                    araone.length,
                    araone.length + aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else if (tagcounter == 3) {
                spanhash[tagone]
                str =
                    SpannableString(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                str.setSpan(
                    spanhash[tagtwo],
                    araone.length,
                    araone.length + aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagthree],
                    araone.length + aratwo.length,
                    araone.length + aratwo.length + arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else if (tagcounter == 4) {
                str =
                    SpannableString(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' } + arafour.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                str.setSpan(
                    spanhash[tagtwo],
                    araone.length,
                    araone.length + aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagthree],
                    araone.length + aratwo.length,
                    araone.length + aratwo.length + arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagfour],
                    araone.length + aratwo.length + arathree.length,
                    araone.length + aratwo.length + arathree.length + arafour.trim { it <= ' ' }.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                //    str.setSpan(attachedpronoun, araone.length() + aratwo.length() + arathree.length() + arafour.length(), araone.length() + aratwo.length() + arathree.length() + arafour.trim().length() + arafive.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (tagcounter == 5) {
                str =
                    SpannableString(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' } + arafour.trim { it <= ' ' } + arafive.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                str.setSpan(
                    spanhash[tagtwo],
                    araone.length,
                    araone.length + aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagthree],
                    araone.length + aratwo.length,
                    araone.length + aratwo.length + arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagfour],
                    araone.length + aratwo.length + arathree.length,
                    araone.length + aratwo.length + arathree.length + arafour.trim { it <= ' ' }.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagfive],
                    araone.length + aratwo.length + arathree.length + arafour.length,
                    araone.length + aratwo.length + arathree.length + arafour.trim { it <= ' ' }.length + arafive.trim { it <= ' ' }.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                str =
                    SpannableString(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' } + arafour.trim { it <= ' ' } + arafive.trim { it <= ' ' })
            }
            return str
        }// "Emphatic lām prefix(لام التوكيد) ",

        // "Imperative lāmprefix(لام الامر)",
        // "Purpose lāmprefix(لام التعليل)",
        // "	Subordinating conjunction(حرف مصدري)",
        // "	Accusative particle(حرف نصب)",
        // "	Answer particle	(حرف جواب)",
        // "Particle of cause	(حرف سببية)",
        // "Particle of certainty	(حرف تحقيق)",
        // "Circumstantial particle	(حرف حال)",
        // "Coordinating conjunction(حرف عطف)",
        // "Conditional particle(حرف شرط)",
        // "	Amendment particle(حرف استدراك)	",
        // "	Aversion particle	(حرف ردع)",
        // "	Comitative particle	(واو المعية)",
        // "	Equalization particle(حرف تسوية)",
        // "	Exhortation particle(حرف تحضيض)",
        // "	Explanation particle(حرف تفصيل)",
        // "	Exceptive particle	(أداة استثناء)",
        // "	Future particle	(حرف استقبال)",
        // "	Inceptive particle	(حرف ابتداء)",
        // "	Particle of interpretation(حرف تفسير)",
        // "	Retraction particle	(حرف اضراب)",
        // "Preventive particle	(حرف كاف)",
        // "	Vocative particle	(حرف نداء)",
        // "	Quranic initials(	(حروف مقطعة	";
        // "Interogative particle	(حرف استفهام)",
        // "	Negative particle(حرف نفي)",
        // "	Prohibition particle(حرف نهي)",
        // "	Resumption particle	(حرف استئنافية)",
        // "	Restriction particle(أداة حصر)",
        // "Result particle(حرف واقع في جواب الشرط)",
        // "	Supplemental particle	(حرف زائد)",
        // "	Surprise particle	(حرف فجاءة)",
        // "Emphatic lām prefix(لام التوكيد) ",
        // "Imperative lāmprefix(لام الامر)",
        // "Purpose lāmprefix(لام التعليل)",
        // "	Subordinating conjunction(حرف مصدري)",
        // "	Accusative particle(حرف نصب)",
        // "	Answer particle	(حرف جواب)",
        // "Particle of cause	(حرف سببية)",
        // "Particle of certainty	(حرف تحقيق)",
        // "Circumstantial particle	(حرف حال)",
        // "Coordinating conjunction(حرف عطف)",
        // "Conditional particle(حرف شرط)",
        // "	Amendment particle(حرف استدراك)	",
        // "	Aversion particle	(حرف ردع)",
        // "	Comitative particle	(واو المعية)",
        // "	Equalization particle(حرف تسوية)",
        // "	Exhortation particle(حرف تحضيض)",
        // "	Explanation particle(حرف تفصيل)",
        // "	Exceptive particle	(أداة استثناء)",
        // "	Future particle	(حرف استقبال)",
        // "	Inceptive particle	(حرف ابتداء)",
        // "	Particle of interpretation(حرف تفسير)",
        // "	Retraction particle	(حرف اضراب)",
        // "Preventive particle	(حرف كاف)",
        // "	Vocative particle	(حرف نداء)",
        // "	Quranic initials(	(حروف مقطعة	";
        // "Interogative particle	(حرف استفهام)",
        // "	Negative particle(حرف نفي)",
        // "	Prohibition particle(حرف نهي)",
        // "	Resumption particle	(حرف استئنافية)",
        // "	Restriction particle(أداة حصر)",
        // "Result particle(حرف واقع في جواب الشرط)",
        // "	Supplemental particle	(حرف زائد)",
        // "	Surprise particle	(حرف فجاءة)",
        @JvmStatic
        val stringForegroundColorSpanMap: Map<String?, ForegroundColorSpan>
            get() {
                val spanhash: MutableMap<String?, ForegroundColorSpan> = HashMap()
                if (dark) {
                    spanhash["PN"] = Constant.propernounspanDark
                    spanhash["REL"] = Constant.relativespanDark
                    spanhash["DEM"] = Constant.demonstrativespanDark
                    spanhash["N"] = Constant.nounspanDark
                    spanhash["PRON"] = Constant.pronounspanDark
                    spanhash["DET"] = Constant.determinerspanDark
                    spanhash["V"] = Constant.verbspanDark
                    spanhash["P"] = Constant.prepositionspanDark
                    spanhash["T"] = Constant.timeadverbspanDark
                    spanhash["LOC"] = Constant.locationadverspanDark
                    spanhash["ADJ"] = Constant.adjectivespanDark
                    spanhash["VN"] = Constant.verbalnounspanDark
                    spanhash["EMPH"] = Constant.emphspanDark // "Emphatic lām prefix(لام التوكيد) ",
                    spanhash["IMPV"] =
                        Constant.lamimpvspanDark // "Imperative lāmprefix(لام الامر)",
                    spanhash["PRP"] =
                        Constant.lamtaleelspandDark // "Purpose lāmprefix(لام التعليل)",
                    spanhash["SUB"] =
                        Constant.masdariaspanDark // "	Subordinating conjunction(حرف مصدري)",
                    spanhash["ACC"] = Constant.nasabspanDark // "	Accusative particle(حرف نصب)",
                    spanhash["ANS"] = Constant.answerspanDark // "	Answer particle	(حرف جواب)",
                    spanhash["CAUS"] =
                        Constant.harfsababiaspanDark // "Particle of cause	(حرف سببية)",
                    spanhash["CERT"] =
                        Constant.certainityspanDark // "Particle of certainty	(حرف تحقيق)",
                    spanhash["CIRC"] = Constant.halspanDark // "Circumstantial particle	(حرف حال)",
                    spanhash["CONJ"] =
                        Constant.particlespanDark // "Coordinating conjunction(حرف عطف)",
                    spanhash["COND"] =
                        Constant.harfshartspanDark // "Conditional particle(حرف شرط)",
                    spanhash["AMD"] =
                        Constant.particlespanDark // "	Amendment particle(حرف استدراك)	",
                    spanhash["AVR"] = Constant.particlespanDark // "	Aversion particle	(حرف ردع)",
                    spanhash["COM"] =
                        Constant.particlespanDark // "	Comitative particle	(واو المعية)",
                    spanhash["EQ"] =
                        Constant.particlespanDark // "	Equalization particle(حرف تسوية)",
                    spanhash["EXH"] =
                        Constant.particlespanDark // "	Exhortation particle(حرف تحضيض)",
                    spanhash["EXL"] =
                        Constant.particlespanDark // "	Explanation particle(حرف تفصيل)",
                    spanhash["EXP"] =
                        Constant.particlespanDark // "	Exceptive particle	(أداة استثناء)",
                    spanhash["FUT"] = Constant.particlespanDark // "	Future particle	(حرف استقبال)",
                    spanhash["INC"] =
                        Constant.particlespanDark // "	Inceptive particle	(حرف ابتداء)",
                    spanhash["INT"] =
                        Constant.particlespanDark // "	Particle of interpretation(حرف تفسير)",
                    spanhash["RET"] =
                        Constant.particlespanDark // "	Retraction particle	(حرف اضراب)",
                    spanhash["PREV"] = Constant.particlespanDark // "Preventive particle	(حرف كاف)",
                    spanhash["VOC"] = Constant.particlespanDark // "	Vocative particle	(حرف نداء)",
                    spanhash["INL"] =
                        Constant.particlespanDark // "	Quranic initials(	(حروف مقطعة	";
                    spanhash["INTG"] =
                        Constant.interrogativespanDark // "Interogative particle	(حرف استفهام)",
                    spanhash["NEG"] = Constant.negativespanDark // "	Negative particle(حرف نفي)",
                    spanhash["PRO"] =
                        Constant.prohibitionspanDark // "	Prohibition particle(حرف نهي)",
                    spanhash["REM"] =
                        Constant.resumtionspanDark // "	Resumption particle	(حرف استئنافية)",
                    spanhash["RES"] =
                        Constant.restrictivespanDark // "	Restriction particle(أداة حصر)",
                    spanhash["RSLT"] =
                        Constant.resultparticlespanDark // "Result particle(حرف واقع في جواب الشرط)",
                    spanhash["SUP"] =
                        Constant.supplementspoanDark // "	Supplemental particle	(حرف زائد)",
                    spanhash["SUR"] = Constant.surprisespanDark // "	Surprise particle	(حرف فجاءة)",
                } else {
                    spanhash["PN"] = Constant.propernounspanLight
                    spanhash["REL"] = Constant.relativespanLight
                    spanhash["DEM"] = Constant.demonstrativespanLight
                    spanhash["N"] = Constant.nounspanLight
                    spanhash["PRON"] = Constant.pronounspanLight
                    spanhash["DET"] = Constant.determinerspanLight
                    spanhash["V"] = Constant.verbspanLight
                    spanhash["P"] = Constant.prepositionspanLight
                    spanhash["T"] = Constant.timeadverbspanLight
                    spanhash["LOC"] = Constant.locationadverspanLight
                    spanhash["ADJ"] = Constant.adjectivespanLight
                    spanhash["VN"] = Constant.verbalnounspanLight
                    spanhash["EMPH"] =
                        Constant.emphspanLight // "Emphatic lām prefix(لام التوكيد) ",
                    spanhash["IMPV"] =
                        Constant.lamimpvspanLight // "Imperative lāmprefix(لام الامر)",
                    spanhash["PRP"] =
                        Constant.lamtaleelspandLight // "Purpose lāmprefix(لام التعليل)",
                    spanhash["SUB"] =
                        Constant.masdariaspanLight // "	Subordinating conjunction(حرف مصدري)",
                    spanhash["ACC"] = Constant.nasabspanLight // "	Accusative particle(حرف نصب)",
                    spanhash["ANS"] = Constant.answerspanLight // "	Answer particle	(حرف جواب)",
                    spanhash["CAUS"] =
                        Constant.harfsababiaspanLight // "Particle of cause	(حرف سببية)",
                    spanhash["CERT"] =
                        Constant.certainityspanLight // "Particle of certainty	(حرف تحقيق)",
                    spanhash["CIRC"] =
                        Constant.particlespanLight // "Circumstantial particle	(حرف حال)",
                    spanhash["CONJ"] =
                        Constant.particlespanLight // "Coordinating conjunction(حرف عطف)",
                    spanhash["COND"] = Constant.eqspanlight // "Conditional particle(حرف شرط)",
                    spanhash["AMD"] =
                        Constant.ammendedparticle // "	Amendment particle(حرف استدراك)	",
                    spanhash["AVR"] = Constant.particlespanLight // "	Aversion particle	(حرف ردع)",
                    spanhash["COM"] =
                        Constant.particlespanLight // "	Comitative particle	(واو المعية)",
                    spanhash["EQ"] =
                        Constant.particlespanLight // "	Equalization particle(حرف تسوية)",
                    spanhash["EXH"] =
                        Constant.particlespanLight // "	Exhortation particle(حرف تحضيض)",
                    spanhash["EXL"] =
                        Constant.particlespanLight // "	Explanation particle(حرف تفصيل)",
                    spanhash["EXP"] =
                        Constant.particlespanLight // "	Exceptive particle	(أداة استثناء)",
                    spanhash["FUT"] =
                        Constant.particlespanLight // "	Future particle	(حرف استقبال)",
                    spanhash["INC"] = Constant.nasabspanLight // "	Inceptive particle	(حرف ابتداء)",
                    spanhash["INT"] =
                        Constant.particlespanLight // "	Particle of interpretation(حرف تفسير)",
                    spanhash["RET"] =
                        Constant.particlespanLight // "	Retraction particle	(حرف اضراب)",
                    spanhash["PREV"] = Constant.inceptivepartile // "Preventive particle	(حرف كاف)",
                    spanhash["VOC"] = Constant.particlespanLight // "	Vocative particle	(حرف نداء)",
                    spanhash["INL"] =
                        Constant.particlespanLight // "	Quranic initials(	(حروف مقطعة	";
                    spanhash["INTG"] =
                        Constant.interrogativespanLight // "Interogative particle	(حرف استفهام)",
                    spanhash["NEG"] = Constant.negativespanLight // "	Negative particle(حرف نفي)",
                    spanhash["PRO"] =
                        Constant.prohibitionspanLight // "	Prohibition particle(حرف نهي)",
                    spanhash["REM"] =
                        Constant.particlespanLight // "	Resumption particle	(حرف استئنافية)",
                    spanhash["RES"] =
                        Constant.restrictivespanLight // "	Restriction particle(أداة حصر)",
                    spanhash["RSLT"] =
                        Constant.resultparticlespanLight // "Result particle(حرف واقع في جواب الشرط)",
                    spanhash["SUP"] =
                        Constant.supplementspanLight // "	Supplemental particle	(حرف زائد)",
                    spanhash["SUR"] =
                        Constant.surprisespanLight // "	Surprise particle	(حرف فجاءة)",
                }
                return spanhash
            }

        @JvmStatic
        fun getSpancolor(b: Boolean): BackgroundColorSpan {
            val sifaspansDark = Constant.sifaspansDark
            val mudhafspansDark = Constant.mudhafspansDark
            return if (b) {
                if (dark) {
                    Constant.mudhafspansDark = BackgroundColorSpan(Constant.MIDNIGHTBLUE)
                } else {
                    Constant.mudhafspansDark = BackgroundColorSpan(Constant.GREENYELLOW)
                }
                mudhafspansDark
            } else {
                if (dark) {
                    Constant.sifaspansDark = BackgroundColorSpan(Constant.WBURNTUMBER)
                } else {
                    Constant.sifaspansDark = BackgroundColorSpan(Constant.CYANLIGHTEST)
                }
                sifaspansDark
            }
        }

        @JvmStatic
        fun getSpannableVerses(arabicword: String, quranverses: String): Spannable {
            val wordlen = arabicword.length
            val str: SpannableString
            val indexOf = quranverses.indexOf(arabicword)
            if (indexOf != -1) {
                str = SpannableString(quranverses)
                if (dark) {
                    str.setSpan(
                        ForegroundColorSpan(Color.CYAN),
                        indexOf,
                        indexOf + wordlen,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                } else {
                    str.setSpan(
                        ForegroundColorSpan(
                            ContextCompat.getColor(
                                QuranGrammarApplication.context!!,
                                R.color.midnightblue
                            )
                        ), indexOf, indexOf + wordlen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
            } else {
                str = SpannableString(quranverses)
            }
            return str
        }

        @JvmStatic
        fun getSpannable(text: String): Spannable {
            val spannable: Spannable = SpannableString(text)
            val REGEX = "لل"
            val p = Pattern.compile(REGEX)
            val m = p.matcher(text)
            var start: Int
            var end: Int

            //region allah match
            while (m.find()) {
                start = m.start()
                while (text[start] != ' ' && start != 0) {
                    start--
                }
                end = m.end()
                while (text[end] != ' ') {
                    end++
                }
                spannable.setSpan(
                    ForegroundColorSpan(Color.RED),
                    start,
                    end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            //endregion
            return spannable
        }

        fun PassageSetWordSpan(
            tagone: String?,
            tagtwo: String?,
            tagthree: String?,
            tagfour: String?,
            tagfive: String?,
            araone: String,
            aratwo: String,
            arathree: String,
            arafour: String,
            arafive: String
        ): SpannableStringBuilder {
            var araone = araone
            var aratwo = aratwo
            var arathree = arathree
            var arafour = arafour
            var arafive = arafive
            var str: SpannableStringBuilder
            val istagnull =
                tagone == null || tagtwo == null || tagthree == null || tagfour == null || tagfive == null
            if (istagnull) {
                return SpannableStringBuilder(araone).also { str = it }
            }
            var tagcounter = 0
            val b = tagone!!.length > 0
            val bb = tagtwo!!.length > 0
            val bbb = tagthree!!.length > 0
            val bbbb = tagfour!!.length > 0
            val bbbbb = tagfive!!.length > 0
            if (b && !bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 1
            } else if (b && bb && !bbb && !bbbb && !bbbbb) {
                tagcounter = 2
            } else if (b && bb && bbb && !bbbb && !bbbbb) {
                tagcounter = 3
            } else if (b && bb && bbb && bbbb && !bbbbb) {
                tagcounter = 4
            } else if (b && bb && bbb && bbbb) {
                tagcounter = 5
            }
            araone = araone.trim { it <= ' ' }
            aratwo = aratwo.trim { it <= ' ' }
            arathree = arathree.trim { it <= ' ' }
            arafour = arafour.trim { it <= ' ' }
            arafive = arafive.trim { it <= ' ' }
            val spanhash: Map<String?, ForegroundColorSpan>
            //   SharedPreferences sharedPreferences =
            //      androidx.preference.PreferenceManager.getDefaultSharedPreferences(DarkThemeApplication.context!!);
            //   String isNightmode = sharedPreferences.getString("themepref", "dark" );
            //   if (isNightmode.equals("dark")||isNightmode.equals("blue")) {
            spanhash = stringForegroundColorSpanMap
            //  }else{
            //   spanhash = getColorSpanforPhrasesLight();
            //  }
            if (tagcounter == 1) {
                str = SpannableStringBuilder(araone.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            } else if (tagcounter == 2) {
                val stro = SpannableStringBuilder(araone.trim { it <= ' ' })
                val strt = SpannableStringBuilder(aratwo.trim { it <= ' ' })
                stro.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                strt.setSpan(spanhash[tagtwo], 0, aratwo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                val concat = TextUtils.concat(stro, strt)
                str = concat as SpannableStringBuilder
            } else if (tagcounter == 3) {
                spanhash[tagone]
                str =
                    SpannableStringBuilder(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                str.setSpan(
                    spanhash[tagtwo],
                    araone.length,
                    araone.length + aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagthree],
                    araone.length + aratwo.length,
                    araone.length + aratwo.length + arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else if (tagcounter == 4) {
                str =
                    SpannableStringBuilder(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' } + arafour.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                str.setSpan(
                    spanhash[tagtwo],
                    araone.length,
                    araone.length + aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagthree],
                    araone.length + aratwo.length,
                    araone.length + aratwo.length + arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagfour],
                    araone.length + aratwo.length + arathree.length,
                    araone.length + aratwo.length + arathree.length + arafour.trim { it <= ' ' }.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                //    str.setSpan(attachedpronoun, araone.length() + aratwo.length() + arathree.length() + arafour.length(), araone.length() + aratwo.length() + arathree.length() + arafour.trim().length() + arafive.trim().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (tagcounter == 5) {
                str =
                    SpannableStringBuilder(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' } + arafour.trim { it <= ' ' } + arafive.trim { it <= ' ' })
                str.setSpan(spanhash[tagone], 0, araone.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                str.setSpan(
                    spanhash[tagtwo],
                    araone.length,
                    araone.length + aratwo.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagthree],
                    araone.length + aratwo.length,
                    araone.length + aratwo.length + arathree.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagfour],
                    araone.length + aratwo.length + arathree.length,
                    araone.length + aratwo.length + arathree.length + arafour.trim { it <= ' ' }.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                str.setSpan(
                    spanhash[tagfive],
                    araone.length + aratwo.length + arathree.length + arafour.length,
                    araone.length + aratwo.length + arathree.length + arafour.trim { it <= ' ' }.length + arafive.trim { it <= ' ' }.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                str =
                    SpannableStringBuilder(araone.trim { it <= ' ' } + aratwo.trim { it <= ' ' } + arathree.trim { it <= ' ' } + arafour.trim { it <= ' ' } + arafive.trim { it <= ' ' })
            }
            return str
        }
    }
}