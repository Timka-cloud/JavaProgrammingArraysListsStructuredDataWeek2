package N2.Class;

import edu.duke.FileResource;

import java.util.HashMap;

public class FindUniquesWordsWithHashMap { // this class find Unique words in the file with HashMap
    private final HashMap<String,Integer> words;
    private final FileResource resource;

    public FindUniquesWordsWithHashMap(){
        words = new HashMap<String,Integer>();
        resource = new FileResource();
    }

    private int countAllWords(FileResource resource){

        int count  = 0;
        for(String s : resource.words()){
            count++;
        }
        return count;
    }

    private void findUnique(FileResource resource){
        for(String s : resource.words()){
            s = s.toLowerCase();
            if(!words.containsKey(s)){
                words.put(s,1);
//                System.out.println(words); // for testing
            }
            else {
                words.put(s,words.get(s) + 1);
//                System.out.println(words); // for testing
            }
        }
    }




    public void test() {
        int total = countAllWords(resource);
        System.out.println("Total words is " + total);
        findUnique(resource);
        System.out.println("The unique words " + words.size());
        for(String s : words.keySet()){
            System.out.println(words.get(s) + "\t" + s);
        }

    }

    public static void main(String[] args) {
        FindUniquesWordsWithHashMap f = new FindUniquesWordsWithHashMap();
        f.test();
    }


}
