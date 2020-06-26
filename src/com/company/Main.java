package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        LinkedHashSet<String> badWords = new LinkedHashSet<>();
        String line;
        String linesFromResult;
        String[] lines;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("text.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                lines = TextFormater.getArrayFromString(line);
                try (FileWriter fileWriter = new FileWriter("result.txt", true)) {
                    for (String item : lines) {
                        if (!item.endsWith(".")) {
                            fileWriter.write(item + " ");
                        } else
                            fileWriter.write(item + "\n");   //считываем все строки текста в промежуточный файл, где все предложения
                                                                  // с новой строки
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("blackList.txt"))) {
            while ((linesFromResult = bufferedReader.readLine()) != null) {
                badWords.add(linesFromResult);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("result.txt"))) {
            int flag=0;
            while ((linesFromResult = bufferedReader.readLine()) != null) {
                if (TextFormater.hasBadWords(linesFromResult, badWords)) {
                    System.out.println(linesFromResult);
                    flag++;
                }
            }
            if(flag==0){
                System.out.println("Текст прошел цензуру");
            } else {
                System.out.printf("Текст не прошел цензуру. Нужно исправить %d предложений(-я)",flag);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

