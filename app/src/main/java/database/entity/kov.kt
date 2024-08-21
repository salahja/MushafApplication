package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kov")
class kov(var c1: String ,  var c2: String,
          var c3: String ,
          var kov: String,
          var rulename: String ,
          var example: String ,

          @PrimaryKey(autoGenerate = true)
          var id: Int = 0)