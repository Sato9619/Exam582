package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao  extends Dao{
	//あげる♡select distinct subject.name,subject.subject_cd,test.no,test.point from test inner join subject on subject.subject_cd=test.subject_cd where student_no = ?

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from test where student_no=? ";

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 *
	 * @param rSet:リザルトセット
	 *            学校
	 * @return 学生のリスト:List<StudentListTest> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		//リストを初期化
		List<TestListStudent> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				TestListStudent testliststudent = new TestListStudent();
				//学生インスタンスに検索結果をセット
				testliststudent.setPoint(rSet.getInt("point"));
				testliststudent.setSubjectCd(rSet.getString("subject_cd"));
				testliststudent.setSubjectName(rSet.getString("subject_name"));
				testliststudent.setTime(rSet.getInt("class_num"));
				//リストに追加
				list.add(testliststudent);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * filterメソッド 学生を指定してテストの一覧を取得する
	 *
	 * @param student:Student
	 *            学生
	 *
	 * @return テストのリスト:List<TestListStudent> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<TestListStudent> filter(Student student) throws Exception {

		System.out.println("☆学生選択の時☆");

		//リストを初期化
		List<TestListStudent> list = new ArrayList<>();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//リザルトセット
		ResultSet rSet = null;

		//SQL分のソート
		String order = " order by subject_no asc";


		try{


			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + order);
			//各部分に値を設定
			statement.setString(1, student.getNo());

			//上記のSQL文を実行し結果を取得する
			rSet = statement.executeQuery();

			list = postFilter(rSet);

		}catch (Exception e){
			throw e;
		}finally {
			//プリペアステートメントを閉じる
			if (statement != null){
				try {
					statement.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;

	}

}

