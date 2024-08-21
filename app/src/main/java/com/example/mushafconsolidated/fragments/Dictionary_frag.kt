package com.example.mushafconsolidated.fragments


import Utility.ArabicLiterals
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Activity.LughatWordDetailsAct
import com.example.mushafconsolidated.Adaptersimport.LexiconAdapter
import com.example.mushafconsolidated.Adaptersimport.WordLughatAdapter
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.utility.QuranGrammarApplication
import ru.dimorinny.floatingtextbutton.FloatingTextButton


class Dictionary_frag(lughatWordDetailsAct: LughatWordDetailsAct, language: String) :
    Fragment() {
    private val worddifinition = ArrayList<String>()
    val language: String
    private var ishans = false
    lateinit var recyclerView: RecyclerView
    private val context: Context
    private var dictionary: ArrayList<lughat>? = null
    private var vocabroot: String? = null

    init {
        context = lughatWordDetailsAct
        this.language = language
    }

    fun newInstance(): Dictionary_frag {
        val f = Dictionary_frag(context as LughatWordDetailsAct, language)
        val dataBundle = requireArguments()
        vocabroot = dataBundle.getString(Constant.QURAN_VERB_ROOT)
        f.arguments = dataBundle
        return f
    }

    override fun onDetach() {
        super.onDetach()
        recyclerView.removeAllViews()
        Log.d("TAG", "verb fragment Detached")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val mainViewModel = ViewModelProvider(this)[QuranVIewModel::class.java]
        val view = inflater.inflate(R.layout.sarfkabeerheader, container, false)
        val callButton = view.findViewById<FloatingTextButton>(R.id.action_buttons)
        val dataBundle = arguments
        if (dataBundle != null) {
            val callingfragment = dataBundle.getString(Constant.MUJARRADVERBTAG)
            if (callingfragment != null) {
                if (callingfragment == "tverblist") {
                    callButton.visibility = View.VISIBLE
                } else {
                    callButton.visibility = View.GONE
                }
            } else {
                callButton.visibility = View.GONE
            }
        }
        //   callButton.setVisibility(View.VISIBLE);
        callButton.setOnClickListener { view1: View? ->
            val fm = requireActivity()
                .supportFragmentManager
            fm.popBackStack()
        }
        val utils = Utils(QuranGrammarApplication.context)
        assert(dataBundle != null)
        var verbroot = dataBundle!!.getString(Constant.QURAN_VERB_ROOT)
        val arabicword = dataBundle.getString("arabicword")
        vocabroot = dataBundle.getString(Constant.QURAN_VERB_ROOT)
        var lanesroot = dataBundle.getString(Constant.QURAN_VERB_ROOT)
        //for lughat convert hamaza to alif
        val starts = vocabroot!!.indexOf(ArabicLiterals.Hamza)
        if (starts != -1) {
            vocabroot = vocabroot!!.replace(
                ArabicLiterals.Hamza,
                ArabicLiterals.LALIF.trim { it <= ' ' })
        }
        if (null != arabicword) {
            dictionary = utils.getArabicWord(arabicword) as ArrayList<lughat>?
        } else if (language == "lanes") {
            val indexOfHamza = verbroot!!.indexOf(ArabicLiterals.Hamza)
            if (indexOfHamza != -1) {
                lanesroot = verbroot.replace(
                    ArabicLiterals.Hamza.toRegex(),
                    ArabicLiterals.LALIF
                )
            }
            val indexofya = lanesroot!!.indexOf("ى")
            if (indexofya != -1) {
                lanesroot = lanesroot.replace("ى".toRegex(), "ي")
            }
            val c2 = verbroot[1]
            val c3 = verbroot[2]
            if (verbroot == "يدي") {
                verbroot = verbroot.substring(0, 2)
            } else if (verbroot == "حيي") {
                verbroot = "حي"
            } else if (verbroot == "ايي") {
                verbroot = "اى"
            } else if (c3.toString() == ArabicLiterals.Ya) {
                verbroot = verbroot.replace(
                    ArabicLiterals.Ya,
                    ArabicLiterals.AlifMaksuraString
                )
            } else if (c3.toString() == ArabicLiterals.LALIF) {
                verbroot = verbroot.replace(
                    ArabicLiterals.LALIF,
                    ArabicLiterals.AlifHamzaAbove
                )
            } else if (c2.toString() == c3.toString()) {
                verbroot = verbroot.substring(0, 2)
            }


            val lanerootdictionaries = mainViewModel.getLanes(lanesroot).value


            val difinitionbuilder = StringBuilder()

            val lanesdictionary = mainViewModel.getLanes(lanesroot).value

            if (lanerootdictionaries != null) {
                if (lanerootdictionaries.isNotEmpty()) {
                    lanerootdictionaries[0].definition?.let { worddifinition.add(it) }
                } else if (!lanesdictionary?.isEmpty()!!) {
                    //  <p style="margin-left:200px; margin-right:50px;">
                    difinitionbuilder.append(Constant.html)
                    var replaced: String
                    for (lanes in lanesdictionary) {
                        //  replaced = getString(lanes);
                        replaced = lanes.definition!!
                        val indexOf = replaced.indexOf("<orth extent=\"full\" lang=\"ar\">ذ</orth>")
                        val indexofForm = replaced.indexOf("<form>")
                        val indexofFormclose = replaced.indexOf("</form>")
                        val indexofforminfl = replaced.indexOf("<form n=\"infl\">")
                        difinitionbuilder.append("<p dir='ltr' style=\" margin-top:0px; margin-bottom:0px; margin-left:0px; margin-right:0px; -qt-block-indent:0; text-indent:0px;\">")
                        if (indexOf != -1) {
                            replaced = replaced.replace(
                                "<orth extent=\"full\" lang=\"ar\">ذ</orth>".toRegex(),
                                ""
                            )
                            //     difinitionbuilder.append(replaced);
                        }
                        if (indexofForm != -1) {
                            replaced = replaced.replace("<form>".toRegex(), "")
                            //     difinitionbuilder.append(replaced);
                        }
                        if (indexofFormclose != -1) {
                            replaced = replaced.replace("</form>".toRegex(), "")
                            //    difinitionbuilder.append(replaced);
                        }
                        if (indexofforminfl != -1) {
                            replaced = replaced.replace("<form n=\"infl\">".toRegex(), "")
                            //    difinitionbuilder.append(replaced);
                        }
                        difinitionbuilder.append(replaced)
                        difinitionbuilder.append("</p>")
                        difinitionbuilder.append("<hr size=\"1\" width=\"100%\" color=\"red\">  ")
                    }
                    worddifinition.add(difinitionbuilder.toString())
                }
            }
        } else if (language == "hans") {
            val probableRoot = verbroot
            val indexOfHamza = verbroot!!.indexOf(ArabicLiterals.Hamza)
            if (indexOfHamza != -1) {
                verbroot = verbroot.replace(
                    ArabicLiterals.Hamza.toRegex(),
                    ArabicLiterals.LALIF
                )
            }
            val c1 = verbroot[0]
            val c2 = verbroot[1]
            val c3 = verbroot[2]
            if (verbroot == "يدي") {
                verbroot = verbroot.substring(0, 2)
            } else if (verbroot == "ايي") {
                verbroot = "آي"
            } else if (c3.toString() == ArabicLiterals.Ya) {
                verbroot = verbroot.replace(
                    ArabicLiterals.Ya,
                    ArabicLiterals.AlifMaksuraString
                )
            } else if (c3.toString() == ArabicLiterals.LALIF) {
                verbroot = verbroot.replace(
                    ArabicLiterals.LALIF,
                    ArabicLiterals.Hamza
                ) //change alif to hamza
            } else if (c2.toString() == c3.toString()) {
                verbroot = verbroot.substring(0, 2) //contract the double at end
            }
            if (c1.toString() == ArabicLiterals.Hamza) {
                verbroot = verbroot.replace(
                    ArabicLiterals.Hamza,
                    ArabicLiterals.LALIF
                )
            }
            val hanssb = StringBuilder()


            var hansdictionary = mainViewModel.getHans(verbroot).value

            if (hansdictionary != null) {
                if (hansdictionary.isEmpty()) {

                    hansdictionary = mainViewModel.getHans(probableRoot.toString()).value
                }
            }
            if (hansdictionary != null) {
                if (hansdictionary.isNotEmpty()) ishans = true
            }
            hanssb.append(Constant.html)
            if (!ishans) {
                hanssb.append("root word ").append(verbroot).append("not found")
            }
            if (hansdictionary != null) {
                for (hans in hansdictionary) {
                    //  <p style="margin-left:200px; margin-right:50px;">
                    hanssb.append("<p style=\"margin-left:10px; margin-right:10px;\">")
                    hanssb.append(hans.definition).append("</p>")
                    hanssb.append("<hr size=\"1\" width=\"100%\" color=\"red\">  ")
                }
            }
            hanssb.append("</html")
            worddifinition.add(hanssb.toString())
        } else {
            dictionary =
                utils.getRootDictionary(vocabroot!!.trim { it <= ' ' }) as ArrayList<lughat>?
            //  utils.grammarRules
        }
        recyclerView = view.findViewById(R.id.sarfrecview)
        val ska: WordLughatAdapter
        val lanesLexiconAdapter: LexiconAdapter
        when (language) {
            "imperative" -> {
                val isimperative = mainViewModel.getGramarRulesbyHarf("Imperative").value
                //     utils.getGrammarRulesByRules("Imperative")
                worddifinition.add(isimperative!![0].detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "genetivenoun" -> {
                val ismmajroor: List<GrammarRules?>? =
                    mainViewModel.getGramarRulesbyHarf("genetivenoun").value
                //   utils.getGrammarRulesByRules("genetivenoun")
                worddifinition.add(ismmajroor?.get(0)!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "accusativenoun" -> {
                val ismmansub: List<GrammarRules?>? =
                    mainViewModel.getGramarRulesbyHarf("accusativenoun").value
                //    utils.getGrammarRulesByRules("accusativenoun")
                worddifinition.add(ismmansub!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "nominativenoun" -> {
                val ismmarfu: List<GrammarRules?>? =
                    mainViewModel.getGramarRulesbyHarf("nomnouns").value


                worddifinition.add(ismmarfu!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "relative" -> {
                val rel: List<GrammarRules?>? = mainViewModel.getGramarRulesbyHarf("relative").value
                //    utils.getGrammarRulesByRules("relative")
                worddifinition.add(rel!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "demonstrative" -> {
                val dem: List<GrammarRules?>? = mainViewModel.getGramarRulesbyHarf("dem").value
                //     utils.getGrammarRulesByRules("dem")
                worddifinition.add(dem!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "Subjunctive" -> {
                val nasab: List<GrammarRules?>? = mainViewModel.getGramarRulesbyHarf("nasab").value
                //       utils.getGrammarRulesByRules("nasab")
                worddifinition.add(nasab!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "Jussive" -> {
                val jazam: List<GrammarRules?>? = mainViewModel.getGramarRulesbyHarf("jazam").value
                //     utils.getGrammarRulesByRules("jazam")
                worddifinition.add(jazam!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "preposition" -> {
                val jarr: List<GrammarRules?>? = mainViewModel.getGramarRulesbyHarf("jar").value
                //     utils.getGrammarRulesByRules("jarr")
                worddifinition.add(jarr!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "conditonal" -> {
                val shart: List<GrammarRules?>? = mainViewModel.getGramarRulesbyHarf("shart").value
                //       utils.getGrammarRulesByRules("shart")
                worddifinition.add(shart!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "accusative" -> {
                val nasab: List<GrammarRules?>? =
                    mainViewModel.getGramarRulesbyHarf("kanainna").value
                //      = utils.getGrammarRulesByRules("kanainna")
                worddifinition.add(nasab!![0]!!.detailsrules)
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            "lanes", "hans" -> {
                if (worddifinition.isEmpty()) {
                    worddifinition.add("Word not Updated")
                }
                lanesLexiconAdapter = LexiconAdapter(worddifinition, requireContext(), language)
                recyclerView.adapter = lanesLexiconAdapter
            }

            else -> {
                ska = WordLughatAdapter(dictionary, requireContext(), language)
                recyclerView.adapter = ska
            }
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.sarfrecview)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
    }
}
