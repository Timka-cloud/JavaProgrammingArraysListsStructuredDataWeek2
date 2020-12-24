package N2.Class;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GladLib { // этот класс создает веселые истории из шаблона заменяя ключевые слова из ArrayList
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    private ArrayList<String> garbage;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private String dataSourceDirectory = "data";

    public GladLib(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source){   // I don't know for what this constructor needs
       initializeFromSource(source);
       myRandom = new Random();
 }

    private void initializeFromSource(String source){ // this method initialize the arrayList
        adjectiveList = readIt(source+"/adjective.txt");
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        garbage = new ArrayList<>();
    }

    private String randomFrom(ArrayList<String> source){ // this method do random
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label){ // сравнивает слово со списком если совпадают то добавляет
        if(label.equals("country")){
            return randomFrom(countryList);
        }
        if(label.equals("color")){
            return randomFrom(colorList);
        }
        if(label.equals("noun")){
            return randomFrom(nounList);
        }
        if(label.equals("name")){
            return randomFrom(nameList);
        }
        if(label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if(label.equals("animal")){
            return randomFrom(animalList);
        }
        if(label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if(label.equals("number")){
            return "" + myRandom.nextInt(50)+5;
        }
        if(label.equals("verb")){
            return randomFrom(verbList);
        }
        if(label.equals("fruit")){
            return randomFrom(fruitList);
        }
        return "UNKNOWN";
    }

    private String processWord(String w){ // ищет ключевые слова с знаками <>
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
//        String prefix = w.substring(0,first); // I don't know why it needs here
//        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while(garbage.contains(sub)){ // этот цикл нужен чтобы не повторялись слова
            sub = getSubstitute(w.substring(first+1,last));
        }

        garbage.add(sub);
//        System.out.println(garbage); // for testing
        //return prefix+sub+suffix;
        return sub;
    }

    private void printOut(String s, int lineWidth){ // этот метод распечатывает текст
        int charsWritten = 0;

        for(String w : s.split("\\s+")){ // \\s+ это шаблон который разделяет строку на массив с помощью метода split
            if (charsWritten + w.length() > lineWidth){ // если превышает lineWidth то начинает с новой строки и обнуляет эту переменную
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) { // для прочтение файла и затем записи в строку
        String story = "";
        //if (source.startsWith("http")) {   // это нужно если берем данные с инета
        //    URLResource resource = new URLResource(source);
        //    for(String word : resource.words()){
        //        story = story + processWord(word) + " ";
        //     }
        // }
        // else {
        FileResource resource = new FileResource(source); // открывает файл и считывает по словам. берет данные с компа
        for (String word : resource.words()) {
            story = story + processWord(word) + " "; // записывает все в переменную story
        }
    // }
        return story;
    }

    private ArrayList<String> readIt(String source){ // читает файл и затем добавляет данные в ArrayList
        ArrayList<String> list = new ArrayList<String>();
//        if (source.startsWith("http")) {         // Это нужно если мы берем файлы из инета
//            for(String line : resource.lines()){
//                list.add(line);
//            }
//        }
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }

        return list;
    }

    public void makeStory(){
        String story = fromTemplate("data/madtemplate1.txt");
        printOut(story, 60);
        System.out.println("\nReplaced words: " + garbage.size());
    }

    public static void main(String[] args) {
        GladLib g = new GladLib();
        g.makeStory();

    }







}
