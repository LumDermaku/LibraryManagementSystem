public class Books
{
    private Key ISBN;
    private String author,title,publish_date ="";

    public Books(Key isbn, String a,String t,String d)
    {
        ISBN = isbn; author = a; title = t; publish_date = d;
    }

    public Key getISBN(){ return ISBN;}
    public String getAuthor(){return author;}
    public String getTitle(){return title;}
    public String getDate(){return publish_date;}
}
