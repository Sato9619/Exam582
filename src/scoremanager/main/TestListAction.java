package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		String subject="";//入力された在学フラグ
		String student_no = "";	//入力された在学フラグ
		int entYear = 0;// 入学年度
//		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
//		int year = todaysDate.getYear();// 現在の年を取得


		List<TestListStudent> testliststudents = null;// 学生別成績リスト
		List<TestListSubject> testlistsubject = null;// 科目別成績リスト

		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		TestListStudentDao tlsDao = new TestListStudentDao();// クラス番号Daoを初期化
		TestListSubjectDao tlsbDao = new TestListSubjectDao();// クラス番号Daoを初期化

		//SubjectDao subjectDao = new SubjectDao();// 科目Daoを初期化
		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		//リクエストパラメータ―の取得 2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject = req.getParameter("f3");
		student_no = req.getParameter("f4");//学生番号


		//DBからデータ取得 3
		//成績参照を選択したときに表示される初期画面
		//ClassNumDaoの中で学校コードからクラスを抽出してる
		List<String> listclassNum = cNumDao.filter(teacher.getSchool());

		//SubjectDaoの中で学校コードから科目を抽出してる
		//List<String> listsubject = SubjectDao.filter(teacher.getSchool());

//		if (entYearStr != null) {
//			// 数値に変換
//			entYear = Integer.parseInt(entYearStr);
//		}


//		if (entYear != 0 && !classNum.equals("0") && subject != null) {
//			// 入学年度とクラス番号を指定(両方選択）
//			//testlistsubject = tlsbDao.filter( subject, entYear, classNum,teacher.getSchool());
//
//			System.out.println("★A★★★★★★★★★★★★★★★★");
//		}  else {
//			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
//			req.setAttribute("errors", errors);
//			// 全学生情報を取得
//			students = sDao.filter(teacher.getSchool(), isAttend);
//			System.out.println("★D★★★★★★★★★★★★★★★★");
//		}


		//ビジネスロジック 4
//		if (entYearStr != null) {
//			// 数値に変換
//			entYear = Integer.parseInt(entYearStr);
//		}
//		// リストを初期化
//		List<Integer> entYearSet = new ArrayList<>();
//		// 10年前から10年後まで年をリストに追加
//		for (int i = year - 10; i < year + 10; i++) {
//			entYearSet.add(i);
//		}

		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストに入学年度をセット
//		req.setAttribute("entYear", entYear);
//		// リクエストにクラス番号をセット
//		req.setAttribute("classNum", classNum);
//
//		// リクエストに学生リストをセット
//		req.setAttribute("student_no", student_no);				//入学年度等の絞り込み結果がstudentsの箱に入ってる
		// リクエストにデータをセット
		req.setAttribute("class_num_set", listclassNum);			//学校コードで絞り込んだ所属している学校のクラスのリスト
		//req.setAttribute("listsubject", listsubject);			//学校コードで絞り込んだ所属している学校のクラスのリスト
//		req.setAttribute("ent_year_set", entYearSet);		//入学年度の範囲の値
		//JSPへフォワード 7
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}

}





