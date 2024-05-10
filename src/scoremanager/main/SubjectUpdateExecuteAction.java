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

public class SubjectUpdateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		SubjectDao sDao = new SubjectDao();// 科目Dao
		SchoolDao scDao = new SchoolDao();//学校Dao
		//boolean isAttend = false;//在学フラグ
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得object型で取り出されるためTeacher型にキャストする
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		School SCHOOL_CD ;//学校コード
		School sc_schoolcd;

		//リクエストパラメータ―の取得 2
//		String SUBJECT_CD= req.getParameter("SUBJECT_CD");
		String SUBJECT_CD= req.getParameter("subject_cd");
//		String SUBJECT_NAME = req.getParameter("SUBJECT_NAME");
		String SUBJECT_NAME = req.getParameter("name");
		//SCHOOL_CD = req.getParameter("SCHOOL_CD");//学校コード
		SCHOOL_CD = teacher.getSchool();
		//sc_schoolcd = scDao.get(SCHOOL_CD);


		// 在学フラグにチェックが入っていた場合
		/**
		if (isAttendStr != null) {
			// 在学フラグを立てる
			isAttend = true;
		}
		*/

		//DBからデータ取得 3
		Subject subject = sDao.get(SUBJECT_CD,SCHOOL_CD);// 科目コードから科目インスタンスを取得
		List<String> list = cNumDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得

		System.out.print("SUBJECT_CD");
		System.out.print(SUBJECT_CD);
		System.out.print("SUBJECT_CD");
		System.out.print("subject");
		System.out.print(subject);
		System.out.print("subject");


		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で4～5が分岐
		if (subject != null) {
			// 学生が存在していた場合
			// インスタンスに値をセット
			subject.setSubject_cd(SUBJECT_CD);
			subject.setSubject_name(SUBJECT_NAME);
			//sundent.setAttend(isAttend);
			// 学生を保存
			sDao.save(subject);
		} else {
			errors.put("SUBJECT_CD", "科目が存在していません");
		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7



		req.setAttribute("Subject_cd_set", list);
		System.out.print("errors");
		System.out.print(errors);
		System.out.print("errors");

		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("SUBJECT_CD", SUBJECT_CD);
			req.setAttribute("SUBJECT_NAME", SUBJECT_NAME);
			//req.setAttribute("name", name);
			//req.setAttribute("class_num", classNum);
			//req.setAttribute("is_attend", isAttendStr);
			req.getRequestDispatcher("subject_update.jsp").forward(req, res);
			return;
		}


		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}
