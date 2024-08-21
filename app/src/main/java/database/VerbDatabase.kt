package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import database.Dao.BuckwaterDao
import database.Dao.kovDao
import database.Dao.mazeedDao
import database.Dao.mujarradDao
import database.entity.BuckwaterEntitiy
import database.entity.MazeedEntity
import database.entity.MujarradVerbs
import database.entity.QuranVerbsEntity
import database.entity.kov

@Database(
    entities = [MazeedEntity::class, MujarradVerbs::class, kov::class, BuckwaterEntitiy::class, QuranVerbsEntity::class],
    version = 2
)
abstract class VerbDatabase : RoomDatabase() {
    abstract fun BuckwaterDao(): BuckwaterDao?
  //  abstract fun QuranVerbsDao(): QuranVerbsDao?

  //  abstract fun verbcorpusDao(): verbcorpusDao?
    abstract fun kovDao(): kovDao
    abstract fun mujarradDao(): mujarradDao
    abstract fun mazeedDao(): mazeedDao












    companion object {
        var verbDatabaseInstance: VerbDatabase? = null
        private val initialCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                //  new InitialAsyncTask(quranAppDatabaseInstance).execute();
            }
        }

        @Synchronized
        fun getInstance(context: Context?): VerbDatabase? {
            if (null == verbDatabaseInstance) {
                verbDatabaseInstance = databaseBuilder(
                    context!!,
                    VerbDatabase::class.java, "v3-conjugator.db"
                )
                    .createFromAsset("databases/v3-conjugator.db")
                    .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build()
            }
            return verbDatabaseInstance
        }
    }
}