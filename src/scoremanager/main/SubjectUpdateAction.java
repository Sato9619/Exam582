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
//		String no = req.getParameter("student_no");//学番
		String no = req.getParameter("no");//学番
		//SCHOOL_CD = req.getParameter("SCHOOL_CD");//学校コード


		System.out.println("取り出している科目コードは"+ no);

		SCHOOL_CD = teacher.getSchool();

		//DBからデータ取得 3
		Subject subject = sDao.get(no,SCHOOL_CD);//選択された科目コードから科目インスタンスを取得
		List<String> list = cNumDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得


		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//条件で手順4~6の内容が分岐
		req.setAttribute("SUBJECT_CD_set", list);
		if (subject != null) {// 学生が存在していた場合
			req.setAttribute("subject_cd", subject.getSubject_cd());
			req.setAttribute("subject_name", subject.getSubject_name());
			//req.setAttribute("name", student.getName());
			//req.setAttribute("class_num", student.getClassNum());
			//req.setAttribute("is_attend", student.isAttend());
		} else {// 学生が存在していなかった場合
			errors.put("SUBJECT_CD", "科目が存在していません");
			req.setAttribute("errors", errors);
		}
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}

