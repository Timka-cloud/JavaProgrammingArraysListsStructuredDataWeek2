package N2.HomeWork;

import edu.duke.FileResource;

import java.util.HashMap;

public class CountCodonsWithHashMap {
    private HashMap<String,Integer> DNAmap;

    public CountCodonsWithHashMap(){
        DNAmap = new HashMap<>();
    }

    private void buildCodonMap(int start, String dna){
        DNAmap.clear();  //Removes all of the mappings from this map. The map will be empty after this call returns, will make sure map will be empty
        String temp;
        for (int i = 0; i < (dna.length() - start)/3; i++) {
            temp = dna.substring(start+i*3, start+i*3+3);
            if (!DNAmap.containsKey(temp)) {
                DNAmap.put(temp, 1);
//                System.out.println(DNAmap);
            }
            else {
                DNAmap.put(temp, DNAmap.get(temp)+1);
//                System.out.println(DNAmap);
            }
        }
    }

    private String getMostCommonCodon(){
        int largest = 0;
        int val = 0;
        String largestkey = null;
        for(String s  : DNAmap.keySet()){
            val = DNAmap.get(s);
            if(val > largest){
                largest = val;
                largestkey = s;
            }

        }
        return largestkey;
    }

    private void printCodonCounts(int start, int end){
        int value = 0;
        for(String key : DNAmap.keySet())
        {
            value = DNAmap.get(key);
            if (value >= start && value <= end)
                System.out.println( key + " " + value + "\t");
        }
    }



    public void tester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        dna = dna.toUpperCase();
        int start = 1;
        int end = 5;

        buildCodonMap(0, dna);
        System.out.println("Reading frame starting with 0 results in "+DNAmap.size()+" unique codons"+"\t");
        String largest = getMostCommonCodon();
        System.out.println("Most common codon is "+largest+" with count "+DNAmap.get(largest)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);

        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 1 results in "+DNAmap.size()+" unique codons"+"\t");
        largest = getMostCommonCodon();
        System.out.println("Most common codon is "+largest+" with count "+DNAmap.get(largest)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);

        buildCodonMap(2, dna);
        System.out.println("Reading frame starting with 2 results in "+DNAmap.size()+" unique codons"+"\t");
        largest = getMostCommonCodon();
        System.out.println("Most common codon is "+largest+" with count "+DNAmap.get(largest)+"\t");
        System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:"+"\t");
        printCodonCounts(start, end);
    }







    public static void main(String[] args) {
        CountCodonsWithHashMap c = new CountCodonsWithHashMap();
        c.tester();

    }
}
