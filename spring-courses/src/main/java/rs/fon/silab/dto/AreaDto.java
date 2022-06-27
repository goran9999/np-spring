package rs.fon.silab.dto;

import java.util.Objects;

public class AreaDto {
	private String areaName;
	private int classesCount;
	private Long courseId;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public AreaDto() {
		super();
	}

	public AreaDto(String areaName, int classesCount, Long courseId) {
		super();
		this.areaName = areaName;
		this.classesCount = classesCount;
		this.courseId = courseId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getClassesCount() {
		return classesCount;
	}

	public void setClassesCount(int classesCount) {
		this.classesCount = classesCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(areaName, classesCount, courseId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaDto other = (AreaDto) obj;
		return Objects.equals(areaName, other.areaName) && classesCount == other.classesCount
				&& Objects.equals(courseId, other.courseId);
	}

	@Override
	public String toString() {
		return "AreaDto [areaName=" + areaName + ", classesCount=" + classesCount + ", courseId=" + courseId + "]";
	}

}