package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		String url = "";

		Teacher teacher = new Teacher();
		School school = new School();

		//これ作った
		TeacherDao teacherDAO=new TeacherDao();

		//リクエストパラメータ―の取得 2
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		//String name = req.getParameter("namae");
		//String school_cd = req.getParameter("school_cd");

		//DBからデータ取得 3
		teacher=teacherDAO.login(id,password);
		//なし
		//ビジネスロジック 4
/*
		teacher.setId(id);
		teacher.setPassword(password);
		teacher.setName(name);

		school.setCd(school_cd);
		school.setName("金沢情報ITクリエイター専門学校");

		teacher.setSchool(school);//School型*/

		// 認証済みフラグを立てる
//		teacher.setAuthenticated(true);
		System.out.println("①★★★★★★★★★★★★★★");
		//もし、ログインが成功したら
		if(teacher!=null){
			System.out.println("②★★★★★★★★★★★★★★");
			//Sessionを有効にする
			HttpSession session = req.getSession(true);
			teacher.setAuthenticated(true);


			//セッションに"user"という変数名で値はTeacher変数の中身
			session.setAttribute("user", teacher);


				//リダイレクト
			url = "main/Menu.action";
			res.sendRedirect(url);
		}else{
				System.out.println("ffff");
				//認証失敗
				 List<String> errors = new ArrayList<>();
					errors.add("ログインに失敗しました。IDまたはパスワードが正しくありません。");
					req.setAttribute("errors", errors);

				//JSPへフォワード
				url = "login.jsp";
				req.getRequestDispatcher(url).forward(req, res);



	}
}
}

