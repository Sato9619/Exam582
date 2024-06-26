package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		int entYear = 0;// 入学年度

		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao sbDao = new SubjectDao();// クラス番号Daoを初期化

		TestListSubjectDao tlsbDao = new TestListSubjectDao();//学生別成績Dao

		Subject subject = null;
		School school=teacher.getSchool();

		//リクエストパラメータ―の取得 2

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject = sbDao.get(req.getParameter("f3"),teacher.getSchool());

		//subjectが入力されていた時
		if(subject != null){
			String sbName = subject.getSubject_name();
			req.setAttribute("f3", sbName);  	//リクエストに科目名をセット
		}

		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}

		System.out.println(entYear);
		System.out.println(classNum);
		System.out.println(subject);

		//DBからデータ取得 3
		List<TestListSubject> list = tlsbDao.filter(subject, entYear, classNum, school);// studentコードをもとにテストの一覧を取得

		System.out.println(list);

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
		//JSPへフォワード 7

		req.setAttribute("testlistsubject", list);//学生別成績のlistをセット
		req.setAttribute("subject", subject);//学生別成績のlistをセット

		// リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		// リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);

		req.setAttribute("class_num_set", listclassNum);	//学校コードで絞り込んだ所属している学校のクラスのリスト
		req.setAttribute("listsubject", listsubject);		//学校コードで絞り込んだ科目のリスト
		req.setAttribute("ent_year_set", entYearSet);		//入学年度の範囲の値

		System.out.println("★A★★★★★★★★★★★★★★★★");
		req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}
}
