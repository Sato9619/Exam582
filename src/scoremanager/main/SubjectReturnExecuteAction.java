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

public class SubjectReturnExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//System.out.println("動いてますか？？");
		//ローカル変数の宣言 1
		SubjectDao sDao = new SubjectDao();// 科目Dao
		SchoolDao scDao = new SchoolDao();//学校Dao
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");// ログインユーザーを取得object型で取り出されるためTeacher型にキャストする
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ


		School SCHOOL_CD ;//学校コード

		//noを探す
		//subeject_cdなのかsubject_nameを確認
		String SUBJECT_CD = req.getParameter("no");


		System.out.println(req.getParameter("no"));

		SCHOOL_CD = teacher.getSchool();


		// 科目コードから科目インスタンスを取得
		Subject subject = sDao.get(SUBJECT_CD,SCHOOL_CD);

		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());


		// 科目が存在していた場合
		if (subject != null) {
			// 科目を保存
			sDao.subject_return(subject);
		}
		//科目が存在していない場合
		else {
			errors.put("SUBJECT_CD", "科目が存在していません");
		}


		req.setAttribute("Subject_cd_set", list);


		//エラーがあった場合、更新画面へ戻る
		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("SUBJECT_CD", SUBJECT_CD);
						req.getRequestDispatcher("subject_return.jsp").forward(req, res);
			return;
		}


		//フォワード
		req.getRequestDispatcher("subject_return_done.jsp").forward(req, res);
	}
}
