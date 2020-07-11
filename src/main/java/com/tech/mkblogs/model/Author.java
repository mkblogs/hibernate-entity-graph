package com.tech.mkblogs.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "author")
@NamedEntityGraph(name = "graph.author.books.publisher", 
	attributeNodes = @NamedAttributeNode(value = "books", subgraph = "books"),
	subgraphs = @NamedSubgraph(name = "books", attributeNodes = @NamedAttributeNode("publisher"))    )

@NamedEntityGraph(name = "graph.author.books", attributeNodes = @NamedAttributeNode(value = "books"))

public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Version
	private Integer version;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@ManyToMany(mappedBy="authors",fetch = FetchType.LAZY)
	private Set<Book> books = new HashSet<Book>();

	public Integer getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(final Set<Book> books) {
		this.books = books;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Author)) {
			return false;
		}
		Author other = (Author) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 31;
	}


	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (firstName != null && !firstName.trim().isEmpty())
			result += "firstName: " + firstName;
		if (lastName != null && !lastName.trim().isEmpty())
			result += ", lastName: " + lastName;
		return result;
	}
}
