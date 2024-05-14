
package bean;

import java.io.Serializable;
import java.util.Set;

public class Teacher extends User implements Serializable {
	/**
	 * 教員ID:String
	 */
	private String id;

	/**
	 * パスワード:String
	 */
	private String password;

	/**
	 * 教員名:String
	 */
	private String name;

	/**
	 * 所属校:School
	 */
	private School school;

	/**
	 * ゲッター、セッター
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		System.out.println("aaaaa");
		this.name = name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public void setClassNumSet(Set<Integer> classNumSet) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setEntYearSet(Set<Integer> entYearSet) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setSubjects(Set<String> subjectSet) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
