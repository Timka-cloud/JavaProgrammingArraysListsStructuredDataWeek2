package N1.Class;

import edu.duke.FileResource;
import edu.duke.StorageResource;

import java.util.Arrays;

/**
 * this class find the unique words but without ArrayList (doing with ArrayList it's easier) see class WordFrequencies
 * with combination StorageResource and array
 */

public class FindUniquesWordsWithArrayAndStorageResource {
    StorageResource myWords;

    public FindUniquesWordsWithArrayAndStorageResource(){
        myWords = new StorageResource();
    }

    private void readWords(){
        myWords.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            myWords.add(s.toLowerCase());
        }
    }

    private boolean contains(String[] list, String word, int number){
        for (int i = 0; i < number; i++) {
            if(list[i].equals(word)) {
                return true;
            }
        }
        return false;
    }

    private int numberOfUniqueWords(){
        int numStored = 0;
        String[] words = new String[myWords.size()];
        for(String s : myWords.data()){
            if(! contains(words,s,numStored)){
                words[numStored] = s;
                System.out.println(Arrays.toString(words));
                numStored++;
            }
        }
        return numStored;
    }
    public void tester(){
        readWords();
        System.out.println("number of words read " + myWords.size());
        int unique = numberOfUniqueWords();
        System.out.println("array count unique words " + unique);

    }

    public static void main(String[] args) {
        FindUniquesWordsWithArrayAndStorageResource w = new FindUniquesWordsWithArrayAndStorageResource();
        w.tester();
    }
}
