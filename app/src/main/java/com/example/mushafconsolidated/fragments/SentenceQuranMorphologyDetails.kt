package com.example.mushafconsolidated.fragments

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.example.mushafconsolidated.Entities.NewCorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.utility.CorpusConstants
import com.example.utility.CorpusConstants.verbfeaturesenglisharabic

class SentenceQuranMorphologyDetails(
    index: Int,
    corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>,
    corpusNounWord: ArrayList<NounCorpus>, verbCorpuses: ArrayList<VerbCorpus>,
    context: Context
) : QuranMorphologyDetails() {
    private val context: Context
    override var form = 0
    override var Thulathi: String? = null
    private val index: Int
    private val corpusNoun: ArrayList<NounCorpus>
    private val indigo = 0
    private val cyan = 0
    private val yellow = 0
    private val green = 0
    private val corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>
    private val verbcorpusform: ArrayList<VerbCorpus>

    init {
        this.index = index - 1
        this.corpusSurahWord = corpusSurahWord
        verbcorpusform = verbCorpuses
        corpusNoun = corpusNounWord
        this.context = context
    }//    pngsb.append(",").append(verbcorpusform.get(index).getThulathibab());//     pngsb.append("," + "(form").append(verbcorpusform.get(index).getForm()).append(")");

    //    setSarfSagheer(true);
    //  mazeedQuery = sm.getMazeedQuery(roots, getForm());
    override val verbDetails: HashMap<String, String?>
        get() {
            val vbdetail = HashMap<String, String?>()
            vbdetail["surahid"] = verbcorpusform[index].chapterno.toString()
            vbdetail["ayahid"] = verbcorpusform[index].verseno.toString()
            vbdetail["wordno"] = verbcorpusform[index].wordno.toString()
            var roots: String?
            roots = verbRoot
            roots = verbCorpusRoot
            vbdetail["root"] = roots
            if (verbcorpusform.size > 0) {
                if (verbcorpusform[index].form != "I") {
                    val mform = verbcorpusform[index].form
                    convertForms(mform)
                    vbdetail["form"] = form.toString()
                    vbdetail["wazan"] = "null"
                    //    setSarfSagheer(true);
                    //  mazeedQuery = sm.getMazeedQuery(roots, getForm());
                } else {
                    val thulathibab = verbcorpusform[index].thulathibab
                    if (thulathibab!!.isEmpty()) vbdetail["thulathi"] =
                        "null" else if (thulathibab.length == 1) {
                        val s = verbcorpusform[index].thulathibab
                        val sb = getThulathiName(s)
                        vbdetail["thulathi"] = sb.toString()
                        vbdetail["wazan"] = thulathibab
                        vbdetail["form"] = "null"
                    } else if (thulathibab.length > 1) {
                        val s = thulathibab.substring(0, 1)
                        val sb = getThulathiName(s)
                        vbdetail["thulathi"] = sb.toString()
                        vbdetail["wazan"] = s
                    }
                }
            }
            val gendernumber = verbcorpusform[index].gendernumber
            val pngsb = getGenderNumberdetails(gendernumber)
            if (vbdetail["form"] != null) {
                val formdetails = getFormName(verbcorpusform[index].form)
                pngsb.append("," + "(form").append(formdetails)
                //     pngsb.append("," + "(form").append(verbcorpusform.get(index).getForm()).append(")");
                vbdetail["png"] = pngsb.toString()
            } else {
                vbdetail["png"] = null
                //    pngsb.append(",").append(verbcorpusform.get(index).getThulathibab());
            }
            vbdetail["png"] = pngsb.toString()
            when (verbcorpusform[index].tense) {
                "IMPF" -> vbdetail["tense"] = verbfeaturesenglisharabic.IMPF
                "IMPV" -> vbdetail["tense"] = verbfeaturesenglisharabic.IMPV
                "PERF" -> vbdetail["tense"] = verbfeaturesenglisharabic.PERF
            }
            when (verbcorpusform[index].voice) {
                "ACT" -> vbdetail["voice"] = verbfeaturesenglisharabic.ACT
                "PASS" -> vbdetail["voice"] = verbfeaturesenglisharabic.PASS
            }
            if (roots == "كون") {
                when (verbcorpusform[index].kana_mood) {
                    "MOOD:SUBJ" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.IND
                        vbdetail["verbmood"] = "Indicative"
                        vbdetail["mood"] = verbfeaturesenglisharabic.JUS
                        vbdetail["verbmood"] = "Jussive"
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
                when (verbcorpusform[index].mood_kananumbers) {
                    "IND" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.IND
                        vbdetail["verbmood"] = "Indicative"
                        vbdetail["mood"] = verbfeaturesenglisharabic.JUS
                        vbdetail["verbmood"] = "Jussive"
                    }

                    "JUS" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.JUS
                        vbdetail["verbmood"] = "Jussive"
                    }

                    "SUBJ" -> {
                        vbdetail["mood"] = verbfeaturesenglisharabic.SUBJ
                        vbdetail["verbmood"] = "Subjunctive"
                    }
                }
            }
            vbdetail["lemma"] = verbcorpusform[index].lemma_a
            return vbdetail
        }

    private val verbCorpusRoot: String?
        private get() {
            var roots: String? = null
            roots = verbcorpusform[index].root_a
            return roots
        }
    private val verbRoot: String?
        private get() {
            var roots: String? = null
            if (corpusSurahWord[index].wordcount == 1) {
                if (corpusSurahWord[index].tagone == "V") {
                    roots = corpusSurahWord[index].root_a
                }
            } else if (corpusSurahWord[index].wordcount == 2) {
                if (corpusSurahWord[index].tagone == "V") {
                    roots = corpusSurahWord[index].rootaraone
                } else if (corpusSurahWord[index].tagtwo == "V") {
                    roots = corpusSurahWord[index].rootaratwo
                }
            } else if (corpusSurahWord[index].wordcount == 3) {
                if (corpusSurahWord[index].tagone == "V") {
                    roots = corpusSurahWord[index].rootaraone
                } else if (corpusSurahWord[index].tagtwo == "V") {
                    roots = corpusSurahWord[index].rootaratwo
                } else if (corpusSurahWord[index].tagthree == "V") {
                    roots = corpusSurahWord[index].rootarathree
                }
            } else if (corpusSurahWord[index].wordcount == 4) {
                if (corpusSurahWord[index].tagone == "V") {
                    roots = corpusSurahWord[index].rootaraone
                } else if (corpusSurahWord[index].tagtwo == "V") {
                    roots = corpusSurahWord[index].rootaratwo
                } else if (corpusSurahWord[index].tagthree == "V") {
                    roots = corpusSurahWord[index].rootarathree
                } else if (corpusSurahWord[index].tagfour == "V") {
                    roots = corpusSurahWord[index].rootarafour
                }
            } else if (corpusSurahWord[index].wordcount == 5) {
                if (corpusSurahWord[index].tagone == "V") {
                    roots = corpusSurahWord[index].rootaraone
                } else if (corpusSurahWord[index].tagtwo == "V") {
                    roots = corpusSurahWord[index].rootaratwo
                } else if (corpusSurahWord[index].tagthree == "V") {
                    roots = corpusSurahWord[index].rootarathree
                } else if (corpusSurahWord[index].tagfour == "V") {
                    roots = corpusSurahWord[index].rootarafour
                } else if (corpusSurahWord[index].tagfive == "V") {
                    roots = corpusSurahWord[index].rootarafive
                }
            }
            return roots
        }//   String form = corpusNoun.get(0).getForm();

    //   String mform = corpusNoun.get(0).getForm();
    /*
     if (corpusNoun.size() > 0) {
         if (corpusNoun.get(index).getProptwo().equals(CorpusConstants.NominalsProp.PCPL)) {
             String form = corpusNoun.get(index).getForm();


             final String mform = form.replaceAll("\\(|\\)", "");

             //   String mform = corpusNoun.get(0).getForm();

             if (!mform.equals("I")) {
                 convertForms(mform);
                 wordbdetail.put("form", SpannableStringBuilder.valueOf(String.valueOf(getForm())));

                 getRoot(corpusSurahWord, wordbdetail);
             } else {
                 if (corpusNoun.get(index).getProptwo().equals("PCPL")) {
                     wordbdetail.put("PCPL", SpannableStringBuilder.valueOf(corpusNoun.get(index).getPropone().concat(corpusNoun.get(index).getProptwo())));
                 } else {
                     wordbdetail.put("PCPL", SpannableStringBuilder.valueOf("NONE"));
                 }


             }

         }
     }
    */
    val noundetails: HashMap<String, SpannableStringBuilder?>
        get() {
            val wordbdetail = HashMap<String, SpannableStringBuilder?>()
            val sb = StringBuilder()

            /*
              if (corpusNoun.size() > 0) {
                  if (corpusNoun.get(index).getProptwo().equals(CorpusConstants.NominalsProp.PCPL)) {
                      String form = corpusNoun.get(index).getForm();


                      final String mform = form.replaceAll("\\(|\\)", "");

                      //   String mform = corpusNoun.get(0).getForm();

                      if (!mform.equals("I")) {
                          convertForms(mform);
                          wordbdetail.put("form", SpannableStringBuilder.valueOf(String.valueOf(getForm())));

                          getRoot(corpusSurahWord, wordbdetail);
                      } else {
                          if (corpusNoun.get(index).getProptwo().equals("PCPL")) {
                              wordbdetail.put("PCPL", SpannableStringBuilder.valueOf(corpusNoun.get(index).getPropone().concat(corpusNoun.get(index).getProptwo())));
                          } else {
                              wordbdetail.put("PCPL", SpannableStringBuilder.valueOf("NONE"));
                          }


                      }

                  }
              }
             */if (corpusNoun.size > 0) {
                if (corpusNoun[index].proptwo == CorpusConstants.NominalsProp.PCPL) {
                    val form = corpusNoun[index].form
                    //   String form = corpusNoun.get(0).getForm();
                    val mform = form!!.replace("\\(|\\)".toRegex(), "")
                    //   String mform = corpusNoun.get(0).getForm();
                    wordbdetail["PART"] = SpannableStringBuilder.valueOf("PCPL")
                    if (mform == "I") {
                        wordbdetail["form"] = SpannableStringBuilder.valueOf(form)
                    }
                    if (mform != "I") {
                        if (mform != "null") {
                            convertForms(mform)
                            wordbdetail["form"] =
                                SpannableStringBuilder.valueOf(this.form.toString())
                            getRoot(corpusSurahWord, wordbdetail)
                        }
                    } else {
                        if (corpusNoun[index].proptwo == "PCPL") {
                            wordbdetail["PCPL"] = SpannableStringBuilder.valueOf(
                                corpusNoun[index].propone + corpusNoun[index].proptwo
                            )
                            wordbdetail["PART"] = SpannableStringBuilder.valueOf("PCPL")
                            wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                            wordbdetail["form"] = SpannableStringBuilder.valueOf("I")
                        } else {
                            wordbdetail["PART"] = SpannableStringBuilder.valueOf("NONE")
                            wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                            wordbdetail["form"] = SpannableStringBuilder.valueOf("I")
                        }
                    }
                }
            }
            if (corpusNoun.size > 0) {
                if (corpusNoun[index].tag == "N") {
                    val propone = corpusNoun[index].propone
                    val proptwo = corpusNoun[index].proptwo
                    var pcpl = ""
                    if (propone != "null" && proptwo != "null") {
                        pcpl = pcpl + propone + proptwo
                    }
                    val form = corpusNoun[index].form
                    val gendernumber = corpusNoun[index].gendernumber
                    val type = corpusNoun[index].type
                    val cases = corpusNoun[index].cases
                    sb.append("Noun:")
                    if (cases != "null") {
                        when (cases) {
                            "NOM" -> sb.append(CorpusConstants.NominalsProp.NOM + " ")
                            "ACC" -> sb.append(CorpusConstants.NominalsProp.ACC + " ")
                            "GEN" -> sb.append(CorpusConstants.NominalsProp.GEN + " ")
                        }
                    }
                    if (gendernumber != "null") {
                        if (gendernumber!!.length == 1) {
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
                    if (form != "null") {
                        sb.append("(form").append(form).append(")")
                    }
                    if (propone != "null" && proptwo != "null") {
                        if (pcpl == "ACTPCPL") {
                            sb.append(CorpusConstants.NominalsProp.ACTPCPL)
                            wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                        } else if (pcpl == "PASSPCPL") {
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
            getProperNounDetails(corpusNoun, wordbdetail, sb)
            return wordbdetail
        }

    //   String mform = corpusNoun.get(0).getForm();
    //get the root,since vercopus is not checked
    // getProperNounDetails(corpusNoun, wordbdetail, sb);
    override val wordDetails: HashMap<String, SpannableStringBuilder?>
        get() {
            val wordbdetail = HashMap<String, SpannableStringBuilder?>()
            val surah = corpusSurahWord[index].surah
            wordbdetail["surahid"] =
                SpannableStringBuilder.valueOf(corpusSurahWord[index].surah.toString())
            wordbdetail["ayahid"] =
                SpannableStringBuilder.valueOf(corpusSurahWord[index].ayah.toString())
            wordbdetail["wordno"] =
                SpannableStringBuilder.valueOf(corpusSurahWord[index].wordno.toString())
            wordbdetail["wordtranslation"] =
                SpannableStringBuilder.valueOf(corpusSurahWord[index].en)
            val arabicword =
                corpusSurahWord[index].araone + corpusSurahWord[index].aratwo + corpusSurahWord[index].arathree + corpusSurahWord[index].arafour + corpusSurahWord[index].arafour
            if (corpusNoun.size > 0) {
                if (corpusNoun[0].proptwo == CorpusConstants.NominalsProp.PCPL) {
                    val form = corpusNoun[0].form
                    val mform = form!!.replace("\\(|\\)".toRegex(), "")
                    //   String mform = corpusNoun.get(0).getForm();
                    if (mform != "I") {
                        convertForms(mform)
                        wordbdetail["form"] = SpannableStringBuilder.valueOf(this.form.toString())
                        getRoot(corpusSurahWord, wordbdetail)
                    } else {
                        if (corpusNoun[0].proptwo == "PCPL") {
                            wordbdetail["PCPL"] = SpannableStringBuilder.valueOf(
                                corpusNoun[0].propone + corpusNoun[0].proptwo
                            )
                            wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                        } else {
                            wordbdetail["PCPL"] = SpannableStringBuilder.valueOf("NONE")
                            wordbdetail["particple"] = SpannableStringBuilder.valueOf("PART")
                        }
                    }
                }
            }
            val size = corpusNoun.size
            val corpousindex = 0
            GetPronounDetails(corpusSurahWord, wordbdetail)
            GetLemmArabicwordWordDetails(corpusSurahWord, wordbdetail)
            val sb = StringBuilder()
            //get the root,since vercopus is not checked
            getRoot(corpusSurahWord, wordbdetail)
            // getProperNounDetails(corpusNoun, wordbdetail, sb);
            return wordbdetail
        }

    private fun getProperNounDetails(
        corpusNoun: ArrayList<NounCorpus>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>,
        sb: StringBuilder
    ) {
        if (corpusNoun.size > 0) {
            if (corpusNoun[index].tag == "PN" || corpusNoun[index].tag == "ADJ") {
                val propone = corpusNoun[index].propone
                val proptwo = corpusNoun[index].proptwo
                var pcpl = ""
                if (propone != "null" && proptwo != "null") {
                    pcpl = pcpl + propone + proptwo
                }
                if (corpusNoun[index].propone == "VN") {
                    sb.append("Proper/Verbal Noun")
                } else if (corpusNoun[index].tag == "ADJ") {
                    sb.append("Adjective:")
                } else {
                    sb.append("Proper Noun:")
                }
                val form = corpusNoun[index].form
                val gendernumber = corpusNoun[index].gendernumber
                val type = corpusNoun[index].type
                val cases = corpusNoun[index].cases
                // sb.append("Proper Noun:");
                if (cases != "null") {
                    when (cases) {
                        "NOM" -> sb.append(CorpusConstants.NominalsProp.NOM)
                        "ACC" -> sb.append(CorpusConstants.NominalsProp.ACC)
                        "GEN" -> sb.append(CorpusConstants.NominalsProp.GEN)
                    }
                }
                if (gendernumber != "null") {
                    if (gendernumber!!.length == 1) {
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
                if (form != "null") {
                    sb.append("(form ").append(form).append(")")
                }
                if (propone != "null" && proptwo != "null") {
                    if (pcpl == "ACTPCPL") {
                        sb.append(CorpusConstants.NominalsProp.ACTPCPL)
                    } else if (pcpl == "PASSPCPL") {
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
            }
        }
    }

    private fun getRoot(
        corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>
    ) {
        val wordno = corpusSurahWord[index].wordno
        for (verb: VerbCorpus in verbcorpusform) {
            if (verb.wordno == wordno && ((verb.voice == "ACTI") || verb.voice == "PASS")) {
                if (verb.form != "I") {
                    val mform = verb.form
                    convertForms(mform)
                    wordbdetail["form"] = SpannableStringBuilder.valueOf(form.toString())
                    wordbdetail["wazan"] = SpannableStringBuilder.valueOf("null")
                    //    setSarfSagheer(true);
                    //  mazeedQuery = sm.getMazeedQuery(roots, getForm());
                } else {
                    val thulathibab = verb.thulathibab
                    if (thulathibab!!.isEmpty()) wordbdetail["thulathi"] =
                        null else if (thulathibab.length == 1) {
                        val s = verb.thulathibab
                        val sb = getThulathiName(s)
                        wordbdetail["thulathi"] = SpannableStringBuilder.valueOf(sb.toString())
                        wordbdetail["wazan"] = SpannableStringBuilder.valueOf(thulathibab)
                        wordbdetail["form"] = SpannableStringBuilder.valueOf("null")
                    } else if (thulathibab.length > 1) {
                        val s = thulathibab.substring(0, 1)
                        val sb = getThulathiName(s)
                        wordbdetail["thulathi"] = SpannableStringBuilder.valueOf(sb.toString())
                        wordbdetail["wazan"] = SpannableStringBuilder.valueOf(s)
                        wordbdetail["form"] = SpannableStringBuilder.valueOf("null")
                    }
                }
                val gendernumber = verb.gendernumber
                val pngsb = getGenderNumberdetails(gendernumber)
                if (wordbdetail["form"] != null) {
                    val formdetails = verb.form
                    pngsb.append("," + "(form").append(formdetails)
                    //     pngsb.append("," + "(form").append(verbcorpusform.get(index).getForm()).append(")");
                    wordbdetail["png"] = SpannableStringBuilder.valueOf(pngsb.toString())
                } else {
                    wordbdetail["png"] = null
                    //    pngsb.append(",").append(verbcorpusform.get(index).getThulathibab());
                }
                wordbdetail["png"] = SpannableStringBuilder.valueOf(pngsb.toString())
                when (verb.tense) {
                    "IMPF" -> wordbdetail["tense"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.IMPF
                    )

                    "IMPV" -> wordbdetail["tense"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.IMPV
                    )

                    "PERF" -> wordbdetail["tense"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.PERF
                    )
                }
                when (verb.voice) {
                    "ACT" -> wordbdetail["voice"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.ACT
                    )

                    "PASS" -> wordbdetail["voice"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.PASS
                    )
                }
                when (verb.mood_kananumbers) {
                    "IND" -> wordbdetail["mood"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.IND
                    )

                    "JUS" -> wordbdetail["mood"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.JUS
                    )

                    "SUBJ" -> wordbdetail["mood"] = SpannableStringBuilder.valueOf(
                        verbfeaturesenglisharabic.SUBJ
                    )
                }
                wordbdetail["lemma"] = SpannableStringBuilder.valueOf(verb.lemma_a)
                wordbdetail["root"] = SpannableStringBuilder.valueOf(verb.root_a)
                //   wordbdetail.put("form", SpannableStringBuilder.valueOf(verb.getForm() ));
                //  wordbdetail.put("wazan", SpannableStringBuilder.valueOf(verb.getThulathibab() ));
            }
        }
        if (corpusSurahWord.size > 0) {
            if (corpusSurahWord[index].wordcount == 1) {
                if (corpusSurahWord[index].rootaraone!!.isNotEmpty()) {
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagone)
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaraone)
                } else {
                    wordbdetail["tag"] = SpannableStringBuilder.valueOf("none")
                }
            } else if (corpusSurahWord[index].wordcount == 2) {
                if (corpusSurahWord[index].rootaraone!!.isNotEmpty()) {
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagone)
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaraone)
                } else if (corpusSurahWord[index].rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaratwo)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagtwo)
                } else {
                    wordbdetail["tag"] = SpannableStringBuilder.valueOf("none")
                }
            } else if (corpusSurahWord[index].wordcount == 3) {
                if (corpusSurahWord[index].rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaraone)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagone)
                } else if (corpusSurahWord[index].rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaratwo)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagtwo)
                } else if (corpusSurahWord[index].rootarathree!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootarathree)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagthree)
                } else {
                    wordbdetail["tag"] = SpannableStringBuilder.valueOf("none")
                }
            } else if (corpusSurahWord[index].wordcount == 4) {
                if (corpusSurahWord[index].rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaraone)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagone)
                } else if (corpusSurahWord[index].rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaratwo)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagtwo)
                } else if (corpusSurahWord[index].rootarathree!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootarathree)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagthree)
                } else if (corpusSurahWord[index].rootarafour!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootarafour)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagfour)
                } else {
                    wordbdetail["tag"] = SpannableStringBuilder.valueOf("none")
                }
            } else if (corpusSurahWord[index].wordcount == 5) {
                if (corpusSurahWord[index].rootaraone!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaraone)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagone)
                } else if (corpusSurahWord[index].rootaratwo!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootaratwo)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagtwo)
                } else if (corpusSurahWord[index].rootarathree!!.isNotEmpty()) {
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagthree)
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootarathree)
                } else if (corpusSurahWord[index].rootarafour!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootarafour)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagfour)
                } else if (corpusSurahWord[index].rootarafive!!.isNotEmpty()) {
                    wordbdetail["root"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].rootarafive)
                    wordbdetail["tag"] =
                        SpannableStringBuilder.valueOf(corpusSurahWord[index].tagfive)
                } else {
                    wordbdetail["tag"] = SpannableStringBuilder.valueOf("none")
                }
            } else {
                wordbdetail["tag"] = SpannableStringBuilder.valueOf("none")
            }
        }
    }

    private fun GetLemmArabicwordWordDetails(
        corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>
    ) {
        if (corpusSurahWord[index].wordcount == 1) {
            val tagone = corpusSurahWord[index].tagone
            val expandTagsone = expandTags(tagone)
            wordbdetail["lemma"] =
                SpannableStringBuilder.valueOf(corpusSurahWord[index].lemaraone)
            wordbdetail["word"] = SpannableStringBuilder.valueOf(corpusSurahWord[index].araone)
            wordbdetail["worddetails"] = SpannableStringBuilder.valueOf(expandTagsone)
        } else if (corpusSurahWord[index].wordcount == 2) {
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                corpusSurahWord[index].lemaraone + corpusSurahWord[index].lemaratwo
            )
            val araone = corpusSurahWord[index].araone
            val aratwo = corpusSurahWord[index].aratwo
            val arabicspannable = SpannableStringBuilder(
                araone + aratwo
            )
            val tagtwo = corpusSurahWord[index].tagtwo
            val tagone = corpusSurahWord[index].tagone
            val expandTagsone = expandTags(tagone)
            val expandTagstwo = expandTags(tagtwo)
            val tagspannable = SpannableStringBuilder(
                expandTagstwo + "|" +
                        expandTagsone
            )
            val one = corpusSurahWord[index].araone.length //2
            val two = corpusSurahWord[index].aratwo.length //3
            val secondend = one + two
            val twotag = expandTagsone.length //1
            val onetag = expandTagstwo.length //3
            val secondendtag = onetag + twotag
            arabicspannable.setSpan(
                ForegroundColorSpan(cyan),
                0,
                one,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            arabicspannable.setSpan(
                ForegroundColorSpan(yellow),
                one,
                secondend,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tagspannable.setSpan(
                ForegroundColorSpan(yellow),
                0,
                onetag + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tagspannable.setSpan(
                ForegroundColorSpan(cyan),
                onetag + 1,
                secondendtag + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            wordbdetail["word"] = arabicspannable
            wordbdetail["worddetails"] = tagspannable
        } else if (corpusSurahWord[index].wordcount == 3) {
            val sb = StringBuilder()
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[index].lemaraone + corpusSurahWord[index].lemaratwo +
                        corpusSurahWord[index].lemrathree)
            )
            val one = corpusSurahWord[index].araone.length //2
            val two = corpusSurahWord[index].aratwo.length //3
            val three = corpusSurahWord[index].arathree.length //10
            val secondend = one + two
            val thirdstart = one + two
            val thirdend = thirdstart + three
            val expandTagsone = expandTags(corpusSurahWord[index].tagone)
            val expandTagstwo = expandTags(corpusSurahWord[index].tagtwo)
            val expandTagsthree = expandTags(corpusSurahWord[index].tagthree)
            val tagone = expandTagsone.length //4
            val tagtwo = expandTagstwo.length //1
            val tagthree = expandTagsthree.length //8
            val spannableStringBuilder = SpannableStringBuilder(
                (corpusSurahWord[index].araone + corpusSurahWord[index].aratwo
                        + corpusSurahWord[index].arathree)
            )
            val tagspannable = SpannableStringBuilder(
                (expandTagsthree + "|" +
                        expandTagstwo + "|"
                        + expandTagsone)
            )
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(cyan),
                0,
                one,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(yellow),
                one,
                secondend,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(green),
                thirdstart,
                thirdend,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            sb.append(corpusSurahWord[index].tagthree)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagtwo)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagone)
            // 0,tagthree
            // tagthree,tagtwo
            // tagtwo,tagone
            /*

tagspannable.subSequence(0,tagthree+1)
tagspannable.subSequence(tagthree+1,tagthree+tagtwo+2)
tagspannable.subSequence(tagthree+tagtwo+2,tagthree+tagtwo+tagone+2)
 */tagspannable.setSpan(
                ForegroundColorSpan(green),
                0,
                tagthree + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tagspannable.setSpan(
                ForegroundColorSpan(yellow),
                tagthree + 1,
                tagthree + tagtwo + 2,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tagspannable.setSpan(
                ForegroundColorSpan(cyan),
                tagthree + tagtwo + 2,
                tagthree + tagtwo + tagone + 2,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            wordbdetail["word"] = spannableStringBuilder
            //      wordbdetail.put("worddetails", sb.toString());
            wordbdetail["worddetails"] = tagspannable
        } else if (corpusSurahWord[index].wordcount == 4) {
            val sb = StringBuilder()
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[index].lemaraone + corpusSurahWord[index].lemaratwo +
                        corpusSurahWord[index].lemrathree)
            )
            val one = corpusSurahWord[index].araone.length //2
            val two = corpusSurahWord[index].aratwo.length //3
            val three = corpusSurahWord[index].arathree.length //5
            val four = corpusSurahWord[index].arafour.length //6
            val onetag = corpusSurahWord[index].tagone.length //1
            val twotag = corpusSurahWord[index].tagtwo.length //3
            val threetag = corpusSurahWord[index].tagthree.length //1
            val secondend = one + two
            val thirdstart = one + two
            val thirdend = thirdstart + three
            val secondendtag = onetag + twotag
            val thirdstarttag = onetag + twotag + 1
            val thirdendtag = thirdstarttag + threetag
            val expandTagsone = expandTags(corpusSurahWord[index].tagone)
            val expandTagstwo = expandTags(corpusSurahWord[index].tagtwo)
            val expandTagsthree = expandTags(corpusSurahWord[index].tagthree)
            val expandTagsfour = expandTags(corpusSurahWord[index].tagfour)
            val spannableStringBuilder = SpannableStringBuilder(
                (corpusSurahWord[index].araone + corpusSurahWord[index].aratwo
                        + corpusSurahWord[index].arathree + corpusSurahWord[index].arafour)
            )
            val tagspannable = SpannableStringBuilder(
                (expandTagsfour + "|" + expandTagsthree + "|" +
                        expandTagstwo + "|"
                        + expandTagsone)
            )
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(cyan),
                0,
                one,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(yellow),
                one,
                secondend,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(green),
                thirdstart,
                thirdend,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(indigo),
                thirdend,
                thirdend + four,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tagspannable.setSpan(
                ForegroundColorSpan(green),
                0,
                onetag + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tagspannable.setSpan(
                ForegroundColorSpan(yellow),
                onetag + 1,
                secondendtag + 2,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            tagspannable.setSpan(
                ForegroundColorSpan(cyan),
                thirdstarttag,
                thirdendtag + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            sb.append(corpusSurahWord[index].tagfour)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagthree)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagtwo)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagone)
            wordbdetail["word"] = spannableStringBuilder
            //      wordbdetail.put("worddetails", sb.toString());
            wordbdetail["worddetails"] = tagspannable
        } else if (corpusSurahWord[index].wordcount == 5) {
            val sb = StringBuilder()
            wordbdetail["lemma"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[index].lemaraone + corpusSurahWord[index].lemaratwo +
                        corpusSurahWord[index].lemrathree + corpusSurahWord[index].lemarafour + corpusSurahWord[index].lemarafive)
            )
            sb.append(corpusSurahWord[index].tagfive)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagfour)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagthree)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagtwo)
            sb.append("|")
            sb.append(corpusSurahWord[index].tagone)
            wordbdetail["word"] = SpannableStringBuilder.valueOf(
                (corpusSurahWord[index].araone + corpusSurahWord[index].aratwo +
                        corpusSurahWord[index].arathree + corpusSurahWord[index].arafour + corpusSurahWord[index].tagfive)
            )
            wordbdetail["worddetails"] = SpannableStringBuilder.valueOf(sb)
        }
    }

    private fun GetPronounDetails(
        corpusSurahWord: ArrayList<NewCorpusExpandWbwPOJO>,
        wordbdetail: HashMap<String, SpannableStringBuilder?>
    ) {
        if (corpusSurahWord[index].wordcount == 1) {
            if ((corpusSurahWord[index].tagone == "PRON")) {
                //   String[] parts = corpusSurahWord.get(index).getDetailsone().toString().split("\"|");
                val gendernumber =
                    corpusSurahWord[index].detailsone.replace("^.*?(\\w+)\\W*$".toRegex(), "$1")
                println(gendernumber)
                // String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord[index].wordcount == 2) {
            if ((corpusSurahWord[index].tagtwo == "PRON")) {
                val gendernumber =
                    corpusSurahWord[index].detailstwo.replace("^.*?(\\w+)\\W*$".toRegex(), "$1")
                //   String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord[index].wordcount == 3) {
            if ((corpusSurahWord[index].tagthree == "PRON")) {
                //   String[] parts = corpusSurahWord.get(index).getDetailsthree().toString().split("|");
                val gendernumber =
                    corpusSurahWord[index].detailsthree.replace("^.*?(\\w+)\\W*$".toRegex(), "$1")
                //    String gendernumber = parts[parts.length - 1];
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord[index].wordcount == 4) {
            if ((corpusSurahWord[index].tagfour == "PRON")) {
                val parts = corpusSurahWord[index].detailsfour.split("\\|PRON:".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                val gendernumber = parts[parts.size - 1]
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        } else if (corpusSurahWord[index].wordcount == 5) {
            if ((corpusSurahWord[index].tagfive == "PRON")) {
                val parts = corpusSurahWord[index].detailsfive.split("\\|".toRegex())
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                val gendernumber = parts[parts.size - 1]
                val builder = getGenderNumberdetails(gendernumber)
                wordbdetail["PRON"] = SpannableStringBuilder.valueOf(builder)
            }
        }
    }
}