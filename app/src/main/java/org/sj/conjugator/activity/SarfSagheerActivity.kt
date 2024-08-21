package org.sj.conjugator.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant.VERBTYPE
import com.example.mushafconsolidated.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.sj.conjugator.fragments.RulesBottomSheet
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class SarfSagheerActivity : BaseActivity() {
    var recyclerView: RecyclerView? = null
    private var verbtype: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sarfsagheer)
        //  bottomNavigationView = findViewById(R.id.bottomNavView);
        val rootText = "ضرب"
        val bundle: Bundle? = getIntent().getExtras()
        if (bundle != null) {
            verbtype = bundle.getString(VERBTYPE)
        }
        val callButton: FloatingTextButton = findViewById(R.id.sagheeractivtyfloat)
        callButton.setOnClickListener {
            super@SarfSagheerActivity.finish()
            val conjugatorintent = Intent(this@SarfSagheerActivity, ConjugatorAct::class.java)
            // finish();
            startActivity(conjugatorintent)
            //  Snackbar.make(viewById, "Call button clicked", Snackbar.LENGTH_SHORT).show();
        }
        val item = RulesBottomSheet()
        //    item.setdata(rootWordMeanings,wbwRootwords,grammarRootsCombined);
        val fragmentManager: FragmentManager = getSupportFragmentManager()
        val TAG = "bottom"
        val data = arrayOf(verbtype)
        val transactions = fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.abc_slide_in_top, android.R.anim.fade_out)
        //   transactions.show(item);
        RulesBottomSheet.newInstance(data).show(getSupportFragmentManager(), RulesBottomSheet.TAG)
    }
}