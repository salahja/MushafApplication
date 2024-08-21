package com.example.mushafconsolidated.fragments


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Constant
import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.Adapters.BookmarksShowAdapter
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.example.utility.QuranGrammarApplication
import com.example.utility.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar


/**
 * Created by Dev. M. Hussein on 5/9/2017.
 */
class PinsFragment constructor() : Fragment() {
    var coordinatorLayout: CoordinatorLayout? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    private var bookmarksShowAdapter: BookmarksShowAdapter? = null
    private lateinit var mRecview: RecyclerView
    val vmodel: QuranVIewModel by viewModels()
    public override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        //    View rootView = inflater.inflate(R.layout.activity_collection, container, false);
        val view: View = inflater.inflate(R.layout.fragment_bookmark, container, false)
        val utils: Utils = Utils(activity)

        val vmodel: QuranVIewModel by viewModels()
        bookmarksShowAdapter = BookmarksShowAdapter(activity)
        vmodel.getBookmarks().observe(viewLifecycleOwner, Observer {

            mRecview = view.findViewById(R.id.recyclerViewAdapterTranslation)
            coordinatorLayout = view.findViewById(R.id.coordinatorLayoutbookmark)
            layoutManager = LinearLayoutManager(QuranGrammarApplication.context)
            mRecview.layoutManager = layoutManager
            //  bookmarksShowAdapter!!.setBookMarkArrayList(bookMarksNew)
            bookmarksShowAdapter!!.bookMarkArrayList = it
            mRecview.adapter = bookmarksShowAdapter
            enableSwipeToDeleteAndUndo()

        })

        return view
    }

    private fun enableSwipeToDeleteAndUndo() {
        val swipeToDeleteCallback: SwipeToDeleteCallback =
            object : SwipeToDeleteCallback(activity) {
                public override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                    val position: Int = viewHolder.adapterPosition
                    /*  val item: BookMarks? =
                          bookmarksShowAdapter!!.getBookMarkArrayList()?.get(position)*/
                    val item: BookMarks? = bookmarksShowAdapter!!.getItem(position)
                    //   final int code = item.hashCode();
                    bookmarksShowAdapter!!.getItemId(position)
                    bookmarksShowAdapter!!.removeItem(position)
                    val snackbar: Snackbar = Snackbar
                        .make(
                            (coordinatorLayout)!!,
                            "Item was removed from the list.",
                            Snackbar.LENGTH_LONG
                        )
                    snackbar.setAction(
                        "UNDO",
                        View.OnClickListener { view: View? -> mRecview!!.scrollToPosition(position) }
                    )
                    snackbar.setActionTextColor(Color.CYAN)
                    snackbar.show()
                    bookmarksShowAdapter!!.bookChapterno
                    //    Utils.deleteBookMarks(item);
                    if (item != null) {
                        vmodel.deletebookmark(item)
                    }
                    //Utils.Companion.deleteBookmark(item)
                }
            }
        val itemTouchhelper: ItemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(mRecview)
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookmarksShowAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val bmark: BookMarks = bookmarksShowAdapter!!.getItem(position) as BookMarks
                val dataBundle: Bundle = Bundle()
                bmark.chapterno.let { dataBundle.putInt(Constant.SURAH_ID, it.toInt()) }
                bmark.verseno.let { dataBundle.putInt(Constant.AYAHNUMBER, it.toInt()) }
                dataBundle.putString(Constant.SURAH_ARABIC_NAME, bmark.surahname)
                val readingintent: Intent = Intent(activity, QuranGrammarAct::class.java)
                readingintent.putExtra(Constant.MUFRADATFRAGTAG, false)
                bmark.chapterno.let { readingintent.putExtra(Constant.CHAPTER, it.toInt()) }
                bmark.verseno.let { readingintent.putExtra(Constant.AYAH_ID, it.toInt()) }
                readingintent.putExtra(Constant.CHAPTERORPART, true)
                readingintent.putExtra(Constant.SURAH_ARABIC_NAME, bmark.surahname)
                readingintent.putExtra(Constant.WBW, true)
                startActivity(readingintent)
            }
        })
    }
}