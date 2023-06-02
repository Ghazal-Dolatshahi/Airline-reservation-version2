package Data;

public interface Generator<E> {
     E generateToRead(String ... values);
     String generateToWrite(E element);
     String fileAddress();
     int recordSize();

}
