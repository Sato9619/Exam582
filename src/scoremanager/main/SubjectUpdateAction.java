package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		SubjectDao sDao = new SubjectDao();//学生Dao
		SchoolDao scDao = new SchoolDao();//学校Dao
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得　object型で取り出されるためTeacher型にキャストする
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		School SCHOOL_CD ;//学校コード


		//リクエストパラメータ―の取得 2
		String no = req.getParameter("no");//学番


		System.out.println("取り出している科目コードは"+ no);

		SCHOOL_CD = teacher.getSchool();

		//選択された科目コードから科目インスタンスを取得
		Subject subject = sDao.get(no,SCHOOL_CD);
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());


		req.setAttribute("SUBJECT_CD_set", list);

		// 学生が存在していた場合
		if (subject != null) {
			req.setAttribute("subject_cd", subject.getSubject_cd());
			req.setAttribute("subject_name", subject.getSubject_name());
		}
		// 学生が存在していなかった場合
		else {
			errors.put("SUBJECT_CD", "科目が存在していません");
			req.setAttribute("errors", errors);
		}

		//フォワード
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}

