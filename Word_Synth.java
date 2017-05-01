import java.io.*;
import java.util.*;

/**
 *
 * @author Emilio Solis
 * @author David Lane
 * 
 */
//Generates files that will contain the most likely pair of words,
//from 2 forwards and backwards, and then 3 forwards and backwards.
//The Files are contained as .SER
public class Word_Synth {
	
    public static void doSave() {
    	//File that will be used for generating, after being modified
        File file = new File("TwitterOutput.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedReader dis;

        try {
            String sCurrentLine;
            fis = new FileInputStream(file);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new BufferedReader(new InputStreamReader(bis));
            
            // Map the words using a Hashtable
			Hashtable words = new Hashtable();
            @SuppressWarnings("rawtypes")
			Hashtable biFwords = new Hashtable();
            @SuppressWarnings("rawtypes")
			Hashtable biBwords = new Hashtable();
            @SuppressWarnings("rawtypes")
			Hashtable thFwords = new Hashtable();
            @SuppressWarnings("rawtypes")
			Hashtable thBwords = new Hashtable();

            // dis.available() returns 0 if the file does not have more lines.
            while ((sCurrentLine = dis.readLine()) != null) {
                
            	// this statement reads the line from the file and
                // Split the line in multiple words.
                StringTokenizer st = new StringTokenizer(sCurrentLine);
                String A[] = new String[st.countTokens()];
                int index = 0;
                while (st.hasMoreTokens()) {
                    A[index] = st.nextToken();
                    index++;
                }
                doWord(A, words);
                doBiFword(A, biFwords);
                doBiBword(A, biBwords);
                doThFword(A, thFwords);
                doThBword(A, thBwords);
            }

            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();

            System.out.println("Creating File/Object output stream...");

            //Generate all the Files fitted with N-Gram Phrases and Words
            FileOutputStream fileOut = new FileOutputStream("HTwords.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            System.out.println("Writing Hashtable Object...");
            out.writeObject(words);

            System.out.println("Closing all output streams...\n");
            out.close();
            fileOut.close();

            //2-Forward Words File
            FileOutputStream bifileOut = new FileOutputStream("BiFwords.ser");
            ObjectOutputStream biout = new ObjectOutputStream(bifileOut);
            biout.writeObject(biFwords);
            biout.close();
            bifileOut.close();
            
            //2-Backwards Words File
            FileOutputStream biBfileOut = new FileOutputStream("BiBwords.ser");
            ObjectOutputStream biBout = new ObjectOutputStream(biBfileOut);
            biBout.writeObject(biBwords);
            biBout.close();
            biBfileOut.close();

            //3-Forward Words File
            FileOutputStream thFfileOut = new FileOutputStream("ThFwords.ser");
            ObjectOutputStream thFout = new ObjectOutputStream(thFfileOut);
            thFout.writeObject(thFwords);
            thFout.close();
            thFfileOut.close();

            //3-Backwards Words File
            FileOutputStream thBfileOut = new FileOutputStream("ThBwords.ser");
            ObjectOutputStream thBout = new ObjectOutputStream(thBfileOut);
            thBout.writeObject(thBwords);
            thBout.close();
            thBfileOut.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function that maps a single word into a Hashtable
    private static void doWord(String A[], Hashtable words) {
        int tot = 0;
        if (words.containsKey("**")) {
            tot = (Integer) words.get("**");
        }
        
        // Maping a single word
        for (int i = 0; i < A.length; i++) {
        	//single word 
            String word = A[i]; 					
            System.out.println(word);
            int freq = 0;
            tot++;

            if (words.containsKey(word)) {
                freq = (Integer) words.get(word);
                freq++;
                words.put(word, freq);
            } else {
                words.put(word, 1);
            }
            words.put("**", tot);
        }
    }

    //Function that maps a double word going forward
    private static void doBiFword(String A[], Hashtable biFwords) {
        int tot = 0;
        if (biFwords.containsKey("**")) {
            tot = (Integer) biFwords.get("**");
        }
        // Maping a double words
        for (int i = 0; i < A.length-1; i++) {
        	// double word
            String biword = A[i]+" "+A[i+1]; 
            System.out.println(biword);
            int freq = 0;
            tot++;

            if (biFwords.containsKey(biword)) {
                freq = (Integer) biFwords.get(biword);
                freq++;
                biFwords.put(biword, freq);
            } else {
                biFwords.put(biword, 1);
            }
            biFwords.put("**", tot);
        }
    }

    //Function that maps a double word going backwards
    private static void doBiBword(String A[], Hashtable biBwords) {
        int tot = 0;
        if (biBwords.containsKey("**")) {
            tot = (Integer) biBwords.get("**");
        }
        // Maping a double words (Back)
        for (int i = A.length; i >1; i--) {
        	// double word (Back)
            String biword = A[i-1]+" "+A[i-2]; 
            System.out.println(biword);
            int freq = 0;
            tot++;

            if (biBwords.containsKey(biword)) {
                freq = (Integer) biBwords.get(biword);
                freq++;
                biBwords.put(biword, freq);
            } else {
                biBwords.put(biword, 1);
            }
            biBwords.put("**", tot);
        }
    }

    //Function that maps a triple word going forward
    private static void doThFword(String A[], Hashtable thFwords) {
        int tot = 0;
        if (thFwords.containsKey("**")) {
            tot = (Integer) thFwords.get("**");
        }
        // Maping a double words
        for (int i = 0; i < A.length-2; i++) {
        	// three words
            String threeword = A[i]+" "+A[i+1]+A[i+2]; 
            System.out.println(threeword);
            int freq = 0;
            tot++;

            if (thFwords.containsKey(threeword)) {
                freq = (Integer) thFwords.get(threeword);
                freq++;
                thFwords.put(threeword, freq);
            } else {
                thFwords.put(threeword, 1);
            }
            thFwords.put("**", tot);
        }
    }

    //Function that maps a triple word going backwards
    private static void doThBword(String A[], Hashtable thBwords) {
        int tot = 0;
        if (thBwords.containsKey("**")) {
            tot = (Integer) thBwords.get("**");
        }
        // Maping a double words (Back)
        for (int i = A.length; i >2; i--) {
        	// three words (Back)
            String threeword = A[i-1]+" "+A[i-2]+" "+A[i-3]; 
            System.out.println(threeword);
            int freq = 0;
            tot++;

            if (thBwords.containsKey(threeword)) {
                freq = (Integer) thBwords.get(threeword);
                freq++;
                thBwords.put(threeword, freq);
            } else {
                thBwords.put(threeword, 1);
            }
            thBwords.put("**", tot);
        }
    }

    //Function that loads the values into a file, then prints them to the screen
    private static void doLoad() {
        Hashtable h = null;

        try {

            System.out.println("Creating File/Object input stream...");

            FileInputStream fileIn = new FileInputStream("HTwords.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            System.out.println("Loading Hashtable Object...");

            h = (Hashtable) in.readObject();

            System.out.println("Closing all input streams...\n");
            in.close();
            fileIn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Printing out loaded elements...");
        for (Enumeration e = h.keys(); e.hasMoreElements();) {
            Object obj = e.nextElement();
            System.out.println("  - Element(" + obj + ") = " + h.get(obj));
        }
        System.out.println();

        try {

            FileInputStream fileIn = new FileInputStream("BiFwords.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            h = (Hashtable) in.readObject();
            in.close();
            fileIn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Enumeration e = h.keys(); e.hasMoreElements();) {
            Object obj = e.nextElement();
            System.out.println("  - Element(" + obj + ") = " + h.get(obj));
        }
        System.out.println();

        try {

            FileInputStream fileIn = new FileInputStream("BiBwords.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            h = (Hashtable) in.readObject();
            in.close();
            fileIn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Enumeration e = h.keys(); e.hasMoreElements();) {
            Object obj = e.nextElement();
            System.out.println("  - Element(" + obj + ") = " + h.get(obj));
        }
        System.out.println();

        try {

            FileInputStream fileIn = new FileInputStream("ThFwords.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            h = (Hashtable) in.readObject();
            in.close();
            fileIn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Enumeration e = h.keys(); e.hasMoreElements();) {
            Object obj = e.nextElement();
            System.out.println("  - Element(" + obj + ") = " + h.get(obj));
        }
        System.out.println();

        try {

            FileInputStream fileIn = new FileInputStream("ThBwords.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            h = (Hashtable) in.readObject();
            in.close();
            fileIn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Enumeration e = h.keys(); e.hasMoreElements();) {
            Object obj = e.nextElement();
            System.out.println("  - Element(" + obj + ") = " + h.get(obj));
        }
        System.out.println();

    }

    public static void main(String[] args) {
        StreamTokenizer Input = new StreamTokenizer(System.in);
        try {
            System.out.print(" Do you want to create a file <y/n> : ");
            Input.nextToken();
            if (Input.sval.equals("y") | Input.sval.equals("Y")) {
                doSave();
            } else {
                doLoad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
