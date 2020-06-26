package com.company;


import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class TextFormater {
    public static String[] getArrayFromString(String string) {
        String[] array = string.split(" ");
        return array;
    }


    public static boolean hasBadWords (String string, LinkedHashSet<String> badWords){
        boolean flag=false;
        LinkedHashSet<String> strings=new LinkedHashSet<>();
        String [] array =string.split(" ");
        List<String> words =Arrays.asList(array);
        for(String item: words){
            strings.add(item);
        }
        int size=strings.size();
        strings.addAll(badWords);
        if(size+badWords.size()==strings.size()){
            flag=false;
        } else {
            flag=true;
        }
        return flag;
    }


}
