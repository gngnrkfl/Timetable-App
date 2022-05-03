import java.util.*
import kotlin.collections.ArrayList

class auto_schedule(var credit: Int, val s_subject: String,
           val e_subject: String, var rest: Int, var ge: Int) {
    companion object {
        fun main(args: Array<String>) {
            var credit: Int = 5
            var name: String
            var s_subject: String
            val e_subject: String
            var rest: Int = 5
            var ge: Int = 0
            val slist = Array(5, { arrayOfNulls<String>(12)})

            //DB에서 전공, 교양, 필수 불러와서 배열 생성, 아직 생성 x
            val em_list = arrayListOf<String>()
            val ge_list = arrayListOf<String>()
            val cs_list = arrayListOf<String>()

           while (cs_list.isNotEmpty()) { // 배열에서 필수과목(division) 불러오기 4-1학기가 없을때까지
                //slist[DB.time/10][DB.time%10] = DB.code
                //credit -= DB.credit
                // 배열.delete(DB.code), code일부분이 일치 하는게 있으면?
            }

            if(rest != 5) { // 공강일 존재
                for(i in 0..11)
                    if(slist[rest][i] == null)
                        //종료, 공강일 불가능 반환
                slist[rest] = arrayOf("none") // 고쳐야함
            }

            while(ge != 0) {
                if (subject_add(ge_list, slist, "교양", credit) == 1)
                    --ge
            }

            while(credit != 0) {
                if(em_list.isNotEmpty())
                    subject_add(em_list, slist, "전공", credit)
                else
                    if(ge_list.isNotEmpty())
                        subject_add(ge_list, slist, "교양", credit)
                    else
                        print("아 망함 ㅋㅋ")
                        break
            }
        }

        fun subject_add(slist: ArrayList<String>, list: Array<Array<String?>>, division: String, credit: Int): Int {
            val random = Random()
            var num = random.nextInt(list.size)
            if (division == "교양") {
                if (credit - list[num] > 0) {// list로 불러온 과목의 학점 체크
                    // slist에 추가;
                    // credit -= list[num].credit
                    // list[num] 삭제
                    return 1
                } else {
                    //list[num] 삭제
                }
            } else if (division == "전공") {
                if (credit - list[num] > 0) {// list로 불러온 과목의 학점 체크
                    // slist에 추가;
                    // credit -= list[num].credit
                    // list[num] 삭제
                    return 1
                } else {
                    //list[num] 삭제
                }
            }
            return 0
        }
    }
}