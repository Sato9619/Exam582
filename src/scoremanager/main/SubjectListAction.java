package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class SubjectListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
				HttpSession session = req.getSession();//セッション
				Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー

				String STBJECT_CD="";// 科目コード
				String SUBJECT_NAME = "";//科目名


				//リクエストパラメータ―の取得 2
				STBJECT_CD = req.getParameter("f1");
				SUBJECT_NAME = req.getParameter("f2");


				//DBからデータ取得 3
				//学生管理を選択したときに表示される初期画面
				//ClassNumDaoの中で学校コードからクラスを抽出してる


				/**
				List<String> list = cNumDao.filter(teacher.getSchool());

				if (entYearStr != null) {
					// 数値に変換
					entYear = Integer.parseInt(entYearStr);
				}
				*/


				/**
				if (entYear != 0 && !classNum.equals("0")) {
					// 入学年度とクラス番号を指定(両方選択）
					students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);

					System.out.println("★A★★★★★★★★★★★★★★★★");
				} else if (entYear != 0 && classNum.equals("0")) {
					// 入学年度のみ選択
					students = sDao.filter(teacher.getSchool(), entYear, isAttend);
					System.out.println("★B★★★★★★★★★★★★★★★★");
				} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
					// 指定なしの場合
					// 全学生情報を取得
					students = sDao.filter(teacher.getSchool(), isAttend);
					System.out.println("★C★★★★★★★★★★★★★★★★");
				} else {
					errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
					req.setAttribute("errors", errors);
					// 全学生情報を取得
					students = sDao.filter(teacher.getSchool(), isAttend);
					System.out.println("★D★★★★★★★★★★★★★★★★");
				}
				*/


				/**
				//ビジネスロジック 4
				if (entYearStr != null) {
					// 数値に変換
					entYear = Integer.parseInt(entYearStr);
				}
				// リストを初期化
				List<Integer> entYearSet = new ArrayList<>();
				// 10年前から10年後まで年をリストに追加
				for (int i = year - 10; i < year + 10; i++) {
					entYearSet.add(i);
				}
				*/


				/**
				//DBへデータ保存 5
				//なし
				//レスポンス値をセット 6
				// リクエストに入学年度をセット
				req.setAttribute("f1", entYear);
				// リクエストにクラス番号をセット
				req.setAttribute("f2", classNum);
				// 在学フラグが送信されていた場合
				if (isAttendStr != null) {
					// リクエストに在学フラグをセット
					req.setAttribute("f3", isAttendStr);
				}
				// リクエストに学生リストをセット
				req.setAttribute("students", students);				//入学年度等の絞り込み結果がstudentsの箱に入ってる
				// リクエストにデータをセット
				req.setAttribute("class_num_set", list);			//学校コードで絞り込んだ所属している学校のクラスのリスト
				req.setAttribute("ent_year_set", entYearSet);		//入学年度の範囲の値
				//JSPへフォワード 7
				 *
				 */
				req.getRequestDispatcher("subject_list.jsp").forward(req, res);
			}


}
