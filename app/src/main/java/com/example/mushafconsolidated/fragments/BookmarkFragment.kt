package com.example.mushafconsolidated.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.Constant.AYAHNUMBER
import com.example.Constant.AYAH_ID
import com.example.Constant.CHAPTER
import com.example.Constant.CHAPTERORPART
import com.example.Constant.MUFRADATFRAGTAG
import com.example.Constant.SURAH_ARABIC_NAME
import com.example.Constant.SURAH_ID
import com.example.Constant.WBW
import com.example.mushafconsolidated.Activity.QuranGrammarAct
import com.example.mushafconsolidated.Adapters.BookmarksShowAdapter
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

internal class BookmarkFragment : Fragment() {
    var coordinatorLayout: CoordinatorLayout? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    private var bookmarksShowAdapter: BookmarksShowAdapter? = null
    private val mRecview: RecyclerView? = null
    private lateinit var mViewPager: ViewPager2
    private val listView: ListView? = null
    private var mediator: TabLayoutMediator? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view: View = inflater.inflate(R.layout.bookmark_frag_tablayout, container, false)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabs)
        val lifecycle = viewLifecycleOwner.lifecycle
        val fm = requireActivity().supportFragmentManager
        val mSectionsPagerAdapter = ViewStateAdapter(fm, lifecycle)


        //  mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = view.findViewById<ViewPager2>(R.id.container)
        mViewPager.adapter = mSectionsPagerAdapter
        mediator = TabLayoutMediator(tabLayout, mViewPager,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.setText(
                    TAB_TITLES[position]
                )
            })
        mediator!!.attach()
        //    new TabLayoutMediator(tabLayout, mViewPager, (tab, position) -> tab.setText(TAB_TITLES[position])).attach();

        //clickable application title
        val applicationTitle = view.findViewById<View>(R.id.title) as TextView
        applicationTitle.text = getString(R.string.bookmarkst)
        val listener: OnItemClickListener? = null

        //  List<BookMarks> bookmarks = new DatabaseAccess().getBookmarks();
        bookmarksShowAdapter = BookmarksShowAdapter(activity)
        layoutManager = LinearLayoutManager(activity)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                mViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        return view
    }
    /*
        private fun enableSwipeToDeleteAndUndo() {
            val swipeToDeleteCallback: SwipeToDeleteCallback =
                object : SwipeToDeleteCallback(activity) {
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                        val position = viewHolder.adapterPosition
                        val item = bookmarksShowAdapter!!.bookMarkArrayList!![position]!!
                        //   final int code = item.hashCode();
                        bookmarksShowAdapter!!.getItemId(position)
                        bookmarksShowAdapter!!.removeItem(position)
                        val snackbar = Snackbar
                            .make(
                                coordinatorLayout!!,
                                "Item was removed from the list.",
                                Snackbar.LENGTH_LONG
                            )
                        snackbar.setAction("UNDO") { //     bookmarksShowAdapter.restoreItem(item, position);
                            mRecview!!.scrollToPosition(position)
                        }
                        snackbar.setActionTextColor(Color.CYAN)
                        snackbar.show()
                        val itemId = bookmarksShowAdapter!!.getItemId(position)
                        val bookmarkid = bookmarksShowAdapter!!.bookmarid
                        bookmarksShowAdapter!!.bookChapterno
                        //      bookmarksShowAdapter.getBookMarkArrayList(bookmarkid)
                        Utils.deleteBookMarks(item)
                    }
                }
            val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
            itemTouchhelper.attachToRecyclerView(mRecview)
        }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookmarksShowAdapter!!.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val bmark = bookmarksShowAdapter!!.getItem(position)
                //        ChaptersAnaEntity surah = (ChaptersAnaEntity) bookmarksShowAdapter.getItem(position);
                val dataBundle = Bundle()
                dataBundle.putInt(SURAH_ID, bmark!!.chapterno.toInt())
                dataBundle.putInt(AYAHNUMBER, bmark.verseno.toInt())
                dataBundle.putString(SURAH_ARABIC_NAME, bmark.surahname)
                val header = bmark.header
                var fragment: Fragment
                val readingintent = Intent(activity, QuranGrammarAct::class.java)
                readingintent.putExtra(MUFRADATFRAGTAG, false)
                readingintent.putExtra(CHAPTER, bmark.chapterno.toInt())
                readingintent.putExtra(AYAH_ID, bmark.verseno.toInt())
                readingintent.putExtra(CHAPTERORPART, true)
                readingintent.putExtra(SURAH_ARABIC_NAME, bmark.surahname)
                readingintent.putExtra(WBW, true)
                startActivity(readingintent)
            }
        })
    }

    private class ViewStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> PinsFragment()
                1 -> CollectionFrag()
                else -> PinsFragment()
            }
        }

        override fun getItemCount(): Int {
            return 2
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediator!!.detach()
        mediator = null
    }

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.pins, R.string.collection)
    }
}