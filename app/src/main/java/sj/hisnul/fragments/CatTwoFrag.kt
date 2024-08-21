package sj.hisnul.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushafconsolidated.R
import org.sj.conjugator.interfaces.OnItemClickListener
import sj.hisnul.activity.NewExpandAct
import sj.hisnul.adapter.CatTwoAdapter
import sj.hisnul.entity.hcategoryEnt
import sj.hisnul.newepository.NewDuaModel

class CatTwoFrag : Fragment() {
    lateinit var recyclerView: RecyclerView

    private val duacatmodel: NewDuaModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val view: View = inflater.inflate(R.layout.frag_catwo_drawble, container, false)
        recyclerView = view.findViewById(R.id.recview)




      lateinit    var duagrouptwo: ArrayList<hcategoryEnt>
        val adapter = CatTwoAdapter( requireContext())
        val layoutManager: GridLayoutManager = GridLayoutManager(activity, 3)
        recyclerView.setHasFixedSize(true)
        // recyclerView.setLayoutManager(new LinearLayoutManager(context!!));
        recyclerView.layoutManager = layoutManager

        duacatmodel.duaCategory().observe(viewLifecycleOwner){
            adapter.setmutable(it)
            recyclerView.adapter = adapter
        }

 /*
         val viewModel: CatwoModel by viewModels()
        viewModel.loadLists().observe(viewLifecycleOwner) { userlist ->
        //   duagrouptwo= userlist as ArrayList<hcategory>
         adapter.setmutable(userlist)
        }
*/






        /*
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
             //   return position == 0 ? 3 : 1;
            }
        });

      */
        adapter.SetOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                val catTwo: hcategoryEnt = adapter.getItem(position) as hcategoryEnt
           //     val catTwo: hcategory = duagrouptwo[position]
                if (catTwo.title.isNotEmpty()) {
                    val intent = Intent(activity, NewExpandAct::class.java)
                    intent.putExtra("PARENT_ACTIVITY_REF", "ParentActivityIsA")
                    startActivity(intent)
                    val catid: Int = catTwo.id
                    intent.putExtra("dua_id", catid)
                    startActivity(intent)
                }
            }
        })
        return view
    }

    companion object {
        fun newInstance(): CatTwoFrag {
            return CatTwoFrag()
        }
    }
}