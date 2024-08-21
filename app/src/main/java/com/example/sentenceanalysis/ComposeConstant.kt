package com.example.sentenceanalysis


import android.graphics.Color.GREEN
import android.graphics.Color.YELLOW
import android.text.style.BackgroundColorSpan
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication

object ComposeConstant {


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
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.orange100)
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
        ContextCompat.getColor(QuranGrammarApplication.context!!, R.color.Lavender)
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
    var nounspanLight = Color(BBLUE)
    var verbspanLight = Color(GREENDARK)
    var verbalnounspanLight = Color(GREENDARK)
    var adjectivespanLight = Color(PURPLE)
    var propernounspanLight = Color(prussianblue)
    var particlespanLight = Color(BBLUE)
    var prepositionspanLight = Color(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.rustred
        )
    )
    var resumtionspanLight = Color(HOTPINK)
    var pronounspanLight = Color(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.steelemetal
        )
    )
    var attachedpronounspanLight = Color(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.steelemetal
        )
    )
    var determinerspanLight = Color(
        ContextCompat.getColor(
            QuranGrammarApplication.context!!,
            R.color.steelemetal
        )
    )
    var timeadverbspanLight = Color(BROWN)
    var locationadverspanLight = Color(SALMON)
    var demonstrativespanLight = Color(BROWN)
    var relativespanLight = Color(DARKGOLDENROD)
    var nasabspanLight = Color(HOTPINK)
    var negativespanLight = Color(pinkshade)
    var eqspanlight = Color(DeepPink)
    var restrictivespanLight = Color(WHOTPINK)
    var prohibitionspanLight = Color(pinkshade)
    var preventivespanLight = Color(ORANGERED)
    var inceptivepartile = Color(WHOTPINK)
    var ammendedparticle = Color(WHOTPINK)
    var supplementspanLight = Color(WHOTPINK)
    var sifaspansLight = Color(WBURNTUMBER)

    // var sifaspanLight = Color(CYANLIGHTEST)
    var mudhafspanLight = Color(CYANLIGHT)
    var mudhafspansLight = BackgroundColorSpan(MIDNIGHTBLUE)
    var harfinnaspanLight = Color(FORESTGREEN)
    var harfismspanLight = Color(BCYAN)
    var harfkhabarspanLight = Color(MARIGOLD)
    var harfshartspanLight = Color(PeachPuff)
    var shartspanLight = Color(GREENDARK)
    var jawabshartspanLight = Color(BCYAN)
    var interrogativespanLight = Color(pinkshade)
    var emphspanLight = Color(WHOTPINK)
    var lamtaleelspandLight = Color(SALMON)
    var masdariaspanLight = Color(GREENDARK)
    var harfsababiaspanLight = Color(DARKMAGENTA)
    var halspanLight = Color(DARKMAGENTA)
    var resultparticlespanLight = Color(LIGHTPINK)
    var lamimpvspanLight = Color(ORANGE100)
    var answerspanLight = Color(BROWN)
    var surprisespanLight = Color(DarkTurquoise)
    var certainityspanLight = Color(DeepPink)

    //dark
    val timeadverbspanDark = Color(CYANLIGHT)
    val locationadverspanDark = Color(CYANLIGHTEST)
    val spanhash: HashMap<String, String?> = object : HashMap<String, String?>() {
        init {
            put("RSLT", "resultspan")
            put("P", "prepositionspan")
            put("T", timeadverbspanDark.toString())
            put("LOC", locationadverspanDark.toString())
            put("N", "nounspan")
        }
    }
    var verbalnounspanDark = Color(WHOTPINK)

    //   var nounspanDark = Color(MARIGOLD)
    var nounspanDark = Color(android.graphics.Color.YELLOW)

    //    public static Color nounspanDark = new Color(MARIGOLD);
    var sifaspansDark = Color(CYANLIGHTEST)
    var sifaspanDark = BackgroundColorSpan(CYANLIGHTEST)

    //   public static BackgroundColorSpan mudhafspanDark = new BackgroundColorSpan(CYANLIGHT);
    var mudhafspansDark = Color(MIDNIGHTBLUE)
    var harfinnaspanDark = Color(GREEN)
    var harfismspanDark = Color(BCYAN)
    var harfkhabarspanDark = Color(YELLOW)
    var harfshartspanDark = Color(PURPLE)
    var shartspanDark = Color(GREENDARK)
    var jawabshartspanDark = Color(BCYAN)
    var propernounspanDark = Color(GOLD)
    var verbspanDark = Color(BCYAN)
    var adjectivespanDark = Color(GREENYELLOW)
    var attachedpronounspanDark = Color(ORANGE400)
    var relativespanDark = Color(DARKGOLDENROD)
    var demonstrativespanDark = Color(TEAL)
    var pronounspanDark = Color(DARKGOLDENROD)
    var particlespanDark = Color(WHOTPINK)
    var interrogativespanDark = Color(pinkshade)
    var determinerspanDark = Color(ORANGE100)
    var emphspanDark = Color(WHOTPINK)
    var lamtaleelspandDark = Color(SALMON)
    var masdariaspanDark = Color(LIME)
    var harfsababiaspanDark = Color(ORANGE400)
    var halspanDark = Color(DARKMAGENTA)
    var nasabspanDark = Color(TEAL400)
    var resultparticlespanDark = Color(LIGHTPINK)
    var negativespanDark = Color(Fuchsia)
    var prohibitionspanDark = Color(ORANGERED)
    var prepositionspanDark = Color(GREENYELLOW)
    var resumtionspanDark = Color(HOTPINK)
    var lamimpvspanDark = Color(ORANGE100)
    var answerspanDark = Color(BROWN)
    var supplementspoanDark = Color(MEDIUMSLATEBLUE)
    var surprisespanDark = Color(DarkTurquoise)
    var restrictivespanDark = Color(Fuchsia)
    var certainityspanDark = Color(DeepPink)


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