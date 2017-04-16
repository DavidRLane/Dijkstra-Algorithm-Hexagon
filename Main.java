import java.io.*;
import java.util.*;

/**
 *
 * @author Emilio Solis
 * Edit Author: @author David Lane
 *
 * Synthetic Shakespeare        CS582
 * Edit: Trump Twitter Generator		CS582
 * 
 * San Diego State University   Fall 2010
 * Edit Date: San Diego State University	Spring 2017
 * 
 */
public class Main {
    public static void main(String[] args) {
        Hashtable word = doLoad("C:\\HTwords.ser");

        try { 
        	//Loading an Array with Different Words
            int index = word.size();
            String A[] = new String[index];
            int i = 0;
            for (Enumeration e = word.keys(); e.hasMoreElements();) {
                Object obj = e.nextElement();
                A[i] = (String) obj;
                i++;
            }

            //Load the different Hashtables
            Hashtable biFword = doLoad("C:\\BiFwords.ser");
            Hashtable biBword = doLoad("C:\\BiBwords.ser");

            //Loading the 2 Forward Words
            int BiF_index = biFword.size();
            String BF[] = new String[BiF_index];
            i = 0;
            for (Enumeration e = biFword.keys(); e.hasMoreElements();) {
                Object obj = e.nextElement();
                BF[i] = (String) obj;
                i++;
            }

            //Loading the 2 Backwards Words
            int BiB_index = biBword.size();
            String BB[] = new String[BiB_index];
            i = 0;
            for (Enumeration e = biBword.keys(); e.hasMoreElements();) {
                Object obj = e.nextElement();
                BB[i] = (String) obj;
                i++;
            }

            Arrays.sort(BF);
            Arrays.sort(BB);
            int count = 0;

            //Number of Sentences to be created (count)
            while (count < 10) {
                boolean flag = false;
                count++;

                // Get a random word
                Random generator = new Random();
                int r = generator.nextInt(index);
                String sentence = A[r];

                //Check if the random sentence is a start
                String pivot = "<s>";

                while (!flag) {
                    int inx = 0;
                    
                    //Finds a starting point
                    while (!BF[inx].startsWith(pivot)) {
                        inx++;
                    }
                    int start = inx;

                    //Finds an ending point
                    while (BF[inx].startsWith(pivot)) {
                        inx++;
                    }
                    int end = inx;

                    String BF_Sen[] = new String[end - start];

                    //Loads words into a sentence array
                    int BF_index = 0;
                    for (i = start; i < end; i++) {
                        BF_Sen[BF_index] = BF[i];
                        BF_index++;
                    }
                    int x = generator.nextInt(end - start);
                    String Sentence = BF_Sen[x];
                    
                    //Get the 1 and 2nd. word
                    StringTokenizer st = new StringTokenizer(Sentence);
                    String Pivot = st.nextToken();
                    pivot = st.nextToken();

                    //Generates a sentence to an arbitrary length (80)
                    //There may be words with '!', '?' or other variations in the sentences
                    if ((pivot.equals("</s>") && sentence.length() > 15) || sentence.length() > 80) // End Sentence
                    {
                        flag = true;
                    } else {
                        if (!pivot.equals("</s>")) {
                            sentence = sentence + " " + pivot;
                        }
                    }
                }
                sentence = (sentence.substring(0, 1)).toUpperCase() + sentence.substring(1);
                System.out.println(count + ") " + sentence);
            }
            System.exit(0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Hashtable doLoad(String file) {
        @SuppressWarnings("UseOfObsoleteCollectionType")
        Hashtable h = null;

        try {
            FileInputStream fileIn = new FileInputStream(file);
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
        return h;
    }
}
