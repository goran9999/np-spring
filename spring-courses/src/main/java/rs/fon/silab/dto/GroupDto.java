package rs.fon.silab.dto;

import java.util.Date;
import java.util.Objects;

public class GroupDto {

	private Long id;
	private String groupName;
	private CourseDto course;
	private Date createdAt;
	private int studentsCount;

	public GroupDto() {
		super();
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getStudentsCount() {
		return studentsCount;
	}

	public void setStudentsCount(int studentsCount) {
		this.studentsCount = studentsCount;
	}

	public GroupDto(Long id, String groupName, CourseDto course, Date createdAt, int studentsCount) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.course = course;
		this.createdAt = createdAt;
		this.studentsCount = studentsCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupName);
	}

	public CourseDto getCourse() {
		return course;
	}

	public void setCourse(CourseDto course) {
		this.course = course;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupDto other = (GroupDto) obj;
		return Objects.equals(groupName, other.groupName);
	}

	@Override
	public String toString() {
		return "GroupDto [groupName=" + groupName + "]";
	}

}