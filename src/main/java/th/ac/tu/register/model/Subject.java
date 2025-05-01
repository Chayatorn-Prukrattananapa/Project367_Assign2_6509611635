package th.ac.tu.register.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    private String subjectId;
    private String subjectName;
    private int credit;
    private int maxSeats;
    private int availableSeats;
    
    public Subject() {}

    public Subject(String subjectId, String subjectName, int credit, int maxStudent, int availableSeats) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credit = credit;
        this.maxSeats = maxStudent;
        this.availableSeats = availableSeats;
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

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxStudent) {
        this.maxSeats = maxStudent;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((subjectId == null) ? 0 : subjectId.hashCode());
        result = prime * result + ((subjectName == null) ? 0 : subjectName.hashCode());
        result = prime * result + credit;
        result = prime * result + maxSeats;
        result = prime * result + availableSeats;
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
        if (maxSeats != other.maxSeats)
            return false;
        if (availableSeats != other.availableSeats)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", credit=" + credit + ", maxSeats="
                + maxSeats + ", availableSeats=" + availableSeats + "]";
    }

    
}