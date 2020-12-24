package N2.HomeWork;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList> myMap;

    public WordsInFiles(){
        myMap = new HashMap<>();
    }

    private void addWordsFromFile(File f){
        /**
         * этот метод добавляет слова из файла и формирует карту где слово это ключ а имя файла это значение
         * пример : cat = [brief1.txt] cat is key, brief1.txt is value
         */
        FileResource resource = new FileResource(f);

        for(String word : resource.words()){

            if(!myMap.containsKey(word)){ // если мапа не содержить этот ключ
                ArrayList<String> a = new ArrayList<>(); // создаем ArrayList
                a.add(f.getName()); // закидываем туда имя файла
                myMap.put(word,a); // слова это ключ а имя файла это значение

            }

            else {
                ArrayList<String> a; // создаем ArrayList
                a = myMap.get(word); // получает значение по ключу и записывает в а
                if ( !a.contains(f.getName())) { // если в списке имя файла нету
                    a.add(f.getName()); // добавить имя файла
                }
            }
        }

    }

    public void buildWordFileMap(){
        /**
         * этот метод перебирает файлы и передает их в метод выше
         */
        myMap.clear(); // сначала очищаем мапу
        DirectoryResource dr = new DirectoryResource(); // этот класс позволяет выбирать много файлов сразу
        for(File f : dr.selectedFiles()){ // делаем итерацию по файлам
            addWordsFromFile(f); // добавляем файл в метод
        }

    }

    public int maxNumber(){
        /**
         * этот метод ищет максимальное число значение которые встречалась в файлах
         */
        int maxSize = 0;
        for (ArrayList s : myMap.values()){ // передаем в ArrayList значение из HashMap
//            System.out.println(s.toString()); // for testing
            if (s.size() > maxSize) {
                maxSize = s.size();
            }
        }
        return maxSize;
    }

    public ArrayList wordsInNumFiles(int number){
        /**
         * этот метод ищет сколько у ключа значений иначе говоря сколько раз в каких файлах встречались слова переданые аргументом number
         */
        System.out.println("\nThis words appear " + number + " times: ");
        ArrayList<String> words = new ArrayList<>();
        int counting =0;
        for (String word : myMap.keySet()){ // передают в строку ключи
            int counts = myMap.get(word).size(); // передают размер значение хранивщиеся под этим ключом
            if(counts == number){ // если равны
                words.add(word); // добавляю в список
                counting++;
            }
        }
        System.out.println("total of words repeated " + number + " times: " + counting);
        return words;
    }

    public void printFilesIn(String word){
        System.out.println(word + " appears in this files:");
        ArrayList<String> files = new ArrayList<>();
        for(String w : myMap.keySet()){
            if(w.equals(word)){
                files = myMap.get(w);
                System.out.println(files);

            }
        }
    }

    public void tester(){
        buildWordFileMap();
        ArrayList list = wordsInNumFiles(2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("The maximum number of words " + maxNumber());
        printFilesIn("quick");
        System.out.println();
        for(String s : myMap.keySet()){
            System.out.println(s + "\t" + myMap.get(s));
        }
    }


    public static void main(String[] args) {
        WordsInFiles w = new WordsInFiles();
        w.tester();



    }

}
