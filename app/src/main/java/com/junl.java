package com;

public class junl {
    /*
    public void syncwordbywordpartwise() {

        Utils utils = new Utils(ReadingSurahPartActivity.this);
        ArrayList<ChaptersAnaEntity> singleChapter = utils.getSingleChapter(surah_id);
        ayahWordArrayList = new ArrayList<AyahWord>();
        ArrayList<ArrayList<ArrayList<CorpusAndWbwTranslation>>> ayahWordArrayListt = new ArrayList<>();
        ArrayList<CorpusAndWbwTranslation> surahandAyah;
        int versescounts = singleChapter.get(0).getVersescount();
        ArrayList<ArrayList<CorpusAndWbwTranslation>> verse = new ArrayList<>();


        for (int ayah = 1; ayah <= versescounts; ayah++) {
            surahandAyah = utils.getCorpusSurahandAyah(surah_id, ayah);
            verse.add(surahandAyah);


        }
        int size = verse.size();
        ayahWordArrayListt.add(verse);
        ArrayList<AyahWord> ayahWords = new ArrayList<>();
        ArrayList<Word> wordArrayList;

        //   AyahWord ayahWord = new AyahWord();
        ArrayList<AyahWord> wordbywords = new ArrayList<>();
        for (int counter = 0; counter < size; counter++) {
            ArrayList<String> collectverses = new ArrayList<>();
            AyahWord ayahWord = new AyahWord();
            wordArrayList = new ArrayList<Word>();
            for (int inner = 0; inner < verse.get(counter).size(); inner++) {
                Word word = new Word();

                CharSequence sequence = concat(verse.get(counter).get(inner).getAraone() + verse.get(counter).get(inner).getAratwo() +
                        verse.get(counter).get(inner).getArathree() + verse.get(counter).get(inner).getArafour());
                collectverses.add(sequence.toString());
                word.setSurahId(verse.get(counter).get(inner).getSurah());
                word.setVerseId(verse.get(counter).get(inner).getAyah());
                word.setWordcount(verse.get(counter).get(inner).getWordcount());
                word.setWordno(verse.get(counter).get(inner).getWordno());
                word.setWordsAr(sequence.toString());
                word.setTranslateEn(verse.get(counter).get(inner).getEn());
                wordArrayList.add(word);


            }

            ayahWord.setWord(wordArrayList);


            ayahWordArrayList.add(ayahWord);


        }
        //   return ayahWordArrayList;

    }


*/

