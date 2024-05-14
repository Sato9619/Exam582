package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SchoolDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession(); //セッション
		SubjectDao sDao = new SubjectDao();//科目Dao
		SchoolDao scDao = new SchoolDao();//学校Dao

		String SUBJECT_CD = "";//科目コード
		String SUBJECT_NAME;//科目名
		School SCHOOL_CD ;//学校コード
		Subject subject = null;//科目


		// エラーメッセージ
		Map<String, String> errors = new HashMap<>();

		// ログインユーザーを取得
		Teacher teacher = (Teacher) session.getAttribute("user");
		SCHOOL_CD = teacher.getSchool();


		//リクエストパラメータ―の取得 2
		SUBJECT_CD = req.getParameter("SUBJECT_CD");//科目コード
		SUBJECT_NAME = req.getParameter("SUBJECT_NAME");//科目名


		//プリントしてみる
		System.out.println(SUBJECT_CD);
		System.out.println(SUBJECT_NAME);



		//DBからデータ取得 3
		// 学生番号から学生インスタンスを取得
		subject = sDao.get(SUBJECT_CD,SCHOOL_CD);

		System.out.print("科目は");
		System.out.println(subject);



		// 科目が未登録だった場合
		if (subject == null) {
			System.out.println("いふ文内にいるよ");
				//エラーならってないため
				//errors.put("SUBJECT_CD", "このフィールドを入力してください");


			    // 科目インスタンスを初期化
				subject = new Subject();


				// インスタンスに値をセット
				subject.setSubject_cd(SUBJECT_CD);
				subject.setSubject_name(SUBJECT_NAME);
				subject.setSchool(((Teacher)session.getAttribute("user")).getSchool());


				//科目コードの文字の長さをSUB_LENにintで入れる
				int SUB_LEN = SUBJECT_CD.length();
				System.out.println(SUB_LEN);

				//科目コードの文字の長さが3でなければ
				if(SUB_LEN != 3){
					errors.put("SUBJECT_CD", "科目コードは３文字で入力してください");
				}else{
				// 科目を保存
				sDao.save(subject);
				}


		//科目が過去に登録済みだった場合
		} else if(subject != null){
				errors.put("SUBJECT_CD", "科目コードが重複しています");
		}











		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7



		req.setAttribute("subject_cd_set", SUBJECT_CD);//科目コードをセット
		req.setAttribute("subject_name_set", SUBJECT_NAME);//科目名をセット



		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("SUBJECT_CD", SUBJECT_CD);
			req.setAttribute("SUBJECT_NAME", SUBJECT_NAME);
			//req.setAttribute("name", name);
			//req.setAttribute("class_num", classNum);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}
}

