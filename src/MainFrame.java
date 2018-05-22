import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JPanel
{
    private final int frame_width = 600;
    private final int frame_height = 600;
    public int y = 100;
    JPanel panel_1;JFrame frame;private JButton add_button;private JButton find_btn,delete_btn,borrow_btn;
    Database database;  int amount_of_books; Key key;
    JLabel author,how_many_books,title,isbn,date;
    JTextField add_author_field,add_title_field,add_isbn_field,add_date_field;
    String added_author, added_title, added_date ,added_isbn ="";
    JLabel added_book;
    public Books [] book;
    public MainFrame()
    {
        /*
        kom perdore Absolute Layout per arsye qe me i vendose JComponents ku t'du
    */
        amount_of_books = new Integer(JOptionPane.showInputDialog("How many books do you want to add?")).intValue();
        database = new Database(amount_of_books);

        frame = new JFrame("Library Managment App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel_1 = new JPanel();
        panel_1.setLayout(null);
        add_button = new JButton("Add");add_button.setBounds(frame_width - 80, 60, 60, 30);

        how_many_books = new JLabel("Amount of books: "+amount_of_books);
        how_many_books.setBounds(20, 20, 200,30);

        author = new JLabel("Author:");author.setBounds(0+20,60,50,30);
        add_author_field = new JTextField("");add_author_field.setBounds(75, 60, 70, 30);

        title = new JLabel("Title:");title.setBounds(150,60,40,30);
        add_title_field = new JTextField("");add_title_field.setBounds(195,60,70,30);

        isbn = new JLabel("ISBN:");isbn.setBounds(270,60,40,30);
        add_isbn_field = new JTextField(""); add_isbn_field.setBounds(315,60,60,30);

        date = new JLabel("Date:"); date.setBounds(380,60,50,30);
        add_date_field = new JTextField(""); add_date_field.setBounds(430,60,60,30);

        JLabel vijat = new JLabel("____________________________________________________________________");
        vijat.setBounds(50,60,600,70);

        find_btn = new JButton("Find books"); find_btn.setBounds(200,20,100,30);
        delete_btn = new JButton("Delete books"); delete_btn.setBounds(310,20,110,30);
        borrow_btn = new JButton("Borrow books"); borrow_btn.setBounds(430,20,120,30);

        panel_1.add(how_many_books);panel_1.add(author);panel_1.add(add_author_field);panel_1.add(add_button);
        panel_1.add(title);panel_1.add(add_title_field);panel_1.add(isbn);panel_1.add(add_isbn_field);panel_1.add(date);
        panel_1.add(add_date_field);panel_1.add(vijat);
        add_button.addActionListener(new ClickAdd());
        frame.getContentPane().add(panel_1);
        frame.setSize(frame_width,frame_height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public class ClickAdd implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==add_button)
            {   y+=30;
                int ISBN = new Integer(add_isbn_field.getText()).intValue();
                 key = new Key(ISBN);
                added_author = add_author_field.getText();added_title = add_title_field.getText() ;added_date = add_date_field.getText();
                added_isbn = String.valueOf(ISBN);
                book = new Books[amount_of_books];//(key,added_author, added_title, added_date)
                for(int i=0; i<=amount_of_books; i++)//smujna me shtu ma shume sesa mban databaza
                {
                    book[i] = new Books(key,added_author, added_title, added_date);
                    database.insert(book[i]);
                    amount_of_books -= 1;
                }

                added_book = new JLabel("Author: " + added_author+"     "+"Title: " + added_title+"      "+"ISBN: " + added_isbn+"       "+"Date:" + added_date);
                added_book.setBounds(0 + 20, y, 500, 30);
                panel_1.add(added_book);
                panel_1.add(find_btn);panel_1.add(delete_btn);panel_1.add(borrow_btn);
                find_btn.addActionListener(new ClickAdd());delete_btn.addActionListener(new ClickAdd());borrow_btn.addActionListener(new ClickAdd());
            }
            else if(e.getSource()==find_btn)
            {
                int i = new Integer( JOptionPane.showInputDialog("Find your book by it's ISBN")).intValue();
                if(i<0)
                {
                    JOptionPane.showMessageDialog(null,"Please type a number greater than 0");
                }
                else
                {
                    key = new Key(i);
                    if ( database.findLocation(key)!= database.not_found && database.borrowed == false)
                    {
                        JOptionPane.showMessageDialog(null, "Book found, " + " author: " + book[i].getAuthor() + ", title:" +
                                book[i].getTitle() + ", date:" + book[i].getDate() + ", status: not borrowed");
                    }
                    else if (database.findLocation(key)!=database.not_found && database.borrowed == true)
                    {
                        JOptionPane.showMessageDialog(null, "Book is borrowed");
                    }
                    else
                        {
                        JOptionPane.showMessageDialog(null, "Book not found");
                    }
                }
            }
            else if(e.getSource()==delete_btn)
            {
                int i = new Integer( JOptionPane.showInputDialog("Delete a book by it's ISBN")).intValue();
                if(i<=0)
                {
                    JOptionPane.showMessageDialog(null,"Please type a positive number");
                }
                else
                    {
                        key = new Key(i);
                        if (database.delete(key) && database.borrowed == false)//perderisa eshte false
                        {
                            JOptionPane.showMessageDialog(null, "Book successfully deleted");
                        }
                        else
                            {
                            JOptionPane.showMessageDialog(null, "Book not found or is currently borrowed");
                        }
                }
            }
            else if(e.getSource()==borrow_btn)
            {
                int i = new Integer( JOptionPane.showInputDialog("Borrow a book by it's ISBN")).intValue();
                if(i<=0)
                {
                    JOptionPane.showMessageDialog(null,"Please type a positive number");
                }
                else
                    {
                    key = new Key(i);
                    if (database.borrow(key) == true)
                    {
                        JOptionPane.showMessageDialog(null, "Book found, " + " author: " + book[i].getAuthor() + ", title:" +
                                book[i].getTitle() + ", date:" + book[i].getDate() + ", status: borrowed");
                    }
                    else if (database.borrow(key) == false)
                    {
                        JOptionPane.showMessageDialog(null, "Book not found or is borrowed");
                    }
                }
            }
            frame.repaint();
        }
    }
}

