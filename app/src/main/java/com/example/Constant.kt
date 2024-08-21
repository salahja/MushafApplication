package com.example

import android.graphics.Color
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication

object Constant {
    const val BIHIHEADER = "مفعول  به" + "\n"
    const val HALHEADER = "حالية/حال/في محل" + "\n"
    const val AJLIHIHEADER = "مفعول لأجله " + "\n"
    const val MUTLAQHEADER = "فعولا  مطلق" + "\n"
    const val BADALHEADER = "بدل" + "\n"
    const val TAMEEZHEADER = "تمييز" + "\n"
    const val BIHI = "مفعول  به"
    const val HAL = "حالية/حال/في محل"
    const val AJLIHI = "مفعول لأجله "
    const val MUTLAQ = "فعولا  مطلق"
    const val BADAL = "بدل"
    const val TAMEEZ = "تمييز"
    const val FATHA = "a"
    const val KASARA = "i"
    const val DHUMMA = "u"
    const val RA = "r"
    const val FATHATAIN = "F"
    const val KASARATAIN = "K"
    const val DHUMMATAIN = "N"
    const val ALIF = "A"
    const val MADDAH = "ٓ "
    const val SUKUN = "o"
    const val MEEM = "m"
    const val MUTTADI = "م"
    const val LAZIM = "ل"
    const val MUTTADILAZIM = "ك"
    const val LALIFMAKSURA = "ى"
    const val ALIFHAMZABELOW = "<"
    const val ALIFHAMZAABOVE = ">"
    const val ALIFMAKSURA = "Y"
    const val HAMZA = "'"
    const val YAHAMZAABOVE = "}"
    const val SMALLYA = "."
    const val WAWHAMZAABOVE = "&"
    const val WAW = "w"
    const val LAM = "l"
    const val TA = "t"
    const val MEEMWITHFATHA = "m" + "a"
    const val MEEMWITHKESARA = "m" + "i"
    const val TAAMARBOOTA = "p"
    const val NUUN = "n"
    const val NOON = "ن"
    const val SHADDA = "~"
    const val YU = "y" + "u"
    const val MU = "m" + "u"
    const val YA = "y"
    const val SEEN = "s"
    const val MADDA = "ٓ "
    const val COMMA = ","
    const val HAMZAABOVE = "#"
    const val DAL = "d"
    const val ZAAL = "*"
    const val ZAIN = "z"
    const val SAD = "S"
    const val DHAD = "D"
    const val TOA = "T"
    const val ZOA = "Z"
    const val ALIFKHANJAR = "`"
    const val PASTHUMA = "A"
    const val RELATIVE = "rel"
    const val CONDITIONAL = "cond"
    const val DEMONSTRATIVE = "dem"
    const val PREPOSITION = "prep"
    const val ACCUSATIVE = "accusative"
    const val VERBMOOD = "verbcase"
    const val INDICATIVE = "Indicative"
    const val VERBTYPE = "verbtype"
    const val NOUNCASE = "nouncase"
    const val ISPARTICPLE = "particple"
    const val IMPERATIVE = "imperative"
    const val QURAN_VERB_WAZAN = "form"
    const val SARFKABEER = "sarfkabeer"
    const val QURAN_VERB_ROOT = "root!!"
    const val VERBDETAILS = "verb"
    const val WORDDETAILS = "worddetails"
    const val QURAN_VOCUBALORY_ROOT = "vroot!!"
    const val MUJARRADVERBTAG = "thulathiverblist"
    const val TAFEEL = "تَفْعِيل-II"
    const val MUFAALA = "مُفَاعَلَة-III"
    const val IFAL = "إِفْعَال-IV"
    const val TAFAUL = "تَفَعُّل-V"
    const val TAFAAUL = "تَفَاعُل-VI"
    const val INFAAL = "اِنْفِعَال-VII"
    const val IFTAALA = "اِفْتِعَال-VIII"
    const val ISTAFALA = "اِسْتِفْعَال-X1"
    const val MITHAL = "Mithal"
    const val AJFAW = "Ajwaf"
    const val MUDHAAF = "Mudhaf"
    const val NAQIS = "Naqis"
    const val MAQROON = "Lafeef Maqroon"
    const val MAFROOQ = "Lafeef Mafrooq"
    const val MAHMOOZ = "Mahmooz"
    const val BNASARA = "نصر" //   A-U // NASRA-YANSURU
    const val BZARABA = "ضرب" //   A-I // ZARABA-YASZRIBU
    const val BFATAH = "فتح" //  A-A // FATHA-YAFTAHU
    const val BSAMIA = "سمح" //  I-A  //SAMIA-YASMAHU
    const val BKARUMU = "كرم" //   U-U  //KARUMA-YAKRUMU
    const val BHASIBA = "حسب" //  I-I  //HASIBA-YAHSIU
    const val NASARA = "nasara" //   A-U // NASRA-YANSURU
    const val ZARABA = "zaraba" //   A-I // ZARABA-YASZRIBU
    const val FATAHA = "fatha" //  A-A // FATHA-YAFTAHU
    const val SAMIA = "samia" //  I-A  //SAMIA-YASMAHU
    const val KARUMA = "karuma" //   U-U  //KARUMA-YAKRUMU
    const val HASIBA = "hasiba" //  I-I  //HASIBA-YAHSIU
    const val SARFKABEERWEAKNESS = "skverb"
    const val ASALEM = "سالم"
    const val AMAHMOOZFA = " المهموز ف"
    const val AMAHMOOZAYN = "  المهموز ع"
    const val AMAHMOOZLAM = " المهموز ل"
    const val AMITHALWAWI = "ميثال  واوي"
    const val AMITHALYAYI = "ميثال   يايئ"
    const val AAJWAFWAWI = " الأَجوَف   واوي"
    const val AAJWAFYAYI = "الأَجوَف    يايئ"
    const val ANAQISWAWI = "الناقص  واوي"
    const val ANAQISYAYI = "الناقص    يايئ"
    const val ALAFEEFMAFROOQ = "لقيق مفروق" //F AND L ARE VOWELS
    const val ALAFEEFMAQROON = "لقيق مقرون" //AYN AND LA ARE VOWELS;
    const val AMUDHAF = "مضاعف" //AYN AND LAM ARE SAME
    const val SURAHFRAGTAG = "surah"
    const val BOOKMARKTAG = "bookmarktag"
    const val MUFRADATFRAGTAG = "mufrad"
    const val READINGSURAHPART = "readingsurahpart"
    const val VERSESCOUNT = "versescount"
    const val SURAHNAME = "suraname"
    const val RUKUCOUNT = "rukucount"
    const val WBW = "wbw"
    const val AYAHNUMBER = "aya"
    const val SURAH_ARABIC_NAME = "arabicname"
    const val ARABICWORD = "arabicword"
    const val WORDMEANING = "wordmeaning"
    const val CHAPTER = "chapter"
    const val LASTAYA = "lastaya"
    const val CHAPTERORPART = "chapterorpart"
    const val MAKKI_MADANI = "makkimadani"
    const val WORDNUMBER = "wordnumber"
    const val AYAH_ID = "ayah_id"
    const val SURAH_ID = "surah_id"
    const val HARF="harf"
    const val RECKT = "reckt"
    const val ISMAKKI = "ismakki"
    const val OVAL = "oval"
    const val ARC = "arc"

