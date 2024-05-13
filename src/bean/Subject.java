package bean;

import java.io.Serializable;

public class Subject implements Serializable {

	/**
	 * 科目コード:String
	 */
	private String subject_cd;

	/**
	 * 科目名:String
	 */
	private String subject_name;

	/**
	 * 所属校:School
	 */
	private School school;
	private boolean IS_ATTEND;


	public String getSubject_cd() {
		return subject_cd;
	}

	public void setSubject_cd(String subject_cd) {
		this.subject_cd = subject_cd;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public boolean isIS_ATTEND() {
		return IS_ATTEND;
	}

	public void setIS_ATTEND(boolean iS_ATTEND) {
		IS_ATTEND = iS_ATTEND;
	}



}
