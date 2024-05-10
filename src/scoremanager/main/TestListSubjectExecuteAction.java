package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		TestListSubjectDao tlsbDao = new TestListSubjectDao();//学生別成績Dao
		SubjectDao sbDao = new SubjectDao();//科目Dao

		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		String student_no = "";	//入力された在学フラグ
		int entYear = 0;// 入学年度
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		Subject subject = null;
		School school=teacher.getSchool();

		Map<String, String> errors = new HashMap<>();// エラーメッセージ


		//リクエストパラメータ―の取得 2
		entYear = Integer.parseInt(req.getParameter("f1"));
		classNum = req.getParameter("f2");
		subject = sbDao.get(req.getParameter("f3"));

		System.out.println(entYear);
		System.out.println(classNum);
		System.out.println(subject);

		//DBからデータ取得 3
		List<TestListSubject> list = tlsbDao.filter(subject, entYear, classNum, school);// studentコードをもとにテストの一覧を取得

		System.out.println(list);

		//ビジネスロジック 4

		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

//		if (list == null) {// 入学年度が選択されていない場合
//			errors.put("student_no", "このフィールドを入力してください");
//		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7

		req.setAttribute("testlistsubject", list);//学生別成績のlistをセット
		System.out.println("★A★★★★★★★★★★★★★★★★");
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}
