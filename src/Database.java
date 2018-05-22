public class Database
{
    public Books[] books;//koleksion i librave
    public int not_found = -1;
    public boolean found; public boolean borrowed;

    public Database(int initial_size)
    {
        if (initial_size > 0) {
            books = new Books[initial_size];
        } else {
            books = new Books[1];
        }
    }
        public int findLocation(Key k)//me gjete ISBN
        {
            int result = not_found;
            found = false;
            int i = 0;
            while ( !found  &&  i != books.length )
            {
                if ( books[i] != null  &&  books[i].getISBN().equals(k) ) //nese isbn qe e kena shkru eshte e barabarte me isbn te librit ne varg
            {
                found = true;
                result = i;
            }
            else
                {
                    i = i + 1;
                }
            }
            return result;
        }

    public boolean insert(Books r)
    { boolean inserted = false;
        if ( findLocation(r.getISBN()) == not_found )
        {
            boolean found_empty_place = false;
            int i = 0;
            while ( !found_empty_place  &&  i != books.length )
            {
                if ( books[i] == null )
                { found_empty_place = true; }
                else { i = i + 1; }
            }
                if ( found_empty_place )
                { books[i] = r; }
                else {
                    Books[] temp = new Books[books.length * 2];
                    for ( int j = 0;  j != books.length;  j = j + 1 )
                    { temp[j] = books[j]; }
                    temp[books.length] = r;
                    books = temp;
                }
            inserted = true;
        }
        return inserted;
    }

    public boolean delete(Key k)
    {
        boolean result = false;
        int index = findLocation(k);
        if ( index != not_found )
        {
            books[index] = null;
            result = true;
        }
        return result;
    }

    public boolean borrow(Key k)
    {
        borrowed = false;
        int index = findLocation(k);
        if(index != not_found)
        {
            borrowed = true;
        }
        else
        {
            borrowed = false;
        }
        return borrowed;
    }
}
