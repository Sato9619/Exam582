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


	public Subject get(String cd,School school) throws Exception {

		//学生インスタンスを初期化
		Subject subject =new Subject();

		//データベースへのコネクションを確立
		Connection connection = getConnection();

		//プリペアードステートメント
		PreparedStatement statement = null;

		try{


			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE SUBJECT_CD = ? and SCHOOL_CD = ?");
			//各部分に値を設定
			statement.setString(1, cd);
			statement.setString(2, school.getCd());

			//上記のSQL文を実行し結果を取得する
			ResultSet rSet = statement.executeQuery();

			//学校Daoを初期化
			SchoolDao schoolDao =new SchoolDao();

			if (rSet.next()){
				subject.setSubject_cd(rSet.getString("subject_cd"));
				subject.setSubject_name(rSet.getString("subject_name"));

				//学校フィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
				subject.setIS_ATTEND(rSet.getBoolean("IS_ATTEND"));

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



	private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
		//リストを初期化
		List<Subject> list = new ArrayList<>();

		try{
			//リザルトセットを全件走査
			while (rSet.next()){
				Subject subject = new Subject();
				//学生インスタンスに検索結果をセット
				subject.setSubject_cd(rSet.getString("SUBJECT_CD"));
				subject.setSubject_name(rSet.getString("SUBJECT_NAME"));
				subject.setSchool(school);;
				//リストに追加
				list.add(subject);

			}
		} catch (SQLException | NullPointerException e){
			e.printStackTrace();
		}
		return list;


	}



		private String baseSql = "select * from subject where school_cd=?";

		public List<Subject> filter(School school,boolean ISATTEND) throws Exception {

			//リストを初期化
			List<Subject> list = new ArrayList<>();
			//コネクションを確立
			Connection connection = getConnection();
			//プリペアードステートメント
			PreparedStatement statement = null;
			//リザルトセット
			ResultSet rSet = null;

//			//在学
			String conditionISAttend = "";
			if(ISATTEND){
				conditionISAttend ="and IS_ATTEND = true";
			}

			try{
//
				statement = connection.prepareStatement(baseSql + conditionISAttend);

				//各部分に値を設定
				statement.setString(1, school.getCd());

				//上記のSQL文を実行し結果を取得する
				rSet = statement.executeQuery();

				list = postFilter(rSet, school);

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
				Subject old = get(subject.getSubject_cd(),subject.getSchool());
				if(old == null){
					//科目が存在しなかった場合
					//プリペアードステートメントにINSERT文をセット
					statement = connection.prepareStatement(
							"insert into subject(school_cd,subject_cd,subject_name,IS_ATTEND)values(?,?,?,true)");
					//プリペアードステートメントに値をバインド
					statement.setString(1, subject.getSchool().getCd());
					statement.setString(2, subject.getSubject_cd());
					statement.setString(3, subject.getSubject_name());
				}else{
					//科目が存在した場合
					//プリペアードステートメントにUPDATE文をセット
					//UPDATE SUBJECT  set SUBJECT_NAME = '林' where SUBJECT_CD = 'Z99';
					statement = connection.prepareStatement(
							"update subject set subject_name = ? where subject_cd = ?");
					//プリペアードステートメントに値をバインド
					//statement.setString(1, subject.getSchool().getCd());
					statement.setString(1, subject.getSubject_name());
					statement.setString(2, subject.getSubject_cd());
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
		public List<Subject> filter_return(School school,boolean ISATTEND) throws Exception {

			//リストを初期化
			List<Subject> list = new ArrayList<>();
			//コネクションを確立
			Connection connection = getConnection();
			//プリペアードステートメント
			PreparedStatement statement = null;
			//リザルトセット
			ResultSet rSet = null;

//			//在学
			String conditionISAttend = "";
			if(ISATTEND){
				conditionISAttend ="and IS_ATTEND = false";
			}

			try{
//
				statement = connection.prepareStatement(baseSql + conditionISAttend);

				//各部分に値を設定
				statement.setString(1, school.getCd());

				//上記のSQL文を実行し結果を取得する
				rSet = statement.executeQuery();

				list = postFilter(rSet, school);

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

		public boolean delete(Subject subject)throws Exception{
			//コネクションを確立
			Connection connection = getConnection();
			//プリペアードステートメント
			PreparedStatement statement = null;
			//実行件数
			int count = 0;

			try{
				//データベースから科目を取得
				Subject old = get(subject.getSubject_cd(),subject.getSchool());

					//科目が存在した場合
					//プリペアードステートメントにUPDATE文をセット
					//UPDATE SUBJECT  set SUBJECT_NAME = '林' where SUBJECT_CD = 'Z99';
					statement = connection.prepareStatement(
							"UPDATE SUBJECT SET IS_ATTEND = FALSE  where SUBJECT_NAME = ? and SCHOOL_CD = ? ");

					//プリペアードステートメントに値をバインド
					//statement.setString(1, subject.getSchool().getCd());
					statement.setString(2, subject.getSchool().getCd());
					statement.setString(1, subject.getSubject_name());
					//statement.setString(2, subject.getSubject_cd());


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



public boolean subject_return(Subject subject)throws Exception{
	//コネクションを確立
	Connection connection = getConnection();
	//プリペアードステートメント
	PreparedStatement statement = null;
	//実行件数
	int count = 0;

	try{
		//データベースから科目を取得
		Subject old = get(subject.getSubject_cd(),subject.getSchool());

			//科目が存在した場合
			//プリペアードステートメントにUPDATE文をセット
			//UPDATE SUBJECT  set SUBJECT_NAME = '林' where SUBJECT_CD = 'Z99';
			statement = connection.prepareStatement(
					"UPDATE SUBJECT SET IS_ATTEND = true  where SUBJECT_NAME = ? and SCHOOL_CD = ? ");

			//プリペアードステートメントに値をバインド
			//statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getSchool().getCd());
			statement.setString(1, subject.getSubject_name());
			//statement.setString(2, subject.getSubject_cd());


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

}

