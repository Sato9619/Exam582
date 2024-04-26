package bean;

import java.util.HashMap;
import java.util.Map;

public class TestListClass {

	/**
	 * 入学年度:int
	 */
	private int entYear;

	/**
	 * 学生番号:String
	 */
	private String student_No;

	/**
	 * 学生名:String
	 */
	private String name;

	/**
	 * クラス番号:String
	 */
	private String classNum;

	/**
	 * 得点りすと:Map<integer,integer>
	 */
	private Map<Integer,Integer> points;

	//せったげった

	public void TestListSubject(){
		points=new HashMap<>();
	}

	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getStudent_No() {
		return student_No;
	}

	public void setStudent_No(String student_No) {
		this.student_No = student_No;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Map<Integer, Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}

	public String getPoint(int key){
		Integer k=points.get(key);
		if (k==null){
			return "-";
		}else{
			return k.toString();
		}
	}

	public Map<Integer, Integer> putPoint(int key,int value){
	 points.put(key, value);
		return points;
	}
}
