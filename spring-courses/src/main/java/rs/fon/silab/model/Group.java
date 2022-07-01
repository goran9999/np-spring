package rs.fon.silab.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author goran
 * 
 *         Class representign group entity in database
 */
@Entity
@Table(name = "course_group")
public class Group {

	/**
	 * Id of group
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Name of group
	 */
	private String name;

	/**
	 * Total amount of students who are part of group
	 */
	private int studentsCount;

	/**
	 * Date when group is created(possible to be null)
	 */
	@Column(nullable = true)
	private Date createdAt;

	/**
	 * Course related to group
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course")
	Course course;

	/**
	 * List of professors who teach group
	 */
	@ManyToMany
	List<Professor> professors;

	/**
	 * List of students who are part of group
	 */
	@ManyToMany
	List<Student> students;

	/**
	 * Non parameter constructor
	 */
	public Group() {
		super();
	}

	/**
	 * Paramater constructor
	 * 
	 * @param id
	 * @param name
	 * @param studentsCount
	 * @param createdAt
	 * @param course
	 */
	public Group(Long id, String name, int studentsCount, Date createdAt, Course course) {
		super();
		this.id = id;
		this.name = name;
		this.studentsCount = studentsCount;
		this.createdAt = createdAt;
		this.course = course;
	}

	/**
	 * Returns id of group
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id of group
	 * 
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
	 * Returns name of group
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of group
	 * 
	 * @param name
	 */
	public void setName(String name) {
		if (name == null) {
			throw new NullPointerException("Name can not be null!");
		}
		if (name.trim() == "") {
			throw new IllegalArgumentException("Name can not be empty!");
		}
		this.name = name;
	}

	/**
	 * Returns total amount of students who are part of group
	 * 
	 * @return
	 */
	public int getStudentsCount() {
		return studentsCount;
	}

	/**
	 * Sets or updates number of studnets in group
	 * 
	 * @param studentsCount
	 */
	public void setStudentsCount(int studentsCount) {
		if (studentsCount < 0) {
			throw new IllegalArgumentException();
		}
		this.studentsCount = studentsCount;
	}

	/**
	 * Returns date when group was created
	 * 
	 * @return
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets date when group was created
	 * 
	 * @param createdAt
	 */
	public void setCreatedAt(Date createdAt) {
		if (createdAt.getTime() > new Date().getTime()) {
			throw new IllegalArgumentException("Date creation can not be in future!");
		}
		this.createdAt = createdAt;
	}

	/**
	 * Returns course related to group
	 * 
	 * @return
	 */
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		if (course.getId() == null) {
			throw new NullPointerException();
		}
		this.course = course;
	}

	/**
	 * Checks if two group objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return Objects.equals(course, other.course) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& studentsCount == other.studentsCount;
	}

	/**
	 * Returns string representation of group object
	 */
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", studentsCount=" + studentsCount + ", createdAt=" + createdAt
				+ ", course=" + course + "]";
	}

}