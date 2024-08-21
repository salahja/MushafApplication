package org.sj.conjugator

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceManager
import com.example.mushafconsolidated.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.sj.conjugator.activity.BaseActivity
import org.sj.conjugator.activity.ConjugatorAct
import org.sj.conjugator.fragments.SettingsFragmentVerb
import org.sj.conjugator.utilities.SharedPref

class MainActivity : BaseActivity() {
    var mazeedform = 0
    var mujarradListingbtn: Button? = null
    var mazeedlistingbtn: Button? = null
    var conjugatorbtn: Button? = null
    var settingbtn: Button? = null
    var qurangrammarbtn: Button? = null
    lateinit var btnBottomSheet: FloatingActionButton
    lateinit var layoutBottomSheet: RelativeLayout
    lateinit var sheetBehavior: BottomSheetBehavior<*>
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val shared = PreferenceManager.getDefaultSharedPreferences(this)
        val preferences = shared.getString("theme", "dark")
        if (preferences == "light") {
            switchTheme("light")
        } else if (preferences == "dark") {
            switchTheme("dark")
        } else if (preferences == "blue") {
            switchTheme("blue")
        } else if (preferences == "brown") {
            switchTheme("brown")
        }
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_verb_conjugation)
        //  setContentView(R.layout.main_activity);
        if (isFirstTime) {
            val fragment = SettingsFragmentVerb()
            val transaction = supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.add(R.id.frame_container, fragment)
            transaction.commit()
        }
        initView()
        SetupBOttomMenu()
        //  DatabaseUtils utils = new DatabaseUtils(this);
        //  ArrayList<Mazeed> mazeedAll = utils.getMazeedAll();
        //   createFile();
        /*
        MujarradVerbList fragments = new MujarradVerbList(MainActivity.this);
        FragmentTransaction transactions = getSupportFragmentManager().beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transactions.add(R.id.frame_container, fragments).addToBackStack("mujarrad");
        transactions.commit();
*/
        val conjugatorintent = Intent(this@MainActivity, ConjugatorAct::class.java)
        // finish();
        startActivity(conjugatorintent)
    }

    private fun checkPermission(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun SetupBOttomMenu() {
        settingbtn!!.setOnClickListener {
            val fragments = SettingsFragmentVerb()
            val transactions = supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transactions.replace(R.id.frame_container, fragments).addToBackStack("mujarrad")
            transactions.commit()
        }
        conjugatorbtn!!.setOnClickListener {
            val conjugatorintent = Intent(this@MainActivity, ConjugatorAct::class.java)
            // finish();
            startActivity(conjugatorintent)
        }
    }

    // first time
    private val isFirstTime: Boolean
        private get() {
            val preferences = getPreferences(MODE_PRIVATE)
            val ranBefore = preferences.getBoolean("RanBefore", false)
            if (!ranBefore) {
                // first time
                val editor = preferences.edit()
                editor.putBoolean("RanBefore", true)
                editor.apply()
            }
            return !ranBefore
        }

    private fun initView() {
        btnBottomSheet = findViewById(R.id.fab)
        mazeedlistingbtn = findViewById(R.id.mazeedlist)
        mujarradListingbtn = findViewById(R.id.conjugationnav)
        settingbtn = findViewById(R.id.conjugatorsetting)
        conjugatorbtn = findViewById(R.id.conjugator)
        layoutBottomSheet = findViewById(R.id.bottom_sheet)
        sheetBehavior = BottomSheetBehavior.from<View>(layoutBottomSheet)

        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet)
        //   navigationView = findViewById(R.id.navigationView);
        val materialToolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        //  BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        //    recyclerView = findViewById(R.id.sarfrecview);
        //   materialToolbar = findViewById(R.id.toolbar);
        if (materialToolbar != null) {
            setSupportActionBar(materialToolbar)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        //  materialToolbar.setNavigationIcon(R.mipmap.ic_ac_foreground);
        //  CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        //   setSupportActionBar(materialToolbar);
        //   getSupportActionBar().setTitle("");
        val pref = SharedPref(this)
        btnBottomSheet.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                toggleBottomSheet()
            }

            fun toggleBottomSheet() {
                if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                    //    btnBottomSheet.setText("Close sheet");
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                    //    btnBottomSheet.setText("Expand sheet");
                }
            }
        })
        sheetBehavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_COLLAPSED -> {}
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_SETTLING -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    companion object {
        const val TAG = "bottom"
        private const val PERMISSION_REQUEST_CODE = 100
        var lang: String? = null
    }
}