package rs.fon.silab.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "area")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String areaName;
	private int classesCount;
	
	@ManyToOne
	@JoinColumn(name = "courseId")
	Course course;

	public Area() {
		super();
	}

	public Area(Long id, String areaName, int classesCount, Course course) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.classesCount = classesCount;
		this.course = course;
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

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		if(areaName==null) {
			throw new NullPointerException("Name can not be null!");
		}
		if(areaName.trim()=="") {
			throw new IllegalArgumentException("Name can not be empty!");
		}
		this.areaName = areaName;
	}

	public int getClassesCount() {
		return classesCount;
	}

	public void setClassesCount(int classesCount) {
		if(classesCount<0) {
			throw new IllegalArgumentException("Classes count can not be less than 0!");
		}
		this.classesCount = classesCount;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		if(course.getId()==null) {
			throw new NullPointerException("Course can not be null");
		}
		this.course = course;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaName, classesCount, course, id);
	}

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

	@Override
	public String toString() {
		return "Area [id=" + id + ", areaName=" + areaName + ", classesCount=" + classesCount + ", course=" + course
				+ "]";
	}
	
	
	
	

}