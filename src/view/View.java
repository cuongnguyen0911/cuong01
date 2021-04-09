/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.BookDAO;
import dto.BookDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class View extends javax.swing.JFrame {

    DefaultTableModel bookModel;

    public View() {
        initComponents();
        this.setSize(1270, 730);
        bookModel = (DefaultTableModel) tblBook.getModel();
        loadBookList();
    }

    private void loadBookList() {
        try {
            bookModel.setRowCount(0);
            BookDAO dao = new BookDAO();
            ArrayList<BookDTO> list = dao.getListBook();
            for (BookDTO dto : list) {
                bookModel.addRow(dto.toVector());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayBookDetail(String bookID) {
        try {
            BookDAO dao = new BookDAO();
            BookDTO dto = dao.getBookDetail(bookID);
            txtBookID.setText(dto.getBookID());
            txtBookName.setText(dto.getBookName());
            txtAuthor.setText(dto.getAuthor());
            txtPublisher.setText(dto.getPublisher());
            cobPublisherYear.setSelectedItem(dto.toString());
            ckbForRent.setSelected(dto.isForRent());

            txtBookID.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortAscendingByBookName() {
        try {
            bookModel.setRowCount(0);
            BookDAO dao = new BookDAO();
            ArrayList<BookDTO> list = dao.sortAscendingByBookName();
            for (BookDTO dto : list) {
                bookModel.addRow(dto.toVector());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sortDescendingByBookName() {
        try {
            bookModel.setRowCount(0);
            BookDAO dao = new BookDAO();
            ArrayList<BookDTO> list = dao.sortDescendingByBookName();
            for (BookDTO dto : list) {
                bookModel.addRow(dto.toVector());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBookListByName(String name) {
        try {
            bookModel.setRowCount(0);
            BookDAO dao = new BookDAO();
            ArrayList<BookDTO> list = dao.findByLikeName(name);
            for (BookDTO dto : list) {
                bookModel.addRow(dto.toVector());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String isValidation() {
        String notice = "";
        if (txtBookID.getText().length() <= 0 || txtBookID.getText().length() > 10) {
            notice += "Book ID must be from 1 to 10 character.";
        }
        if (txtBookName.getText().length() <= 0 || txtBookName.getText().length() > 50) {
            if (notice.length() != 0) {
                notice += "/n";
            }
            notice += "Book name must be from 1 to 50 character.";
        }
        if (txtPublisher.getText().length() <= 0 || txtPublisher.getText().length() > 50) {
            if (notice.length() != 0) {
                notice += "/n";
            }
            notice += "Publisher must be from 1 to 50 character.";
        }
        return notice;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBook = new javax.swing.JTable();
        cobSortByName = new javax.swing.JComboBox<>();
        btnSearchByName = new javax.swing.JButton();
        txtSearchByName = new javax.swing.JTextField();
        btnGetAllBook = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtBookID = new javax.swing.JTextField();
        txtBookName = new javax.swing.JTextField();
        txtAuthor = new javax.swing.JTextField();
        txtPublisher = new javax.swing.JTextField();
        cobPublisherYear = new javax.swing.JComboBox<>();
        ckbForRent = new javax.swing.JCheckBox();
        btnFindByID = new javax.swing.JButton();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BOOK MANAGEMENT");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 10, 420, 60);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Main part:"));
        jPanel1.setLayout(null);

        jLabel2.setText("Sort by name:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 420, 68, 30);

        tblBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Book ID", "Book name", "Author", "Publisher", "Publisher year", "For rent"
            }
        ));
        tblBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBookMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblBook);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 630, 350);

        cobSortByName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ascending", "Descending" }));
        cobSortByName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cobSortByNameItemStateChanged(evt);
            }
        });
        cobSortByName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cobSortByNameMouseClicked(evt);
            }
        });
        cobSortByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobSortByNameActionPerformed(evt);
            }
        });
        jPanel1.add(cobSortByName);
        cobSortByName.setBounds(110, 420, 150, 30);

        btnSearchByName.setText("Search by name:");
        btnSearchByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchByNameActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearchByName);
        btnSearchByName.setBounds(300, 420, 140, 30);
        jPanel1.add(txtSearchByName);
        txtSearchByName.setBounds(450, 420, 190, 30);

        btnGetAllBook.setText("Get all book");
        btnGetAllBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllBookActionPerformed(evt);
            }
        });
        jPanel1.add(btnGetAllBook);
        btnGetAllBook.setBounds(240, 470, 140, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 80, 650, 600);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Detailed part:"));
        jPanel2.setLayout(null);

        jLabel3.setText("Book ID:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(50, 50, 100, 30);

        jLabel4.setText("Book name:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(50, 120, 100, 30);

        jLabel5.setText("Author:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(50, 190, 100, 30);

        jLabel6.setText("Publisher:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(50, 260, 100, 30);

        jLabel7.setText("Publisher year:");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(50, 330, 100, 30);
        jPanel2.add(txtBookID);
        txtBookID.setBounds(180, 50, 190, 30);
        jPanel2.add(txtBookName);
        txtBookName.setBounds(180, 120, 310, 30);
        jPanel2.add(txtAuthor);
        txtAuthor.setBounds(180, 190, 310, 30);
        jPanel2.add(txtPublisher);
        txtPublisher.setBounds(180, 260, 310, 30);

        cobPublisherYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));
        jPanel2.add(cobPublisherYear);
        cobPublisherYear.setBounds(180, 330, 110, 30);

        ckbForRent.setText("For rent");
        jPanel2.add(ckbForRent);
        ckbForRent.setBounds(180, 400, 65, 23);

        btnFindByID.setText("Find by ID");
        btnFindByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindByIDActionPerformed(evt);
            }
        });
        jPanel2.add(btnFindByID);
        btnFindByID.setBounds(390, 50, 100, 30);

        btnAddNew.setText("AddNew");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });
        jPanel2.add(btnAddNew);
        btnAddNew.setBounds(50, 480, 120, 30);

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave);
        btnSave.setBounds(210, 480, 120, 30);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete);
        btnDelete.setBounds(370, 480, 120, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(670, 80, 510, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetAllBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllBookActionPerformed
        loadBookList();
    }//GEN-LAST:event_btnGetAllBookActionPerformed

    private void btnSearchByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchByNameActionPerformed
        String name = txtSearchByName.getText();
        loadBookListByName(name);
    }//GEN-LAST:event_btnSearchByNameActionPerformed

    private void btnFindByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindByIDActionPerformed
        String id = txtBookID.getText();
        try {
            BookDAO dao = new BookDAO();
            BookDTO dto = dao.findByID(id);

            txtBookID.setEditable(false);
            txtBookName.setText(dto.getBookName());
            txtAuthor.setText(dto.getAuthor());
            txtPublisher.setText(dto.getPublisher());
            cobPublisherYear.setSelectedItem(dto.toString());
            ckbForRent.setSelected(dto.isForRent());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnFindByIDActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        txtBookID.setEditable(true);
        txtBookID.setText("");
        txtBookID.requestFocus();
        txtBookName.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");
        cobPublisherYear.setSelectedIndex(0);
        ckbForRent.setSelected(false);
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void cobSortByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobSortByNameActionPerformed
        String selected = (String) cobSortByName.getSelectedItem();

        try {
            if (selected.equals("Ascending")) {
                sortAscendingByBookName();
            }
            if (selected.equals("Descending")) {
                sortDescendingByBookName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cobSortByNameActionPerformed

    private void cobSortByNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cobSortByNameItemStateChanged

    }//GEN-LAST:event_cobSortByNameItemStateChanged

    private void cobSortByNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cobSortByNameMouseClicked

    }//GEN-LAST:event_cobSortByNameMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String notice = isValidation();
        BookDAO dao = new BookDAO();
        String bookID = txtBookID.getText();
        String bookName = txtBookName.getText();
        String author = txtAuthor.getText();
        String publisher = txtPublisher.getText();
        int publisherYear = Integer.valueOf((String) cobPublisherYear.getSelectedItem());
        boolean forRent = ckbForRent.isSelected();
        BookDTO dto = new BookDTO(bookID, bookName, author, publisher, publisherYear, forRent);
        if (notice.length() == 0) {
            if (txtBookID.isEditable()) {
                try {
                    if (dao.insert(dto)) {
                        loadBookList();
                        JOptionPane.showMessageDialog(null, "Insert completely");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insert false");
                    }
                } catch (SQLException e) {
                    if (e.getMessage().contains("duplicate")) {
                        JOptionPane.showMessageDialog(null, "Duplicate BookID");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    if (dao.update(dto)) {
                        loadBookList();
                        JOptionPane.showMessageDialog(null, "Update completely");
                    } else {
                        JOptionPane.showMessageDialog(null, "Update failed.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, notice);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (tblBook.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row");
        } else {
            if (!txtBookID.isEditable()) {
                try {
                    int input = JOptionPane.showConfirmDialog(null, "Are you sure to delete?");
                    if (input == 0) {
                        BookDAO dao = new BookDAO();
                        String bookID = txtBookID.getText();
                        String bookName = txtBookName.getText();
                        String author = txtAuthor.getText();
                        String publisher = txtPublisher.getText();
                        int publisherYear = Integer.valueOf((String) cobPublisherYear.getSelectedItem());
                        boolean forRent = ckbForRent.isSelected();
                        if (dao.delete(new BookDTO(bookID, bookName, author, publisher, publisherYear, forRent))) {
                            loadBookList();
                            JOptionPane.showMessageDialog(null, "Delete completely");
                        } else {
                            JOptionPane.showMessageDialog(null, "Delete false");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "choose a book");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tblBookMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookMousePressed
        int selectedRow = tblBook.getSelectedRow();
        if (selectedRow >= 0) {
            displayBookDetail((String) bookModel.getValueAt(selectedRow, 0));
        }
    }//GEN-LAST:event_tblBookMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFindByID;
    private javax.swing.JButton btnGetAllBook;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSearchByName;
    private javax.swing.JCheckBox ckbForRent;
    private javax.swing.JComboBox<String> cobPublisherYear;
    private javax.swing.JComboBox<String> cobSortByName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBook;
    private javax.swing.JTextField txtAuthor;
    private javax.swing.JTextField txtBookID;
    private javax.swing.JTextField txtBookName;
    private javax.swing.JTextField txtPublisher;
    private javax.swing.JTextField txtSearchByName;
    // End of variables declaration//GEN-END:variables
}
