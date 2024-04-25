package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import tool.Action;

public class LoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";

		Teacher teacher = new Teacher();
		School school = new School();

		//リクエストパラメータ―の取得 2
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("namae");
		String school_cd = req.getParameter("school_cd");

		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4

		//Teacher.java(bean)使用              教師１の場合
		teacher.setId(id);					// admin をTeacherインスタンスのidにセット
		teacher.setPassword(password);		// password をTeacherインスタンスのidにセット
		teacher.setName(name);				// 大原花子 をTeacherインスタンスのidにセット

		//School.java(bean)使用
		school.setCd(school_cd);			// knz をschoolインスタンスの㏅にセット
		school.setName("金沢校");			// "金沢校" をschoolインスタンスのnameにセット

		//Teacher.java(bean)の中のSchool型
		teacher.setSchool(school);			//school.cd=knz school.name="金沢校" をTeacherインスタンスのschoolにセット

		// 認証済みフラグを立てる
		teacher.setAuthenticated(true);

		//Sessionを有効にする
		HttpSession session = req.getSession(true);
		//セッションに"user"という変数名で値はTeacher変数の中身
		session.setAttribute("user", teacher);		//セッションのユーザーの箱に上記のteacherに含まれる内容が入ってる

		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7
		//req.getRequestDispatcher("main/Menu.action").forward(req, res);

		//リダイレクト
		url = "main/Menu.action";
		res.sendRedirect(url);
	}

}