    /*
check for mushaf,need to inser the ayanumber
    ArrayList<CorpusAndWbwTranslation> arrayLists = utils.getCorpusSurahnew(surah_id);
                ArrayList<CorpusEntity> ayhCount = utils.getCorpusSurahAyhCount(surah_id);
                arrayLists.toArray();
                int acount = 0;
                for (int i = 0; i <= arrayLists.size(); i++) {
                    int wordno = ayhCount.get(0).getWordcount();

                    for (; acount <= wordno; acount++) {
                        if (acount == wordno) {
                            int ayah = ayhCount.get(0).getAyah();
                            Object ayanumber = String.valueOf(ayah);
                            String valueOf = String.valueOf(ayah);
                            //     arrayLists.add(4,  ayanumber);


                        }
                    }

                }


                ArrayList<ArrayList<CorpusAndWbwTranslation>> arrayList = new ArrayList<>();
                arrayList.add(arrayLists);
                NewFlowWOrdAdapter(arrayList, erab, transliteration, jalalayn);


 */

/*

 bottomNavigationView.setOnNavigationItemReselectedListener(item ->

        {


            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.surah:

                    fragment = new SurahDisplayFragment();
                    loadFragment(fragment, SURAHFRAGTAG);
                    break;

                case R.id.mufradat:
                    Log.e(TAG, "onClick called");
                    final Intent intent = getIntent().putExtra("chapter", chapterno).putExtra("chapterorpart", chapterorpart).putExtra(PARTNAME, partname).putExtra(MUFRADATFRAGTAG, true);

                    finish();
                    startActivity(intent);
                               break;


                case R.id.bookmark:
                    // drawerLayout.closeDrawers();
                    //   materialToolbar.setTitle("BookMarks");
                    fragment = new BookmarkFragment();
                    loadFragment(fragment, BOOKMARKTAG);

                    break;
                case R.id.setting:
                       Intent intents = new Intent(ReadingSurahPartActivity.this, ActivitySettings.class);
                    startActivity(intents);

                    break;
                default:
                    break;
            }
        });

 */
/*
    private class currentPartSyncWordByWord extends AsyncTask<ArrayList<AyahWord>, Integer, ArrayList<AyahWord>> {
        ProgressDialog progressBarDD = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();

        }

        private void showProgressDialog() {

            if (progressBarDD == null) {
                progressBarDD = new ProgressDialog(ReadingSurahPartActivity.this);
                //    progressBarDD.setMessage("Please wait"+file.toString()+"being updated");
                progressBarDD.setMessage("loading word by word");
                progressBarDD.setIndeterminate(false);
                progressBarDD.setCancelable(false);
                progressBarDD.show();
            }
            progressBarDD.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBarDD.setProgress(values[0]);
            //   tv.setText(values[0]);

        }

        @Override
        protected void onPostExecute(ArrayList<AyahWord> ayahWords) {
            super.onPostExecute(ayahWords);
            progressBarDD.dismiss();
            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView);
            Utils utils = new Utils(ReadingSurahPartActivity.this);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ReadingSurahPartActivity.this);
            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView);
            ArrayList<TranslationDataEntity> erab = null;
            List<TranslationDataEntity> transliteration = null;
            final ArrayList<juz> startEndVerses = utils.RawgetStartEndVerses(chapterno);


            List<TranslationDataEntity> jalalayn = null;
            String translation_id = prefs.getString("selecttranslation", "en_sahih");

            if (showTranslation) {
                translationsdataentry = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), translation_id);


            }
            if (showErab) {
                erab = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), "ar_irab");


            }
            if (showTransliteration) {
                transliteration = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), TRANSLITERATION);

            }
            if (showJalalayn) {
                jalalayn = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), JALALAYN);

                //   = util.RawgetTranslationsBySurah(JALALAYN, surah_id);

            }


            ArrayList<QuranEntity> quranEntitiesverses = utils.getVersesByPartQuranEntity(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id());
            ArrayList<allwords> allwordstranslation = utils.getwordstranslationByPart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id());
            final ArrayList<AyahWord> arrayList = newwordbywordByJuz(startEndVerses, quranEntitiesverses);
            //      ArrayList<AyahWord> ayahWords = newwordbywordSplitQuranJuz(quranEntitiesverses,allwordstranslation);
            OnItemClickListener listener = null;
            JuzFlowAyahWordAdapter juzFlowAyahWordAdapter = new JuzFlowAyahWordAdapter(transliteration, erab, jalalayn, translationsdataentry, ayahWordArrayList, ReadingSurahPartActivity.this, surah_id, suraharabicname, isMakkiMadani, listener);
            juzFlowAyahWordAdapter.addContext(ReadingSurahPartActivity.this);
            parentRecyclerView.setHasFixedSize(true);
            parentRecyclerView.setAdapter(juzFlowAyahWordAdapter);
            parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));
        }

        @Override
        protected ArrayList<AyahWord> doInBackground(ArrayList<AyahWord>... strings) {
            Utils utils = new Utils(ReadingSurahPartActivity.this);
            //    ArrayList<ChaptersAnaEntity> singleChapter = utils.getSingleChapter(surah_id);
            ayahWordArrayList = new ArrayList<AyahWord>();
            ArrayList<ArrayList<ArrayList<CorpusAndWbwTranslation>>> ayahWordArrayListt = new ArrayList<>();
            ArrayList<CorpusAndWbwTranslation> surahandAyah;
            //  int versescounts = singleChapter.get(0).getVersescount();
            ArrayList<ArrayList<CorpusAndWbwTranslation>> verse = new ArrayList<>();
            final ArrayList<juz> startEndVerses = utils.RawgetStartEndVerses(chapterno);
            int end = startEndVerses.get(0).getEnding_verse_id();

            int start = startEndVerses.get(0).getStarting_verse_id();
            int versesnumbers = end - start;
            ArrayList<wbwentity> wbw = utils.getwbwQuranbypart(chapterno);

            // ArrayList<AyahWord> ayahWordArrayList = new ArrayList<AyahWord>();
            ArrayList<String> words;
            List<QuranEntity> quran = utils.getVersesByPartQuranEntity(start, end);
            int size = verse.size();
            ayahWordArrayListt.add(verse);
            ArrayList<AyahWord> ayahWords = new ArrayList<>();
            //  ArrayList<Word> wordArrayList;
            int verseglobal = 0;
            int quranglobal;
            quranglobal = 0;
            int versesize = verse.size();
            int wbwsize = wbw.size();
            ArrayList<ArrayList> wbwa = new ArrayList<>();
            for (wbwentity wb : wbw) {
                words = new ArrayList<>();

                words.add(String.valueOf(wb.getSurah()));
                words.add(String.valueOf(wb.getAyah()));
                words.add(String.valueOf(wb.getWordno()));
                words.add(String.valueOf(wb.getWordcount()));
                CharSequence sequence = concat(wb.getAraone() + wb.getAratwo() +
                        wb.getArathree() + wb.getArafour());
                words.add(sequence.toString());
                words.add(wb.getEn());
                words.add(wb.getIn());
                words.add(wb.getBn());
                wbwa.add(words);
            }

            int tempVerseWord;
            int tempVerseQuran;
            int verseexit = wbw.size();
            int quranexit = quran.size();
            int i = wbw.get(verseglobal).getAyah();
            versesnumbers += i;
            for (; i <= versesnumbers; i++) {
                tempVerseWord = i;
                tempVerseQuran = i;

                AyahWord ayahWord = new AyahWord();


                ArrayList<Word> wordArrayList = new ArrayList<Word>();
                while (tempVerseWord == i) {

                    if (verseexit == verseglobal) {
                        break;
                    }

                    for (; verseglobal < wbw.size(); verseglobal++) {
                        Word word = new Word();
                        //  Object o = wbwa.get(1);

                        tempVerseWord = wbw.get(verseglobal).getAyah();
                        // tempVerseWord = Integer.valueOf((String) o);

                        if (tempVerseWord != i) {
                            break;
                        }
                        final Object o6 = wbwa.get(verseglobal).get(0);
                        Object o1 = wbw.get(verseglobal).getSurah();
                        Object o2 = wbw.get(verseglobal).getAyah();
                        Object o3 = wbw.get(verseglobal).getWordno();
                        Object wcount = wbw.get(verseglobal).getWordcount();
                        CharSequence sequence = concat(wbw.get(verseglobal).getAraone() + wbw.get(verseglobal).getAratwo() +
                                wbw.get(verseglobal).getArathree() + wbw.get(verseglobal).getArafour());
                        //   Object o4 = wbw.get(verseglobal).getWord();
                        Object en = wbw.get(verseglobal).getEn();
                        Object bn = wbw.get(verseglobal).getBn();
                        Object ind = wbw.get(verseglobal).getIn();
                        word.setSurahId(Long.parseLong(o1.toString()));
                        word.setVerseId(Long.parseLong(o2.toString()));
                        word.setWordsId(Long.parseLong(o3.toString()));
                        word.setWordcount((Integer) wcount);


                        word.setWordsAr(sequence.toString());
                        word.setTranslateEn(en.toString());
                        word.setTranslateBn(bn.toString());
                        word.setTranslateIndo(ind.toString());
                        wordArrayList.add(word);

                    }


                }
                while (tempVerseQuran == i) {

                    if (quranexit == quranglobal) {
                        break;
                    }
                    for (; quranglobal < quran.size(); quranglobal++) {
                        tempVerseQuran = quran.get(quranglobal).getSurah();
                        if (tempVerseQuran != i) {
                            break;
                        }
                        ayahWord.setQuranTranslate(quran.get(quranglobal).getQurantext());

                        ayahWord.setChapterno(quran.get(quranglobal).getSurah());
                        ayahWord.setVerseno(quran.get(quranglobal).getAyah());

                        //      ayahWord.setHasProstration(quran.get(quranglobal).getHas_prostration());
                        ayahWord.setRukuno(quran.get(quranglobal).getPassage_no());
                    }


                }


                ayahWord.setWord(wordArrayList);
                ayahWordArrayList.add(ayahWord);

            }


            return ayahWordArrayList;
            //    ayahWord.setWord(wordArrayList);
            // ayahWordArrayList.add(ayahWord);


        }
    }
 */
/*

private class PartSyncWordByWord extends AsyncTask<ArrayList<AyahWord>, Integer, ArrayList<AyahWord>> {
        ProgressDialog progressBarDD = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();

        }

        private void showProgressDialog() {

            if (progressBarDD == null) {
                progressBarDD = new ProgressDialog(ReadingSurahPartActivity.this);
                //    progressBarDD.setMessage("Please wait"+file.toString()+"being updated");
                progressBarDD.setMessage("loading word by word");
                progressBarDD.setIndeterminate(false);
                progressBarDD.setCancelable(false);
                progressBarDD.show();
            }
            progressBarDD.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBarDD.setProgress(values[0]);
            //   tv.setText(values[0]);

        }

        @Override
        protected void onPostExecute(ArrayList<AyahWord> ayahWords) {
            super.onPostExecute(ayahWords);
            progressBarDD.dismiss();
            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView);
            Utils utils = new Utils(ReadingSurahPartActivity.this);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ReadingSurahPartActivity.this);
            parentRecyclerView = findViewById(R.id.overlayViewRecyclerView);
            ArrayList<TranslationDataEntity> erab = null;
            List<TranslationDataEntity> transliteration = null;
            final ArrayList<juz> startEndVerses = utils.RawgetStartEndVerses(chapterno);


            List<TranslationDataEntity> jalalayn = null;
            String translation_id = prefs.getString("selecttranslation", "en_sahih");

            if (showTranslation) {
                translationsdataentry = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), translation_id);


            }
            if (showErab) {
                erab = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), "ar_irab");


            }
            if (showTransliteration) {
                transliteration = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), TRANSLITERATION);

            }
            if (showJalalayn) {
                jalalayn = utils.getTranslationBypart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id(), JALALAYN);

                //   = util.RawgetTranslationsBySurah(JALALAYN, surah_id);

            }


            ArrayList<QuranEntity> quranEntitiesverses = utils.getVersesByPartQuranEntity(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id());
            ArrayList<allwords> allwordstranslation = utils.getwordstranslationByPart(startEndVerses.get(0).getStarting_verse_id(), startEndVerses.get(0).getEnding_verse_id());
            final ArrayList<AyahWord> arrayList = newwordbywordByJuz(startEndVerses, quranEntitiesverses);
            //      ArrayList<AyahWord> ayahWords = newwordbywordSplitQuranJuz(quranEntitiesverses,allwordstranslation);
            OnItemClickListener listener = null;
            JuzFlowAyahWordAdapter juzFlowAyahWordAdapter = new JuzFlowAyahWordAdapter(transliteration, erab, jalalayn, translationsdataentry, ayahWordArrayList, ReadingSurahPartActivity.this, surah_id, suraharabicname, isMakkiMadani, listener);
            juzFlowAyahWordAdapter.addContext(ReadingSurahPartActivity.this);
            parentRecyclerView.setHasFixedSize(true);
            parentRecyclerView.setAdapter(juzFlowAyahWordAdapter);
            parentRecyclerView.post(() -> parentRecyclerView.scrollToPosition(verse_no));
        }

        @Override
        protected ArrayList<AyahWord> doInBackground(ArrayList<AyahWord>... strings) {
            Utils utils = new Utils(ReadingSurahPartActivity.this);
            ArrayList<ChaptersAnaEntity> singleChapter = utils.getSingleChapter(surah_id);
            ayahWordArrayList = new ArrayList<AyahWord>();
            ArrayList<ArrayList<ArrayList<CorpusAndWbwTranslation>>> ayahWordArrayListt = new ArrayList<>();
            ArrayList<CorpusAndWbwTranslation> surahandAyah;
            int versescounts = singleChapter.get(0).getVersescount();
            ArrayList<ArrayList<CorpusAndWbwTranslation>> verse = new ArrayList<>();


            for (int ayah = 1; ayah <= versescounts; ayah++) {
                surahandAyah = utils.getCorpusSurahandAyah(surah_id, ayah);
                verse.add(surahandAyah);


            }
            int size = verse.size();
            ayahWordArrayListt.add(verse);
            ArrayList<AyahWord> ayahWords = new ArrayList<>();
            ArrayList<Word> wordArrayList;

            //   AyahWord ayahWord = new AyahWord();
            ArrayList<AyahWord> wordbywords = new ArrayList<>();
            for (int counter = 0; counter < size; counter++) {
                ArrayList<String> collectverses = new ArrayList<>();
                AyahWord ayahWord = new AyahWord();
                wordArrayList = new ArrayList<Word>();
                for (int inner = 0; inner < verse.get(counter).size(); inner++) {
                    Word word = new Word();

                    CharSequence sequence = concat(verse.get(counter).get(inner).getAraone() + verse.get(counter).get(inner).getAratwo() +
                            verse.get(counter).get(inner).getArathree() + verse.get(counter).get(inner).getArafour());
                    collectverses.add(sequence.toString());
                    word.setSurahId(verse.get(counter).get(inner).getSurah());
                    word.setVerseId(verse.get(counter).get(inner).getAyah());
                    word.setWordcount(verse.get(counter).get(inner).getWordcount());
                    word.setWordno(verse.get(counter).get(inner).getWordno());
                    word.setWordsAr(sequence.toString());
                    word.setTranslateEn(verse.get(counter).get(inner).getEn());
                    wordArrayList.add(word);


                }

                ayahWord.setWord(wordArrayList);


                ayahWordArrayList.add(ayahWord);


            }


            //    ayahWord.setWord(wordArrayList);
            // ayahWordArrayList.add(ayahWord);


            return ayahWordArrayList;


        }
    }

*/

}
