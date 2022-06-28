package rs.fon.silab.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import rs.fon.silab.util.Semester;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "course_name")
	private String courseName;
	private Semester semester;
	@Column(name = "startDate")
	private Date startDate;
	@Column(name = "endDate")
	private Date endDate;
	@Column(name = "imageUrl")
	private String imageUrl;
	@Column(name = "groupsCount")
	private int groupsCount;
	private String level;
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Group.class)
	Set<Group> groups;
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Area.class)
	Set<Area> areas;

	public Course() {
		super();
	}

	public String getImageUrl() {
		
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		if(imageUrl==null) {
			throw new NullPointerException("Image url can not be null!");
		}
		this.imageUrl = imageUrl;
		if(imageUrl.trim()=="") {
			throw new IllegalArgumentException("Image url can note be empty!");
		}
	}

	public Course(Long id, String courseName, Semester semester, Date startDate, Date endDate, String imageUrl,
			int groupsCount, String level) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.semester = semester;
		this.startDate = startDate;
		this.endDate = endDate;
		this.imageUrl = imageUrl;
		this.groupsCount = groupsCount;
		this.level = level;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id==null) {
			throw new NullPointerException();
		}
		if(id<0) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	public String getCourseName() {

		return courseName;
	}

	public void setCourseName(String courseName) {
		if (courseName == null || courseName.trim() == "") {
			throw new IllegalArgumentException();
		}
		this.courseName = courseName;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		if(semester==null) {
			throw new NullPointerException("Semester can not be null");
		}
		this.semester = semester;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		if (startDate == null) {
			throw new IllegalArgumentException();
		}
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		if (endDate == null || endDate.getTime() < new Date().getTime()) {
			throw new IllegalArgumentException("End date can not be in past!");
		}
		this.endDate = endDate;
	}

	public int getGroupsCount() {
		return groupsCount;
	}

	public void setGroupsCount(int groupsCount) {
		if (groupsCount == 0) {
			throw new NullPointerException();
		}
		if (groupsCount < 0) {
			throw new IllegalArgumentException();
		}
		this.groupsCount = groupsCount;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", semester=" + semester + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", groupsCount=" + groupsCount + ", level=" + level + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseName, endDate, groupsCount, id, level, semester, startDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseName, other.courseName) && Objects.equals(endDate, other.endDate)
				&& groupsCount == other.groupsCount && Objects.equals(id, other.id)
				&& Objects.equals(level, other.level) && semester == other.semester
				&& Objects.equals(startDate, other.startDate);
	}

}