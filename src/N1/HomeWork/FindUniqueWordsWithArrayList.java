package N1.HomeWork;

import edu.duke.FileResource;

import java.util.ArrayList;

public class FindUniqueWordsWithArrayList {  // this class find Unique words in the file with ArrayList
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public FindUniqueWordsWithArrayList(){
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    private void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index,value + 1);
            }
        }
    }

    private int findIndexOfMax(){
        int max = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if(myFreqs.get(i) > max){
                max = myFreqs.get(i);
            }
        }
        return max;
    }

    public void test(){
        findUnique();
        System.out.println("unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        int maximum = findIndexOfMax();
        for (int i = 0; i < myFreqs.size(); i++) {
            if(myFreqs.get(i) == maximum){
                System.out.println("The maximum value: " + myWords.get(i) + " " + maximum);
                break;
            }

        }

    }

    public static void main(String[] args) {
        FindUniqueWordsWithArrayList uw = new FindUniqueWordsWithArrayList();
        uw.test();
    }

}
