package com.example.mushafconsolidated.fragments


import android.content.Context
import android.text.SpannableStringBuilder
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.model.QuranCorpusWbw
import com.example.utility.CorpusConstants
import com.example.utility.CorpusConstants.verbfeaturesenglisharabic
import com.example.utility.CorpusUtilityorig.Companion.NewSetWordSpan
import com.example.utility.CorpusUtilityorig.Companion.NewSetWordSpanTag
import java.util.Objects


open class NewQuranMorphologyDetails(
    corpusSurahWord: List<QuranCorpusWbw>,
    corpusNounWord: ArrayList<NounCorpus>?,
    verbCorpuses: ArrayList<VerbCorpus>?,
    context: Context?
) {
    open var form = 0
    open var Thulathi: String? = null

    // --Commented out by Inspection (16/08/23, 1:44 pm):private CorpusWbwWord word;
    private var corpusNoun: ArrayList<NounCorpus>? = corpusNounWord

    //  private var corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>? = null
    private var corpusSurahWord: ArrayList<QuranCorpusWbw>? = null
    private var verbcorpusform: ArrayList<VerbCorpus>? = verbCorpuses

    init {
        this.corpusSurahWord = corpusSurahWord as ArrayList<QuranCorpusWbw>
    }


    //     pngsb.append("," + "(form").append(verbcorpusform.get(0).getForm()).append(")");
    open val verbDetails: HashMap<String, String?>
        get() {
            val vbdetail = HashMap<String, String?>()
            val roots: String = verbcorpusform!![0].root_a!!
            vbdetail["root"] = roots
            if (verbcorpusform!!.size > 0) {
                if (!verbcorpusform!![0].form.equals("I")) {
                    val mform: String? = verbcorpusform!![0].form
                    convertForms(mform)
                    vbdetail["formnumber"] = mform
                    vbdetail["form"] = form.toString()
                } else {
                    val thulathibab: String? = verbcorpusform!![0].thulathibab
                    if (thulathibab != null) {
                        when (thulathibab.length) {
                            0 -> vbdetail["thulathi"] =
                                null

                            1 -> {
                                val s: String? = verbcorpusform!![0].thulathibab
                                val sb = getThulathiName(s)
                                vbdetail["thulathi"] = sb.toString()
                                vbdetail["wazan"] = thulathibab
                            }

                            else -> {
                                thulathibab.length
                                val s = thulathibab.substring(0, 1)
                                val sb = getThulathiName(s)
                                vbdetail["thulathi"] = sb.toString()
                                vbdetail["wazan"] = s
                            }
                        }
                    }
                }
            }
            val gendernumber: String? = verbcorpusform!![0].gendernumber
            val pngsb = getGenderNumberdetails(gendernumber!!)
            if (vbdetail["form"] != null) {
                val formdetails = getFormName(
                    verbcorpusform!![0].form
                )
                pngsb.append(",").append(formdetails)
                //     pngsb.append("," + "(form").append(verbcorpusform.get(0).getForm()).append(")");
                vbdetail["png"] = pngsb.toString()
            } else {
                vbdetail["png"] = null
                //    pngsb.append(",").append(verbcorpusform.get(0).getThulathibab());
            }
            vbdetail["png"] = pngsb.toString()
            when (verbcorpusform!![0].tense) {
                "IMPF" -> vbdetail["tense"] = verbfeaturesenglisharabic.IMPF
                "IMPV" -> vbdetail["tense"] = verbfeaturesenglisharabic.IMPV
                "PERF" -> vbdetail["tense"] = verbfeaturesenglisharabic.PERF
            }
            when (verbcorpusform!![0].voice) {
                "ACT" -> vbdetail["voice"] = verbfeaturesenglisharabic.ACT
                "PASS" -> vbdetail["voice"] = verbfeaturesenglisharabic.PASS
            }
            if ((roots == "كون")) {
                when (verbcorpusform!![0].kana_mood) {
                    "MOOD:SUBJ" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.IND
                        vbdetail["verbmood"] = "subjunctive"
                    }

                    "JUS" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.JUS
                        vbdetail["verbmood"] = "Jussive"
                    }

                    "SUBJ" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.SUBJ
                        vbdetail["verbmood"] = "Subjunctive"
                    }

                    else -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.IND
                        vbdetail["verbmood"] = "Indicative"
                    }
                }
            } else {
                when (verbcorpusform!![0].mood_kananumbers) {
                    "IND" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.IND
                        vbdetail["verbmood"] = "Indicative"




                        if (corpusSurahWord!![0].corpus.detailsone!!.contains("SUFFIX|+n:EMPH") || corpusSurahWord!![0].corpus.detailstwo!!.contains(
                                "SUFFIX|+n:EMPH"
                            )
                            || corpusSurahWord!![0].corpus.detailsthree!!.contains("SUFFIX|+n:EMPH") || corpusSurahWord!![0].corpus.detailsfour!!.contains(
                                "SUFFIX|+n:EMPH"
                            )
                            || corpusSurahWord!![0].corpus.detailsfour!!.contains("SUFFIX|+n:EMPH")
                        ) {
                            vbdetail["emph"] = "EMPH"
                        }


                    }

                    "JUS" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.JUS
                        vbdetail["verbmood"] = "Jussive"

                        if (corpusSurahWord!![0].corpus.detailsone!!.contains("SUFFIX|+n:EMPH") || corpusSurahWord!![0].corpus.detailstwo!!.contains(
                                "SUFFIX|+n:EMPH"
                            )
                            || corpusSurahWord!![0].corpus.detailsthree!!.contains("SUFFIX|+n:EMPH") || corpusSurahWord!![0].corpus.detailsfour!!.contains(
                                "SUFFIX|+n:EMPH"
                            )
                            || corpusSurahWord!![0].corpus.detailsfour!!.contains("SUFFIX|+n:EMPH")
                        ) {
                            vbdetail["emph"] = "EMPH"
                        }
                    }

                    "SUBJ" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.SUBJ
                        vbdetail["verbmood"] = "Subjunctive"

                        if (corpusSurahWord!![0].corpus.detailsone!!.contains("SUFFIX|+n:EMPH") || corpusSurahWord!![0].corpus.detailstwo!!.contains(
                                "SUFFIX|+n:EMPH"
                            )
                            || corpusSurahWord!![0].corpus.detailsthree!!.contains("SUFFIX|+n:EMPH") || corpusSurahWord!![0].corpus.detailsfour!!.contains(
                                "SUFFIX|+n:EMPH"
                            )
                            || corpusSurahWord!![0].corpus.detailsfour!!.contains("SUFFIX|+n:EMPH")
                        ) {
                            vbdetail["emph"] = "EMPH"
                        }
                    }

                }
            }
            vbdetail["lemma"] = verbcorpusform!![0].lemma_a
            return vbdetail
        }

    private fun convertForms(mform: String?) {
        when (mform) {
            "IV" -> form = 1
            "II" -> form = 2
            "III" -> form = 3
            "VII" -> form = 4
            "VIII" -> form = 5
            "VI" -> form = 7
            "V" -> form = 8
            "X" -> form = 9
            else -> {
                var s = ""
                try {
                    s = verbcorpusform!![0].thulathibab!!
                } catch (e: IndexOutOfBoundsException) {
                    println("Exception occurred . . . . . . . . ")
                }
                Thulathi = s.ifEmpty {
                    null
                }
            }
        }
    }

    //get the root,since vercopus is not checked
