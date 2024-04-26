package bean;

public class TestListStudent {

	/**
	 *  科目名：String
	 */
	private String subject_name;

	/**
	 *  科目コード：String
	 */
	private String subject_cd;

	/**
	 *  回数：int
	 */
	private int time;

	/**
	 *  得点：int
	 */
	private int point;


	//せったげった

	public String getSubjectName() {
		return subject_name;
	}

	public void setSubjectName(String subjectName) {
		this.subject_name = subjectName;
	}

	public String getSubjectCd() {
		return subject_cd;
	}

	public void setSubjectCd(String subjectCd) {
		this.subject_cd = subjectCd;
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
