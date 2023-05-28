package com.devnatao.springrestfulapicourse.unittests;

import java.util.ArrayList;
import java.util.List;

import com.devnatao.springrestfulapicourse.data.vo.v1.BookVO;
import com.devnatao.springrestfulapicourse.model.Book;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
        	books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
        	books.add(mockVO(i));
        }
        return books;
    }
    
    public Book mockEntity(Integer number) {
    	Book book = new Book();
    	book.setAuthor("Author" + number);
        book.setLaunchDate("Launch Date" + number);
        book.setPrice(5d + number);
        book.setTitle("Book title" + number); 
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        // Book attributes
        book.setAuthor("Author" + number);
        book.setLaunchDate("Launch Date" + number);
        book.setPrice(5d + number);
        book.setTitle("Book title" + number);
        book.setKey(number.longValue());        
        return book;
    }
}
