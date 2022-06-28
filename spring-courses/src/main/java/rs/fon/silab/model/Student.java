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

@Entity(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private StudentStatus studentStatus;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="group")
	List<Group>groups;

	public Student() {
		super();
	}

	public Student(Long id, String firstName, String lastName, Date birthDate, StudentStatus studentStatus) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.studentStatus = studentStatus;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName==null) {
			throw new NullPointerException("Name can not be null!");
		}
		if(firstName.trim()=="") {
			throw new IllegalArgumentException("Name can not be empty!");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName==null) {
			throw new NullPointerException("Name can not be null!");
		}
		if(lastName.trim()=="") {
			throw new IllegalArgumentException("Name can not be empty!");
		}
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		if(birthDate.getTime()>new Date().getTime()) {
			throw new IllegalArgumentException("Birth date can not be in future!");
		}
		this.birthDate = birthDate;
	}

	public StudentStatus getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(StudentStatus studentStatus) {
		if(!(studentStatus instanceof StudentStatus)) {
			throw new IllegalArgumentException("Wrong student status input!");
		}
		this.studentStatus = studentStatus;
	}

	public List<Group> getGroups() {
		return groups;
	}

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

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", studentStatus=" + studentStatus + ", groups=" + groups + "]";
	}
	
	
	
	
	
	
}