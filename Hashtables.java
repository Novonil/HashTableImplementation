import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Hashtables 
{
    static int filledentries = 0;
    static int filledentriesquad = 0;
    public int HashCode(String input, int tablesize)
        {
            int hash = 0;
            int index;
            char[] ch = input.toCharArray();
            for (int i = 0; i < ch.length; i++)
            {
                hash += (int)ch[i];
            }
            index = hash % tablesize;
            return index;
        }
        
        public static void main(String[] args) throws IOException
        {
            String input;
            double loadfactor = 0.00, loadfactorquad = 0.00;
            int tablesize = 53, ElementFound = -1;
            //int filledentries = 0;
            //int filledentriesquad = 0;

            String[] hashtable = new String[1000];
            String[] hashtablequad = new String[1000];
            BufferedReader flreader;
            flreader = new BufferedReader(new FileReader("Words.txt"));

            
            //String[] lines = {"abc","cde"};//System.IO.File.ReadAllLines(@"C:\\Words.txt");
            boolean isLinearProbing;

            Hashtables ht = new Hashtables();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            //LINEAR PROBING

            isLinearProbing = true;
            ht.ResetHashTable(hashtable, tablesize);
            System.out.println("Linear Probing");
            input = flreader.readLine();
            while (input != null)
            {
                ElementFound = ht.SearchElement(hashtable, input, tablesize, isLinearProbing);

                if (ElementFound > -1)
                {
                    System.out.println("------------------------------------------");
                    System.out.println("Element: " + input + " exists at index: " + ElementFound);
                    System.out.println("------------------------------------------");
                    input = flreader.readLine();
                    continue;
                }

                System.out.println("Inserting Element: " + input);
                System.out.println("------------------------------------------");
                if (loadfactor < 0.5)
                {
                    //ht.InsertElement(hashtable, input, tablesize, isLinearProbing, filledentries);
                    ht.InsertElement(hashtable, input, tablesize, isLinearProbing, false);
                }

                else
                {
                    tablesize *= 2;
                    System.out.println("Resizing Table to " + tablesize);
                    ht.Rehash(hashtable, tablesize, isLinearProbing);
                    ht.InsertElement(hashtable, input, tablesize, isLinearProbing, false);
                }
                loadfactor = (double)filledentries / (double)tablesize;
                System.out.println("Filled Entries " + filledentries);
                input = flreader.readLine();
                System.out.println("------------------------------------------");
            }
            flreader.close();

            System.out.println("IF THE WORD EXIST - WE WILL PRINT THE INDEX ELSE WE WILL INSERT IT INTO THE HASH TABLE");
            System.out.println("ENTER WORD OR TYPE exit TO STOP");
            input = reader.readLine();
            System.out.println("=================================================");

            
            while (!input.equals("exit"))
            {
                ElementFound = ht.SearchElement(hashtable, input, tablesize, isLinearProbing);

                if (ElementFound > -1)
                {
                    System.out.println("Element: " + input + " exists at index: " + ElementFound);
                    System.out.println("=================================================");
                    System.out.println("ENTER WORD. PRESS CTRL + X to exit");
                    input = reader.readLine();
                    
                    System.out.println("=================================================");
                    continue;
                }

                System.out.println("Inserting Element: " + input);
                if (loadfactor < 0.5)
                {
                    ht.InsertElement(hashtable, input, tablesize, isLinearProbing, false);
                }

                else
                {
                    tablesize *= 2;
                    System.out.println("Resizing Table to " + tablesize);
                    ht.Rehash(hashtable, tablesize, isLinearProbing);
                    ht.InsertElement(hashtable, input, tablesize, isLinearProbing, false);
                }
                loadfactor = (double)filledentries / (double)tablesize;
                System.out.println("Filled Entries " + filledentries);
                System.out.println("=================================================");
                System.out.println("ENTER WORD. PRESS CTRL + X to exit");
                input = reader.readLine();
                
                System.out.println("=================================================");
            }

            //QUADRATIC PROBING STARTS HERE

            BufferedReader flquadreader;
            flquadreader = new BufferedReader(new FileReader("Words.txt"));
            isLinearProbing = false;
            tablesize = 53;
            ht.ResetHashTable(hashtablequad, tablesize);
            System.out.println("Quadratic Probing");
            input = flquadreader.readLine();
            while (input != null)
            {
                ElementFound = ht.SearchElement(hashtablequad, input, tablesize, isLinearProbing);

                if (ElementFound > -1)
                {
                    System.out.println("------------------------------------------");
                    System.out.println("Element: " + input + " exists at index: " + ElementFound);
                    System.out.println("------------------------------------------");
                    input = flquadreader.readLine();
                    continue;
                }

                System.out.println("Inserting Element: " + input);
                System.out.println("------------------------------------------");
                if (loadfactorquad < 0.5)
                {
                    ht.InsertElement(hashtablequad, input, tablesize, isLinearProbing, false);
                }

                else
                {
                    tablesize *= 2;
                    System.out.println("Resizing Table to " + tablesize);
                    ht.Rehash(hashtablequad, tablesize, isLinearProbing);
                    ht.InsertElement(hashtablequad, input, tablesize, isLinearProbing, false);
                }
                loadfactorquad = (double)filledentriesquad / (double)tablesize;
                System.out.println("Filled Entries " + filledentriesquad);
                input = flquadreader.readLine();
                System.out.println("------------------------------------------");
            }
            flquadreader.close();



            System.out.println("IF THE WORD EXIST - WE WILL PRINT THE INDEX ELSE WE WILL INSERT IT INTO THE HASH TABLE");
            System.out.println("ENTER WORD OR TYPE exit TO STOP");
            input = reader.readLine();
            System.out.println("=================================================");
            while (!input.equals("exit"))
            {
                ElementFound = ht.SearchElement(hashtablequad, input, tablesize, isLinearProbing);

                if(ElementFound > -1)
                {
                    System.out.println("Element: " + input + " exists at index: " + ElementFound);
                    System.out.println("=================================================");
                    System.out.println("ENTER WORD. PRESS CTRL + X to exit");
                    input = reader.readLine();
                    System.out.println("=================================================");
                    continue;
                }

                System.out.println("Inserting Element: " + input);
                if (loadfactorquad < 0.5)
                {
                    ht.InsertElement(hashtablequad, input, tablesize, isLinearProbing, false);
                }

                else
                {
                    tablesize *= 2;
                    System.out.println("Resizing Table to " + tablesize);
                    ht.Rehash(hashtablequad, tablesize, isLinearProbing);
                    ht.InsertElement(hashtablequad, input, tablesize, isLinearProbing, false);
                }
                loadfactorquad = (double)filledentriesquad / (double)tablesize;
                System.out.println("Filled Entries " + filledentriesquad);
                System.out.println("=================================================");
                System.out.println("ENTER WORD. PRESS CTRL + X to exit");
                input = reader.readLine();
                System.out.println("=================================================");
            } 
        }


        public int SearchElement(String[] hashtable, String input, int tablesize, boolean isLinearProbing)
        {
            int index, i = 0;
            Hashtables ht = new Hashtables();

            index = ht.HashCode(input,tablesize);
            
            if(isLinearProbing)
            {
                while(hashtable[(index + i) % tablesize] != null && i<tablesize)
                {
                    if(hashtable[(index + i) % tablesize].equals(input))
                    {
                        return (index + i) % tablesize;
                    }
                    i++;
                }
                return -1;
            }
            else
            {
                while (hashtable[(index + (i*i)) % tablesize] != null && i < tablesize)
                {
                    if (hashtable[(index + (i * i)) % tablesize].equals(input))
                    {
                        return (index + (i * i)) % tablesize;
                    }
                    i++;
                }
                return -1;
            }
        }

        public void InsertElement(String[] hashtable, String input, int tablesize, boolean isLinearProbing, boolean isFromRehash)
        {
            int index, i = 0;
            Hashtables ht = new Hashtables();

            index = ht.HashCode(input, tablesize);

            if (isLinearProbing)
            {
                while (hashtable[(index + i) % tablesize] != null && i < tablesize)
                {
                    i++;
                }
                if (i > 0)
                {
                    System.out.println("Collision Occured " + i + " times.");
                }
                if(i==tablesize)
                {
                    System.out.println("Table Full or no empty space to accomodate the values in its correct position.");
                    return;
                }
                hashtable[(index + i) % tablesize] = input;
                System.out.println("Inserted: " + input + " at index: " + ((index + i) % tablesize));
                if(!isFromRehash)
                {
                    filledentries++;
                }
            }
            else
            {
                while (hashtable[(index + (i * i)) % tablesize] != null && i < tablesize)
                {
                    i++;
                }
                if (i > 0)
                {
                    System.out.println("Collision Occured " + i + " times.");
                }
                if (i == tablesize)
                {
                    System.out.println("Table Full or no empty space to accomodate the values in its correct position.");
                    return;
                }
                hashtable[(index + (i * i)) % tablesize] = input;
                System.out.println("Inserted: " + input + " at index: " + ((index + (i * i)) % tablesize));
                if(!isFromRehash)
                {
                    filledentriesquad++;
                }
            }
        }

        public void Rehash(String[] hashtable, int tablesize, boolean isLinearProbing)
        {
            int OldLength = tablesize / 2;
            String[] temp = new String[OldLength];
            temp = Arrays.copyOf(hashtable, temp.length);
            //hashtable.clone(temp);
            ResetHashTable(hashtable, OldLength);
            for (int i = 0; i<OldLength; i++)
            {
                if(temp[i] != null)
                {
                    InsertElement(hashtable, temp[i], tablesize, isLinearProbing, true);
                }
            }
        }
        public void ResetHashTable(String[] hashtable,int tablesize)
        {
            for (int i = 0; i < hashtable.length; i++)
            {
                hashtable[i] = null;
            }
        }
}