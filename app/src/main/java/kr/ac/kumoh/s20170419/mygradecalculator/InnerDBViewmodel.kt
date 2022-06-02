package kr.ac.kumoh.s20170419.mygradecalculator

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class InnerDBViewmodel(context: Application) : AndroidViewModel(context) {
    private val weekdb = ScheduleDatabase.getDatabase(context)!!.weekDao()
    private val gpdb = GPDatabase.getDatabase(context)!!.gpDao()
//    var red: Int = 0
//    var blue: Int = 0
//    var green: Int = 0
//    var redList: ArrayList<Int> = arrayListOf()
//    var blueList: ArrayList<Int> = arrayListOf()
//    var grrenList: ArrayList<Int> = arrayListOf()

    fun connect(gs: String, subject: ViewModel.Subject) {
        val key = gs + subject.code
        val data = weekstate(
            key,
            gs,
            subject.college,
            subject.subject,
            subject.name,
            subject.professor,
            subject.code,
            subject.room,
            subject.time,
            subject.division,
            subject.credit,
            subject.grade,
            subject.semester
        )
        weekdb.insert(data)
    }

    fun connect(db: weekstate) {
        val data = gpstate(
            0,
            db.gs,
            db.subject,
            db.name,
            db.credit,
            "A+"
        )
        gpdb.insert(data)
    }

    fun connect(gs: String, name: String?, credit: String?, gp: String?, check: Boolean) {
        val subject = if (check) "전공" else null
        val data = gpstate(
            0,
            gs,
            subject,
            name,
            credit,
            gp
        )
        gpdb.insert(data)
    }

    fun getInfo(): List<gpstate> {
        return gpdb.getAll()
    }

    fun getInfo(gs: String): List<gpstate> {
        return gpdb.getInfo(gs)
    }

    fun delInfo(gs: String) {
        gpdb.delete(gs)
    }

    fun getSubject(gs: String): List<weekstate> {
        return weekdb.getSubject(gs)
    }

    fun resetDB(gs: String){
        weekdb.delete(gs)
    }

    fun deleteDB(name : String?, gs: String?){
        weekdb.deletename(name, gs)
    }

    fun update(db: gpstate) {
        gpdb.update(db)
    }
}