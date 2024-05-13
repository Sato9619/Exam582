package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String classNum = "";//入力されたクラス番号
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao sbDao = new SubjectDao();// 科目Daoを初期化



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

		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストにクラス番号をセット
		req.setAttribute("classNum", classNum);
		// リクエストにデータをセット
		req.setAttribute("class_num_set", listclassNum);	//学校コードで絞り込んだ所属している学校のクラスのリスト
		req.setAttribute("listsubject", listsubject);		//学校コードで絞り込んだ科目のリスト
		req.setAttribute("ent_year_set", entYearSet);		//入学年度の範囲の値
		//JSPへフォワード 7
		System.out.println("★★★★★★★★★★★★★★★★★");
		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}

}





