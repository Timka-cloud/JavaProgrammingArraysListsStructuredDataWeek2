package N1.HomeWork;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> character;
    private ArrayList<Integer> counter;

    public CharactersInPlay(){
        character = new ArrayList<>();
        counter = new ArrayList<>();
    }
    public void update(String person){
        //If person is not already in names, add person as a new names object and 1 to the arrayList as a new object:
        int index = character.indexOf(person);
        if(index == -1) {
            character.add(person);
            counter.add(1);
//            System.out.println(character); // it's for see how it works
        }
        /**If person is already in names, change counts adding 1 everytime we find a person.
         * This is so intelligent. By playing with the index of the Sting arrayList, you can
         * manipulate the counts without modifying the names ArrayList
         **/
        else {
            int val = counter.get(index);
            counter.set(index, val + 1);
//            System.out.println(character); // it's for see how it works
        }
    }

    public void findAllCharacters(){
        FileResource resource = new FileResource();
        String temp;
        for(String line : resource.lines()){
            if(line.indexOf(".") != - 1){
                temp = line.substring(0,line.indexOf("."));
                update(temp);
            }
        }
    }

    public void tester(){
        counter.clear();
        character.clear();
        findAllCharacters();
        for (int i=0; i < character.size(); i++){
            if (counter.get(i) > 1){
                System.out.println(character.get(i) + "\t" + counter.get(i));
            }
        }
        charactersWithNumParts(10,15);
    }

    public void charactersWithNumParts(int num1, int num2){
        System.out.println("Characters that have between "+ num1 + " and " + num2+ " lines:");
        for (int i=0; i < character.size(); i++){
            if (counter.get(i) >= num1 && counter.get(i)<= num2){
                System.out.println(character.get(i) + "\t" + counter.get(i));
            }
        }
    }


    public static void main(String[] args) {
        CharactersInPlay c = new CharactersInPlay();
        c.tester();
    }
}
