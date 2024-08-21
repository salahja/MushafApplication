package database


import ImageItem
import android.app.SearchManager
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Activityimport.BaseActivity
import com.example.mushafconsolidated.R
import database.entity.AllahNames
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.fragments.NamesDetail
import sj.hisnul.newepository.NewDuaModel
import java.util.Objects

class NamesGridImageAct : BaseActivity() {
    private lateinit var gadapter: GridAdapter
    override fun onCreateView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(parent, name, context, attrs)
    }

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dua_group)
        val toolbar: Toolbar = findViewById(R.id.my_action_bar)
        setSupportActionBar(toolbar)
        /*     final int color = ContextCompat.getColor(this, R.color.color_background_overlay);
        final int colorsurface = ContextCompat.getColor(this, R.color.DarkGoldenrod);
        final int coloronbackground = ContextCompat.getColor(this, R.color.neutral0);
        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(QuranGrammarApplication.getContext());
        String isNightmode = shared.getString("theme", "dark");
        if (isNightmode.equals("dark") || isNightmode.equals("blue")) {
            toolbar.setBackgroundColor(coloronbackground);
            toolbar.setBackgroundColor(color);
        } else {
            toolbar.setBackgroundColor(colorsurface);
        }
*/Objects.requireNonNull(getSupportActionBar())?.setDisplayHomeAsUpEnabled(true)
        val recyclerView: RecyclerView = findViewById(R.id.duaListView)
        recyclerView.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                true
            )
        )
        //  gridView = (GridView) findViewById(R.id.gridView);
        val mLayoutManager = GridLayoutManager(this, 2)
        //  gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());

        val viewmodel: NewDuaModel by viewModels()
        viewmodel.getNames().observe(this, Observer {
    //    viewmodel.getNames().observe(this) {

            gadapter = GridAdapter(this, it, data)
            //  gridView.setAdapter(gridAdapter);
            recyclerView.setHasFixedSize(true)
            recyclerView.setLayoutManager(mLayoutManager)
            recyclerView.setAdapter(gadapter)
            gadapter.SetOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(v: View?, position: Int) {
                    val item1 = gadapter.getItem(position) as AllahNames
                    println(item1.arabic)
                    val item = NamesDetail()
                    val dataBundle = Bundle()
                    dataBundle.putInt(Constant.SURAH_ID, item1.id)
                    item.arguments = dataBundle
                    val data = item1.id
                    NamesDetail.newInstance(data).show(supportFragmentManager, NamesDetail.TAG)
                }
            })

        })


        /*
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());

                //Start details activity
                startActivity(intent);
            }
        }
       */
    }

    //    TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
    private val data: ArrayList<ImageItem>
        private get() {
            val imageItems: ArrayList<ImageItem> = ArrayList<ImageItem>()
            val imgs: TypedArray = getResources().obtainTypedArray(R.array.names_imgs)
            //    TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
            for (i in 0 until imgs.length()) {
                val bitmap: Bitmap =
                    BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1))
                imageItems.add(ImageItem(bitmap, "Image#$i"))
            }
            imgs.recycle()
            return imageItems
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.mainsearch, menu)
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView?
        if (searchView != null) {
            val sear: Drawable? = ContextCompat.getDrawable(this, R.drawable.custom_search_box)
            searchView.clipToOutline = true
            searchView.setBackgroundDrawable(sear)
            searchView.gravity = View.TEXT_ALIGNMENT_CENTER
            searchView.maxWidth = Int.MAX_VALUE
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()))
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String): Boolean {
                    gadapter.getFilter().filter(s)
                    return true
                }
            })
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.bookmark) {
        }
        return super.onOptionsItemSelected(item)
    }
}