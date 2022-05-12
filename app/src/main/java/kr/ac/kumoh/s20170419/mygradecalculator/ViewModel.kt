package kr.ac.kumoh.s20170419.mygradecalculator

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONObject

class ViewModel(application: Application): AndroidViewModel(application) {
    companion object{
        const val QUEUE_TAG = "VolleyRequest"
    }
    private lateinit var mQueue: RequestQueue
    data class Subject(
        val requiredsubject_id: String?,
        val majorselection_id: String?,
        val geselection_id: String?,
        val name: String,
        val professor: String,
        val code: String,
        val room: String,
        val time: String,
        val division: String,
        val credit: String,
        val grade: String,
        val semester: String
    )
    val list = MutableLiveData<ArrayList<Subject>>()
    private val R_subject = ArrayList<Subject>()
    init {
        list.value = R_subject
        mQueue = VolleyRequest.getInstance(application).requestQueue
    }
    fun requestList(grade: String, semester: String, division: String): ArrayList<Subject> {
        val url = "https://expresssongdb-ocmes.run.goorm.io/?t=1651835082540"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                R_subject.clear()
                parseSubjectJSON(it, grade, semester, division)
                list.value = R_subject
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )
        request.tag = QUEUE_TAG
        mQueue.add(request)

        return R_subject
    }

    override fun onCleared() {
        super.onCleared()
        mQueue.cancelAll(QUEUE_TAG)
    }

    fun getR_subject(i : Int) = R_subject[i]
    fun getSize() = R_subject.size
    private fun parseSubjectJSON(items: JSONArray, Grade: String, Semester: String, Division: String){
        for (i in 0 until items.length()){
            val item: JSONObject = items.getJSONObject(i)
            val requiredsubject_id = item.getString("requiredsubject_id")
            val majorselection_id = item.getString("majorselection_id")
            val geselection_id = item.getString("geselection_id")
            val name = item.getString("name")
            val professor = item.getString("professor")
            val code = item.getString("code")
            val room = item.getString("room")
            val time = item.getString("time")
            val division = item.getString("division")
            val credit = item.getString("credit")
            val grade = item.getString("grade")
            val semester = item.getString("semester")

            if(Grade == grade && Semester == semester) {
                if(Division == "필수" && requiredsubject_id != "null") {
                    R_subject.add(
                        Subject(
                            requiredsubject_id, majorselection_id, geselection_id, name,
                            professor, code, room, time, division, credit, grade, semester
                        )
                    )
                }
                else if(Division == "전공선택" && majorselection_id != "null") {
                    R_subject.add(
                        Subject(
                            requiredsubject_id, majorselection_id, geselection_id, name,
                            professor, code, room, time, division, credit, grade, semester
                        )
                    )
                }
                else if(Division == "교양선택" && geselection_id != "null"){
                    R_subject.add(
                        Subject(
                            requiredsubject_id, majorselection_id, geselection_id, name,
                            professor, code, room, time, division, credit, grade, semester
                        )
                    )
                }
                else
                    R_subject.add(
                        Subject(
                            requiredsubject_id, majorselection_id, geselection_id, name,
                            professor, code, room, time, division, credit, grade, semester
                        )
                    )
            }
        }
    }
}