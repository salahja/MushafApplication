package com.example.mushafconsolidated.fragments


import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.Adapters.BookmarkCreateAdapter
import com.example.mushafconsolidated.DAO.BookMarksPojo
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.R
import com.example.mushafconsolidated.Utils
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener
import com.example.mushafconsolidated.quranrepo.QuranVIewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 * ThemeListPrefrence.newInstance(30).show(getSupportFragmentManager(), "dialog");
</pre> *
 */
class BookMarkCreateFrag : BottomSheetDialogFragment(), OnItemClickListener,
    View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var suraname: String
    private var chapter: Int = 0
    private var verse: Int = 0
    private var selectedposition: Int = 0
    private lateinit var addtocollection: MaterialButton
    private lateinit var newcollection: MaterialButton
    lateinit var mItemClickListener: OnItemClickListener
    lateinit var radioGroup: RadioGroup
    private val bookmarCrateAdapter: BookmarkCreateAdapter? = null
    var collectionC: List<BookMarksPojo?>? = ArrayList()
    fun SetOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mItemClickListener = mItemClickListener!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val bundle: Bundle? = arguments
        assert(bundle != null)
        val stringArray: Array<String>? =
            bundle!!.getStringArray(BookMarkCreateFrag.ARG_OPTIONS_DATA)
        chapter = stringArray!![0].toInt()
        verse = stringArray[1].toInt()
        suraname = (stringArray[2])
        return inflater.inflate(R.layout.bookmark_create, container, false)
    }

    public override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val details: ArrayList<String> = ArrayList()
        val utils: Utils = Utils(activity)
        collectionC = utils.collectionC
        //     val bookmarkCreateAdapter = BookmarkCreateAdapter(collectionC as List<BookMarksPojo>?)
        val bookmarkCreateAdapter = BookmarkCreateAdapter(collectionC as List<BookMarksPojo>?)
        newcollection = view.findViewById(R.id.newcollection)
        addtocollection = view.findViewById(R.id.addtocollection)
        newcollection.setOnClickListener(this)
        addtocollection.setOnClickListener(this)
        recyclerView.adapter = bookmarCrateAdapter
        newcollection.setOnClickListener { v ->
            val dialogPicker = AlertDialog.Builder(
                activity!!
            )
            val dlg: Dialog = Dialog(requireActivity())
            //  AlertDialog dialog =  .create();
            //      soraList = utils.getAllAnaChapters();
            val inflater: LayoutInflater = requireActivity().layoutInflater
            val view: View = inflater.inflate(R.layout.folder_bookmark, null)
            //  View view = inflater.inflate(R.layout.activity_wheel, null);
            dialogPicker.setView(view)
            val mTextView: EditText = view.findViewById(R.id.tvFolderName)
            dialogPicker.setPositiveButton(
                "Done"
            ) { dialogInterface: DialogInterface?, i: Int ->
                val str: String = mTextView.text.toString()
                bookMarkSelected(v, selectedposition, str)
            }
            dialogPicker.setNegativeButton(
                "Cancel"
            ) { dialogInterface: DialogInterface?, i: Int -> }
            dialogPicker.show()
        }
        addtocollection.setOnClickListener { v ->
            val bookMarksPojo: BookMarksPojo = collectionC?.get(selectedposition)!!
            bookMarkSelected(
                v,
                bookMarksPojo.getChapterno().toInt(),
                bookMarksPojo.header.toString()
            )
        }


        bookmarkCreateAdapter.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val holder = recyclerView.findViewHolderForAdapterPosition(position)
                if (holder != null) {
                    val ck = holder.itemView.findViewById<CheckBox>(R.id.checkbox)
                    if (ck != null) {
                        selectedposition = position
                    }
                    println("check")
                }

                val tag = v!!.tag
                if (tag == "newcollection") {
                    val dialogPicker = AlertDialog.Builder(
                        activity!!
                    )
                    val dlg = Dialog(activity!!)
                    //  AlertDialog dialog = builder.create();
                    //      soraList = utils.getAllAnaChapters();
                    val inflater = activity!!.layoutInflater
                    val view = inflater.inflate(R.layout.folder_bookmark, null)
                    //  View view = inflater.inflate(R.layout.activity_wheel, null);
                    dialogPicker.setView(view)
                    val mTextView = view.findViewById<EditText>(R.id.tvFolderName)
                    dialogPicker.setPositiveButton("Done") { dialogInterface: DialogInterface?, i: Int ->
                        val str = mTextView.text.toString()
                        bookMarkSelected(v, position, str)
                    }
                    dialogPicker.setNegativeButton(
                        "Cancel"
                    ) { dialogInterface: DialogInterface?, i: Int -> }
                    dialogPicker.show()
                } else if (tag == "addcollection") {
                    --selectedposition
                    val book = collectionC!![selectedposition]!!
                    if (holder != null) {
                        val ck =
                            recyclerView.findViewHolderForAdapterPosition(position - 2)!!.itemView.findViewById<CheckBox>(
                                R.id.checkbox
                            )
                        println("check")
                    }
                    book.header?.let { bookMarkSelected(v, selectedposition, it) }
                    Toast.makeText(activity, "create collections", Toast.LENGTH_SHORT).show()
                }
            }


        })


    }

    private fun bookMarkSelected(view: View, position: Int, text: String) {
        //  position = flowAyahWordAdapter.getAdapterposition();


        //  position = flowAyahWordAdapter.getAdapterposition();
        val en = BookMarks()
        en.header = text


        en.chapterno = (chapter.toString())
        en.verseno = (verse.toString())
        en.surahname = (suraname)
        //     Utils utils = new Utils(ReadingSurahPartActivity.this);
        //     Utils utils = new Utils(ReadingSurahPartActivity.this);
        val vm: QuranVIewModel by viewModels()
        // val utils = Utils(context)
        // utils.insertBookMark(en)
        vm.Insertbookmark(en)
        //     View view = findViewById(R.id.bookmark);
        //     View view = findViewById(R.id.bookmark);
        val snackbar = Snackbar
            .make(view, "BookMark Created", Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.BLUE)
        snackbar.setTextColor(Color.CYAN)
        snackbar.setBackgroundTint(Color.BLACK)
        snackbar.show()
    }

    public override fun onItemClick(v: View?, position: Int) {}
    public override fun onClick(v: View) {}

    companion object {
        const val TAG: String = "opton"

        // TODO: Customize parameter argument names
        private const val ARG_OPTIONS_DATA: String = "item_count"

        // TODO: Customize parameters
        fun newInstance(data: Array<String>): BookMarkCreateFrag {
            val fragment: BookMarkCreateFrag = BookMarkCreateFrag()
            val args: Bundle = Bundle()
            args.putStringArray(BookMarkCreateFrag.ARG_OPTIONS_DATA, data)
            fragment.arguments = args
            return fragment
        }
    }
}