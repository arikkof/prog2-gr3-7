package at.ac.fhcampuswien.fhmdb.exceptions;
// custom Exceptions schreiben
// zb ein movie soll hinzugefuegt werden aber er ist schon da ("movie ist schon gespeichert")
public class DatabaseException extends Exception{
    public DatabaseException(String errorMessage){
        System.out.println(errorMessage);
    }
}
