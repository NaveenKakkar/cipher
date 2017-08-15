package duster.ramson.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Publisher {
    private int id;
    private String name;
    private Set<BookP> books;

    public Publisher(){

    }

    public Publisher(String name){
        this.name = name;
    }

    public Publisher(String name, Set<BookP> books){
        this.name = name;
        this.books = books;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "publishers")
    public Set<BookP> getBooks() {
        return books;
    }

    public void setBooks(Set<BookP> books) {
        this.books = books;
    }
}