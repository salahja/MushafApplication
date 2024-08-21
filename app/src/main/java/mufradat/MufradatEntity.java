package mufradat;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;




@Entity(tableName = "mufradat_verses")
public class MufradatEntity {
    @PrimaryKey(autoGenerate = true)
    public int Id;
    public int SurahNumber;
    public int AyahNumber;
    @NonNull
    public String AyahTextQalam;
    @NonNull
    public int AyahTextMuhammadi;
    @NonNull
    public String AyahTextPdms;
    @NonNull
    public String AyahTextPlain;
    @NonNull
    public String Translation;
    @NonNull
    public String Tafseer;
    @NonNull
    public String Words;
    @NonNull
    public String SurahName;
    public int Ruku;
    public int Sajda;
    public int ParahNumber;
    public int RukuParahNumber;
    @NonNull
    public String RukuSurahNumber;

    public MufradatEntity(int Id, int SurahNumber, int  AyahNumber, @NonNull String AyahTextQalam,
                          int AyahTextMuhammadi, @NonNull String  AyahTextPdms,
                          @NonNull String AyahTextPlain, @NonNull String  Translation,
                          @NonNull String   Tafseer, @NonNull String  Words,
                          @NonNull String SurahName, int Ruku, int Sajda ,
                          int ParahNumber, int  RukuParahNumber, @NonNull String RukuSurahNumber) {
        this. Id =Id;
        this.SurahNumber = SurahNumber;
        this.AyahNumber =  AyahNumber;
        this. AyahTextQalam = AyahTextQalam;
        this.  AyahTextMuhammadi = AyahTextMuhammadi;
        this.  AyahTextPdms =  AyahTextPdms;
        this. AyahTextPlain = AyahTextPlain;
        this.Translation = Translation;
        this.Tafseer =   Tafseer;
        this. Words =  Words;
        this.  SurahName = SurahName;
        this.  Ruku = Ruku;
        this.  Sajda = Sajda ;
        this. ParahNumber = ParahNumber;
        this. RukuParahNumber =  RukuParahNumber;
        this.  RukuSurahNumber = RukuSurahNumber;
    }

    public int getId() {
        return Id;
    }

    public int getSurahNumber() {
        return SurahNumber;
    }

    public int getAyahNumber() {
        return AyahNumber;
    }

    @NonNull
    public String getAyahTextQalam() {
        return AyahTextQalam;
    }

    public int getAyahTextMuhammadi() {
        return AyahTextMuhammadi;
    }

    @NonNull
    public String getAyahTextPdms() {
        return AyahTextPdms;
    }

    @NonNull
    public String getAyahTextPlain() {
        return AyahTextPlain;
    }

    @NonNull
    public String getTranslation() {
        return Translation;
    }

    @NonNull
    public String getTafseer() {
        return Tafseer;
    }

    @NonNull
    public String getWords() {
        return Words;
    }

    @NonNull
    public String getSurahName() {
        return SurahName;
    }

    public int getRuku() {
        return Ruku;
    }

    public int getSajda() {
        return Sajda;
    }

    public int getParahNumber() {
        return ParahNumber;
    }

    public int getRukuParahNumber() {
        return RukuParahNumber;
    }

    @NonNull
    public String getRukuSurahNumber() {
        return RukuSurahNumber;
    }
}