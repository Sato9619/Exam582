package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		TestListStudentDao tlsDao = new TestListStudentDao();//学生別成績Dao
		StudentDao sDao = new StudentDao();//学生別成績Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao sbDao = new SubjectDao();// 科目Daoを初期化

		String student_no = "";//学生番号
		Student student = null;//学生
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		//リクエストパラメータ―の取得 2
		student_no = req.getParameter("f4");//学生番号

		System.out.println(student_no);

		//DBからデータ取得 3

		student = sDao.get(student_no);// 学生番号から学生インスタンスを取得

		if(student == null){
			errors.put("student", "学生情報が存在しません");
		}else{
			List<TestListStudent> list = tlsDao.filter(student);// studentコードをもとにテストの一覧を取得
			req.setAttribute("testliststudent", list);//学生別成績のlistをセット
			req.setAttribute("student", student);//学生情報のstudentをセット

		}
		System.out.println(student);


		//リクエストパラメータ―の取得 2

		//DBからデータ取得 3
		//成績参照を選択したときに表示される初期画面
		//ClassNumDaoの中で学校コードからクラスを抽出してる
		List<String> listclassNum = cNumDao.filter(teacher.getSchool());

		//SubjectDaoの中で学校コードから科目を抽出してる
		List<Subject> listsubject = sbDao.filter(teacher.getSchool(),true);

		//ビジネスロジック 4

		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から10年後まで年をリストに追加
		for (int i = year - 10; i < year + 10; i++) {
			entYearSet.add(i);
		}
		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7

		req.setAttribute("class_num_set", listclassNum);	//学校コードで絞り込んだ所属している学校のクラスのリスト
		req.setAttribute("listsubject", listsubject);		//学校コードで絞り込んだ科目のリスト
		req.setAttribute("ent_year_set", entYearSet);		//入学年度の範囲の値

		if(!errors.isEmpty()){
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
			return;
		}
		System.out.println("★A★★★★★★★★★★★★★★★★");
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
