package rs.fon.silab.dto;

import java.util.Date;

import rs.fon.silab.util.Semester;

public class CourseDto {

	private Long id;
	private String courseName;
	private Semester semester;
	private Date startDate;
	private Date endDate;
	private String level;
	private String imageUrl;
	private int groupCount;

	public CourseDto() {
		super();
	}

	public CourseDto(Long id, String courseName, Semester semester, Date startDate, Date endDate, String level,
			int groupCount, String imageUrl) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.semester = semester;
		this.startDate = startDate;
		this.endDate = endDate;
		this.level = level;
		this.groupCount = groupCount;
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	@Override
	public String toString() {
		return "CourseDto [id=" + id + ", courseName=" + courseName + ", semester=" + semester + ", startDate="
				+ startDate + ", endDate=" + endDate + ", level=" + level + ", groupCount=" + groupCount + "]";
	}

}