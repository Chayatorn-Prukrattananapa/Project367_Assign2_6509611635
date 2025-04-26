package th.ac.tu.register.model;

import java.util.Arrays;
import jakarta.persistence.GeneratedValue;

public class Subject {
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String subjectId;
    private String subjectName;
    private int credit;
    private int maxStudent;
    private String[] preRequisite;
    private String description;

    public Subject() {}
    
    public Subject(Long id, String subjectId, String subjectName, int credit, int maxStudent, String description) {
        this.id = id;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credit = credit;
        this.maxStudent = maxStudent;
        this.preRequisite = null;
        this.description = description;
    }

    public Subject(String subjectId, String subjectName, int credit, int maxStudent, String[] preRequisite, String description) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credit = credit;
        this.maxStudent = maxStudent;
        this.preRequisite = preRequisite;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public String[] getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(String[] preRequisite) {
        this.preRequisite = preRequisite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
        result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
        result = prime * result + credit;
        result = prime * result + maxStudent;
        result = prime * result + Arrays.hashCode(preRequisite);
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subject other = (Subject) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (subjectId == null) {
            if (other.subjectId != null)
                return false;
        } else if (!subjectId.equals(other.subjectId))
            return false;
        if (subjectName == null) {
            if (other.subjectName != null)
                return false;
        } else if (!subjectName.equals(other.subjectName))
            return false;
        if (credit != other.credit)
            return false;
        if (maxStudent != other.maxStudent)
            return false;
        if (!Arrays.equals(preRequisite, other.preRequisite))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Subject [id=" + id + ", subjectId=" + subjectId + ", subjectName=" + subjectName + ", credit=" + credit
                + ", maxStudent=" + maxStudent + ", preRequisite=" + Arrays.toString(preRequisite) + ", description="
                + description + "]";
    }
}
