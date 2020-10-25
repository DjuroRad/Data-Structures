import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;


public class SimpleTextEditor2 {
    ArrayList<Character> text;
    SimpleTextEditor2(){
        text = new ArrayList<>();
    }

    /**
     * Read method to read in a text file and construct the text.
     * @param file_name String - name of the file to read
     * @throws IOException if file not found throws this exception
     */
    public void readFile(String file_name)throws IOException {
        File my_file = new File( file_name );
        Scanner reader = new Scanner(my_file);
        String current_line = new String();
        while( reader.hasNextLine() ){
            current_line = reader.nextLine();
            for( int i = 0; i<current_line.length(); ++i )
                text.add(current_line.charAt(i));
            text.add('\n');
        }
        reader.close();
    }
    /**
     * Read method to read in a text file and construct the text.(just uses iterator insted)
     * @param file_name String - name of the file to read
     * @throws IOException if file not found throws this exception
     */
    public void readFile2(String file_name)throws IOException {
        File my_file = new File( file_name );
        Scanner reader = new Scanner(my_file);
        String current_line = new String();
        ListIterator<Character> itr = text.listIterator();
        while( reader.hasNextLine() ){
            current_line = reader.nextLine();
            for( int i = 0; i<current_line.length(); ++i )
                itr.add(current_line.charAt(i));
            itr.add('\n');
        }
        reader.close();
    }
    /**
     * adds one or more characters (given as a string) at the specified
     * position (given as an integer index)
     * @param text text to add
     * @param index position at which text is to be added
     */
    public void add(String text, int index ){
        if( index >= 0 && index < this.text.size() ){
            for( int i = 0; i<text.length(); ++i, ++index )
                this.text.add( index, text.charAt(i) );
        }
        else
            throw new IndexOutOfBoundsException();
    }

    /**
     * adds one or more characters (given as a string) at the specified( just uses iterator instead )
     * position (given as an integer index)
     * @param text text to add
     * @param index position at which text is to be added
     */
    public void add2(String text, int index ){
        ListIterator<Character> itr = this.text.listIterator(index);
        if( index >= 0 && index < this.text.size() ){
            for( int i = 0; i<text.length(); ++i, ++index ) {
                itr.add(text.charAt(i));
            }
        }
        else
            throw new IndexOutOfBoundsException();
    }
    /**
     *returns the start index of the first occurrence of the searched
     * group of characters.
     * @param text string to find
     * @return -1 if not found, index if found
     */
    public int find(String text){
        int index = -1;
        boolean found = false;
        for( int i = 0; i<this.text.size() && !found; ++i ){
            found = true;
            index = i;
            for( int j = i, k = 0;j < this.text.size() && k < text.length() && found; ++j, ++k ){
                if( this.text.get(j) != text.charAt(k) ) {
                    found = false;
                    index = -1;
                }
            }
        }
        return index;
    }
    /**
     *returns the start index of the first occurrence of the searched -- just uses iterator instead
     * group of characters.
     * @param text string to find
     * @return -1 if not found, index if found
     */
    public int find2(String text){
        int index = -1;
        boolean found = false;
        for( int i = 0; i<this.text.size() && !found; ++i ){
            found = true;
            index = i;
            ListIterator<Character> itr = this.text.listIterator(i);
            for( int k = 0;itr.hasNext() && k < text.length() && found; ++k ){
                if( itr.next() != text.charAt(k) ) {
                    found = false;
                    index = -1;
                }
            }
        }
        return index;
    }
    /**
     * replaces all occurrences of a character with another character.
     * @param ch char to replace
     * @param ch_new new character
     */
    public void replace(Character ch, Character ch_new){
        for( int i = 0; i<text.size(); ++i ){
            if( text.get(i) == ch )
                text.set(i, ch_new);
        }
    }
    /**
     * replaces all occurrences of a character with another character. -- just uses iterator instead
     * @param ch char to replace
     * @param ch_new new character
     */
    public void replace2(Character ch, Character ch_new){
        ListIterator<Character> itr = this.text.listIterator();
        while(itr.hasNext()){
            if( itr.next() == ch )
                itr.set(ch_new);
        }
    }
    /**
     * Overloaded toString method
     * @return our text as a string
     */
    public String toString(){
        String txt = "";
        for(int i = 0; i<text.size();++i)
            txt += text.get(i);
        return txt;
    }
}

