package sj.hisnul.activity

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sj.hisnul.fragments.AllDuaFrag
import sj.hisnul.fragments.CatTwoFrag
import sj.hisnul.fragments.NewHisnulBookmarkFragment

//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class HisnulBottomACT : BaseActivity(), OnItemClickListenerOnLong {
    private lateinit var btnBottomSheet: FloatingActionButton

    private lateinit var bottomNavigationView: BottomNavigationView
/*    override fun onBackPressed() {
        val fragment: Fragment? = getSupportFragmentManager().findFragmentById(R.id.frame_container)
        if (fragment !is IOnBackPressed || !(fragment as IOnBackPressed).onBackPressed()) {
            super.onBackPressed()
        }

    }*/

    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        val shared: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(this@HisnulBottomACT)
        when (shared.getString("themePref", "dark")) {
            "white" -> switchTheme("white")
            "dark" -> switchTheme("dark")
            "blue" -> switchTheme("blue")
            "green" -> switchTheme("green")
            "brwon" -> switchTheme("brown")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hisnulbottom)
        android.preference.PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
        initView()
        initnavigation()
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
        val newCustomFragment: CatTwoFrag = CatTwoFrag.newInstance()
        transaction.replace(R.id.frame_container, newCustomFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun initnavigation() {
        btnBottomSheet = findViewById(R.id.fab)
        bottomNavigationView = findViewById(R.id.bottomNavView)
        btnBottomSheet.setOnClickListener({ v: View? -> toggleBottomSheets() })
        bottomNavigationView.setOnNavigationItemReselectedListener(
            { item: MenuItem ->
                var fragment: Fragment
                when (item.itemId) {
                    R.id.category -> {
                        //    materialToolbar.setTitle("Surah");
                        val fragmentManager: FragmentManager = supportFragmentManager
                        val transaction = fragmentManager.beginTransaction()
                        transaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                        val newCustomFragment: CatTwoFrag = CatTwoFrag.newInstance()
                        transaction.replace(R.id.frame_container, newCustomFragment)
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }

                    R.id.alldua -> {
                        val fragmentManagers: FragmentManager = supportFragmentManager
                        val transactions = fragmentManagers.beginTransaction()
                        transactions.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                        val ALLFAR: AllDuaFrag = AllDuaFrag.newInstance()
                        transactions.replace(R.id.frame_container, ALLFAR)
                        transactions.addToBackStack(null)
                        transactions.commit()
                    }

                    R.id.bookmark -> {
                        val fragmentManagerss: FragmentManager = supportFragmentManager
                        val transactionss = fragmentManagerss.beginTransaction()
                        transactionss.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                        val b: NewHisnulBookmarkFragment = NewHisnulBookmarkFragment.newInstance()
                        //     BookmarkFragment b = BookmarkFragment.newInstance();
                        transactionss.replace(R.id.frame_container, b)
                        transactionss.addToBackStack(null)
                        transactionss.commit()
                    }



                }
            })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun initView() {
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        btnBottomSheet = findViewById(R.id.fab)
    //    layoutBottomSheet = findViewById(R.id.bottom_sheet)
        //    btnBottomSheet=findViewById(R.id.btn_bottom_sheet);



        //  coordinatorLayout = findViewById(R.id.coordinatorLayout);
        val verlayViewRecyclerView: RecyclerView = findViewById(R.id.overlayViewRecyclerView)
        verlayViewRecyclerView.layoutManager = linearLayoutManager
        // bookmarkchip.setOnClickListener(v -> CheckStringLENGTHS());
    }

    private fun toggleBottomSheets() {
        if (bottomNavigationView.visibility == View.VISIBLE) {
            bottomNavigationView.visibility = View.GONE
            //    btnBottomSheet.setText("Close sheet");
        } else {
            bottomNavigationView.visibility = View.VISIBLE
            //    btnBottomSheet.setText("Expand sheet");
        }
    }

    override fun onDestroy() {
     //   context = null
        finish()
        super.onDestroy()
    }





    override fun onItemClick(v: View, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemLongClick(position: Int, v: View) {
        TODO("Not yet implemented")
    }
}