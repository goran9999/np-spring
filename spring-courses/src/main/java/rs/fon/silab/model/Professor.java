package rs.fon.silab.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import rs.fon.silab.util.DegreeLevel;

@Entity(name = "professor")
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private Date bithDate;
	private DegreeLevel degreeLevel;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "group")
	List<Group>groups;
	
	
	public List<Group> getGroups() {
		return groups;
	}
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	public Professor() {
		super();
	}
	public Professor(Long id, String firstName, String lastName, Date bithDate, DegreeLevel degreeLevel) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bithDate = bithDate;
		this.degreeLevel = degreeLevel;
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
	public Date getBithDate() {
		return bithDate;
	}
	public void setBithDate(Date bithDate) {
		this.bithDate = bithDate;
	}
	public DegreeLevel getDegreeLevel() {
		return degreeLevel;
	}
	public void setDegreeLevel(DegreeLevel degreeLevel) {
		this.degreeLevel = degreeLevel;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bithDate, degreeLevel, firstName, id, lastName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(bithDate, other.bithDate) && degreeLevel == other.degreeLevel
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName);
	}
	@Override
	public String toString() {
		return "Professor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bithDate=" + bithDate
				+ ", degreeLevel=" + degreeLevel + "]";
	}
	
	
	
	
	
}