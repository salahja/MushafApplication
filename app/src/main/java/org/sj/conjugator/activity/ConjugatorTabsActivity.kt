package org.sj.conjugator.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.Constant.QURAN_VERB_ROOT
import com.example.Constant.QURAN_VERB_WAZAN
import com.example.Constant.VERBMOOD
import com.example.Constant.VERBTYPE
import com.example.mushafconsolidated.R
import com.example.utility.QuranGrammarApplication
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import org.sj.conjugator.fragments.FragmentIsmIsmAla
import org.sj.conjugator.fragments.FragmentIsmZarf
import org.sj.conjugator.fragments.FragmentIsmfaelIsmMafools
import org.sj.conjugator.fragments.FragmentVerb
import org.sj.conjugator.fragments.MazeedTabSagheerFragmentVerb
import org.sj.conjugator.utilities.ArabicLiterals
import org.sj.conjugator.utilities.SharedPref
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class ConjugatorTabsActivity :  BaseActivity() {
    // Arrey of strings FOR TABS TITLES
    private val thulathientitles = arrayOf(
        "Sarf Sagheer",
        "Verb Conjugaton",
        "Active/Passive PCPL",
        "N.Of Instrument",
        "N.Place/Time"
    )
    private val thulathiartitles = arrayOf(
        "صرف صغير",
        "تصريف الأفعال ",
        "لاسم الفاعل/الاسم المفعول",
        "الاسم الآلة",
        "الاسم الظرف"
    )
    private val mazeedentitles =
        arrayOf("Sarf Sagheer", "Verb Conjugaton", "Active/Passive Participle")
    private val mazeedartitles = arrayOf("صرف صغير", "تصريف الأفعال ", "لاسم الفاعل/الاسم المفعول")
    var dataBundle: Bundle? = null
    private var ismujarrad = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newtabs)
        val callButton: FloatingTextButton = findViewById(R.id.action_button)
        callButton.setOnClickListener { view: View? ->
            // viewPager.adapters=null;
            finish()
        }
        val fm: FragmentManager = getSupportFragmentManager()
        val sa: ViewStateAdapter = ViewStateAdapter(fm, lifecycle)
        val viewPager: ViewPager2 = findViewById(R.id.pager)
        viewPager.adapter = sa
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        dataBundle = Bundle()
        val bundle: Bundle? = getIntent().getExtras()
        val root = bundle?.getString(QURAN_VERB_ROOT)
        val ss = root!!.replace("[\\[\\]]".toRegex(), "")
        var verbroot = ss.replace("[,;\\s]".toRegex(), "")
        val starts = verbroot.indexOf(ArabicLiterals.LALIF)
        if (starts != -1) {
            verbroot = verbroot.replace(ArabicLiterals.LALIF, "ء")
        }
        var verbform: String? = null
        var verbmood: String? = null
        var verbtype: String? = null
        if (bundle != null) {
            verbform = bundle.getString(QURAN_VERB_WAZAN)
            verbmood = bundle.getString(VERBMOOD)
            verbtype = bundle.getString(VERBTYPE)
        }
        ismujarrad = verbtype == "mujarrad"
        dataBundle!!.putSerializable(QURAN_VERB_ROOT, verbroot)
        dataBundle!!.putString(QURAN_VERB_WAZAN, verbform)
        dataBundle!!.putString(VERBMOOD, verbmood)
        dataBundle!!.putString(VERBTYPE, verbtype)
        val sharedPref = SharedPref(
            QuranGrammarApplication.context!!
        )

        // Up to here, we have working scrollable pages
        if (ismujarrad) {
            if (SharedPref.language.equals("en")) {
                TabLayoutMediator(
                    tabLayout,
                    viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = thulathientitles[position]
                }.attach()
            } else {
                TabLayoutMediator(
                    tabLayout,
                    viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = thulathiartitles[position]
                }.attach()
            }
        } else {
            if (SharedPref.language.equals("en")) {
                TabLayoutMediator(
                    tabLayout,
                    viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = mazeedentitles[position]
                }.attach()
            } else {
                TabLayoutMediator(
                    tabLayout,
                    viewPager
                ) { tab: TabLayout.Tab, position: Int ->
                    tab.text = mazeedartitles[position]
                }.attach()
            }
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
            // Hardcoded in this order, you'll want to use lists and make sure the titles match
            if (position == 0) {
                val fragv = MazeedTabSagheerFragmentVerb(this@ConjugatorTabsActivity)
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 1) {
                val fragv = FragmentVerb()
                fragv.arguments = dataBundle
                return fragv.newInstance()
            } else if (position == 2) {
                val fragvs = FragmentIsmfaelIsmMafools()
                fragvs.arguments = dataBundle
                return fragvs.newInstance()
            } else if (position == 3) {
                val fragvsi = FragmentIsmIsmAla()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            } else if (position == 4) {
                val fragvsi = FragmentIsmZarf()
                fragvsi.arguments = dataBundle
                return fragvsi.newInstance()
            }
            val fragv = FragmentVerb()
            fragv.arguments = dataBundle
            return fragv.newInstance()
        }

        override fun getItemCount(): Int {
            return if (ismujarrad) {
                NUM_PAGES_THULATHI
            } else {
                NUM_PAGES_MAZEED
            }
        }
    }

    companion object {
        private const val NUM_PAGES_THULATHI = 5
        private const val NUM_PAGES_MAZEED = 3
    }
}