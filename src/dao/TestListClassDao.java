package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.TestListClass;

public class TestListClassDao  extends Dao{

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from test where =? ";

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 *
	 * @param rSet:リザルトセット
	 *            学校
	 * @return 学生のリスト:List<StudentListTest> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	private List<TestListClass> postFilter(ResultSet rSet) throws Exception {
		//リストを初期化
		List<TestListClass> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				TestListClass testlistclass = new TestListClass();
				//testインスタンスに検索結果をセット
				testlistclass.setClassNum(rSet.getString("class_num"));
				testlistclass.setEntYear(rSet.getInt("subject_cd"));
				testlistclass.setName(rSet.getString("subject_name"));
				testlistclass.setStudent_No(rSet.getString("student_no"));
				testlistclass.setPoints(testlistclass.putPoint(rSet.getInt("time"),rSet.getInt("point")));

				//リストに追加
				list.add(testlistclass);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;
	}




	/**
	 * filterメソッド 科目、学校、入学年度、クラス番号を指定してテストの一覧を取得する
	 *
	 * @param school:School
	 *            学校
	 * @param entYear:int
	 *            入学年度
	 * @param classNum:String
	 *            クラス番号
	 * @param subject:sbject
	 *            在学フラグ
	 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<TestListClass> filter(Subject subject, int entYear, String classNum, School school) throws Exception {

		System.out.println("☆科目、学校、入学年度、クラス番号選択の時☆");

		//リストを初期化
		List<TestListClass> list = new ArrayList<>();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		//SQLの条件文
		String condition = " and ent_year=? and class_num=? and subject_no=? and school_no=?";

		try{


			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition );
			//各部分に値を設定
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			//statement.setString(3, Subject.getsubjectNo());
			statement.setString(4, school.getCd());

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();

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