    class Constant {
        enum class Status {
            PENDING,
            RUNNING,
            FINISHED
        }
    }

    //verb
    //AGFINST
    const val DATABASE_URL =
        "http://127.0.0.1/drupal/sites/default/files/2021-04/QuranDatabase.db.zip"
    const val DATABAWSE_FILENAME = "QuranDatabase.db.zip"
    private val PURPLE = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.purple)
    val PURPLES = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.purples)
    private val PeachPuff =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.PeachPuff)
    val BBLUE = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.RoyalBlue)
    val BCYAN = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.cyan)
    val BYELLOW = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.yellow)
    val BWHITE = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.white)
    private val TEAL = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.teal300)
    val GOLD = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.Gold)
    private val DARKGOLDENROD =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.DarkGoldenrod)
    val MARIGOLD = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.marigold)
    val WMIDNIHTBLUE =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.midnightblue)
    val WBURNTUMBER =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.burntamber)
    val GREENDARK = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.DarkGreen)
    val WHOTPINK = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.hotred)

    //  public static final int MARIGOLD = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.marigold);
    val FORESTGREEN =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.sforestgreen)
    private val LIME = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.Lime)
    val KASHMIRIGREEN =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.kashmirigreen)
    val MIDNIGHTBLUE =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.midnightblue)
    val INDIGO = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.indigo)
    val ORANGE400 = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.orange400)
    val DARKMAGENTA =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.DarkMagenta)
    private val TEAL400 = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.teal400)
    private val ORANGE100 =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.orange400)
    private val LIGHTPINK =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.LightPink)
    private val CYANLIGHT =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.cyan_light)
    val MUSLIMMATE =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.colorMuslimMate)
    val CYANLIGHTEST =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.cyan_lightest)
    private val ORANGERED =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.OrangeRed)
    val GREENYELLOW =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.GreenYellow)
    private val HOTPINK = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.HotPink)
    private val SALMON = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.Salmon)
    private val BROWN =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.LightSlateGray)
    private val MEDIUMSLATEBLUE =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.MediumSlateBlue)
    private val DarkTurquoise =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.prussianblue)
    val DeepPink = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.DeepPink)
    private val Fuchsia = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.Fuchsia)
    val pinkshade = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.pinkshade)
    val Magenta = ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.Magenta)
    val prussianblue =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.prussianblue)
    val deepburnsienna =
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.deepburnsienna)
    private var mazeedsignificance: Map<String, String>? = null

    //lgiht
    var nounspanLight = ForegroundColorSpan(BBLUE)
    var verbspanLight = ForegroundColorSpan(GREENDARK)
    var verbalnounspanLight = ForegroundColorSpan(GREENDARK)
    var adjectivespanLight = ForegroundColorSpan(PURPLE)
    var propernounspanLight = ForegroundColorSpan(prussianblue)
    var particlespanLight = ForegroundColorSpan(BBLUE)
    var prepositionspanLight = ForegroundColorSpan(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.rustred
        )
    )
    var resumtionspanLight = ForegroundColorSpan(HOTPINK)
    var pronounspanLight = ForegroundColorSpan(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.steelemetal
        )
    )
    var attachedpronounspanLight = ForegroundColorSpan(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.steelemetal
        )
    )
    var determinerspanLight = ForegroundColorSpan(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.steelemetal
        )
    )
    var timeadverbspanLight = ForegroundColorSpan(BROWN)
    var locationadverspanLight = ForegroundColorSpan(SALMON)
    var demonstrativespanLight = ForegroundColorSpan(BROWN)
    var relativespanLight = ForegroundColorSpan(DARKGOLDENROD)
    var nasabspanLight = ForegroundColorSpan(HOTPINK)
    var negativespanLight = ForegroundColorSpan(Color.RED)
    var eqspanlight = ForegroundColorSpan(DeepPink)
    var restrictivespanLight = ForegroundColorSpan(WHOTPINK)
    var prohibitionspanLight = ForegroundColorSpan(Color.RED)
    var preventivespanLight = ForegroundColorSpan(ORANGERED)
    var inceptivepartile = ForegroundColorSpan(WHOTPINK)
    var ammendedparticle = ForegroundColorSpan(WHOTPINK)
    var supplementspanLight = ForegroundColorSpan(WBURNTUMBER)
    var sifaspansLight = BackgroundColorSpan(WBURNTUMBER)
    var sifaspanLight = BackgroundColorSpan(CYANLIGHTEST)
    var mudhafspanLight = BackgroundColorSpan(CYANLIGHT)
    var mudhafspansLight = BackgroundColorSpan(MIDNIGHTBLUE)
    var harfinnaspanLight = ForegroundColorSpan(Color.GREEN)
    var harfismspanLight = ForegroundColorSpan(BCYAN)
    var harfkhabarspanLight = ForegroundColorSpan(Color.YELLOW)
    var harfshartspanLight = ForegroundColorSpan(PeachPuff)
    var shartspanLight = ForegroundColorSpan(GREENDARK)
    var jawabshartspanLight = ForegroundColorSpan(BCYAN)
    var interrogativespanLight = ForegroundColorSpan(Color.RED)
    var emphspanLight = ForegroundColorSpan(WHOTPINK)
    var lamtaleelspandLight = ForegroundColorSpan(SALMON)
    var masdariaspanLight = ForegroundColorSpan(GREENDARK)
    var harfsababiaspanLight = ForegroundColorSpan(DARKMAGENTA)
    var halspanLight = ForegroundColorSpan(DARKMAGENTA)
    var resultparticlespanLight = ForegroundColorSpan(KASHMIRIGREEN)
    var lamimpvspanLight = ForegroundColorSpan(ORANGE100)
    var answerspanLight = ForegroundColorSpan(BROWN)
    var surprisespanLight = ForegroundColorSpan(DarkTurquoise)
    var certainityspanLight = ForegroundColorSpan(DeepPink)

    //dark
    val timeadverbspanDark = ForegroundColorSpan(CYANLIGHT)
    val locationadverspanDark = ForegroundColorSpan(CYANLIGHTEST)
    val spanhash: HashMap<String, String?> = object : HashMap<String, String?>() {
        init {
            put("RSLT", "resultspan")
            put("P", "prepositionspan")
            put("T", timeadverbspanDark.toString())
            put("LOC", locationadverspanDark.toString())
            put("N", "nounspan")
        }
    }
    var verbalnounspanDark = ForegroundColorSpan(WHOTPINK)
    var nounspanDark = ForegroundColorSpan(Color.YELLOW)

    //    public static ForegroundColorSpan nounspanDark = new ForegroundColorSpan(MARIGOLD);
    var sifaspansDark = BackgroundColorSpan(WBURNTUMBER)
    var sifaspanDark = BackgroundColorSpan(CYANLIGHTEST)

    //   public static BackgroundColorSpan mudhafspanDark = new BackgroundColorSpan(CYANLIGHT);
    var mudhafspansDark = BackgroundColorSpan(MIDNIGHTBLUE)
    var harfinnaspanDark = ForegroundColorSpan(Color.GREEN)
    var harfismspanDark = ForegroundColorSpan(BCYAN)
    var harfkhabarspanDark = ForegroundColorSpan(Color.YELLOW)
    var harfshartspanDark = ForegroundColorSpan(PURPLE)
    var shartspanDark = ForegroundColorSpan(GREENDARK)
    var jawabshartspanDark = ForegroundColorSpan(BCYAN)
    var propernounspanDark = ForegroundColorSpan(GOLD)
    var verbspanDark = ForegroundColorSpan(BCYAN)
    var adjectivespanDark = ForegroundColorSpan(GREENYELLOW)
    var attachedpronounspanDark = ForegroundColorSpan(ORANGE400)
    var relativespanDark = ForegroundColorSpan(DARKGOLDENROD)
    var demonstrativespanDark = ForegroundColorSpan(TEAL)
    var pronounspanDark = ForegroundColorSpan(DARKGOLDENROD)
    var particlespanDark = ForegroundColorSpan(WHOTPINK)
    var interrogativespanDark = ForegroundColorSpan(Color.RED)
    var determinerspanDark = ForegroundColorSpan(ORANGE100)
    var emphspanDark = ForegroundColorSpan(WHOTPINK)
    var lamtaleelspandDark = ForegroundColorSpan(SALMON)
    var masdariaspanDark = ForegroundColorSpan(LIME)
    var harfsababiaspanDark = ForegroundColorSpan(ORANGE400)
    var halspanDark = ForegroundColorSpan(DARKMAGENTA)
    var nasabspanDark = ForegroundColorSpan(TEAL400)
    var resultparticlespanDark = ForegroundColorSpan(LIGHTPINK)
    var negativespanDark = ForegroundColorSpan(Fuchsia)
    var prohibitionspanDark = ForegroundColorSpan(ORANGERED)
    var prepositionspanDark = ForegroundColorSpan(GREENYELLOW)
    var resumtionspanDark = ForegroundColorSpan(HOTPINK)
    var lamimpvspanDark = ForegroundColorSpan(ORANGE100)
    var answerspanDark = ForegroundColorSpan(BROWN)
    var supplementspoanDark = ForegroundColorSpan(MEDIUMSLATEBLUE)
    var surprisespanDark = ForegroundColorSpan(DarkTurquoise)
    var restrictivespanDark = ForegroundColorSpan(Fuchsia)
    var certainityspanDark = ForegroundColorSpan(DeepPink)
    var htmltwo = """<!DOCTYPE html> <html> <head>   <style>      
             text-align: left;}  
             h2{font-style: italic;  
              font-size: 30px;  
              color: #f08080;}  
            p{font-size: 20px;}  
      </style>  
    </head>  """
    var html =
        """<!DOCTYPE html> <html> <head>   <style>                body{background-color:lavender;  
             text-align: center;}  
             h1{font-style: italic;  
              font-size: 40px;  
              color: #f08080;}  
            p{font-size: 20px;              color-blue}  
      </style>  
    </head>  """

    init {
        mazeedsignificance = object : HashMap<String, String>() {
            init {
                put(
                    "II", """                   Form II
                   F-a-33-a-L-a(فَعَّلَ)
                   3-a-LL-a-M-a()
                   ("to teach")
EG.1              Causative:
                   (96:4:2)
                   ʿallama()
                    taught

EG.2              Intensity:
                  (12:23:8)
                  waghallaqati()
                  And she closed

A verb that is already transitive becomes doubly so,
 as it takes a meaning of "make do" or "make become", 
so the meaning could be "to make one learn" i.e. "to teach". 
This form reflects meaning in three ways:  

   1. Intensity of the verb (repetition or the energy in which the action is performed).
   2. He made himself do (to make himself).
   3.Causative (to make another do).
In the intensity example on the right, the form of the verb shows  the intensity and the repetition of the action, 
i.e. she closed all the doors and bolted them."""
                )
                put(
                    "III", """                   Form III
                   F-aa-3-a-L-a(فَاعَلَ)
                   Q-aa-T-a-L-a()
                   ("to fight")
EG.1              Causative:
                   (12:26:7)
                   shāhidun()
                   a witness

EG.2              Intensity:
                  (2:244:1)
                  waqātilū()
                  And fight

This form implies that there is someone or something else present and
 that the action is performed upon him/her/it.
 This forms reflects meaning in two ways:   

   1. Causative ("to be") as an active participle.
   2. Mutual action (he made him do the same).

  In the causative example  ,
 the active participle is derived from form I SH-a-H-i-D-a
 "to witness" or "to be present", which also occurs in the same verse.
 So here it is almost as if to say "he caused himself to witness".

In the second example, the verb "fight" requires someone
 to be fought with, and so the action is mutual."""
                )
                put(
                    "IV", """                Form IV
                    a-F-3-a-L-a(أَفْعَلَ)
                    a-H-L-a-K-a()
                   ("to destory")
Example 1:                      Example 2:      Example 3:(2:205:8)                       (12:25:15)      (5:30:7)  wayuh'lika()                    arāda()         fa-aṣbaḥa()and destroys                    intended        and becameThis pattern is similar to form II in that it makes
intransitive verbs transitive, and transitive verbs doubly so.
This form has the meaning of:

    He made himself do or perform an action.
    A reflexive causative, i.e. he made himself do something transformative to a place or a state.

In the first example on the right, he made himself "destroy the crops".

In the second example, the verb is causative, so that he made himself "want to harm".
In the third example, he was not of the losers before this action of killing,
 but now was transformed into that state. """
                )
                put(
                    "V", """                    Form V
                   t-a-F-33-a-L-a(تَفَعَّلَ)
                    t-a-DH-KK-a-RR-a()
                   ("to receive admonition")
Form 5 is linked to form 2. Whatever action is done through a F-a-33-a-L-a form 2 verb,
the t-a-F-33-a-L-a form 5 verb is from the point of view of the object of the verb.
This usually reflects the reflexive or effective meaning, e.g. "he made himself" or 
"he made something undergo an action".

In the first example on the right, DH-a-KK-a-R-a "to remind" is form II, 
and now in form V it is from the point of view of the object, i.e. "he received the reminder".

In the second example, the verb here is t-a-GH-a-YY-a-R-a "to undergo change",
 so these rivers in paradise do not undergo any change of state or taste 
even if ones tries to do that (in relation to form II: GH-a-YY-a-R-a "to cause to change")"""
                )
                put(
                    "VI", """                   Form VI
                   t-a-F-aa-3-a-L-a(تَفَاعَلَ)
                    t-a-DH-aa-H-a-R-a()
                   ("to support one another")
Form 6 is the reflection of how the object underwent the action of form 3 (F-aa-3-a-L-a).
 Notice that as in form 5, this is obtained by adding ta- before the verb.
 Since form 3 implies an action done on someone, form 6 implies reciprocity
 as in the English sentence "they looked at each other".

The subject cannot be singular in this function of the form. For example, t-a-K-aa-T-a-B-a
 itself would mean "they corresponded with each other" (they wrote to each other).
 Here they support one another in this particular action. This usually reflects the meaning of:

    1.Pure mutuality, e.g. t-a-B-aa-D-a-L-a "he exchanged" takes one object,
      or t-a-3-aa-W-a-N-a "he became assisting". More than one party needs to be involved in this action
    2.Conative - he made himself be the doer.
    3.Pretension – he made himself do something, e.g. "He made himself appear to forget""""
                )
                put(
                    "VII", """                   Form VII
                   i-n-F-a-3-a-L-a(إِنْفَعَلَ)
                    i-n-Q-a-L-a-B-a()
                   ("to turn away")
 EG.1           Reflexive:
                (3:144:18)
                yanqalib()
                turns back

EG.2           Agentless passive:
               (73:18:2)
               munfaṭirun()
               (will) break apart

This form expresses submission to an action or effect. 
In the case of an animate being, this is an involuntary submission.
The form reflects meaning on two levels: 

   1. Reflexive (to let oneself be put through).
   2. Angentless passive (non-reciprocal of form I).
In the second example, the verb is i-n-F-a-T-a-R-a "to be taken apart". 
In the Quranic sense, the agent of the action is God, as the skies do not
 split without a cause. But here it serves the heaven's submission
 to be broken apart.  """
                )
                put(
                    "VIII", """<body>  <h1>Form VIII
i-F-t-a-3-a-L-a(إِفْتَعَلَ)<br>
i-3-t-a-R-a-DH-a<br>("to excuse oneself")<br></h1><h1>Example 1:
Conative:
(9:94:8)
taʿtadhirū
make excuse,
</h1><h1>Example 2:
Causative :
(2:51:7)
ittakhadhtumu
you took
</h1><p> 
This form is generally the reflexive of the simple
			form
			<span class="r1">K</span>-a-<span class="r2">T</span>-a-<span class="r3">B</span>-a
			"he wrote", where the object of form 1 becomes its own object. This form reflects two meanings:
			<ol>
				<li>Either conative or causative (to make oneself do).</li>
				<li>Reciprocal.</li>
			</ol>
			In the conative example on the right, the verb is
			i-<span class="r1">3</span>-t-a-<span class="r2">R</span>-a-<span class="r3">DH</span>-a
			"to excuse oneself". Here in the second person, the meaning becomes "do not excuse yourselves".
			<p>
			In the causative example, they made themselves take a conscious effortful action.</body>"""
                )
                put(
                    "IX", """Form IX
i-F-3-a-LL-a(إِفْعَلَّ)
i-S-W-a-DD-a
("to turn black in color")

Color:

(3:106:4)
wataswaddu
 
This form usually reflects the meaning of stativity,
 and typically refers to bodily defects and colors. For example, i-3-W-a-JJ-a "to be crocked or lame""""
                )
                put(
                    "X", """<b>Form X</b><br>
				i-s-t-a-<span class="r1">F</span>-<span class="r2">3</span>-a-<span class="r3">L</span>-a<br>
				<span class="at">إِسْتَفْعَلَ</span>
			</td>
			<td class="c2">i-s-t-a-<span class="r1">H</span>-<span class="r2">Z</span>-a-<span class="r3">A</span>-a<br>("to make oneself mock at")</td>
Reflexive causative:

(13:32:2)
us'tuh'zi-a
were mocked

Causative:

(4:106:1)
wa-is'taghfiri
And seek forgiveness<br>
The tenth form usually reflects the meaning of
			someone seeking something. Typically the form reflects the meaning
			of:
			<ol>
				<li>Causative -
				i-s-t-<span class="r1">KH</span>-<span class="r2">R</span>-a-<span class="r3">J</span>-a
				"to effortfully make come out"
				(i.e. he extracted) .</li>
				<li>Reflexive causative -
				i-s-t-a-<span class="r1">H</span>-<span class="r2">Z</span>-a-<span class="r3">A</span>-a
				"he made himself
				deride".
				<br>
				Reflexive transformative - "he made be himself be something", e.g.
				i-s-t-a-<span class="r1">3</span>-<span class="r2">R</span>-a-<span class="r3">B</span>-a
				"he made himself an Arab"</li>
				<li>Causative - "to do to the self", e.g. "he made the object do himself"
				(as the subject), or "He sought to be done by the object".
				i-s-t-<span class="r1">GH</span>-<span class="r2">F</span>-a-<span class="r3">R</span>-a
				"he sought to be forgiven by someone else".</li>
			</ol>"""
                )
            }
        }
    }

    //  EMPH = "Emphatic lām prefix(لام التوكيد) ",
    // IMPV = "Imperative lāmprefix(لام الامر)",
    // PRP = "Purpose lāmprefix(لام التعليل)",
    // CONJ = "Coordinating conjunction(حرف عطف)",
    // SUB = "	Subordinating conjunction(حرف مصدري)",
    //  ACC = "	Accusative particle(حرف نصب)",
    //  AMD = "	Amendment particle(حرف استدراك)	",
    //  ANS = "	Answer particle	(حرف جواب)",
    //  AVR = "	Aversion particle	(حرف ردع)",
    //  CAUS = "Particle of cause	(حرف سببية)",
    //  CERT = "Particle of certainty	(حرف تحقيق)",
    // CIRC = "Circumstantial particle	(حرف حال)",
    internal interface Particles {
        companion object {
            const val P = "Prepositions(حرف جر)"
            const val DET = "determiner()"
            const val EMPH = "Emphatic lām prefix(لام التوكيد) "
            const val IMPV = "Imperative lāmprefix(لام الامر)"
            const val PRP = "Purpose lāmprefix(لام التعليل)"
            const val CONJ = "Coordinating conjunction(حرف عطف)"
            const val SUB = "	Subordinating conjunction(حرف مصدري)"
            const val ACC = "	Accusative particle(حرف نصب)"
            const val AMD = "	Amendment particle(حرف استدراك)	"
            const val ANS = "	Answer particle	(حرف جواب)"
            const val AVR = "	Aversion particle	(حرف ردع)"
            const val CAUS = "Particle of cause	(حرف سببية)"
            const val CERT = "Particle of certainty	(حرف تحقيق)"
            const val CIRC = "Circumstantial particle	(حرف حال)"
            const val COM = "	Comitative particle	(واو المعية)"
            const val COND = "Conditional particle(حرف شرط)"
            const val EQ = "	Equalization particle(حرف تسوية)"
            const val EXH = "	Exhortation particle(حرف تحضيض)"
            const val EXL = "	Explanation particle(حرف تفصيل)"
            const val EXP = "	Exceptive particle	(أداة استثناء)"
            const val FUT = "	Future particle	(حرف استقبال)"
            const val INC = "	Inceptive particle	(حرف ابتداء)"
            const val INT = "	Particle of interpretation(حرف تفسير)"
            const val INTG = "Interogative particle	(حرف استفهام)"
            const val NEG = "	Negative particle(حرف نفي)"
            const val PREV = "Preventive particle	(حرف كاف)"
            const val PRO = "	Prohibition particle(حرف نهي)"
            const val REM = "	Resumption particle	(حرف استئنافية)"
            const val RES = "	Restriction particle(أداة حصر)"
            const val RET = "	Retraction particle	(حرف اضراب)"
            const val RSLT = "Result particle(حرف واقع في جواب الشرط)"
            const val SUP = "	Supplemental particle	(حرف زائد)"
            const val SUR = "	Surprise particle	(حرف فجاءة)"
            const val VOC = "	Vocative particle	(حرف نداء)"
            const val INL = "	Quranic initials(	(حروف مقطعة	"
        }
    }
}