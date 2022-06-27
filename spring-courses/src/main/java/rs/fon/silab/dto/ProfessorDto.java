package rs.fon.silab.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import rs.fon.silab.model.Group;
import rs.fon.silab.util.DegreeLevel;

public class ProfessorDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private DegreeLevel degreeLevel;
	private List<GroupDto> groups;
	public List<GroupDto> getGroups() {
		return groups;
	}
	public void setGroups(List<GroupDto> groups) {
		this.groups = groups;
	}
	public ProfessorDto() {
		super();
	}
	public ProfessorDto(Long id, String firstName, String lastName, Date birthDate, DegreeLevel degreeLevel,List<GroupDto>groups) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.degreeLevel = degreeLevel;
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
	public DegreeLevel getDegreeLevel() {
		return degreeLevel;
	}
	public void setDegreeLevel(DegreeLevel degreeLevel) {
		this.degreeLevel = degreeLevel;
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthDate, degreeLevel, firstName, id, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfessorDto other = (ProfessorDto) obj;
		return Objects.equals(birthDate, other.birthDate) && degreeLevel == other.degreeLevel
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}
	@Override
	public String toString() {
		return "ProfessorDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", degreeLevel=" + degreeLevel + "]";
	}
	
	
}