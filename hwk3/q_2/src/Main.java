import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.LogManager;

public class Main {
    private final static Logger logr = Logger.getLogger("SimpleTextEditor");
    
    public static void main(String[] args) {
        String info = "";
	LogManager.getLogManager().reset();
        long start = System.currentTimeMillis();
        long finish = System.currentTimeMillis();
        long timeElapsed[] = new long[4];
        long timeElapsed2[] = new long[4];
        long timeElapsed3[] = new long[4];
        long timeElapsed4[] = new long[4];

        SimpleTextEditor text = new SimpleTextEditor();

        start = System.currentTimeMillis();
        try {
            text.readFile("text2.txt");
        }
        catch (Exception e) {
            System.out.println("Error is: " + e.getMessage());
        }
        timeElapsed[0] = System.currentTimeMillis()-start;
        info += "\n\nUsing list as linked list and not using iterator\nRead method\n";
        info += text.toString() + "\n";

        start = System.currentTimeMillis();
        text.add("  Djuro je legendica  ",  493);
        timeElapsed[1] = System.currentTimeMillis()-start;
        info += "Using list as linked list and not using iterator\nAdd method, adding '  Djuro je legendica  ' at 493th index \n";
        info += text.toString() + "\n";

        start = System.currentTimeMillis();
        text.replace('a', '.');
        timeElapsed[2] = System.currentTimeMillis()-start;
        info += "Using list as linked list and not using iterator\nReplace method. Replacing all 'a' chars with '.' chars\n";
        info += text.toString() + "\n";

        info += "Using list as linked list and not using iterator\nFind method should find Lorem\n";
        info += "\n" + "Lorem is at position: " + text.find("Lorem") + "\n";

        start = System.currentTimeMillis();
        System.out.println();
        info += "Using list as linked list and not using iterator\nFind method should not find legendic.a and should return -1\n";
        info += "legendic.a is at position: " + text.find("legendic.a") + "\n";
        timeElapsed[3] = System.currentTimeMillis()-start;
//
//        System.out.println("\nReading from a file takes: " + timeElapsed[0] + "ms\n"+
//                "Adding a string takes: " + timeElapsed[1] + "ms\n" +
//                "Replacing characters takes: " + timeElapsed[2] + "ms\n" +
//                "Finding a string inside taxt takes: " + timeElapsed[3] + "ms\n"
//        );




        SimpleTextEditor text2 = new SimpleTextEditor();
        start = System.currentTimeMillis();
        try {
            text2.readFile2("text2.txt");
        }
        catch (Exception e) {
            System.out.println("Error is: " + e.getMessage());
        }
        timeElapsed2[0] = System.currentTimeMillis()-start;
        info += "Using list as linked list and using iterator\nRead method\n";
        info += text2.toString() + "\n";

        start = System.currentTimeMillis();
        text2.add2("  Djuro je legendica  ",  493);
        timeElapsed2[1] = System.currentTimeMillis()-start;
        info += "Using list as linked list and using iterator\nAdd method, adding Djuro je legendica  at 493th position\n";
        info += text2.toString() + "\n";

        start = System.currentTimeMillis();
        text2.replace2('a', '.');
        timeElapsed2[2] = System.currentTimeMillis()-start;
        info += "Using list as linked list and using iterator\nReplace method, replacing 'a' with '.'\n";
        info += text2.toString() + "\n";


        info += "Using list as linked list and using iterator\nFind method, finding Lorem should be possible\n";
        info += "Lorem is at position: " + text2.find("Lorem") + "\n";


        start = System.currentTimeMillis();
        info += "Using list as linked list and using iterator\nFind method, finding legencdic.a should not be possible\n";
        info += "legencdic.a is at position: " + text2.find("legencdic.a ") + "\n";
        timeElapsed2[3] = System.currentTimeMillis()-start;


        SimpleTextEditor2 text3 = new SimpleTextEditor2();

        start = System.currentTimeMillis();
        try {
            text3.readFile("text2.txt");
        }
        catch (Exception e) {
            System.out.println("Error is: " + e.getMessage());
        }
        timeElapsed3[0] = System.currentTimeMillis()-start;
        info += "Using list as arrayList and not using iterator\nRead method\n";
        info += text3.toString() + "\n";

        start = System.currentTimeMillis();
        text3.add("  Djuro je legendica  ",  493);
        timeElapsed3[1] = System.currentTimeMillis()-start;
        info += "Using list as arrayList and not using iterator\nAdd method, adding   Djuro je legencdica   \n";
        info += text3.toString() + "\n";


        start = System.currentTimeMillis();
        text3.replace('a', '.');
        timeElapsed3[2] = System.currentTimeMillis()-start;
        info += "Using list as arrayList and not using iterator\nReplace method, replacing 'a's with '.'s \n";
        info += text3.toString() + "\n";

        info += "Using list as arrayList and not using iterator\nFind method, finding Lorem should be possible\n";
        info += "Lorem is at position: " + text3.find("Lorem") + "\n";

        start = System.currentTimeMillis();
        info += "Using list as arrayList and not using iterator\nFind method, finding legendic.a should not be possible\n";
        info += "legendic.a is at position: " + text3.find("legendic.a") + "\n";
        timeElapsed3[3] = System.currentTimeMillis()-start;




        SimpleTextEditor2 text4 = new SimpleTextEditor2();

        start = System.currentTimeMillis();
        try {
            text4.readFile2("text2.txt");
        }
        catch (Exception e) {
            System.out.println("Error is: " + e.getMessage());
        }
        timeElapsed4[0] = System.currentTimeMillis()-start;
        info += "Using list as arrayList and using iterator\nRead method\n";
        info += text4.toString() + "\n";

        start = System.currentTimeMillis();
        text4.add2("  Djuro je legendica  ",  493);
        timeElapsed4[1] = System.currentTimeMillis()-start;
        info += "Using list as arrayList and using iterator\nAdd method, adding Djuro je legendica at 493th position\n";
        info += text4.toString() + "\n";

        start = System.currentTimeMillis();
        text4.replace2('a', '.');
        timeElapsed4[2] = System.currentTimeMillis()-start;
        info += "Using list as arrayList and using iterator\nReplace method, replacing 'a's with '.'s \n";
        info += text4.toString() + "\n";


        info += "Using list as arrayList and using iterator\nFind method, should find Lorem\n";
        info += "Lorem is at position: " + text4.find2("Lorem") + "\n";


        start = System.currentTimeMillis();
        info += "Using list as arrayList and using iterator\nFind method, should not find legendic.a\n";
        info += "legendic.a is at position: " + text4.find2("legendic.a") + "\n";
        timeElapsed4[3] = System.currentTimeMillis()-start;

        info += "Methods are displayed in ms in the following order: read, add, find, replace";
        info += "\n List(NoIter): " + timeElapsed[0] + ", " + timeElapsed[1] + ", " + timeElapsed[2] + ", " + timeElapsed[3];
        info += "\n List(Iter): " + timeElapsed2[0] + ", " + timeElapsed2[1] + ", " + timeElapsed2[2] + ", " + timeElapsed2[3];
        info += "\n AList(NoIter): " + timeElapsed3[0] + ", " + timeElapsed3[1] + ", " + timeElapsed3[2] + ", " + timeElapsed3[3];
        info += "\n AList(Iter): " + timeElapsed4[0] + ", " + timeElapsed4[1] + ", " + timeElapsed4[2] + ", " + timeElapsed4[3];

        try{
            FileHandler fh = new FileHandler("SimpleTextEditor.log");
            fh.setLevel(Level.INFO);
            logr.addHandler(fh);
            if (logr.isLoggable(Level.INFO)) {
                logr.info(info);
            }
        }
        catch( IOException e ){
            logr.log( Level.SEVERE, "File logger not working.",e );
        }
    }
}

