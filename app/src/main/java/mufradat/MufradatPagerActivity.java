package mufradat;

import static com.example.Constant.MAKKI_MADANI;
import static com.example.Constant.SURAH_ARABIC_NAME;
import static com.example.Constant.SURAH_ID;
import static com.example.Constant.VERSESCOUNT;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;
import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.Utils;

import org.sj.conjugator.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import sj.hisnul.activity.CustomAdapter;

public class MufradatPagerActivity extends BaseActivity {
    ViewPager2 viewPagertwo;
    Utils utils;
    MufradatPagerAdapter adapter;
    private int surah_id;
    private int versescount;
    private String suraharabicname;
    private int isMakkiMadani;
    private Toolbar materialToolbar;
    private Spinner spin;
    private static final String TAG = " ";
    private ArrayList<ChaptersAnaEntity> surahArray;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.grammer_menu_spinner, menu);
        MenuItem item = menu.findItem(R.id.grammarbarspinner);
        ;
        spin = (Spinner) MenuItemCompat.getActionView(item);

        materialToolbar = findViewById(R.id.mufradattoolbar);
        materialToolbar.setTitle("Raghib AlSifhani");
        setuptoolbar();
        return super.onCreateOptionsMenu(menu);



    }

    private void setuptoolbar() {
        //  TypedArray imgs = getResources().obtainTypedArray(R.array.quran_imgs);
        String[] surahnamelist = getResources().getStringArray(R.array.quran_names);
        TypedArray imgs = getResources().obtainTypedArray(R.array.sura_imgsspinner);
        int check = 0;

        ArrayAdapter adapter
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                surahnamelist);
        adapter.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
      //  spin.setAdapter(adapter);

        MCustomerAdapter customAdapter = new MCustomerAdapter(this, surahnamelist, imgs);

        spin.setAdapter(customAdapter);
        // spin.setAdapter(customAdapter);
        spin.setSelected(false);  // must
        //   spin.setSelection(0, true);
        spin.setSelection(surah_id , true);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final ArrayList<ChaptersAnaEntity> surahArray = (ArrayList<ChaptersAnaEntity>) utils.getSingleChapter(i);
                final int SELECTEDSURAH=0;
                final String namearabic = surahArray.get(SELECTEDSURAH).getNamearabic();

                int ismakki = surahArray.get(SELECTEDSURAH).getIsmakki();
                final int chapterid = surahArray.get(SELECTEDSURAH).getChapterid();

                MufradatInit(chapterid,namearabic,ismakki);
                //   new MufradatFragment.QuranAyatSetup(surahno,  arabicname).invoke();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //   Log.d(TAG, "ON OPTION MENU");
        switch (item.getItemId()) {

            case R.id.grammarbarspinner:
                //         Log.d(TAG, "ON OPTION MENU");
                   setuptoolbar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);
        materialToolbar = findViewById(R.id.mufradattoolbar);
        setSupportActionBar(materialToolbar);
        Intent intent=getIntent();
         boolean isExpanded = false;
        if (null!=intent) {
            surah_id = intent.getIntExtra(SURAH_ID,1);
            versescount = intent.getIntExtra(VERSESCOUNT,7);
            suraharabicname = intent.getStringExtra(SURAH_ARABIC_NAME);
            isMakkiMadani = intent.getIntExtra(MAKKI_MADANI,0);
        }else{
            surah_id = 1;
            versescount = 7;
            suraharabicname ="\"الْفَاتِحَة\"";
            isMakkiMadani = 1;
        }


        materialToolbar.inflateMenu(R.menu.grammer_menu_spinner);
        //    spin = findViewById(R.id.grammarbarspinner);
        MufradatInit(surah_id, suraharabicname, isMakkiMadani );


    }

    private void MufradatInit(int surahno, String arabicname, int ismakki) {
        this.surah_id=surahno;
        this.suraharabicname=arabicname;
        this.isMakkiMadani=ismakki;
        ViewPager2 viewPagertwo;
        viewPagertwo = findViewById(R.id.viewpager2);


        utils = new Utils(MufradatPagerActivity.this);
        List<MufradatEntity> tafseerList = utils.getShaikhTafseer(surah_id);


            surahArray = (ArrayList<ChaptersAnaEntity>) utils.getSingleChapter(surah_id);
        //   adapter.makkiMadani(isMakkiMadani);
            MufradatPagerAdapter tafseerAdapter = new MufradatPagerAdapter(suraharabicname, (ArrayList<MufradatEntity>) tafseerList, viewPagertwo, isMakkiMadani, this  );
        viewPagertwo.setAdapter(tafseerAdapter);
         viewPagertwo.setCurrentItem(0);
        //viewPagertwo.setAdapter(new MufradatPagerAdapter(MufradatPagerActivity.this, suraharabicname, tafseerList, viewPagertwo,isMakkiMadani, this));
    }



}
