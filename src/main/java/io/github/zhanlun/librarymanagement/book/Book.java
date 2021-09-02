package io.github.zhanlun.librarymanagement.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.zhanlun.librarymanagement.model.NamedEntity;
import io.github.zhanlun.librarymanagement.visitor.Checkout;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
public class Book extends NamedEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    @JsonIgnoreProperties("books")
    @NotNull
    private Subject subject;

    @Column
    @NotBlank
    private String publisher;

    @Column(name = "published_year")
    @NotNull
    private Integer publishedYear;

    @Column(length = 1000)
    @NotBlank
    private String contributors;

    @Column(length = 100000)
    @NotBlank
    private String summary;

    @Column(length = 1000)
    @NotBlank
    private String coverImagePath;

    @Column
    @NotNull
    private Integer numberOfCopy;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("book")
    private List<Checkout> checkouts;

    @Transient
    private Integer availableCopy;

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

    public Integer getAvailableCopy() {
        return checkouts == null || checkouts.isEmpty() ?
                numberOfCopy :
                numberOfCopy - (int) checkouts.stream().filter(c -> c.getReturnDate() == null).count();
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }

    public void setAvailableCopy(Integer availableCopy) {
        this.availableCopy = availableCopy;
    }
}
