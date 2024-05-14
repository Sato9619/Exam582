package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

		String STBJECT_CD="";// 科目コード
		String SUBJECT_NAME = "";//科目名
		List<Subject> subjects = null;// 科目リスト
		String IS_ATTENDStr = "";
		boolean IS_ATTEND = true;


		ClassNumDao cNumDao = new ClassNumDao();//科目Dao

		SubjectDao sDao = new SubjectDao();//科目Dao


		//リクエストパラメータ―の取得 2
		STBJECT_CD = req.getParameter("f1");
		SUBJECT_NAME = req.getParameter("f2");
		IS_ATTENDStr = req.getParameter("f3");


		if(IS_ATTENDStr != null){
			IS_ATTEND = true;
		}



		//DBからデータ取得 3
		//学生管理を選択したときに表示される初期画面
		//ClassNumDaoの中で学校コードからクラスを抽出してる


		List<String> list = cNumDao.filter(teacher.getSchool());
		subjects = sDao.filter(teacher.getSchool(),IS_ATTEND);

		// リクエストに学生リストをセット
		req.setAttribute("subjects", subjects);

		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);


		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}


}
