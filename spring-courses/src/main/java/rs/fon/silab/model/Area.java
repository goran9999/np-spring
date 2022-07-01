package rs.fon.silab.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author goran Class that represents area of specific course
 */
@Entity(name = "area")
public class Area {
	/**
	 * Id of area
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Name of specific area
	 */
	private String areaName;
	/**
	 * Total amount of classes which attend course with specific area
	 */
	private int classesCount;

	/**
	 * Course to which area is related
	 */
	@ManyToOne
	@JoinColumn(name = "courseId")
	Course course;

	/**
	 * Non parameter constructor
	 */
	public Area() {
		super();
	}

	/**
	 * Constructor with parameters
	 * 
	 * @param id
	 * @param areaName
	 * @param classesCount
	 * @param course
	 */
	public Area(Long id, String areaName, int classesCount, Course course) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.classesCount = classesCount;
		this.course = course;
	}

	/**
	 * Returns id of area
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id of area
	 * @param id
	 */
	public void setId(Long id) {
		if (id == null) {
			throw new NullPointerException();
		}
		if (id < 0) {
			throw new IllegalArgumentException();
		}
		this.id = id;
	}

	/**
	 * Returns name of area
	 * @return
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Sets name of area
	 * @param areaName
	 */
	public void setAreaName(String areaName) {
		if (areaName == null) {
			throw new NullPointerException("Name can not be null!");
		}
		if (areaName.trim() == "") {
			throw new IllegalArgumentException("Name can not be empty!");
		}
		this.areaName = areaName;
	}

	/**
	 * Returns number of classes for area
	 * @return
	 */
	public int getClassesCount() {
		return classesCount;
	}

	/**
	 * Sets or updates number of classes of area
	 * @param classesCount
	 */
	public void setClassesCount(int classesCount) {
		if (classesCount < 0) {
			throw new IllegalArgumentException("Classes count can not be less than 0!");
		}
		this.classesCount = classesCount;
	}

	/**
	 * Returns course area is related to
	 * @return
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Sets course related to area
	 * @param course
	 */
	public void setCourse(Course course) {
		if (course.getId() == null) {
			throw new NullPointerException("Course can not be null");
		}
		this.course = course;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaName, classesCount, course, id);
	}

	/**
	 * Checks if two area objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		return Objects.equals(areaName, other.areaName) && classesCount == other.classesCount
				&& Objects.equals(course, other.course) && Objects.equals(id, other.id);
	}

	/**
	 * Returns string representation of area object
	 */
	@Override
	public String toString() {
		return "Area [id=" + id + ", areaName=" + areaName + ", classesCount=" + classesCount + ", course=" + course
				+ "]";
	}

}