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

public class SubjectDeleteExecuteAction extends Action {
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

		//System.out.println("a");
		System.out.println(req.getParameter("no"));

		SCHOOL_CD = teacher.getSchool();





		//DBからデータ取得 3
		Subject subject = sDao.get(SUBJECT_CD,SCHOOL_CD);// 科目コードから科目インスタンスを取得
		List<String> list = cNumDao.filter(teacher.getSchool());//ログインユーザーの学校コードをもとにクラス番号の一覧を取得

		//System.out.print("SUBJECT_CD");
		//System.out.print("subject");
		//System.out.print(subject);
		//System.out.print("subject");


		//ビジネスロジック 4
		//DBへデータ保存 5
		if (subject != null) {
			// 学生が存在していた場合
			// インスタンスに値をセット
			//subject.setSubject_name(SUBJECT_CD);

			// 学生を保存
			sDao.delete(subject);
		} else {
			errors.put("SUBJECT_CD", "科目が存在していません");
		}

		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7



		req.setAttribute("Subject_cd_set", list);
		//System.out.print("errors");
		//System.out.print(errors);
		//System.out.print("errors");

		if(!errors.isEmpty()){//エラーがあった場合、更新画面へ戻る
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("SUBJECT_CD", SUBJECT_CD);
						req.getRequestDispatcher("subject_delete.jsp").forward(req, res);
			return;
		}


		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);
	}
}
