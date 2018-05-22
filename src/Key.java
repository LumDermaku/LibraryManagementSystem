public class Key
{
    public int isbn;
    public Key(int i){isbn = i;}
    public boolean equals(Key k) { return ( isbn == k.getKey() ); }
    public int getKey(){return isbn;}
}
