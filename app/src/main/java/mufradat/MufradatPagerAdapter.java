package mufradat;


import static com.example.utility.QuranGrammarApplication.context;
import static org.sj.conjugator.utilities.SharedPref.sharedPreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.mushafconsolidated.Entities.ChaptersAnaEntity;

import com.example.mushafconsolidated.R;
import com.example.mushafconsolidated.intrfaceimport.OnItemClickListener;
import com.example.utility.PreferenceUtil;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;
import org.sj.conjugator.utilities.SharedPref;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.example.utility.SharedPref;

public class MufradatPagerAdapter extends RecyclerView.Adapter<MufradatPagerAdapter.MufradatViewHolder> {

    String URDU_FONTS = "fonts/Mehr.ttf";
    String QURAN_FONTS = "fonts/AlQalam.ttf";
    //  private final Typeface alQalamFont;

    //  private final boolean applyLineSpacing;

    //  private final Typeface arabicFont;
    private boolean isExpanded = false;
    //    private final Integer arabicFontSize;
    private final SharedPref prefs;
    private final String surahName;
    private final String isdark;

    private int bookChapterno;
    private int bookVerseno;
    int bookmarkpostion;



    private Integer ayahNumber;

    private final Context mufradContext;


    OnItemClickListener mItemClickListener;


    private ArrayList<MufradatEntity> tafseeArrayList;

    private int isMakkiMadani;

    PreferenceUtil pref;

    private String arabic_font_selection;




    public void setBookChapterno(int bookChapterno) {
        this.bookChapterno = bookChapterno;
    }


    public void setBookVerseno(int bookVerseno) {
        this.bookVerseno = bookVerseno;
    }

    public int getBookmarkpostion() {
        return bookmarkpostion;
    }

    public void setBookmarkpostion(int bookmarkpostion) {
        this.bookmarkpostion = bookmarkpostion;
    }

    public String getSurahName() {
        return surahName;
    }

    public MufradatPagerAdapter(String suraharabicname, ArrayList<MufradatEntity> tafseerList, ViewPager2 viewPagertwo, int isMakkiMadani, FragmentActivity activity) {
        //   LayoutInflater mInflater = LayoutInflater.from(context);
        this.tafseeArrayList = tafseerList;
        this.surahName=suraharabicname;
        this.isMakkiMadani = isMakkiMadani;
        this.mufradContext = activity;


        prefs=new SharedPref(mufradContext);

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(mufradContext);
        pref = new PreferenceUtil(sharedPreferences);
        isdark = sharedPreferences.getString("themepref", "dark").toString();

    }





    // }

    @NotNull
    @Override
    public MufradatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //   View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mufradat_ayah_list_row, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ayah_slide_two, parent, false);

        MufradatViewHolder viewHolder = new MufradatViewHolder(view);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NotNull MufradatViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final MufradatEntity verse = tafseeArrayList.get(position);

        setAyahText(holder, verse);
        setStartText(holder, verse);
        setTranslationAndTafseer(holder, verse);
        setrukuinfo(holder, verse);
        setWordByWord(holder, verse);
        setBookMarkFeilds(holder, verse, position);
        setTextViewStyles(holder);

