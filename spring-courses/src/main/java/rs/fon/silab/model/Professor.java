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

/**
 * 
 * @author goran
 * 
 * Class presenting professor entity in database
 */
@Entity(name = "professor")
public class Professor {
	
	/**
	 * Id of professor
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * First name of professor
	 */
	private String firstName;
	/**
	 * Last name of professor
	 */
	private String lastName;
	/**
	 * Birth date of professor
	 */
	private Date bithDate;
	/**
	 * Degree level of professor
	 */
	private DegreeLevel degreeLevel;
	/**
	 * List of groups in which professor is teaching
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "group")
	List<Group>groups;
	
	/**
	 * Returns all groups where professor is teaching
	 * @return
	 */
	public List<Group> getGroups() {
		return groups;
	}
	/**
	 * 
	 * Sets or updates list of groups where professor is teaching
	 * @param groups
	 */
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	/**
	 * Non parameter constructor
	 */
	public Professor() {
		super();
	}
	/**
	 * Parameter constructor
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param bithDate
	 * @param degreeLevel
	 */
	public Professor(Long id, String firstName, String lastName, Date bithDate, DegreeLevel degreeLevel) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bithDate = bithDate;
		this.degreeLevel = degreeLevel;
	}
	
	/**
	 * Returns id of professor
	 * @return
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Sets id of professor
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
	 * Returns first name of professor
	 * @return
	 */
	public String getFirstName() {
		
		return firstName;
	}
	/**
	 * Sets first name of professor
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
	 * Returns last name of professor
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets last name of professor
	 * @return
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
	 * Returns birth date of professor
	 * @return
	 */
	public Date getBithDate() {
		
		return bithDate;
	}
	/**
	 * Sets birth date of professor
	 * @return
	 */
	public void setBithDate(Date bithDate) {
		if(bithDate.getTime()>new Date().getTime()) {
			throw new IllegalArgumentException("Birth date can not be in future!");
		}
		this.bithDate = bithDate;
	}
	/**
	 * Returns degree level of professor
	 * @return
	 */
	public DegreeLevel getDegreeLevel() {
		
		return degreeLevel;
	}
	/**
	 * Sets degree level of professor
	 * @return
	 */
	public void setDegreeLevel(DegreeLevel degreeLevel) {
		if(degreeLevel==null) {
			throw new NullPointerException("Degree level can not be null!");
		}
		if(!(degreeLevel instanceof DegreeLevel)) {
			throw new IllegalArgumentException("Wrong degree level input!");
		}
		this.degreeLevel = degreeLevel;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bithDate, degreeLevel, firstName, id, lastName);
	}
	
	/**
	 * Checks if two professor objects are equal
	 */
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
	/**
	 * Returns string representation of professor object
	 */
	@Override
	public String toString() {
		return "Professor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", bithDate=" + bithDate
				+ ", degreeLevel=" + degreeLevel + "]";
	}
	
	
	
	
	
}