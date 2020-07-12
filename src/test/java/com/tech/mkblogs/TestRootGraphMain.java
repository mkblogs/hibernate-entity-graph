package com.tech.mkblogs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.graph.GraphParser;
import org.hibernate.graph.RootGraph;

import com.tech.mkblogs.model.Author;
import com.tech.mkblogs.model.Book;

public class TestRootGraphMain {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {
			System.out.println(
					"1 for without Entity Graph \n 2 for With Entity Graph for get Books attributes \n 3 for With Entity Graph for all attributes ");
			Integer firstInput = input.nextInt();
			if (firstInput == 1) {
				selectWithOutEntityGraph();
			} else if (firstInput == 2) {
				System.out.println(" 1 for Fetch Graph \n 2 for Load Graph");
				Integer secondInput = input.nextInt();
				if (secondInput == 1) {
					selectRootGraphBooksAndFetch();
				} else if (secondInput == 2) {
					selectRootGraphBooksAndLoad();
				} else {
					System.out.println("Got Wrong Input. Please try again");
				}
			} else if (firstInput == 3) {
				System.out.println(" 1 for Fetch Graph \n 2 for Load Graph");
				Integer secondInput = input.nextInt();
				if (secondInput == 1) {
					selectRootGraphBooksPublisherAndFetch();
				} else if (secondInput == 2) {
					selectRootGraphBooksPublisherAndLoad();
				} else {
					System.out.println("Got Wrong Input. Please try again");
				}
			} else {
				System.out.println("Got Wrong Input. Please try again");
			}
		} catch (Exception e) {

		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	public static void selectWithOutEntityGraph() {
		System.out.println("... selectWithOutEntityGraph ...");
		EntityManager entityManager = getEntityManager();
		Author author = entityManager.find(Author.class, 1);
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		 Set<Book> books = author.getBooks(); 
		 for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		 }
	}
	
	public static void selectRootGraphBooksAndFetch() {
		System.out.println("... selectRootGraphBooksAndFetch ...");
		EntityManager entityManager = getEntityManager();
		RootGraph<Author> graph = GraphParser.parse(Author.class, "books", entityManager);
		Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("javax.persistence.fetchgraph", graph);
		Author author = entityManager.find(Author.class, 1,properties);
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		 Set<Book> books = author.getBooks(); 
		 for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		 }
	}
	
	public static void selectRootGraphBooksPublisherAndFetch() {
		System.out.println("... selectRootGraphBooksPublisherAndFetch ...");
		EntityManager entityManager = getEntityManager();
		RootGraph<Author> graph = GraphParser.parse(Author.class, "books(publisher)", entityManager);
		Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("javax.persistence.fetchgraph", graph);
		Author author = entityManager.find(Author.class, 1,properties);
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		 Set<Book> books = author.getBooks(); 
		 for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		 }
	}
	
	public static void selectRootGraphBooksAndLoad() {
		System.out.println("... selectRootGraphBooksAndLoad ...");
		EntityManager entityManager = getEntityManager();
		RootGraph<Author> graph = GraphParser.parse(Author.class, "books", entityManager);
		Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("javax.persistence.loadgraph", graph);
		Author author = entityManager.find(Author.class, 1,properties);
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		 Set<Book> books = author.getBooks(); 
		 for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		 }
	}
	
	public static void selectRootGraphBooksPublisherAndLoad() {
		System.out.println("... selectRootGraphNestedObjectWithLoad ...");
		EntityManager entityManager = getEntityManager();
		RootGraph<Author> graph = GraphParser.parse(Author.class, "books(publisher)", entityManager);
		Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("javax.persistence.loadgraph", graph);
		Author author = entityManager.find(Author.class, 1,properties);
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		 Set<Book> books = author.getBooks(); 
		 for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		 }
	}
	
	public static void selectRootGraphQueryBooksAndLoad() {
		System.out.println("... selectRootGraphBooksAndLoad ...");
		EntityManager entityManager = getEntityManager();
		String HQL ="SELECT a FROM Author a WHERE a.id = 1"; 
		RootGraph<Author> graph = GraphParser.parse(Author.class, "books", entityManager);
		Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("javax.persistence.loadgraph", graph);
	    TypedQuery<Author> query = entityManager.createQuery(HQL, Author.class);
	    Author author = query.getSingleResult();
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		 Set<Book> books = author.getBooks(); 
		 for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		 }
	}
	
	public static EntityManager getEntityManager() {
		 EntityManagerFactory entityManagerFactory =
		            Persistence.createEntityManagerFactory("entity-graph-persistence");
		 EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}
}
