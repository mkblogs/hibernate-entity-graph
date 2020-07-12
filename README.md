# Hibernate Entity Graph
 In Hibernate/JPA we face N+1 Problem. First we will see what is the N+1 Problem.
 
 In order to find, what is N+1 Problem Please go through the below stackoverflow link
 
 https://stackoverflow.com/questions/32453989/what-is-the-solution-for-the-n1-issue-in-jpa-and-hibernate?noredirect=1&lq=1
   
 We can fix N+1 select problem by using Entity Graphs provided by JPA 2.1. we can achieve following ways. 
 
 - Using Entity Manager
 - Using JPA Repository
 - Using 3rd Party Entity Graph Utility
  
```mermaid
graph TD
	A[Entity Graph] -->|Using Entity Manager Class | B(Hibernate Entity Manager )
	A[Entity Graph] -->|Using JPA Repository Class | C(JPA Repository )
	A[Entity Graph] -->|Using 3rd Party Entity Graph | D(3rd Party Entity Graph Utility )
```


In this example we will use Hibernate Entity Manager to solve N+1 problem.





```mermaid
graph TD
	A[Entity Graph] -->|Using Entity Manager Class | B(Hibernate Entity Manager )
	A[Entity Graph] -->|Using JPA Repository Class | C(JPA Repository )
	A[Entity Graph] -->|Using 3rd Party Entity Graph | D(3rd Party Entity Graph Utility )
	
	B[Hibernate Entity Manager] --> | Using Named Entity Graph API | E(NamedEntityGraph)
	B[Hibernate Entity Manager] --> | Using Dynamically pass graph attribute nodes | G(Dynamic Entity Graph API)
	B[Hibernate Entity Manager] --> | Using Root Entity Graph API | H(RootGraph API)
```
### DB Design

![alt text](/ERDiagram.png)

# Named Entity Graph

### Entity Class (declaring Named Entity Graph as below)

```java
@Entity
@Table(name = "author")
@NamedEntityGraph(name = "graph.author.books.publisher", 
	attributeNodes = @NamedAttributeNode(value = "books", subgraph = "books"),
	subgraphs = @NamedSubgraph(name = "books", attributeNodes = @NamedAttributeNode("publisher"))    )

@NamedEntityGraph(name = "**graph.author.books**", attributeNodes = @NamedAttributeNode(value = "books"))

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


}

```

### Named Entity Graph Testing Here

In this example we are using `@NamedEntityGraph` name attribute `@@graph.author.books@@` to get entity graph api. 
It will load only Books objects but not publisher associate with books

```java

	EntityManager entityManager = getEntityManager();
	String hql = "SELECT a FROM Author a WHERE a.id = 1";
	EntityGraph graph = entityManager.getEntityGraph("graph.author.books");
	TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
	query.setHint("javax.persistence.loadgraph", graph);
	Author author = query.getSingleResult();

```

In this example we are using `@NamedEntityGraph` name attribute `graph.author.books.publisher` to get entity graph api. 
It will load only Books objects as well as publisher associate with books

```java

	EntityManager entityManager = getEntityManager();
	String hql = "SELECT a FROM Author a WHERE a.id = 1";
	EntityGraph graph = entityManager.getEntityGraph("graph.author.books");		
	TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
	query.setHint("javax.persistence.loadgraph", graph);
	Author author = query.getSingleResult();

```

# Dynamic Entity Graph

### Entity Class (declaring Named Entity Graph as below)

```java
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


}

```

### Named Entity Graph Testing Here

In this example we are using `@NamedEntityGraph` name attribute(graph.author.books) to get entity graph api. 
It will load only Books objects but not publisher associate with books

```java

	EntityManager entityManager = getEntityManager();
	String hql = "SELECT a FROM Author a WHERE a.id = 1";
	EntityGraph graph = entityManager.getEntityGraph("graph.author.books");
	TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
	query.setHint("javax.persistence.loadgraph", graph);
	Author author = query.getSingleResult();

```

In this example we are using `@NamedEntityGraph` name attribute(graph.author.books.publisher) to get entity graph api. 
It will load only Books objects as well as publisher associate with books

```java

	EntityManager entityManager = getEntityManager();
	String hql = "SELECT a FROM Author a WHERE a.id = 1";
	EntityGraph graph = entityManager.getEntityGraph("graph.author.books");		
	TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
	query.setHint("javax.persistence.loadgraph", graph);
	Author author = query.getSingleResult();

```

