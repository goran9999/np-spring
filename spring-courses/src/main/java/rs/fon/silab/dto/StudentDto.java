package rs.fon.silab.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import rs.fon.silab.util.StudentStatus;

public class StudentDto {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private StudentStatus studentStatus;
	private List<GroupDto> groups;
	


	public List<GroupDto> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDto> groups) {
		this.groups = groups;
	}

	public StudentDto() {
		super();
	}

	public StudentDto(Long id, String firstName, String lastName, Date birthDate, StudentStatus studentStatus,List<GroupDto>groups) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.studentStatus = studentStatus;
		this.groups=groups;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public StudentStatus getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(StudentStatus studentStatus) {
		this.studentStatus = studentStatus;
	}



	@Override
	public int hashCode() {
		return Objects.hash(birthDate, firstName, id, lastName, studentStatus);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(id, other.id) && Objects.equals(lastName, other.lastName)
				&& studentStatus == other.studentStatus;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", studentStatus=" + studentStatus + "]";
	}
	
	
}