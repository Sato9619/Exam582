package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao  extends Dao{


	public Subject get(String cd) throws Exception {

		//学生インスタンスを初期化
		Subject subject =new Subject();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		try{


			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE SUBJECT_CD = ?");
			//各部分に値を設定
			statement.setString(1, cd);

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();

			//学校Daoを初期化
			SchoolDao schoolDao =new SchoolDao();

			if (rSet.next()){
				subject.setSubject_cd(rSet.getString("subject_cd"));
				subject.setSubject_name(rSet.getString("subject_name"));

				//学校フィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));

			}else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject = null;
			}

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
		return subject;
	}



		private String baseSql = "select * from subject where school_cd=?";

		private List<Subject> filter(School school) throws Exception {

			//リストを初期化
			List<Subject>list = new ArrayList<>();
			//コネクションを確立
			Connection connection = getConnection();
			//プリペアードステートメント
			PreparedStatement statement = null;
			//リザルトセット
			ResultSet rSet = null;
			//SQL文の条件
			String condition = "and subject_cd=? and name=?";
			//SQL文のソート
			String order = "order by no asc";

			try{
				statement = connection.prepareStatement(baseSql + condition + order);
			}catch(Exception e){
				throw e;
			}finally{
				if(statement != null){
					try{
						statement.close();
					}catch(SQLException sqle){
						throw sqle;
					}
				}
				if(connection != null){
					try{
						connection.close();
					}catch(SQLException sqle){
						throw sqle;
					}
				}
			}

			return list;
		}


		public boolean save(Subject subject)throws Exception{
			//コネクションを確立
			Connection connection = getConnection();
			//プリペアードステートメント
			PreparedStatement statement = null;
			//実行件数
			int count = 0;

			try{
				//データベースから科目を取得
				Subject old = get(subject.getSubject_cd());
				if(old == null){
					//科目が存在しなかった場合
					//プリペアードステートメントにINSERT文をセット
					statement = connection.prepareStatement(
							"insert into subject(school_cd,subject_cd,subject_name)values(?,?,?)");
					//プリペアードステートメントに値をバインド
					statement.setString(1, subject.getSchool().getCd());
					statement.setString(2, subject.getSubject_cd());
					statement.setString(3, subject.getSubject_name());
				}else{
					//科目が存在した場合
					//プリペアードステートメントにUPDATE文をセット
					statement = connection.prepareStatement(
							"update subject set school_cd = ?,subject_name = ?");
					//プリペアードステートメントに値をバインド
					statement.setString(1, subject.getSchool().getCd());
					statement.setString(2, subject.getSubject_name());
				}

			//プリぺードステートメントを実行
			count = statement.executeUpdate();

		}catch(Exception e){
			throw e;
		}finally{
			if(statement != null){
				try{
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection != null){
				try{
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		if(count >0){
			return true;
		}else{
			return false;
		}
	}

		//private boolean delete(Subject subject)throws Exception{

		//}

}
