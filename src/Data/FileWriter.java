package Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileWriter <E> {
    private final Generator<E> generator;
    private final RandomAccessFile file;

    public FileWriter(Generator<E> generator) throws FileNotFoundException {
        this.generator = generator;
      this.file = new RandomAccessFile(generator.fileAddress(), "rw");

    }

    public Generator<E> getGenerator() {
        return generator;
    }
    public RandomAccessFile getFile() {
        return file;
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Write on file</span>
     * @param element the element that we want to write it on file
     */
    public void write(E element) throws IOException {

        file.seek(file.length());
        file.writeChars(generator.generateToWrite(element));
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Read from file </span>
     * @param start the initial byte to start reading from the file
     * @return the one of record that includes element
     */
    public E read(long start) throws IOException {
        String[] readFields = new String[12];
        int i = 0 ;
        long index = start + generator.recordSize();
        while (start < index) {
          readFields[i] = (fixStringToRead(start));
          i++;
          start += 40;
        }
        return generator.generateToRead(readFields);
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF"> Search from file</span>
     * @param start the initial byte to start reading from the file
     * @param values the list of values that we want to search base on
     * @return the arraylist of element that found
     */
    public ArrayList<E> search(int start ,String...values) throws IOException {
        ArrayList<E> searchArray = new ArrayList<>();
            for (long i = 0; i < file.length(); i += generator.recordSize()) {
                int count = 0;
                int temp = start;
                for (String value : values) {
                    if (value.equals(fixStringToRead(temp + i))) {
                        temp += 40;
                        count++;
                    }
                }

                if(count == values.length) {
                    searchArray.add(read(i));
                }
        }
        return searchArray;
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF"> Search from file</span>
     * @param start the initial byte to start reading from the file
     * @param value the value that we want to search base on
     * @return the initial byte to start record that found  from the file
     */
    public long searchIndex(int start , String value) throws IOException {

        for (long i = 0; i < file.length(); i += generator.recordSize()) {
                if (value.equals(fixStringToRead(start + i))) {
                   return  i;
                }
            }
        return -1;
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF"> Remove from file</span>
     * @param value the value that we want to remove base on
     * @param start  the initial byte to start reading from the file
     * @return return if remove is done successfully
     */
    public boolean remove(String value , int start) throws IOException {

        if(search(start, value).size() != 0) {
            E foundElement = search(start, value).get(0);

                if (foundElement instanceof Flight Flight) {
                    FlightWriter flightWriter = new FlightWriter(Data.Flight.generator);
                    flightWriter.removeOption(Flight);
                }else if (foundElement instanceof Ticket){

                    ArrayList<E> tickets = new ArrayList<>(search(start, value));
                    for (E ticket : tickets)
                        removeOption(ticket);

                } else
                    removeOption(foundElement);

                return true;
        }
        return false;
    }

    /**
     *
     * @param foundElement the element that we want to remove it from the file
     */
    public void removeOption(E foundElement) throws IOException {

          for (long i = 0; i < file.length(); i += generator.recordSize()) {
              if (read(i).equals(foundElement)) {

                  for (long k = i; k < file.length() - generator.recordSize(); k += generator.recordSize()) {
                      for (long j = k; j < generator.recordSize() + k; j += 40) {
                          String data = fixStringToRead(j + generator.recordSize());
                          file.seek(j);
                          file.writeChars(fixStringToWrite(data));
                      }
                  }
              }
          }
        file.setLength(file.length() - generator.recordSize());
    }

    /**
     * <span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF"> update from file</span>
     *
     * @param value  the value that we want to update it
     * @param start  the initial byte to start reading from the file
     * @param update the value to replace the previous value
     */
    public void update(String value , int start, String update) throws IOException {

        if (search(start, value).size() != 0) {
            E foundElement = search(start, value).get(0);

            if (foundElement instanceof Flight) {
                FlightWriter flightWriter = new FlightWriter(Ticket.generator);
                flightWriter.update(value, start, update);
            }
                for (long i = 0; i < file.length(); i += generator.recordSize()) {
                    if (value.equals(fixStringToRead(start + i))) {
                        file.seek(i + start);
                        file.writeChars(fixStringToWrite(update));
                    }
                }
        }
    }

    /**
      *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF"> correct the desired format for writing to the file</span>
     * @param str the desired field to write
     * @return string in the correct format
     */
    static String fixStringToWrite(String str) {

        StringBuilder strBuilder = new StringBuilder(str);
        while (strBuilder.length() < 20)
            strBuilder.append(" ");
        return  strBuilder.substring(0, 20);
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Reading the field from file </span>
     * @param start  the initial byte to start reading from the file
     * @return string in the correct format
     */
     public String fixStringToRead(long start) throws IOException {

        StringBuilder strBuilder = new StringBuilder();
        file.seek(start);
        for(long i = 0 ; i < 20 ; i++){
                strBuilder.append(file.readChar());
        }
        return strBuilder.toString().trim();
    }
}
