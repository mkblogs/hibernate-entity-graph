package com.tech.mkblogs;

import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.tech.mkblogs.model.Author;
import com.tech.mkblogs.model.Book;

@SuppressWarnings("rawtypes")
public class TestNamedEntityGraph {
	
	public static void main(String[] args) {
		
		 Scanner input = new Scanner(System.in);
		try {
			System.out.println("1 for without Entity Graph \n 2 for With Entity Graph for get Books attributes \n 3 for With Entity Graph for all attributes ");
			Integer firstInput = input.nextInt();
			if(firstInput == 1) {
				selectWithOutEntityGraph();
			}else if(firstInput == 2) {
				System.out.println(" 1 for Fetch Graph \n 2 for Load Graph");
				Integer secondInput = input.nextInt();
				if(secondInput == 1) {
					selectWithEntityGraphWithFetchGraph();
				}else if(secondInput == 2) {
					selectWithEntityGraphWithLoad();
				}else {
					System.out.println("Got Wrong Input. Please try again");
				}
			}else if(firstInput == 3) {
				System.out.println(" 1 for Fetch Graph \n 2 for Load Graph");
				Integer secondInput = input.nextInt();
				if(secondInput == 1) {
					selectWithNamedEntityGraphWithPublisherWithFetchGraph();
				}else if(secondInput == 2) {
					selectWithNamedEntityGraphWithPublisherWithLoadGraph();
				}else {
					System.out.println("Got Wrong Input. Please try again");
				}
			}else {
				System.out.println("Got Wrong Input. Please try again");
			}
		}catch(Exception e) {
			
		}finally {
			if(input!= null) {
				input.close();
			}
		}
	}
	
	public static void selectWithOutEntityGraph() {
		EntityManager entityManager = getEntityManager();
		Author author = entityManager.find(Author.class, 1);
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		 Set<Book> books = author.getBooks(); 
		 for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		 }
	}

	public static void selectWithEntityGraphWithFetchGraph() {
		System.out.println("... selectWithEntityGraphWithFetchGraph ...");		
		EntityManager entityManager = getEntityManager();
		EntityGraph graph = entityManager.getEntityGraph("graph.author.books");	
		String hql = "SELECT a FROM Author a WHERE a.id = 1";
		TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
		query.setHint("javax.persistence.fetchgraph", graph);
		Author author = query.getSingleResult();
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		Set<Book> books = author.getBooks(); 
		for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		}
	}
	
	
	public static void selectWithEntityGraphWithLoad() {
		System.out.println("... selectWithEntityGraphWithLoad ...");
		EntityManager entityManager = getEntityManager();
		String hql = "SELECT a FROM Author a WHERE a.id = 1";
		EntityGraph graph = entityManager.getEntityGraph("graph.author.books");
		TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
		query.setHint("javax.persistence.loadgraph", graph);
		Author author = query.getSingleResult();
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		Set<Book> books = author.getBooks(); 
		for(Book book: books) {
		   System.out.println(book.getPublisher()); 
		}
	}
	
	
	public static void selectWithNamedEntityGraphWithPublisherWithFetchGraph() {
		System.out.println("... selectWithNamedEntityGraphWithPublisherWithFetchGraph ...");		
		EntityManager entityManager = getEntityManager();
		String hql = "SELECT a FROM Author a WHERE a.id = 1";
		EntityGraph graph = entityManager.getEntityGraph("graph.author.books.publisher");		
		TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
		query.setHint("javax.persistence.fetchgraph", graph);
		Author author = query.getSingleResult();
		System.out.println(author.getFirstName()+" "+author.getLastName()+" wrote "+author.getBooks().size()+" books.");
		Set<Book> books = author.getBooks(); 
		for(Book book: books) {
			 System.out.println(book.getPublisher()); 
		}
	}
	
	public static void selectWithNamedEntityGraphWithPublisherWithLoadGraph() {
		System.out.println("... selectWithNamedEntityGraphWithPublisherWithFetchGraph ...");		
		EntityManager entityManager = getEntityManager();
		String hql = "SELECT a FROM Author a WHERE a.id = 1";
		EntityGraph graph = entityManager.getEntityGraph("graph.author.books.publisher");
		TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
		query.setHint("javax.persistence.loadgraph", graph);
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
