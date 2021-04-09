/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import ultils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BookDAO implements Serializable {
    Connection conn = null;
     PreparedStatement stm = null;
    ResultSet rs = null;
    public BookDAO() {
        
    }
    private void closeConnection(){
        try {
            if(rs != null){
                rs.close();
            }
            if(conn != null){
                conn.close();
            }
            if(stm != null) {
                stm.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<BookDTO> getListBook() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<BookDTO> list = null;
        BookDTO dto = null;
        String bookID, bookname, author, publisher;
        int publishedYear;
        boolean forRent;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT BookID, Bookname, Author, Publisher, PublishedYear, ForRent "
                    + "FROM tblBooks";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {                
                bookID = rs.getString("BookID");
                bookname = rs.getString("Bookname");
                author = rs.getString("Author");
                publisher = rs.getString("Publisher");
                publishedYear = rs.getInt("PublishedYear");
                forRent = rs.getBoolean("ForRent");
                dto = new BookDTO(bookID, bookname, author, publisher, publishedYear, forRent);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
     public BookDTO getBookDetail (String bookID) throws ClassNotFoundException, SQLException, Exception {
        BookDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT BookID, Bookname, Author, Publisher, PublisherYear, Forent"
                    + "FROM tblBooks"
                    + "WHERE BookID = ?";
          stm = conn.prepareStatement(sql);
            stm.setString(1, bookID);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto = new BookDTO();
                dto.setBookID(bookID);
                dto.setBookName(rs.getString("Bookname"));
                dto.setAuthor(rs.getString("Author"));
                dto.setPublisher(rs.getString("Publisher"));
                dto.setPublishedYear(rs.getInt("PublishedYear"));
                dto.setForRent(rs.getBoolean("ForRent"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
                       
   
    public ArrayList<BookDTO> findByLikeName (String name) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<BookDTO> list = new ArrayList<>();
        BookDTO dto = null;
        String bookID, bookname, author,publisher;
        int publishedYear;
        boolean forRent;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT BookID, Bookname, Author, Publisher, PublishedYear, ForRent "
                    + "FROM tblBooks "
                    + "WHERE Bookname LIKE ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            rs = stm.executeQuery();
            while (rs.next()) {                
                bookID = rs.getString("BookID");
                bookname = rs.getString("Bookname");
                author = rs.getString("Author");
                publisher = rs.getString("Publisher");
                publishedYear = rs.getInt("PublishedYear");
                forRent = rs.getBoolean("ForRent");
                dto = new BookDTO(bookID, bookname, author, publisher, publishedYear, forRent);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public BookDTO findByID(String id) throws ClassNotFoundException, SQLException, Exception {
        BookDTO dto = new BookDTO();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT BookID, Bookname, Author, Publisher, PublishedYear, ForRent "
                    + "FROM tblBooks "
                    + "WHERE BookID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, id);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto.setBookID(rs.getString("BookID"));
                dto.setBookName(rs.getString("Bookname"));
                dto.setAuthor(rs.getString("Author"));
                dto.setPublisher(rs.getString("Publisher"));
                dto.setPublishedYear(rs.getInt("PublishedYear"));
                dto.setForRent(rs.getBoolean("ForRent"));
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public ArrayList<BookDTO> sortAscendingByBookName() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<BookDTO> list = new ArrayList<>();
        BookDTO dto = null;
        String bookID, bookname, author, publisher;
        int publishedYear;
        boolean forRent;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT BookID, Bookname, Author, Publisher, PublishedYear, ForRent "
                    + "FROM tblBooks "
                    + "ORDER BY Bookname ASC";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {                
                bookID = rs.getString("BookID");
                bookname = rs.getString("Bookname");
                author = rs.getString("Author");
                publisher = rs.getString("Publisher");
                publishedYear = rs.getInt("PublishedYear");
                forRent = rs.getBoolean("ForRent");
                dto = new BookDTO(bookID, bookname, author, publisher, publishedYear, forRent);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public ArrayList<BookDTO> sortDescendingByBookName() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<BookDTO> list = new ArrayList<>();
        BookDTO dto = null;
        String bookID, bookname, author, publisher;
        int publishedYear;
        boolean forRent;
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT BookID, Bookname, Author, Publisher, PublishedYear, ForRent "
                    + "FROM tblBooks "
                    + "ORDER BY Bookname DESC";
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {                
                bookID = rs.getString("BookID");
                bookname = rs.getString("Bookname");
                author = rs.getString("Author");
                publisher = rs.getString("Publisher");
                publishedYear = rs.getInt("PublishedYear");
                forRent = rs.getBoolean("ForRent");
                dto = new BookDTO(bookID, bookname, author, publisher, publishedYear, forRent);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean insert(BookDTO dto) throws ClassNotFoundException, SQLException, Exception {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "INSERT INTO tblBooks(BookID,Bookname,Author,Publisher,PublishedYear,ForRent) VALUES(?,?,?,?,?,?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getBookID());
            stm.setString(2, dto.getBookName());
            stm.setString(3, dto.getAuthor());
            stm.setString(4, dto.getPublisher());
            stm.setInt(5, dto.getPublishedYear());
            stm.setBoolean(6, dto.isForRent());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean update(BookDTO dto) throws ClassNotFoundException, SQLException, Exception {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblBooks SET Bookname = ?, Author = ?, Publisher = ?, PublishedYear = ?, ForRent = ? "
                    + "WHERE BookID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getBookName());
            stm.setString(2, dto.getAuthor());
            stm.setString(3, dto.getPublisher());
            stm.setInt(4, dto.getPublishedYear());
            stm.setBoolean(5, dto.isForRent());
            stm.setString(6, dto.getBookID());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    public boolean delete(BookDTO dto) throws ClassNotFoundException, SQLException, Exception {
        boolean check = false;
        try {
            conn = DBUtils.getConnection();
            String sql = "DELETE FROM tblBooks WHERE BookID = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, dto.getBookID());
            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
