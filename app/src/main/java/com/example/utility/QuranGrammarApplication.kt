/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.utility

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import androidx.preference.PreferenceManager
import com.example.mushafconsolidated.quranrepo.QuranGraph
import com.example.utility.ThemeHelper.applyTheme
import database.verbrepo.VerbGraph
import leakcanary.LeakCanary
import leakcanary.LeakCanary.config
import sj.hisnul.newepository.Graph
import java.util.Locale

class QuranGrammarApplication : Application() {
    /*
        private val applicationScope = CoroutineScope(SupervisorJob())
        val database by lazy { QuranAppDatabase.getInstance(this) }
        val repository by lazy { DuaInfoRepository(database!!.gethDuaCategoryDao(),

        ) }*/


    //  val repository by lazy { DuaInfoRepository(database.hDuaNamesDao()) }
    /** By using lazy the database and the repository are only created when they're needed
     * rather than when the application starts
     **/
    //val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

//    val database: QuranAppDatabase by lazy { QuranAppDatabase.getDatabase(this) }
    // val repository by lazy { MazeedInfoRepository(database.MazeedDao()) }
    override fun onCreate() {
        super.onCreate()

        val config: LeakCanary.Config = config
        if (context == null) {
            context = this
        }
        Graph.provide(this)
        VerbGraph.provide(this)
        QuranGraph.provide(this)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        //  String theme = sharedPreferences.getString("theme", 1);
        val themePref = sharedPreferences.getString("themepref", "white")
        applyTheme(themePref!!)
    }

    companion object {
        @JvmField
        var context: Context? = null

        //Check application language
        val instance: Context?
            get() {
                //Check application language
                var locale: Locale
                val config = Configuration()
                context!!.resources.updateConfiguration(config, context!!.resources.displayMetrics)
                return context
            }
    }
}