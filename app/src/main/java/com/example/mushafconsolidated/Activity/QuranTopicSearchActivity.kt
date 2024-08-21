package com.example.mushafconsolidated.Activity

import android.app.SearchManager
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.Adapters.QuranTopicSearchAdapter
import com.example.mushafconsolidated.Entities.quranexplorer
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.R.drawable
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.fragments.TopicDetailsFrag
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListenerOnLong
import com.google.android.material.floatingactionbutton.FloatingActionButton

class QuranTopicSearchActivity : BaseActivity(), OnItemClickListenerOnLong {
    private lateinit var btnSelection: FloatingActionButton
    private var searchDownloadAdapter: QuranTopicSearchAdapter? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                var result: String? = null
                if (data != null) {
                    result = data.getStringExtra("result")
                }

            }

        }
    }

    override fun onResume() {
        super.onResume()
        btnSelection.show()
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(R.string.toolbar_title)
        /*      SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.getContext());
        String isNightmode = shared.getString("theme", "dark");
        final int color = ContextCompat.getColor(this, R.color.color_background_overlay);
        final int colorsurface = ContextCompat.getColor(this, R.color.colorAccent);
        final int coloronbackground = ContextCompat.getColor(this, R.color.neutral0);
        if (isNightmode.equals("dark") || isNightmode.equals("blue")) {
            toolbar.setBackgroundColor(coloronbackground);
            toolbar.setBackgroundColor(color);+
        } else {
            toolbar.setBackgroundColor(colorsurface);
        }*/
        val utils = Utils(this)
        btnSelection = findViewById(R.id.btnShow)
        //     searchDownloadAdapter = new DownloadSearchAdapter(this,translationEntity -> {});
        val qurandictionaryArrayList = utils.topicSearchAll as ArrayList<quranexplorer>?
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        searchDownloadAdapter =
            QuranTopicSearchAdapter(this@QuranTopicSearchActivity, qurandictionaryArrayList)
        whiteNotificationBar(recyclerView)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        //   recyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        recyclerView.adapter = searchDownloadAdapter
        btnSelection.setOnClickListener(View.OnClickListener { v: View? ->
            val data = StringBuilder()
            val titles = StringBuilder()
            val stList = (searchDownloadAdapter as QuranTopicSearchAdapter).list
            val datas = HashMap<String, String?>()
            for (i in stList.indices) {
                val selectedlist = stList[i]
                if (selectedlist.isSelected) {
                    datas[selectedlist.title] = selectedlist.ayahref
                    data.append(selectedlist.ayahref).append(",")
                    titles.append(selectedlist.title).append(",")
                }
            }
            //  extracted(data, titles);
            val dataBundle = Bundle()

            if (data.isNotEmpty()) {

                btnSelection.hide()

                dataBundle.putSerializable("map", datas)

                val fragmentManagers = this.supportFragmentManager
                val transactions = fragmentManagers.beginTransaction()
                transactions.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                val fragvsi = TopicDetailsFrag.newInstance(dataBundle)
                fragvsi.arguments = dataBundle
                transactions.replace(R.id.frame_container, fragvsi)
                transactions.addToBackStack(null)
                transactions.commit()

            } else {
                Toast.makeText(this@QuranTopicSearchActivity, "Please Select", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        btnSelection.show()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.action_search)
            .actionView as SearchView?
        searchView?.setSearchableInfo(
            searchManager
                .getSearchableInfo(componentName)
        )
        searchView!!.maxWidth = Int.MAX_VALUE
        searchView.queryHint = "Type something…"
        val sear = ContextCompat.getDrawable(this, drawable.custom_search_box)
        searchView.clipToOutline = true
        searchView.setBackgroundDrawable(sear)
        searchView.gravity = View.TEXT_ALIGNMENT_CENTER
        searchView.maxWidth = Int.MAX_VALUE
        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                searchDownloadAdapter!!.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                searchDownloadAdapter!!.filter.filter(query)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if (id == R.id.action_search) {
            return true
        }
        if (id == R.id.backButtonView) {
            val rintent = Intent(this@QuranTopicSearchActivity, QuranGrammarAct::class.java)
            startActivity(rintent)
            finish()
        }
        val rintent = Intent(this@QuranTopicSearchActivity, QuranGrammarAct::class.java)
        startActivity(rintent)
        finish()
        //return super.onOptionsItemSelected(item);
        return true
    }

    private fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }

    override fun onItemClick(v: View, position: Int) {
        Toast.makeText(this, "onItemCLick", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(position: Int, v: View) {}

    companion object {
        private const val LAUNCH_SECOND_ACTIVITY = 1
    }
}