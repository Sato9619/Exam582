package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション

		TestListStudentDao tlsDao = new TestListStudentDao();//学生別成績Dao
		StudentDao sDao = new StudentDao();//学生別成績Dao

		String student_no = "";//学生番号
		Student student = null;//学生
		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		//リクエストパラメータ―の取得 2


		//DBからデータ取得 3
		student = sDao.get(student_no);// 学生番号から学生インスタンスを取得
		List<TestListStudent> list = tlsDao.filter(student);// studentコードをもとにテストの一覧を取得


		//ビジネスロジック 4

		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

//		if (list == null) {// 入学年度が選択されていない場合
//			errors.put("student_no", "このフィールドを入力してください");
//		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7
		System.out.println("★A★★★★★★★★★★★★★★★★");
		req.setAttribute("testliststudent", list);//学生別成績のlistをセット

		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
