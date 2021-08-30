package io.github.zhanlun.librarymanagement.visitor;

import io.github.zhanlun.librarymanagement.model.Person;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "visitor")
public class Visitor extends Person {
}
