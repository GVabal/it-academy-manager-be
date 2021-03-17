package lt.akademija.itacademymanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "streamId")
    private Stream stream;

    private int overallGrade;
    private String overallComment;
    private int abilityToLearnGrade;
    private String abilityToLearnComment;
    private int motivationGrade;
    private String motivationComment;
    private int extraMileGrade;
    private String extraMileComment;
    private int communicationGrade;
    private String communicationComment;

    public static class Builder {
        private Integer id;
        private Student student;
        private Stream stream;
        private int overallGrade;
        private String overallComment;
        private int abilityToLearnGrade;
        private String abilityToLearnComment;
        private int motivationGrade;
        private String motivationComment;
        private int extraMileGrade;
        private String extraMileComment;
        private int communicationGrade;
        private String communicationComment;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder withStream(Stream stream) {
            this.stream = stream;
            return this;
        }

        public Builder withOverallGrade(int overallGrade) {
            this.overallGrade = overallGrade;
            return this;
        }

        public Builder withOverallComment(String overallComment) {
            this.overallComment = overallComment;
            return this;
        }

        public Builder withAbilityToLearnGrade(int abilityToLearnGrade) {
            this.abilityToLearnGrade = abilityToLearnGrade;
            return this;
        }

        public Builder withAbilityToLearnComment(String abilityToLearnComment) {
            this.abilityToLearnComment = abilityToLearnComment;
            return this;
        }

        public Builder withMotivationGrade(int motivationGrade) {
            this.motivationGrade = motivationGrade;
            return this;
        }

        public Builder withMotivationComment(String motivationComment) {
            this.motivationComment = motivationComment;
            return this;
        }

        public Builder withExtraMileGrade(int extraMileGrade) {
            this.extraMileGrade = extraMileGrade;
            return this;
        }

        public Builder withExtraMileComment(String extraMileComment) {
            this.extraMileComment = extraMileComment;
            return this;
        }

        public Builder withCommunicationGrade(int communicationGrade) {
            this.communicationGrade = communicationGrade;
            return this;
        }

        public Builder withCommunicationComment(String communicationComment) {
            this.communicationComment = communicationComment;
            return this;
        }

        public Review build() {
            Review review = new Review();
            review.id = this.id;
            review.student = this.student;
            review.stream = this.stream;
            review.overallGrade = this.overallGrade;
            review.overallComment = this.overallComment;
            review.abilityToLearnGrade = this.abilityToLearnGrade;
            review.abilityToLearnComment = this.abilityToLearnComment;
            review.motivationGrade = this.motivationGrade;
            review.motivationComment = this.motivationComment;
            review.extraMileGrade = this.extraMileGrade;
            review.extraMileComment = this.extraMileComment;
            review.communicationGrade = this.communicationGrade;
            review.communicationComment = this.communicationComment;
            return review;
        }
    }
}
