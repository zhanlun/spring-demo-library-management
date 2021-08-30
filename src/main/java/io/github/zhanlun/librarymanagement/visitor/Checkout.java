package io.github.zhanlun.librarymanagement.visitor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.zhanlun.librarymanagement.book.Book;
import io.github.zhanlun.librarymanagement.model.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checkout")
public class Checkout extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("checkouts")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("checkouts")
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    @Column(name = "checkout_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkoutDate;

    @Column(name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
