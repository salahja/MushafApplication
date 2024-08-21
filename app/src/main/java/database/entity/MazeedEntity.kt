package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mazeed")
class MazeedEntity(/*
   root!!     TEXT    NOT NULL,
    form     TEXT    NOT NULL,
    verbtype TEXT    NOT NULL,
    babname  TEXT    NOT NULL,
    kov      TEXT    NOT NULL,
    kovname  TEXT    NOT NULL,
    id       INTEGER PRIMARY KEY AUTOINCREMENT
                     NOT NULL
);
 */
             var root: String,
                   var form: String,
                   var verbtype: String,
                   var babname: String,
                   var kov: String,
                   var kovname: String,
                   @field:PrimaryKey var id: Int
)