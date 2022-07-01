package rs.fon.silab.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import rs.fon.silab.util.StudentStatus;

/**
 * 
 * @author goran
 * 
 * Class presenting student entity in database
 */
@Entity(name = "student")
public class Student {
	
	/**
	 * Id of student
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * First name of student
	 */
	private String firstName;
	/**
	 * Last name of student
	 */
	private String lastName;
	/**
	 * Date of birth of student
	 */
	private Date birthDate;
	/**
	 * Status of student
	 */
	private StudentStatus studentStatus;
	/**
	 * List of groups student is part of
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="group")
	List<Group>groups;

	/**
	 * Non parameter constructor
	 */
	public Student() {
		super();
	}

	/**
	 * Parameter constructor
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param studentStatus
	 */
	public Student(Long id, String firstName, String lastName, Date birthDate, StudentStatus studentStatus) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.studentStatus = studentStatus;
	}

	/**
	 * Returns id of student
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id of student
	 * @return
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
	 * Returns first name of student
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name of student
	 * @return
	 */
	public void setFirstName(String firstName) {
		if(firstName==null) {
			throw new NullPointerException("Name can not be null!");
		}
		if(firstName.trim()=="") {
			throw new IllegalArgumentException("Name can not be empty!");
		}
		this.firstName = firstName;
	}

	/**
	 * Returns last name of student
	 * @return
	 */
	public String getLastName() {
		
		return lastName;
	}

	/**
	 * Sets last name of student
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		if(lastName==null) {
			throw new NullPointerException("Name can not be null!");
		}
		if(lastName.trim()=="") {
			throw new IllegalArgumentException("Name can not be empty!");
		}
		this.lastName = lastName;
	}

	/**
	 * Returns date of birth of student
	 * @return
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets or updates date of birth of student
	 * @param birthDate
	 */
	public void setBirthDate(Date birthDate) {
		if(birthDate.getTime()>new Date().getTime()) {
			throw new IllegalArgumentException("Birth date can not be in future!");
		}
		this.birthDate = birthDate;
	}

	/**
	 * Returns current status of student
	 * @return
	 */
	public StudentStatus getStudentStatus() {
		return studentStatus;
	}

	/**
	 * Sets or updates status of student
	 * @param studentStatus
	 */
	public void setStudentStatus(StudentStatus studentStatus) {
		if(studentStatus==null) {
			throw new NullPointerException("Wrong student status input!");
		}
		this.studentStatus = studentStatus;
	}

	/**
	 * Returns all groups related to a student
	 * @return
	 */
	public List<Group> getGroups() {
		return groups;
	}

	/**
	 * Sets or updates groups related to a student
	 * @param groups
	 */
	public void setGroups(List<Group> groups) {
		if(groups.contains(null)) {
			throw new NullPointerException("Null can not be stored as group!");
		}
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, firstName, groups, id, lastName, studentStatus);
	}

	/**
	 * Checks if two student objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(groups, other.groups) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && studentStatus == other.studentStatus;
	}

	/**
	 * Returns string representation of student object
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", studentStatus=" + studentStatus + ", groups=" + groups + "]";
	}
	
	
	
	
	
	
}