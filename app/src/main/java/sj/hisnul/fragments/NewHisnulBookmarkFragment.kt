package sj.hisnul.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.utility.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.adapter.NewHisnulBookmarksShowAdapter
import sj.hisnul.entity.hduanamesEnt
import sj.hisnul.newepository.NewDuaModel

/**
 * Bookmark fragment class
 */
class NewHisnulBookmarkFragment : Fragment(), AdapterView.OnItemClickListener {
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var bookmarksShowAdapter: NewHisnulBookmarksShowAdapter
    private lateinit var mRecview: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmark, container, false)
        val utils = Utils(activity)
        //    List<BookMarks> bookMarksNew = utils.getBookMarksNew();
        val bookmarked: ArrayList<hduanamesEnt> = ArrayList()
        val viewmodel: NewDuaModel by viewModels()
        //  List<BookMarks> bookmarks = new DatabaseAccess().getBookmarks();
        bookmarksShowAdapter = NewHisnulBookmarksShowAdapter(activity)
        mRecview = view.findViewById(R.id.recyclerViewAdapterTranslation)
  coordinatorLayout = view.findViewById(R.id.coordinatorLayoutbookmark)
        layoutManager = LinearLayoutManager(activity)
        mRecview.layoutManager=layoutManager

        //    bookmarksShowAdapter.setBookMarkArrayList((ArrayList<String>) bookmarstringarray);

        viewmodel.Duadetailsbychapter(1).observe(viewLifecycleOwner){
            // val dd: ArrayList<hduanames> = utils.getdualistbychapter(chap_id) as ArrayList<hduanames>
            bookmarksShowAdapter.bookMarkArrayList= it as ArrayList<hduanamesEnt>?
            mRecview.adapter=bookmarksShowAdapter
        }




        //    mRecview.setLayoutManager(new LinearLayoutManager(getActivity()));
        enableSwipeToDeleteAndUndo()
        return view
    }

    private fun enableSwipeToDeleteAndUndo() {
        val viewmodel: NewDuaModel by viewModels()
        val swipeToDeleteCallback: SwipeToDeleteCallback =
            object : SwipeToDeleteCallback(activity) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                    //    final int position = viewHolder.getAdapterPosition();
                    //  final String item = mAdapter.getData().get(position);
                    //   mAdapter.removeItem(position);
                    val position = viewHolder.adapterPosition
                    val item = bookmarksShowAdapter.bookMarkArrayList?.get(position)
                    //   final int code = item.hashCode();
                    bookmarksShowAdapter.getItemId(position)
                    bookmarksShowAdapter.removeItem(position)
                    val snackbar = Snackbar
                        .make(
                            coordinatorLayout,
                            "Item was removed from the list.",
                            Snackbar.LENGTH_LONG
                        )
                    snackbar.setAction("UNDO") { //     bookmarksShowAdapter.restoreItem(item, position);
                        mRecview.scrollToPosition(position)
                    }
                    snackbar.setActionTextColor(Color.CYAN)
                    snackbar.show()
                    val itemId = bookmarksShowAdapter.getItemId(position)
                    val bookmarkid = bookmarksShowAdapter.bookmarid
                    bookmarksShowAdapter.bookChapterno
                    //      bookmarksShowAdapter.getBookMarkArrayList(bookmarkid)
                    val butils = Utils(activity)
                    //       butils.deleteBookmarks(bookmarid);
                    if (item != null) {
                      //  butils.updateFav(0, item.chap_id)
                        viewmodel.update(0,item.chap_id)
                    }
                    //    Utils.deleteBookMarks(item);
                }
            }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(mRecview)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookmarksShowAdapter.SetOnItemClickListener(object : OnItemClickListener {


            override fun onItemClick(view: View?, position: Int) {
                val viewmodel: NewDuaModel by viewModels()
                val tag = view!!.tag
                val butils = Utils(activity)
                val id = tag == "id"
                val delete = tag == "delete"
                val bookid = bookmarksShowAdapter.getItem(position) as hduanamesEnt
                if (id) {
                    val bundle1 = Bundle()
                    val chap_id = bookid.chap_id
                    bundle1.putInt("chap_id", chap_id)
                    val fragmentManagers = activity!!.supportFragmentManager
                    val transactions = fragmentManagers.beginTransaction()
                    transactions.setCustomAnimations(R.anim.slide_down, R.anim.slide_up)
                    val fragvsi = DisplayFromBookMark.newInstance()
                    fragvsi.arguments = bundle1
                    transactions.replace(R.id.frame_container, fragvsi)
                    transactions.addToBackStack(null)
                    transactions.commit()
                } else if (delete) {
                    val catid = bookid.chap_id
                   // butils.updateFav(0, catid)
                    viewmodel.update(0,catid)

                    val snackbar = Snackbar
                        .make(
                            coordinatorLayout,
                            "Item was removed from the list.",
                            Snackbar.LENGTH_LONG
                        )
                    snackbar.setActionTextColor(Color.CYAN)
                    snackbar.show()
                    //     RefreshActivity();
                    //      HisnulMainAct.viewPager.setCurrentItem(2);
                }
            }
        })
    }

    companion object {
        fun newInstance(): NewHisnulBookmarkFragment {
            return NewHisnulBookmarkFragment()
        }
    }
}