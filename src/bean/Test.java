package bean;

import java.io.Serializable;

import javax.security.auth.Subject;

public class Test implements Serializable{
	/**
	 * 生徒の箱：Student
	 */

	private String student;


	/**
	 * クラス番号：String
	 */

	private String classNum;


	/**
	 * 科目の箱：Subject
	 */

	private Subject subject;

	/**
	 * 学校の箱:School
	 */

	private int time;

	/**
	 * 点数:point
	 */

	private int point;

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}


}