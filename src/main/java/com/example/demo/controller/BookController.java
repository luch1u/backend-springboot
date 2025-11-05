package com.example.demo.controller;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Management", description = "API para gestión de libros")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    @Operation(summary = "Obtener todos los libros", description = "Retorna una lista de todos los libros disponibles")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener libro por ID", description = "Retorna un libro específico por su ID")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    
    @PostMapping
    @Operation(summary = "Crear nuevo libro", description = "Agrega un nuevo libro al sistema")
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar libro", description = "Actualiza un libro existente")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            //existingBook.setTitle(book.getTitle());
            //existingBook.setAuthor(book.getAuthor());
            return bookService.saveBook(existingBook);
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar libro", description = "Elimina un libro del sistema")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}