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

/**
 * 
 * @author goran
 *
 * Class representing course entity in database
 */
@Entity
@Table(name = "course")
public class Course {

	/**
	 * Id of course
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Name of course
	 */
	@Column(name = "course_name")
	private String courseName;
	/**
	 * Semmester during which course is active
	 */
	private Semester semester;
	/**
	 * Start date of course
	 */
	@Column(name = "startDate")
	private Date startDate;
	/**
	 * End date of course
	 */
	@Column(name = "endDate")
	private Date endDate;
	@Column(name = "imageUrl")
	/**
	 * Logo image of course
	 */
	private String imageUrl;
	@Column(name = "groupsCount")
	/**
	 * Total amount of groups related to course
	 */
	private int groupsCount;
	/**
	 * Level of course
	 */
	private String level;
	/**
	 * List of groups that are related to course
	 */
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Group.class)
	Set<Group> groups;
	/**
	 * List of areas related to course
	 */
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Area.class)
	Set<Area> areas;

	/**
	 * Non parameter constructor
	 */
	public Course() {
		super();
	}

	/**
	 * Returns logo image of course
	 * @return
	 */
	public String getImageUrl() {
		
		return imageUrl;
	}

	/**
	 * Sets or updateds logo image of course
	 * @param imageUrl
	 */
	public void setImageUrl(String imageUrl) {
		if(imageUrl==null) {
			throw new NullPointerException("Image url can not be null!");
		}
		this.imageUrl = imageUrl;
		if(imageUrl.trim()=="") {
			throw new IllegalArgumentException("Image url can note be empty!");
		}
	}

	/**
	 * Parameter constructor
	 * @param id
	 * @param courseName
	 * @param semester
	 * @param startDate
	 * @param endDate
	 * @param imageUrl
	 * @param groupsCount
	 * @param level
	 */
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

	/**
	 * Returns id of course
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id of course
	 * @param id
	 */
	public void setId(Long id) {
		if(id==null) {
			throw new NullPointerException();
		}
		if(id<0) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	/**
	 * Returns name of course
	 * @return
	 */
	public String getCourseName() {

		return courseName;
	}

	/**
	 * Sets name of course
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		if (courseName == null || courseName.trim() == "") {
			throw new IllegalArgumentException();
		}
		this.courseName = courseName;
	}

	/**
	 * Returns semester during which course is active
	 * @return
	 */
	public Semester getSemester() {
		return semester;
	}

	/**
	 * Sets semester for course
	 * @param semester
	 */
	public void setSemester(Semester semester) {
		if(semester==null) {
			throw new NullPointerException("Semester can not be null");
		}
		this.semester = semester;
	}

	/**
	 * Returns start date of course
	 * @return
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets start date of course
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		if (startDate == null) {
			throw new IllegalArgumentException();
		}
		this.startDate = startDate;
	}

	/**
	 * Returns end date of course
	 * @return
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets end date of course
	 * @param endDate
	 */
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

	/**
	 * Returns level of course
	 * @return
	 */
	public String getLevel() {
		return level;
	}
	
	/**
	 * Sets level of course
	 * @param level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * String representation of course object
	 */
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", semester=" + semester + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", groupsCount=" + groupsCount + ", level=" + level + "]";
	}


	/**
	 * Checks if two course objects are equal
	 */
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