       /* if (isExpanded) {
            holder.ayah_translation.setVisibility(View.VISIBLE);
            holder.ayah_tafseer.setVisibility(View.VISIBLE);
            holder.toggleIcon.setVisibility(View.GONE); // Hide the toggle icon when expanded
        } else {
            holder.ayah_translation.setVisibility(View.GONE);
            holder.ayah_tafseer.setVisibility(View.GONE);
            holder.toggleIcon.setVisibility(View.VISIBLE); // Show the toggle icon when collapsed
        }

        // Toggle expansion on icon click
        holder.toggleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpanded = !isExpanded; // Toggle state
                notifyItemChanged(position); // Refresh the view to reflect the new state
            }
        });*/
    }






    private void setBookMarkFeilds(MufradatViewHolder holder, MufradatEntity verse, int position) {

        setBookChapterno(verse.getSurahNumber());
        setBookVerseno(verse.AyahNumber);
        setBookmarkpostion(position);

    }

    private void setWordByWord(MufradatViewHolder holder, MufradatEntity verse) {
        String colorizedAyahWords;

        if(isdark.equals("dark")){
       // if (SharedPref.themePreferences().equals("dark")) {
            colorizedAyahWords = getColorizedAyahWords(verse.getWords(), Color.YELLOW, Color.GREEN);
        } else {

            colorizedAyahWords = getColorizedAyahWords(verse.getWords(), Color.BLUE,  ContextCompat.getColor(mufradContext, R.color.burntamber));
        }
        setHtmlText(colorizedAyahWords);
        holder.ayah_words.setText(Html.fromHtml(colorizedAyahWords));

    }

    private void setTranslationAndTafseer(MufradatViewHolder holder, MufradatEntity verse) {
        // assert arabic_font_size != null;
        //  int anInt = Integer.parseInt(arabic_font_size);
        //   Typeface typeface = Typeface.createFromAsset(applicationContext.getAssets(), urdu_font_selection);
        //   TextView urdu = holder.ayah_translation;
        //  urdu.setTypeface(typeface);

        holder.ayah_translation.setTypeface(Typeface.createFromAsset(mufradContext.getAssets(), URDU_FONTS));
        //if (SharedPref.themePreferences().equals("light")) {
            if(!isdark.equals("blue")){

            holder.ayah_translation.setTextColor(ContextCompat.getColor(mufradContext, R.color.burntamber));
            holder.ayah_translation.setText(verse.getTranslation());
            holder.ayah_tafseer.setTextColor(ContextCompat.getColor(mufradContext, R.color.burntamber));
            holder.ayah_tafseer.setText(verse.getTafseer());
        }
        else{
            holder.ayah_translation.setTextColor(ContextCompat.getColor(mufradContext, R.color.cyan));
            holder.ayah_translation.setText(verse.getTranslation());
            holder.ayah_tafseer.setTextColor(ContextCompat.getColor(mufradContext, R.color.green));
            holder.ayah_tafseer.setText(verse.getTafseer());

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setrukuinfo(MufradatViewHolder holder, MufradatEntity verse) {

        StringBuilder surahInfo = new StringBuilder();
        surahInfo.append(verse.getSurahName() + ".");
        surahInfo.append(verse.getSurahNumber() + ".");
        surahInfo.append(verse.getAyahNumber());
        //   surahInfo.append( "");
        //  surahInfo.append(verse.getRukuSurahNumber());
        //  surahname,verseno,chapterno,chaperruku,surahrukuh
        //   getFormattedRukuInfo(verse.getSurahName(),verse.getAyahNumber(),verse.getParahNumber(),verse.getSurahNumber(),verse.getRukuParahNumber(),verse.getRukuSurahNumber())
        if (isMakkiMadani == 1) {
            holder.ruku_info_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_makkah_48, 0, 0, 0);
            if(isdark.equals("dark")){
                holder.ruku_info_text.setCompoundDrawableTintList(ColorStateList.valueOf(Color.GREEN));
            } else {
                holder.ruku_info_text.setCompoundDrawableTintList(ColorStateList.valueOf(Color.BLACK));
            }


        } else {
            holder.ruku_info_text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_madinah_48, 0, 0, 0);

            if(isdark.equals("dark")){
                holder.ruku_info_text.setCompoundDrawableTintList(ColorStateList.valueOf(Color.GREEN));
            } else {
                holder.ruku_info_text.setCompoundDrawableTintList(ColorStateList.valueOf(Color.BLACK));
            }
        }

        holder.ruku_info_text.setText(surahInfo);
    }
    //    holder.ruku_info_text.setText(String.valueOf(verse.getRuku()));





    private void setStartText(MufradatViewHolder holder, MufradatEntity verse) {
        holder.starttext.setText("بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّحِيْمِ");
        if (verse.getSurahNumber() == 1 || verse.getSurahNumber() == 9 || verse.getAyahNumber() != 1) {
            holder.starttext.setVisibility(View.GONE);
        }
    }

    private void setAyahText(MufradatViewHolder holder, MufradatEntity verse) {

         arabic_font_selection =
                sharedPreferences.getString("Arabic_Font_Selection", "AlQalam.ttf");


       // holder.ayah_text.setTextSize(SharedPref.SeekarabicFontsize());
        final String arabicFontSelection =arabic_font_selection;
        holder.ayah_text.setTypeface(Typeface.createFromAsset(mufradContext.getAssets(), arabicFontSelection));


        //  holder.ayah_text.setTextSize(Float.parseFloat(pref.getArabicTextFonts()));
        //   holder.ayah_text.setText(verse.getAyahTextQalam());

        ;  holder.ayah_text.setText(verse.getAyahTextPdms());
//        if (SharedPref.arabicFontSelection().equals("Muhammadi.ttf")) {
//            holder.ayah_text.setLineSpacing(1.3F, 1.3F);
//        } else
//        if(SharedPref.arabicFontSelection().equals("AlQalam.ttf")){
//            holder.ayah_text.setLineSpacing(1.08F, 1.08F);
//        }
        //   holder.ayah_text.setTextSize(this.arabicFontSize.intValue());



    }

    private void setTextViewStyles(MufradatViewHolder holder) {

//        colorwordfont = Typeface.createFromAsset(
//                QuranGrammarApplication.context!!.assets,
//                FONTS_LOCATION_PATH
//        )

        holder.ayah_text.setTypeface(Typeface.createFromAsset(mufradContext.getAssets(), QURAN_FONTS));
        holder.ayah_tafseer.setTypeface(Typeface.createFromAsset(mufradContext.getAssets(), URDU_FONTS));

        /// TODO: 23/8/20         holder.ayah_translation.setTypeface(this.urduFont);
        /// TODO: 23/8/20         holder.ayah_tafseer.setTypeface(this.urduFont);

      /*  if (SharedPref.urduFontSelection().equals("Mehr.TTF"))
        {
            holder.ayah_translation.setLineSpacing(1.07F, 1.07F);
            holder.ayah_tafseer.setLineSpacing(1.07F, 1.07F);
        }
        holder.ayah_translation.setTextSize(SharedPref.urduFontsize());
        holder.ayah_tafseer.setTextSize(SharedPref.urduFontsize());
        //   SharedPref.urduFontSelection();

        holder.ayah_translation.setTypeface(Typeface.createFromAsset(mufradContext.getAssets(), SharedPref.urduFontSelection()));
        holder.ayah_tafseer.setTypeface(Typeface.createFromAsset(mufradContext.getAssets(), SharedPref.urduFontSelection()));

        */
        //TODO
         /*
              if (this.applyLineSpacing) {
            holder.ayah_translation.setLineSpacing(1.07F, 1.07F);
            holder.ayah_tafseer.setLineSpacing(1.07F, 1.07F);
        }
        if (this.muhammadiFontSelected) {
            holder.ayah_text.setLineSpacing(1.3F, 1.3F);
            return;
        }
        if (this.qalamFontSelected)
            holder.ayah_text.setLineSpacing(1.08F, 1.08F);


        */


    }

    private void setHtmlText(String colorizedAyahWords) {
        if (colorizedAyahWords == null) {
            colorizedAyahWords = "";
        } else {
            colorizedAyahWords = colorizedAyahWords.replace("\n", "<br/>").replace("\\n", "<br/>");
        }

    }


    @Override
    public long getItemId(int position) {
        //  Surah surah = surahArrayList.get(position);

        return tafseeArrayList.get(position).getSurahNumber();
    }

    public Object getItem(int position) {

        return tafseeArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return tafseeArrayList.size();

    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public class MufradatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Views in the layout
        public final TextView ayah_text;
        public final TextView ayah_words;
        public final TextView ayah_translation;
        public final TextView ayah_tafseer;
        public final TextView ruku_info_text;
        final View ruku_info_text_separator;
        public final TextView starttext;
        MaterialCardView tafseerCardView;
        ImageView toggleIcon;

        public MufradatViewHolder(View view) {
            super(view);

            // Initialize the views
            ruku_info_text_separator = view.findViewById(R.id.ruku_info_text_separator);
            starttext = view.findViewById(R.id.start_text);
            ayah_words = view.findViewById(R.id.ayah_words);
            ayah_text = view.findViewById(R.id.ayah_text);
            ayah_translation = view.findViewById(R.id.ayah_translation);
            ayah_tafseer = view.findViewById(R.id.ayah_tafseer);
            ruku_info_text = view.findViewById(R.id.ruku_info_text);
            tafseerCardView = view.findViewById(R.id.tafseer_card_view);
            toggleIcon = view.findViewById(R.id.toggle_icon);

            view.setOnClickListener(this);
            toggleIcon.setOnClickListener(this);
            toggleIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    if (ayah_tafseer.getVisibility() == View.GONE) {
                        ayah_tafseer.setVisibility(View.VISIBLE);
                        // AnimationUtility.slide_down(context, erabexpand);
                        //AnimationUtility.AnimateArrow(90.0f, toggleIcon);
                    } else {
                        ayah_tafseer.setVisibility(View.GONE);
                       // AnimationUtility.AnimateArrow(0.0f, toggleIcon);
                        // Fader.slide_down(context, expandImageButton);
                    }
                }
            });

        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getLayoutPosition());
            }
        }
    }



    private void getArabicVerseWwords(String ayah_words) {
        Pattern pattern = Pattern.compile("^(.+?):|(?<=\\|)(.*?)(?=\\:)");
        Matcher m = pattern.matcher(ayah_words);

        int lastMatchPos = 0;
        while (m.find()) {
            System.out.println("  " + m.group(1) + " - " + m.group(2));
            lastMatchPos = m.end();
        }
        if (lastMatchPos != ayah_words.length()) {
            System.out.println("  Invalid string!");
        }


    }

    private String getColorizedAyahWords(String ayahwords, int yellow, int green) {

        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("<font color=\"");
        stringBuilder2.append(yellow);
        stringBuilder2.append("\">");
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("</font><font color=\"");
        stringBuilder3.append(green);
        stringBuilder3.append("\"> :");
        ayahwords = ayahwords.replace(":", stringBuilder3.toString());
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("</font><font color=\"");
        stringBuilder1.append(yellow);
        stringBuilder1.append("\">&nbsp;&nbsp;");
        ayahwords = ayahwords.replace("|", stringBuilder1.toString());
        stringBuilder1 = new StringBuilder();
        stringBuilder1.append("</font><font color=\"");
        stringBuilder1.append(yellow);
        stringBuilder1.append("\">\n");
        stringBuilder2.append(ayahwords.replace("\n", stringBuilder1.toString()).replace("\n", "<br/>"));

        return stringBuilder2.toString();


    }


    static String getFormattedRukuInfo(Integer paramInteger1,
                                       Integer paramInteger2, String paramString,
                                       Integer paramInteger3, Integer paramInteger4
            , Integer paramInteger5) {
        return String.format("%s   %s  %d       %s %d       %s  %d       %s  %d       %s  %d", new Object[]{
                "سورۃ", paramString, paramInteger1, "آیت نمبر", paramInteger2, "پارہ",
                paramInteger3, "پارہ   رکوع", paramInteger4, "سورۃ   رکوع",
                paramInteger5});

    }
}
