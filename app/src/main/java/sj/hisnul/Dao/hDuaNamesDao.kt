package sj.hisnul.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sj.hisnul.entity.hduanamesEnt

@Dao
interface hDuaNamesDao {
    @Query("select  * from hduanames where chap_id=:cid")
    fun getdualistbychapter(cid: Int):  LiveData<List<hduanamesEnt>>

/*

    @get:Query("select ROWID,* from hduanames group by chap_id")
    val duanames: LiveData<List<hduanamesEnt>>

*/




    @Query("select ROWID,* from hduanames group by chap_id")
    fun duanames(): Flow<List<hduanamesEnt>>


    @Query("select ROWID,* from hduanames group by chap_id")
    fun duanameslive(): LiveData<List<hduanamesEnt>>

    @Query("SELECT * FROM hduanames where category=:id ORDER BY category")
    fun getDuanamesid(id: String?): LiveData<List<hduanamesEnt>>

    @Query("SELECT * FROM hduanames where category LIKE '%' || :search || '%'")
    fun getDunamesbyCatId(search: String): LiveData<List<hduanamesEnt>>

    /*
    WHERE (category == 'search' OR
           category LIKE '%,search' OR
           category LIKE 'search,%' OR
           category LIKE '%,search,%');
     */
    @Query(
        "SELECT * FROM hduanames where category =:search ||" +
                " category LIKE '%,'||:search ||" +
                " category like :search || ',%'"
    )
    fun getDunamesbyCatIdnew(search: String?): LiveData<List<hduanamesEnt>>

    @Query("SELECT * FROM hduanames where ID=:id ORDER BY category")
    fun getDuanamesByID(id: String?): LiveData<List<hduanamesEnt>>

    @Query("SELECT * FROM hduanames where fav=:id ORDER BY fav")
    fun getFavdua(id: Int): LiveData<List<hduanamesEnt>>

    @Query("SELECT * FROM hduanames where fav=:id ORDER BY fav")
    fun getBookmarked(id: Int): LiveData<List<hduanamesEnt>>

    @Query("SELECT * FROM hduanames where ID=:id ORDER BY fav")
    fun isBookmarked(id: String?): LiveData<List<hduanamesEnt>>

    @Query(value = "UPDATE hduanames set fav=:fav where chap_id=:id")
  suspend  fun updateFav(fav: Int, id: Int): Int
}