//    wordbdetail.put("form", SpannableStringBuilder.valueOf("I"));
    //chedk if particple
    open val wordDetails: HashMap<String, SpannableStringBuilder?>
        get() {
            val wordbdetail = HashMap<String, SpannableStringBuilder?>()
            wordbdetail["surahid"] = SpannableStringBuilder.valueOf(
                java.lang.String.valueOf(
                    corpusSurahWord!![0].corpus.surah
                )
            )
            wordbdetail["ayahid"] = SpannableStringBuilder.valueOf(
                java.lang.String.valueOf(
                    corpusSurahWord!![0].corpus.ayah
                )
            )
            wordbdetail["wordno"] = SpannableStringBuilder.valueOf(
                java.lang.String.valueOf(
                    corpusSurahWord!![0].corpus.wordno
                )
            )
            //    wordbdetail["wordtranslation"] =
            //       SpannableStringBuilder.valueOf(corpusSurahWord!!.get(0).wbw.en)
            val arabicword: String =
                corpusSurahWord!![0].corpus.araone + (corpusSurahWord!![0].corpus.aratwo + (
                        corpusSurahWord!![0].corpus.arathree
                                + (
                                corpusSurahWord!![0].corpus.arafour
                                        + (corpusSurahWord!![0].corpus.arafour) + corpusSurahWord!![0].corpus.arafive
                                )
                        )
                        )
            wordbdetail["arabicword"] = SpannableStringBuilder.valueOf(arabicword)
            if (corpusNoun!!.size > 0) {
                if (corpusNoun!![0].proptwo.equals(CorpusConstants.NominalsProp.PCPL)) {
                    val form: String? = corpusNoun!![0].form
                    val mform = form?.replace("[()]".toRegex(), "")
                    if (mform != "I") {
                        convertForms(mform)
                        wordbdetail["form"] = SpannableStringBuilder.valueOf(this.form.toString())
                        getRoot(corpusSurahWord!!, wordbdetail)
                        //chedk if particple
                    } else {
                        wordbdetail["form"] =
                            SpannableStringBuilder.valueOf(corpusNoun!![0].form)
                    }
                    if (corpusNoun!![0].proptwo.equals("PCPL")) {
                        wordbdetail["PCPL"] =
                            SpannableStringBuilder.valueOf(
                                corpusNoun!![0].propone + (
                                        corpusNoun!![0].proptwo
                                        )
                            )
                        wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                        //    wordbdetail.put("form", SpannableStringBuilder.valueOf("I"));
                    } else {
                        wordbdetail["PCPL"] = SpannableStringBuilder.valueOf("NONE")
                        wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                        //   wordbdetail.put("form", SpannableStringBuilder.valueOf("I"));
                    }
                }
            }
            GetPronounDetails(corpusSurahWord, wordbdetail)
            GetLemmArabicwordWordDetails(corpusSurahWord!!, wordbdetail)
            val sb = StringBuilder()
            //get the root,since vercopus is not checked
            getRoot(corpusSurahWord!!, wordbdetail)
            getNdetails(corpusNoun, wordbdetail, sb)
            getProperNounDetails(corpusNoun, wordbdetail, sb)
            val isNoun = wordbdetail["worddetails"].toString().contains("Noun")
            if (wordbdetail["worddetails"] != null) { //todo need refactor based on wordcount
                if (!isNoun) {
                    val relative =
                        wordbdetail["worddetails"].toString().contains("Relative Pronoun")
                    val prep = Objects.requireNonNull(
                        wordbdetail["worddetails"]
                    ).toString().contains("Prepositions")
                    val cond =
                        wordbdetail["worddetails"].toString().contains("Conditional particle")
                    val pron = wordbdetail["worddetails"].toString().contains("Pronouns")
                    val dem =
                        wordbdetail["worddetails"].toString().contains("Demonstrative Pronoun")
                    val time = wordbdetail["worddetails"].toString().contains("Time Adverb")
                    val harfnasb =
                        wordbdetail["worddetails"].toString().contains("Accusative(حرف نصب)")
                    if (relative) {
                        wordbdetail["relative"] = SpannableStringBuilder.valueOf("relative")
                    } else if (cond || time) {
                        wordbdetail["cond"] = SpannableStringBuilder.valueOf("cond")
                    } else if (harfnasb) {
                        wordbdetail["harfnasb"] = SpannableStringBuilder.valueOf("harfnasb")
                    } else if (prep) {
                        wordbdetail["prep"] = SpannableStringBuilder.valueOf("prep")
                    } else if (dem) {
                        wordbdetail["dem"] = SpannableStringBuilder.valueOf("dem")
                    }
                }
            }
            return wordbdetail
        }

    private fun getProperNounDetails(
        corpusNoun: ArrayList<NounCorpus>?,
        wordbdetail: HashMap<String, SpannableStringBuilder?>,
        sbs: StringBuilder
    ) {
        val sb = StringBuilder()
        if (corpusNoun!!.size > 0) {
            if (corpusNoun[0].tag == "PN" || corpusNoun[0].tag == "ADJ") {
                val propone: String? = corpusNoun[0].propone
                val proptwo: String? = corpusNoun[0].proptwo
                var pcpl = ""
                if (propone != "null" && proptwo != "null") {
                    pcpl = pcpl + propone + proptwo
                }
                if (corpusNoun[0].propone.equals("VN")) {
                    sb.append("Proper/Verbal Noun")
                } else if (corpusNoun[0].tag == "ADJ") {
                    sb.append("Adjective:")
                } else {
                    sb.append("Proper Noun:")
                }
                val form: String? = corpusNoun[0].form
                val gendernumber: String? = corpusNoun[0].gendernumber
                val type: String? = corpusNoun[0].type
                val cases: String? = corpusNoun[0].cases
                // sb.append("Proper Noun:");
                if (cases != "null") {
                    when (cases) {
                        "NOM" -> sb.append(CorpusConstants.NominalsProp.NOM)
                        "ACC" -> sb.append(CorpusConstants.NominalsProp.ACC)
                        "GEN" -> {
                            sb.append(CorpusConstants.NominalsProp.GEN)
                            sb.append(CorpusConstants.NominalsProp.GEN)
                        }
                    }
                }
                if (gendernumber != "null") {
                    if (gendernumber != null) {
                        if (gendernumber.length == 1) {
                            when (gendernumber) {
                                "M" -> sb.append(CorpusConstants.png.M + " ")
                                "F" -> sb.append(CorpusConstants.png.F + " ")
                                "P" -> sb.append(CorpusConstants.png.P + " ")
                            }
                        } else if (gendernumber.length == 2) {
                            val gender = gendernumber.substring(0, 1)
                            val number = gendernumber.substring(1, 2)
                            when (gender) {
                                "M" -> sb.append(CorpusConstants.png.M + " ")
                                "F" -> sb.append(CorpusConstants.png.F + " ")
                            }
                            when (number) {
                                "P" -> sb.append(CorpusConstants.png.P + " ")
                                "S" -> sb.append(CorpusConstants.png.S + " ")
                                "D" -> sb.append(CorpusConstants.png.D + " ")
                            }
                        }
                    }
                }
                if (form != "null") {
                    sb.append("(form ").append(form).append(")")
                    wordbdetail["formnumber"] = SpannableStringBuilder.valueOf(form)
                }
                if (propone != "null" && proptwo != "null") {
                    if ((pcpl == "ACTPCPL")) {
                        sb.append(CorpusConstants.NominalsProp.ACTPCPL)
                        wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                    } else if ((pcpl == "PASSPCPL")) {
                        sb.append(CorpusConstants.NominalsProp.PASSPCPL)
                        wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                    }
                }
                if (type != "null") {
                    sb.append(CorpusConstants.NominalsProp.INDEF)
                    sb.append(",")
                }
                if (sb.length > 5) {
                    wordbdetail["noun"] = SpannableStringBuilder.valueOf(sb.toString())
                }
            }
        }
    }

    private fun getNdetails(
        corpusNoun: ArrayList<NounCorpus>?,
        wordbdetail: HashMap<String, SpannableStringBuilder?>,
        sb: StringBuilder
    ) {
        if (corpusNoun!!.size > 0) {
            if ((corpusNoun[0].tag == "N" || corpusNoun[0].tag == "PN" ||
                        corpusNoun[0].tag == "VN" ||
                        corpusNoun[0].tag == "ADJ")
            ) {
                //   if (corpusNoun.get(0).tag.equals("N")) {
                val propone: String? = corpusNoun[0].propone
                val proptwo: String? = corpusNoun[0].proptwo
                var pcpl = ""
                if (propone != "null" && proptwo != "null") {
                    pcpl = pcpl + propone + proptwo
                }
                val form: String? = corpusNoun[0].form
                val gendernumber: String? = corpusNoun[0].gendernumber
                val type: String? = corpusNoun[0].type
                val cases: String? = corpusNoun[0].cases
                when (corpusNoun[0].tag) {
                    "N" -> sb.append("Noun:" + " ")
                    "ADJ" -> sb.append("Adjective:" + " ")
                    "PN" -> sb.append("Porper Noun:" + " ")
                    "VN" -> sb.append("Verbal Noun:" + " ")
                }
                //   sb.append(corpusNoun.get(0).tag.concat(" "));
                if (cases != "null") {
                    when (cases) {
                        "NOM" -> {
                            wordbdetail["nouncase"] = SpannableStringBuilder.valueOf("NOM")
                            sb.append(CorpusConstants.NominalsProp.NOM + " ")
                        }

                        "ACC" -> {
                            wordbdetail["nouncase"] = SpannableStringBuilder.valueOf("ACC")
                            sb.append(CorpusConstants.NominalsProp.ACC + " ")
                        }

                        "GEN" -> {
                            wordbdetail["nouncase"] = SpannableStringBuilder.valueOf("GEN")
                            sb.append(CorpusConstants.NominalsProp.GEN + " ")
                        }
                    }
                }
                if (gendernumber != "null") {
                    if (gendernumber != null) {
                        if (gendernumber.length == 1) {
                            when (gendernumber) {
                                "M" -> sb.append(CorpusConstants.png.M)
                                "F" -> sb.append(CorpusConstants.png.F)
                                "P" -> sb.append(CorpusConstants.png.P)
                            }
                        } else if (gendernumber.length == 2) {
                            val gender = gendernumber.substring(0, 1)
                            val number = gendernumber.substring(1, 2)
                            when (gender) {
                                "M" -> sb.append(CorpusConstants.png.M + " ")
                                "F" -> sb.append(CorpusConstants.png.F + " ")
                            }
                            when (number) {
                                "P" -> sb.append(CorpusConstants.png.P + " ")
                                "S" -> sb.append(CorpusConstants.png.S + " ")
                                "D" -> sb.append(CorpusConstants.png.D + " ")
                            }
                        }
                    }
                }
                if (form != "null") {
                    sb.append("(form").append(" ").append(form).append(")")
                }
                if (propone != "null" && proptwo != "null") {
                    if ((pcpl == "ACTPCPL")) {
                        sb.append(CorpusConstants.NominalsProp.ACTPCPL)
                    } else if ((pcpl == "PASSPCPL")) {
                        sb.append(CorpusConstants.NominalsProp.PASSPCPL)
                    }
                }
                if (type != "null") {
                    sb.append(CorpusConstants.NominalsProp.INDEF)
                    sb.append(",")
                }
                if (sb.length > 5) {
                    wordbdetail["noun"] = SpannableStringBuilder.valueOf(sb.toString())
                }
            } else if ((corpusNoun[0].tag == "PN" ||
                        corpusNoun[0].tag == "VN" ||
                        corpusNoun[0].tag == "ADJ")
            ) {
                val cases: String? = corpusNoun[0].cases
                //  sb.append("Noun:");
                if (cases != "null") {
                    when (cases) {
                        "NOM" -> wordbdetail["nouncase"] = SpannableStringBuilder.valueOf("NOM")
                        "ACC" -> wordbdetail["nouncase"] = SpannableStringBuilder.valueOf("ACC")
                        "GEN" -> wordbdetail["nouncase"] = SpannableStringBuilder.valueOf("GEN")
                    }
                }
            }
        }
    }

    private fun getRoot(
        corpusSurahWord: ArrayList<QuranCorpusWbw>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>
    ) {
        if (corpusSurahWord.size > 0) {
            if (corpusSurahWord[0].corpus.wordcount === 1) {
                if (corpusSurahWord[0].corpus.rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaraone!!)
                }
            } else if (corpusSurahWord[0].corpus.wordcount === 2) {
                if (corpusSurahWord[0].corpus.rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaraone!!)
                } else if (corpusSurahWord[0].corpus.rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaratwo)
                }
            } else if (corpusSurahWord[0].corpus.wordcount === 3) {
                if (corpusSurahWord[0].corpus.rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaraone!!)
                } else if (corpusSurahWord[0].corpus.rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaratwo)
                } else if (corpusSurahWord[0].corpus.rootarathree!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootarathree)
                }
            } else if (corpusSurahWord[0].corpus.wordcount === 4) {
                if (corpusSurahWord[0].corpus.rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaraone!!)
                } else if (corpusSurahWord[0].corpus.rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaratwo)
                } else if (corpusSurahWord[0].corpus.rootarathree!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootarathree)
                } else if (corpusSurahWord[0].corpus.rootarafour!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootarafour)
                }
            } else if (corpusSurahWord[0].corpus.wordcount === 5) {
                if (corpusSurahWord[0].corpus.rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaraone!!)
                } else if (corpusSurahWord[0].corpus.rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootaratwo)
                } else if (corpusSurahWord[0].corpus.rootarathree!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootarathree)
                } else if (corpusSurahWord[0].corpus.rootarafour!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootarafour)
                } else if (corpusSurahWord[0].corpus.rootarafive!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.rootarafive)
                }
            }
        }
    }

    private fun GetLemmArabicwordWordDetails(
        corpusSurahWord: ArrayList<QuranCorpusWbw>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>
    ) {
        val tagone: String = corpusSurahWord[0].corpus.tagone!!
        val tagtwo: String = corpusSurahWord[0].corpus.tagtwo!!
        val tagthree: String = corpusSurahWord[0].corpus.tagthree!!
        val tagfour: String = corpusSurahWord[0].corpus.tagfour!!
        val tagfive: String = corpusSurahWord[0].corpus.tagfive!!
        val araone: String = corpusSurahWord[0].corpus.araone!!
        val aratwo: String = corpusSurahWord[0].corpus.aratwo!!
        val arathree: String = corpusSurahWord[0].corpus.arathree!!
        val arafour: String = corpusSurahWord[0].corpus.arafour!!
        val arafive: String = corpusSurahWord[0].corpus.arafive!!
        if (corpusSurahWord[0].corpus.wordcount === 1) {
            //noun yelllo
            //verb cyan
            //
            val expandTagsone = expandTags(tagone)
            wordbdetail["lemma"] =
                SpannableStringBuilder.valueOf(corpusSurahWord[0].corpus.lemaraone)
            val spannableString = NewSetWordSpan(tagone, "", "", "", "", araone, "", "", "", "")
            val tagspannables =
                NewSetWordSpanTag(tagone, "", "", "", "", araone, "", "", "", expandTagsone)
            wordbdetail["word"] = SpannableStringBuilder.valueOf(spannableString)
            wordbdetail["worddetails"] = SpannableStringBuilder.valueOf(tagspannables)
            if (corpusSurahWord[0].corpus.detailsone!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            }
        } else if (corpusSurahWord[0].corpus.wordcount === 2) {
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                corpusSurahWord[0].corpus.lemaraone + corpusSurahWord[0].corpus.lemaratwo
            )
            val arabicspannable = SpannableStringBuilder(
                araone + aratwo
            )
            val expandTagsone = expandTags(tagone)
            val expandTagstwo = expandTags(tagtwo)
            if (corpusSurahWord[0].corpus.detailsone!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailstwo!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            }
            val one: Int = corpusSurahWord[0].corpus.araone!!.length //2
            val two: Int = corpusSurahWord[0].corpus.aratwo!!.length //3
            val spannableString =
                NewSetWordSpan(tagone, tagtwo, "", "", "", araone, aratwo, "", "", "")
            val tagspannables = NewSetWordSpanTag(
                tagone,
                tagtwo,
                "",
                "",
                "",
                araone,
                aratwo,
                "",
                expandTagstwo,
                expandTagsone
            )
            wordbdetail["word"] = SpannableStringBuilder.valueOf(spannableString)
            wordbdetail["worddetails"] = SpannableStringBuilder.valueOf(tagspannables)
        } else if (corpusSurahWord[0].corpus.wordcount === 3) {
            val sb = StringBuilder()
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[0].corpus.lemaraone + corpusSurahWord[0].corpus.lemaratwo +
                        corpusSurahWord[0].corpus.lemarathree)
            )
            val one: Int = corpusSurahWord[0].corpus.araone!!.length //2
            val two: Int = corpusSurahWord[0].corpus.aratwo!!.length //3
            var expandTagsthree = ""
            val expandTagsone = expandTags(
                corpusSurahWord[0].corpus.tagone!!
            )
            val expandTagstwo = expandTags(
                corpusSurahWord[0].corpus.tagtwo!!
            )

            expandTagsthree =
                if (corpusSurahWord[0].corpus.detailsthree!!.contains("SUFFIX|+n:EMPH")) {
                    "EMPH – emphatic suffix nūn"
                } else {
                    expandTags(
                        corpusSurahWord[0].corpus.tagthree!!
                    )
                }


            sb.append(corpusSurahWord[0].corpus.tagthree)
            sb.append("|")
            sb.append(corpusSurahWord[0].corpus.tagtwo)
            sb.append("|")
            sb.append(corpusSurahWord[0].corpus.tagone)
            // 0,tagthree
            // tagthree,tagtwo
            // tagtwo,tagone
            val spannableString =
                NewSetWordSpan(tagone, tagtwo, tagthree, "", "", araone, aratwo, arathree, "", "")
            val tagspannables = NewSetWordSpanTag(
                tagone,
                tagtwo,
                tagthree,
                "",
                "",
                " ",
                " ",
                expandTagsthree,
                expandTagstwo,
                expandTagsone
            )
            wordbdetail["word"] = SpannableStringBuilder.valueOf(spannableString)
            wordbdetail["worddetails"] = SpannableStringBuilder.valueOf(tagspannables)
            if (corpusSurahWord[0].corpus.detailsone!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailstwo!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailsthree!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            }
        } else if (corpusSurahWord[0].corpus.wordcount === 4) {
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[0].corpus.lemaraone + corpusSurahWord[0].corpus.lemaratwo +
                        corpusSurahWord[0].corpus.lemarathree)
            )
            val one: Int = corpusSurahWord[0].corpus.araone!!.length //2
            val two: Int = corpusSurahWord[0].corpus.aratwo!!.length //3
            val onetag: Int = corpusSurahWord[0].corpus.tagone!!.length //1
            val twotag: Int = corpusSurahWord[0].corpus.tagtwo!!.length //3
            val expandTagsone = expandTags(
                corpusSurahWord[0].corpus.tagone!!
            )
            val expandTagstwo = expandTags(
                corpusSurahWord[0].corpus.tagtwo!!
            )
            val expandTagsthree = expandTags(
                corpusSurahWord[0].corpus.tagthree!!
            )
            val expandTagsfour = expandTags(
                corpusSurahWord[0].corpus.tagfour!!
            )
            val spannableString = NewSetWordSpan(
                tagone,
                tagtwo,
                tagthree,
                tagfour,
                "",
                araone,
                aratwo,
                arathree,
                arafour,
                ""
            )
            // SpannableString tagspannables = CorpusUtilityorig.NewSetWordSpanTag(tagone, tagtwo, tagthree, tagfour, tagfive," ", expandTagsfour, expandTagsthree, expandTagstwo, expandTagsone);
            val tagspannables = NewSetWordSpanTag(
                tagone, tagtwo, tagthree,
                tagfour, tagfive, " ", expandTagsfour, expandTagsthree, expandTagstwo, expandTagsone
            )
            wordbdetail["word"] = SpannableStringBuilder.valueOf(spannableString)
            wordbdetail["worddetails"] = SpannableStringBuilder.valueOf(tagspannables)
            if (corpusSurahWord[0].corpus.detailsone!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailstwo!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailsthree!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailsfour!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            }
        } else if (corpusSurahWord[0].corpus.wordcount === 5) {
            val expandTagsone = expandTags(
                corpusSurahWord[0].corpus.tagone!!
            )
            val expandTagstwo = expandTags(
                corpusSurahWord[0].corpus.tagtwo!!
            )
            val expandTagsthree = expandTags(
                corpusSurahWord[0].corpus.tagthree!!
            )
            val expandTagsfour = expandTags(
                corpusSurahWord[0].corpus.tagfour!!
            )
            val expandTagsfive = expandTags(
                corpusSurahWord[0].corpus.tagfive!!
            )
            val sb = StringBuilder()
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[0].corpus.lemaraone + corpusSurahWord[0].corpus.lemaratwo +
                        corpusSurahWord[0].corpus.lemarathree + corpusSurahWord[0].corpus
                    .lemarafour + corpusSurahWord[0].corpus.lemarafive)
            )
            val tagspannables = NewSetWordSpanTag(
                tagone,
                tagtwo,
                tagthree,
                tagfour,
                tagfive,
                expandTagsfive,
                expandTagsfour,
                expandTagsthree,
                expandTagstwo,
                expandTagsone
            )
            sb.append(corpusSurahWord[0].corpus.tagfive)
            sb.append("|")
            sb.append(corpusSurahWord[0].corpus.tagfour)
            sb.append("|")
            sb.append(corpusSurahWord[0].corpus.tagthree)
            sb.append("|")
            sb.append(corpusSurahWord[0].corpus.tagtwo)
            sb.append("|")
            sb.append(corpusSurahWord[0].corpus.tagone)
            wordbdetail["word"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[0].corpus.araone + corpusSurahWord[0].corpus.aratwo +
                        corpusSurahWord[0].corpus.arathree + corpusSurahWord[0].corpus
                    .arafour + corpusSurahWord[0].corpus.tagfive)
            )
            //   wordbdetail.put("worddetails", SpannableStringBuilder.valueOf(sb));
            wordbdetail["worddetails"] = SpannableStringBuilder.valueOf(tagspannables)
            if (corpusSurahWord[0].corpus.detailsone!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailstwo!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailsthree!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailsfour!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            } else if (corpusSurahWord[0].corpus.detailsfive!!.contains("SP:kaAn")) {
                wordbdetail["spkana"] = SpannableStringBuilder.valueOf("spkana")
            }
        }
    }

    private fun GetPronounDetails(
        corpusSurahWord: ArrayList<QuranCorpusWbw>?,
        wordbdetail: HashMap<String, SpannableStringBuilder?>
    ) {
        if (corpusSurahWord!![0].corpus.wordcount === 1) {
            if (corpusSurahWord!![0].corpus.tagone.equals("PRON")) {
                //   String[] parts = corpusSurahWord.get(0).getDetailsone().toString().split("\"|");
                val gendernumber: String =
                    corpusSurahWord[0].corpus.detailsone!!.replace("^.*?(\\w+)\\W*$", "$1")
                println(gendernumber)
                // String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 2) {
            if (corpusSurahWord!![0].corpus.tagtwo.equals("PRON")) {
                val gendernumber: String =
                    corpusSurahWord[0].corpus.detailstwo!!.replace("^.*?(\\w+)\\W*$", "$1")
                //   String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 3) {
            if (corpusSurahWord!![0].corpus.tagthree.equals("PRON")) {
                //   String[] parts = corpusSurahWord.get(0).corpus.getDetailsthree().toString().split("|");
                val gendernumber: String =
                    corpusSurahWord[0].corpus.detailsthree!!.replace("^.*?(\\w+)\\W*$", "$1")
                //    String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 4) {
            if (corpusSurahWord!![0].corpus.tagfour.equals("PRON")) {
                val parts: List<String> = corpusSurahWord[0].corpus.detailsfour!!.split("\\|")
                val gendernumber = parts[parts.size - 1]
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 5) {
            if (corpusSurahWord!![0].corpus.tagfive.equals("PRON")) {
                val parts: List<String> = corpusSurahWord[0].corpus.detailsfive!!.split("\\|")
                val gendernumber = parts[parts.size - 1]
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        }
    }

    private fun GetHarfDetail(
        corpusSurahWord: ArrayList<QuranCorpusWbw>?,
        wordbdetail: HashMap<String, SpannableStringBuilder>
    ) {
        if (corpusSurahWord!![0].corpus.wordcount === 1) {
            if (corpusSurahWord!![0].corpus.tagone.equals("PRON")) {
                //   String[] parts = corpusSurahWord.get(0).corpus.getDetailsone().toString().split("\"|");
                val gendernumber: String =
                    corpusSurahWord[0].corpus.detailsone!!.replace("^.*?(\\w+)\\W*$", "$1")
                println(gendernumber)
                // String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 2) {
            if (corpusSurahWord!![0].corpus.tagtwo.equals("PRON")) {
                val gendernumber: String =
                    corpusSurahWord[0].corpus.detailstwo!!.replace("^.*?(\\w+)\\W*$", "$1")
                //   String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 3) {
            if (corpusSurahWord!![0].corpus.tagthree.equals("PRON")) {
                //   String[] parts = corpusSurahWord!!.get(0).corpus.getDetailsthree().toString().split("|");
                val gendernumber: String =
                    corpusSurahWord[0].corpus.detailsthree!!.replace("^.*?(\\w+)\\W*$", "$1")
                //    String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 4) {
            if (corpusSurahWord!![0].corpus.tagfour.equals("PRON")) {
                val parts: List<String> = corpusSurahWord[0].corpus.detailsfour!!.split("\\|")
                val gendernumber = parts[parts.size - 1]
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord!![0].corpus.wordcount === 5) {
            if (corpusSurahWord!![0].corpus.tagfive.equals("PRON")) {
                val parts: List<String> = corpusSurahWord[0].corpus.detailsfive!!.split("\\|")
                val gendernumber = parts[parts.size - 1]
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        }
    }

    companion object {
        fun getFormName(form: String?): String {
            var formdetails = ""
            formdetails = when (form) {
                "IV" -> "Form-IV(إِفْعَال)"
                "II" -> "Form-II(تَفْعِيل)"
                "III" -> "Form-III(فَاعَلَ)"
                "VII" -> "Form-VII(اِنْفِعَال)"
                "VIII" -> "Form-VIII(اِفْتِعَال)"
                "VI" -> "Form-VI(تَفَاعُل)"
                "V" -> "Form-V(تَفَعُّل)"
                "X" -> "Form-X(اِسْتِفْعَال)"
                else ->
                    ""
            }
            return formdetails
        }

        fun getThulathiName(s: String?): StringBuilder {
            val sb = StringBuilder()
            sb.append("(")
            sb.append(("Form I-"))
            when (s) {
                "N" -> sb.append(CorpusConstants.thulathi.NASARA)
                "Z" -> sb.append(CorpusConstants.thulathi.ZARABA)
                "F" -> sb.append(CorpusConstants.thulathi.FATAH)
                "S" -> sb.append(CorpusConstants.thulathi.SAMIA)
                "K" -> sb.append(CorpusConstants.thulathi.KARUMU)
                "H" -> sb.append(CorpusConstants.thulathi.HASIBA)
            }
            sb.append(")")
            return sb
        }

        fun getGenderNumberdetails(gendernumber: String?): StringBuilder {
            val person: String
            val gender: String
            val number: String
            val sb = StringBuilder()
            if (gendernumber != null) {
                if (gendernumber.length == 3) {
                    person = gendernumber.substring(0, 1)
                    gender = gendernumber.substring(1, 2)
                    number = gendernumber.substring(2, 3)
                    when (person) {
                        "1" -> sb.append("1st Person ")
                        "2" -> sb.append("2nd Person ")
                        "3" -> sb.append("3rd Person ")
                    }
                    sb.append(" ")
                    if ((gender == "M")) {
                        sb.append("Mas.")
                    } else if ((gender == "F")) {
                        sb.append("Fem.")
                    }
                    sb.append(" ")
                    when (number) {
                        "S" -> sb.append("Sing.")
                        "P" -> sb.append("Plural")
                        "D" -> sb.append("Dual")
                    }
                } else if (gendernumber.length == 2) {
                    person = gendernumber.substring(0, 1)
                    number = gendernumber.substring(1, 2)
                    val chars = gendernumber.toCharArray()
                    //   if ((chars[0] >= 'a' && chars[0] <= 'z') || (chars[0] >= 'A' && chars[0] <= 'Z')
                    if ((chars[0] in 'a'..'z') || (chars[0] in 'A'..'Z')
                    ) {
                        when (person) {
                            "M" -> {
                                sb.append("Masc.")
                                sb.append(" ")
                            }

                            "F" -> {
                                sb.append("Fem.")
                                sb.append(" ")
                            }
                        }
                    } else {
                        when (person) {
                            "1" -> sb.append("1st Person")
                            "2" -> sb.append("2nd Person")
                            "3" -> sb.append("3rd Person")
                        }
                    }
                    when (number) {
                        "S" -> sb.append("Singular")
                        "P" -> sb.append("Plural")
                        "D" -> sb.append("Dual")
                    }
                } else {
                    gendernumber.length
                    if (gendernumber.length == 1) {
                        person = gendernumber.substring(0, 1)
                        when (person) {
                            "S" -> sb.append("Singular")
                            "P" -> sb.append("Plural")
                            "D" -> sb.append("Dual")
                            "1" -> sb.append("1st Per.")
                            "2" -> sb.append("2nd Per.")
                            "3" -> sb.append("3rd Per.")
                            "M" -> sb.append("Mas.")
                            "F" -> sb.append("Fem.")
                        }
                    }
                }
            }
            return sb
        }

        fun expandTags(tagtwo: String): String {
            var tagtwo = tagtwo
            when (tagtwo) {
                "PASS" -> tagtwo = verbfeaturesenglisharabic.PASS
                "PERF" -> tagtwo = verbfeaturesenglisharabic.PERF
                "IMPF" -> tagtwo = verbfeaturesenglisharabic.IMPF
                "ACTPCPL" -> tagtwo = CorpusConstants.NominalsProp.ACTPCPL
                "PASSPCPL" -> tagtwo = CorpusConstants.NominalsProp.PASSPCPL
                "V" -> tagtwo = verbfeaturesenglisharabic.V
                "N" -> tagtwo = CorpusConstants.Nominals.N
                "PN" -> tagtwo = CorpusConstants.Nominals.PN
                "ADJ" -> tagtwo = CorpusConstants.Nominals.ADJ
                "PRON" -> tagtwo = CorpusConstants.Nominals.PRON
                "DEM" -> tagtwo = CorpusConstants.Nominals.DEM
                "REL" -> tagtwo = CorpusConstants.Nominals.REL
                "T" -> tagtwo = CorpusConstants.Nominals.T
                "LOC" -> tagtwo = CorpusConstants.Nominals.LOC
                "ACT" -> tagtwo = CorpusConstants.NominalsProp.PASS
                "VN" -> tagtwo = CorpusConstants.NominalsProp.VN
                "INDEF" -> tagtwo = CorpusConstants.NominalsProp.INDEF
                "DEF" -> tagtwo = CorpusConstants.NominalsProp.DEF
                "DET" -> tagtwo = CorpusConstants.Particles.DET
                "NOM" -> tagtwo = CorpusConstants.NominalsProp.NOM
                "ACC" -> tagtwo = CorpusConstants.NominalsProp.ACC
                "P" -> tagtwo = CorpusConstants.Particles.P
                "EMPH" -> tagtwo = CorpusConstants.Particles.EMPH
                "IMPV" -> tagtwo = CorpusConstants.Particles.IMPV
                "PRP" -> tagtwo = CorpusConstants.Particles.PRP
                "CONJ" -> tagtwo = CorpusConstants.Particles.CONJ
                "SUB" -> tagtwo = CorpusConstants.Particles.SUB
                "AMD" -> tagtwo = CorpusConstants.Particles.AMD
                "ANS" -> tagtwo = CorpusConstants.Particles.ANS
                "AVR" -> tagtwo = CorpusConstants.Particles.AVR
                "CAUS" -> tagtwo = CorpusConstants.Particles.CAUS
                "CERT" -> tagtwo = CorpusConstants.Particles.CERT
                "CIRC" -> tagtwo = CorpusConstants.Particles.CIRC
                "COM" -> tagtwo = CorpusConstants.Particles.COM
                "COND" -> tagtwo = CorpusConstants.Particles.COND
                "EQ" -> tagtwo = CorpusConstants.Particles.EQ
                "EXH" -> tagtwo = CorpusConstants.Particles.EXH
                "EXL" -> tagtwo = CorpusConstants.Particles.EXL
                "EXP" -> tagtwo = CorpusConstants.Particles.EXP
                "FUT" -> tagtwo = CorpusConstants.Particles.FUT
                "INC" -> tagtwo = CorpusConstants.Particles.INC
                "INT" -> tagtwo = CorpusConstants.Particles.INT
                "INTG" -> tagtwo = CorpusConstants.Particles.INTG
                "NEG" -> tagtwo = CorpusConstants.Particles.NEG
                "PREV" -> tagtwo = CorpusConstants.Particles.PREV
                "PRO" -> tagtwo = CorpusConstants.Particles.PRO
                "REM" -> tagtwo = CorpusConstants.Particles.REM
                "RES" -> tagtwo = CorpusConstants.Particles.RES
                "RET" -> tagtwo = CorpusConstants.Particles.RET
                "RSLT" -> tagtwo = CorpusConstants.Particles.RSLT
                "SUP" -> tagtwo = CorpusConstants.Particles.SUP
                "SUR" -> tagtwo = CorpusConstants.Particles.SUR
                "VOC" -> tagtwo = CorpusConstants.Particles.VOC
                "INL" -> tagtwo = CorpusConstants.Particles.INL
            }
            return tagtwo
        }
    }
}


