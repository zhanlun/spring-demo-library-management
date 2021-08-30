package io.github.zhanlun.librarymanagement.book;

import io.github.zhanlun.librarymanagement.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject extends NamedEntity {
}
