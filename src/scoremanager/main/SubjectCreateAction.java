package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectCreateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		/**
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		*/

		//リクエストパラメータ―の取得 2
		//なし

		//DBからデータ取得 3
		//List<String> list = cNumDao.filter(teacher.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得

		//ビジネスロジック 4

		//DBへデータ保存 5
		//なし

		//レスポンス値をセット 6
		//req.setAttribute("class_num_set", list);			//クラス番号のlistをセット
		//req.setAttribute("ent_year_set", entYearSet);		//入学年度の範囲のリストをセット

		//JSPへフォワード 7
		req.getRequestDispatcher("subject_create.jsp").forward(req, res);
	}
}
