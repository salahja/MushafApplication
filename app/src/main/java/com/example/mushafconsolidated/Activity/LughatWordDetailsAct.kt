package com.example.mushafconsolidated.Activity


import SharedPref
import Utility.ArabicLiterals
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.Constant
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.fragments.Dictionary_frag
import com.example.utility.QuranGrammarApplication
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import org.sj.conjugator.fragments.FragmentIsmIsmAla
import org.sj.conjugator.fragments.FragmentIsmZarf
import org.sj.conjugator.fragments.FragmentIsmfaelIsmMafools
import org.sj.conjugator.fragments.FragmentVerb
import ru.dimorinny.floatingtextbutton.FloatingTextButton


class LughatWordDetailsAct : BaseActivity() {
    // Arrey of strings FOR TABS TITLES


    private var shared: SharedPreferences? = null

    private val dictionarytitle = arrayOf("Lane Lexicon", "Hans Weir")
    private val arabicwordharfnasb = arrayOf("English lughat", "Urdu Lughat", "Harf ")
    private val vocabularytitles =
        arrayOf("Lane Lexicon", "Hans Weir", "English lughat", "Urdu Lughat")
    private val thulathientitles = arrayOf(
        "Lane Lexicon",
        "Hans Weir",
        "English lughat",
        "Urdu Lughat",
        "Verb Conjugaton",
        "Active/Passive PCPL",
        "N. Instrument",
        "N.Place/Time"
    )
    private val thulathientitlesmansub = arrayOf(
        "Lane Lexicon", "Hans Weir", "English lughat", "Urdu Lughat", "Subjunctive",
        "Verb Conjugaton", "Active/Passive PCPL", "N. Instrument", "N.Place/Time"
    )
    private val mujarradparticple = arrayOf(
        "Lane Lexicon", "Hans Weir", "English lughat", "Urdu Lughat", " ",
        "Verb Conjugaton", "Active/Passive PCPL", "N. Instrument", "N.Place/Time"
    )
    private val mazeedparticpletitle = arrayOf(
        "Lane Lexicon",
        "Hans Weir",
        "English lughat",
        "Urdu Lughat",
        " ",
        "Verb Conjugaton",
        "Active/Passive Participle"
    )
    private val thulathientitlesmajzoom = arrayOf(
        "Lane Lexicon", "Hans Weir", "English lughat", "Urdu Lughat",
        "Jussive", "Verb Conjugaton", "Active/Passive PCPL", "N. Instrument", "N.Place/Time"
    )
    private val ismmansubtitle =
        arrayOf("Lane Lexicon", "Hans Weir", "English lughat", "Urdu Lughat", "")
    private val ismmarfutitle =
        arrayOf("Lane Lexicon", "Hans Weir", "English lughat", "Urdu Lughat", "")
    private val mazeedentitlesmajzoom = arrayOf(
        "Lane Lexicon",
        "Hans Weir",
        "English lughat",
        "Urdu Lughat",
        "Jussive",
        "Verb Conjugaton",
        "Active/Passive Participle"
    )
    private val mazeedimperative = arrayOf(
        "Lane Lexicon",
        "Hans Weir",
        "English lughat",
        "Urdu Lughat",
        "Imperative",
        "Jussive",
        "Verb Conjugaton",
        "Active/Passive Participle"
    )
    private val mujarradimperative = arrayOf(
        "Lane Lexicon", "Hans Weir", "English lughat", "Urdu Lughat", "Imperative", "Jussive",
        "Verb Conjugaton", "Active/Passive PCPL", "N. Instrument", "N.Place/Time"
    )
    private val mazeedentitlesmansub = arrayOf(
        "Lane Lexicon",
        "Hans Weir",
        "English lughat",
        "Urdu Lughat",
        "Subjunctive",
        "Verb Conjugaton",
        "Active/Passive Participle"
    )
    private val getMazeedentitles = arrayOf(
        "Lane Lexicon",
        "Hans Weir",
        "English lughat",
        "Urdu Lughat",
        "Verb Conjugaton",
        "Active/Passive Participle"
    )
    private val languages = arrayOfNulls<String>(10)
    var dataBundle: Bundle? = null
    private var isUnaugmentedWazan = false
    private var isAugmentedWazan = false
    private var isnoconjugation = false
    private var isonlyarabicword = false
    private var isVerbMajzoom = false
    private var isVerbMansub = false
    private var isVerbEmpha = false
    private var isnoun = false
    private var isHarf = false
    private var nouncase: String? = null
    private var isIsmMarfu = false
    private var isIsmMansub = false
    private var isIsmMajroor = false
    private var isimperative = false
    private var isdictionary = false
    private var isVerbMarfu = false
    private var isparticple = false
    private var ismujarrad = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtabs)
        //   setContentView(R.layout.activity_tabs);
        val callButton = findViewById<FloatingTextButton>(R.id.action_button)
        callButton.setOnClickListener { view: View? ->
            // viewPager.adapters=null;
            finish()
        }
        shared = androidx.preference.PreferenceManager.getDefaultSharedPreferences(
            QuranGrammarApplication.context!!
        )
        val sharedpref = SharedPref(this@LughatWordDetailsAct)
        val fm = supportFragmentManager
        val sa: ViewStateAdapter = ViewStateAdapter(fm, lifecycle)
        val viewPager = findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = sa
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        dataBundle = Bundle()
        val bundle = intent.extras
        //  bundle.getParcelableArray("dictionary");
        var conjugationroot = bundle!!.getString(Constant.QURAN_VERB_ROOT)
        val vocubaluryroot = bundle.getString(Constant.QURAN_VERB_ROOT)
        val verbformthulathi = bundle.getString(Constant.QURAN_VERB_WAZAN)
        val ss = conjugationroot!!.replace("[\\[\\]]".toRegex(), "")
        val verbroot = ss.replace("[,;\\s]".toRegex(), "")
        val starts = conjugationroot.indexOf(ArabicLiterals.LALIF)
        val hamza = "ء"
        if (starts != -1) {
            conjugationroot =
                conjugationroot.replace(ArabicLiterals.LALIF, hamza.trim { it <= ' ' })
        }
        if (bundle.getString("nouncase") != null) {
            nouncase = bundle.getString(Constant.NOUNCASE)
            isnoun = true
            when (nouncase) {
                "NOM" -> isIsmMarfu = true
                "ACC" -> isIsmMansub = true
                "GEN" -> isIsmMajroor = true
            }
        }
        val verbform: String? = bundle.getString(Constant.QURAN_VERB_WAZAN)
        val verbmood: String? = bundle.getString(Constant.VERBMOOD)
        val verbtype: String? = bundle.getString(Constant.VERBTYPE)
        val arabicword: String? = bundle.getString("arabicword")
        isdictionary = bundle.getBoolean("dictionary")
        isimperative = bundle.getBoolean(Constant.IMPERATIVE, false)
        isparticple = bundle.getBoolean(Constant.ISPARTICPLE, false)
        val isharfnasab = bundle.getBoolean(Constant.ACCUSATIVE, false)
        val isdem = bundle.getBoolean(Constant.DEMONSTRATIVE, false)
        val isrelative = bundle.getBoolean(Constant.RELATIVE, false)
        val isShart = bundle.getBoolean(Constant.CONDITIONAL, false)
        val isprep = bundle.getBoolean(Constant.PREPOSITION, false)
        isHarf = isShart == isrelative == isharfnasab == isprep == isdem
        try {
            when (verbmood) {
                "Jussive" -> isVerbMajzoom = true
                "Subjunctive" -> isVerbMansub = true
                "Indicative" -> isVerbMarfu = true
                "Emphasized"->isVerbEmpha=true
            }
        } catch (e: NullPointerException) {
            println(e.message)
        }
        ismujarrad = verbtype != null && verbtype == "mujarrad"
        if (isdictionary) {
            dataBundle!!.putString(Constant.QURAN_VERB_ROOT, verbroot)
        } else if (arabicword != null && arabicword.isEmpty()) {
            try {
                dataBundle!!.putString(Constant.QURAN_VERB_ROOT, verbroot)
                dataBundle!!.putString(Constant.QURAN_VERB_WAZAN, verbform)
                dataBundle!!.putString(Constant.VERBMOOD, verbmood)
                dataBundle!!.putString(Constant.VERBTYPE, verbtype)
                if (bundle.getString("nouncase") != null) {
                    dataBundle!!.putString(Constant.NOUNCASE, nouncase)
                }
                assert(verbtype != null)
                if (verbtype == "mujarrad") {
                    isUnaugmentedWazan = true
                } else if (verbtype == "mazeed") {
                    isAugmentedWazan = true
                } else {
                    isnoconjugation = true
                }
            } catch (e: Exception) {
                dataBundle!!.putString(Constant.QURAN_VOCUBALORY_ROOT, vocubaluryroot)
                dataBundle!!.putString(Constant.QURAN_VERB_WAZAN, verbformthulathi)
                dataBundle!!.putSerializable(Constant.QURAN_VERB_ROOT, conjugationroot)
                isUnaugmentedWazan = true
            }
        } else {
            isonlyarabicword = true
            dataBundle!!.putString("arabicword", arabicword)
            dataBundle!!.putString(Constant.QURAN_VERB_WAZAN, "")
            dataBundle!!.putSerializable(Constant.QURAN_VERB_ROOT, "")
            isnoconjugation = false
        }
        // Up to here, we have working scrollable pages
        if (isimperative) {
            languages[0] = "lanes"
            languages[1] = "hans"
            languages[2] = "english"
            languages[3] = "urdu"
            languages[4] = "imperative"
            languages[5] = "Jussive"
            if (isAugmentedWazan) {
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = mazeedimperative[position]
                }.attach()
            } else if (ismujarrad) {
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = mujarradimperative[position]
                }.attach()
            }
        } else if (ismujarrad && isparticple && (isIsmMajroor || isIsmMansub || isIsmMarfu)) {
            languages[0] = "lanes"
            languages[1] = "hans"
            languages[2] = "english"
            languages[3] = "urdu"
            if (isIsmMajroor) {
                languages[4] = "genetivenoun"
                mujarradparticple[4] = "Genitive.Noun"
            } else if (isIsmMansub) {
                languages[4] = "accusativenoun"
                mujarradparticple[4] = "Accusative.Noun"
            } else if (isIsmMarfu) {
                languages[4] = "nominativenoun"
                mujarradparticple[4] = "Nominative.Noun"
            }
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = mujarradparticple[position]
            }.attach()
        } else if (isAugmentedWazan && isparticple && (isIsmMajroor || isIsmMansub || isIsmMarfu)) {
            languages[0] = "lanes"
            languages[1] = "hans"
            languages[2] = "english"
            languages[3] = "urdu"
            languages[4] = "genetivenoun"
            if (isIsmMajroor) {
                languages[4] = "genetivenoun"
                mazeedparticpletitle[4] = "Genitive.Noun"
            } else if (isIsmMansub) {
                languages[4] = "accusativenoun"
                mazeedparticpletitle[4] = "Accusative.Noun"
            } else if (isIsmMarfu) {
                languages[4] = "nominativenoun"
                mazeedparticpletitle[4] = "Nominative.Noun"
            }
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = mazeedparticpletitle[position]
            }.attach()
        } else if (isIsmMajroor || isIsmMansub || isIsmMarfu) {
            languages[0] = "lanes"
            languages[1] = "hans"
            languages[2] = "english"
            languages[3] = "urdu"
            languages[4] = "genetivenoun"
            mujarradparticple[4] = "Genitive"
            if (isIsmMajroor) {
                languages[4] = "genetivenoun"
                ismmansubtitle[4] = "Genitive.Noun"
            } else if (isIsmMansub) {
                languages[4] = "accusativenoun"
                ismmansubtitle[4] = "Accusative.Noun"
            } else if (isIsmMarfu) {
                languages[4] = "nominativenoun"
                ismmansubtitle[4] = "Nominative.Noun"
            }
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = ismmansubtitle[position]
            }.attach()
        } else if (isIsmMansub) {
            languages[0] = "lanes"
            languages[1] = "hans"
            languages[2] = "english"
            languages[3] = "urdu"
            languages[4] = "accusativenoun"
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = ismmansubtitle[position]
            }.attach()
        } else if (isIsmMarfu) {
            languages[0] = "lanes"
            languages[1] = "hans"
            languages[2] = "english"
            languages[3] = "urdu"
            languages[4] = "nominativenoun"
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = ismmarfutitle[position]
            }.attach()
        } else if (ismujarrad) {
            if (SharedPref.language == "en" && verbmood == "Jussive") {
                languages[0] = "lanes"
                languages[1] = "hans"
                languages[2] = "english"
                languages[3] = "urdu"
                languages[4] = "Jussive"
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = thulathientitlesmajzoom[position]
                }.attach()
            } else if (SharedPref.language == "en" && verbmood == "Subjunctive") {
                languages[0] = "lanes"
                languages[1] = "hans"
                languages[2] = "english"
                languages[3] = "urdu"
                languages[4] = "Subjunctive"
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = thulathientitlesmansub[position]
                }.attach()
            } else if (SharedPref.language == "en" && (verbmood == "Indicative" || verbmood=="Emphasized")) {
                languages[0] = "lanes"
                languages[1] = "hans"
                languages[2] = "english"
                languages[3] = "urdu"
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = thulathientitles[position]
                }.attach()
            }
        } else if (isAugmentedWazan) {


            if (SharedPref.language == "en" && verbmood == "Jussive") {
                languages[0] = "lanes"
                languages[1] = "hans"
                languages[2] = "english"
                languages[3] = "urdu"
                languages[4] = "Jussive"
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = mazeedentitlesmajzoom[position]
                }.attach()
            } else if (SharedPref.language == "en" && verbmood == "Subjunctive") {
                languages[0] = "lanes"
                languages[1] = "hans"
                languages[2] = "english"
                languages[3] = "urdu"
                languages[4] = "Subjunctive"
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = mazeedentitlesmansub[position]
                }.attach()
            } else {
                TabLayoutMediator(
                    tabLayout, viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = getMazeedentitles[position]
                }.attach()
                languages[0] = "lanes"
                languages[1] = "hans"
                languages[2] = "english"
                languages[3] = "urdu"
            }
        } else if (isonlyarabicword && isrelative) {
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = arabicwordharfnasb[position]
            }.attach()
            languages[0] = "english"
            languages[1] = "urdu"
            languages[2] = "relative"
        } else if (isonlyarabicword && isdem) {
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = arabicwordharfnasb[position]
            }.attach()
            languages[0] = "english"
            languages[1] = "urdu"
            languages[2] = "demonstrative"
        } else if (isonlyarabicword && isharfnasab) {
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = arabicwordharfnasb[position]
            }.attach()
            languages[0] = "english"
            languages[1] = "urdu"
            languages[2] = "accusative"
        } else if (isonlyarabicword && isShart) {
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = arabicwordharfnasb[position]
            }.attach()
            languages[0] = "english"
            languages[1] = "urdu"
            languages[2] = "conditonal"
        } else if (isonlyarabicword && isprep) {
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = arabicwordharfnasb[position]
            }.attach()
            languages[0] = "english"
            languages[1] = "urdu"
            languages[2] = "preposition"
        } else if (isnoconjugation) {
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = vocabularytitles[position]
            }.attach()
            languages[0] = "lanes"
            languages[1] = "hans"
            languages[2] = "english"
            languages[3] = "urdu"
        } else if (isdictionary) {
            TabLayoutMediator(
                tabLayout, viewPager
            ) { tab: TabLayout.Tab, position: Int ->
                tab.text = dictionarytitle[position]
            }.attach()
            languages[0] = "lanes"
            languages[1] = "hans"
        } else {
            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show()
            finish()
        }
        // Now we have tabs, NOTE: I am hardcoding the order, you'll want to do something smarter
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        // And now we have tabs that, when clicked, navigate to the correct page
    }

    private inner class ViewStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun createFragment(position: Int): Fragment {
            if (isdictionary) {
                return getdictionary(position)
            } else if (ismujarrad && isparticple && (isIsmMajroor || isIsmMansub || isIsmMarfu)) {
                return getMujarradParticiple(position)
            } else if (ismujarrad && (isVerbMajzoom || isVerbMansub||isVerbEmpha)) {
                return getMujarradMajzoomOrMansub(position)
            } else if (ismujarrad && isVerbMarfu && !isimperative) {
                return getMujarradMarfuu(position)
            } else if (isAugmentedWazan && (isVerbMajzoom || isimperative)) {
                return getMazeedMajzoomOrMarfuImp(position)
            } else if (ismujarrad && (isVerbMajzoom || isimperative)) {
                return getMujarradImperative(position)
            } else if (isAugmentedWazan && (isVerbMajzoom || isVerbMansub||isVerbEmpha)) {
                return getMazeedMajzoomOrMarfu(position)
            } else if (isAugmentedWazan && isVerbMarfu) {
                return getMazeedMarfu(position)
            } else if (isIsmMajroor || isIsmMansub || isIsmMarfu) {
                return getMujarradMajzoomOrMansub(position)
            } else if (isonlyarabicword && isHarf) //isShart == isrelative == isharfnasab == isprep == isdem;
            {
                return getHarf(position)
            }
            return getMujarradMajzoomOrMansub(position)
        }

        private fun getdictionary(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())

                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            }
            /*     val fragv = FragmentVerb()
                 fragv.arguments = dataBundle
                 return fragv.newInstance()*/
            return TODO("Provide the return value")
        }

        private fun getMujarradParticiple(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 3) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[3].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 4) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[4].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 5) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 6) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            } else if (position == 7) {
                val fragvsi = FragmentIsmIsmAla()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            } else if (position == 8) {
                val fragvsi = FragmentIsmZarf()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            //return fragv.newInstance()
            return TODO("Provide the return value")
        }

        private fun getMujarradMarfuu(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 3) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[3].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 4) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 5) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            } else if (position == 6) {
                val fragvsi = FragmentIsmIsmAla()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            } else if (position == 7) {
                val fragvsi = FragmentIsmZarf()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            return fragv.newInstance()

        }

        private fun getMujarradMajzoomOrMansub(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 3) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[3].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 4) {
                //jussive/sujunnctive/marfu/mansub/majroor
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[4].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 5) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 6) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            } else if (position == 7) {
                val fragvsi = FragmentIsmIsmAla()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            } else if (position == 8) {
                val fragvsi = FragmentIsmZarf()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            return fragv.newInstance()

        }

        private fun getMazeedMarfu(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 3) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[3].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 4) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 5) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            return fragv.newInstance()

        }

        private fun getHarf(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            }
            /* val fragv = FragmentVerb()
             fragv.arguments = dataBundle
             return fragv.newInstance()*/
            return TODO("Provide the return value")
        }

        private fun getMujarradImperative(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 3) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[3].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 4) {
                //jussive/sujunnctive/marfu/mansub/majroor
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[4].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 5) {
                // org.sj.conjugator.fragments.FragmentVerb fragv = new org.sj.conjugator.fragments.FragmentVerb();
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[5].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } /*else if (position == 6) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 7) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            } else if (position == 8) {
                val fragvsi = FragmentIsmIsmAla()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            } else if (position == 9) {
                val fragvsi = FragmentIsmZarf()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            return fragv.newInstance()*/
            return TODO("Provide the return value")
        }

        private fun getMazeedMajzoomOrMarfuImp(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 3) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[3].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 4) {
                //jussive/sujunnctive/marfu/mansub/majroor
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[4].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 5) {
                // org.sj.conjugator.fragments.FragmentVerb fragv = new org.sj.conjugator.fragments.FragmentVerb();
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[5].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } /*else if (position == 6) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 7) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            return fragv.newInstance()*/
            return TODO("Provide the return value")
        }

        private fun getMazeedMajzoomOrMarfu(position: Int): Fragment {
            if (position == 0) {
                //hanes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[0].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                //kabes
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[1].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                //english
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[2].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 3) {
                //urudu
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[3].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 4) {
                //jussive/sujunnctive/marfu/mansub/majroor
                val fragv = Dictionary_frag(this@LughatWordDetailsAct, languages[4].toString())
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } /*else if (position == 5) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 6) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            return fragv.newInstance()*/
            return TODO("Provide the return value")
        }

        override fun getItemCount(): Int {
            if (isdictionary) {
                return 2
            } else if (isimperative && isAugmentedWazan) {
                return 8
            } else if (isimperative && ismujarrad) {
                return 10
            } else if (isAugmentedWazan && isparticple) {
                return 7
            } else if (ismujarrad && isparticple) {
                return 9
            } else if (isnoun) {
                return NUM_PAGES_ISMMARFU
            }
            return if (isHarf) {
                NUM_PAGES_ARABICWORD_HARFNASB
            } else if (isUnaugmentedWazan && (isVerbMajzoom || isVerbMansub)) {
                NUM_PAGES_THULATHI_MAJZOOMMANSUB
            } else if (isUnaugmentedWazan) {
                NUM_PAGES_THULATHI
            } else if (isAugmentedWazan && (isVerbMansub || isVerbMajzoom)) {
                NUM_PAGES_MAZEED_MAJZOOMMANSUB
            } else if (isAugmentedWazan) {
                NUM_PAGES_MAZEED
            } else if (isnoconjugation) {
                NUM_PAGES_VOCABULARY
            } else {
                0
            }
        }
    }

    companion object {
        private const val NUM_PAGES_THULATHI = 8
        private const val NUM_PAGES_THULATHI_MAJZOOMMANSUB = 9
        private const val NUM_PAGES_MAZEED = 6
        private const val NUM_PAGES_MAZEED_MAJZOOMMANSUB = 7
        private const val NUM_PAGES_VOCABULARY = 4
        private const val NUM_PAGES_ARABICWORD_HARFNASB = 3
        private const val NUM_PAGES_ISMMARFU = 5
    }
}
