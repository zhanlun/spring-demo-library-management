package io.github.zhanlun.librarymanagement.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.zhanlun.librarymanagement.model.NamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book extends NamedEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties("books")
    private Subject subject;

    @Column
    private String publisher;

    @Column(name = "published_year")
    private Integer publishedYear;

    @Column(length = 1000)
    private String contributors;

    @Column(length = 100000)
    private String summary;

    @Column
    private String coverImagePath;

    @Column
    private Integer numberOfCopy;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(String contributors) {
        this.contributors = contributors;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImagePath() {
        return coverImagePath;
    }

    public void setCoverImagePath(String coverImagePath) {
        this.coverImagePath = coverImagePath;
    }

    public Integer getNumberOfCopy() {
        return numberOfCopy;
    }

    public void setNumberOfCopy(Integer numberOfCopy) {
        this.numberOfCopy = numberOfCopy;
    }
}
