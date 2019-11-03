/**
 *
 *  @author Baka Krzysztof S16696
 *
 */

package zad1;


import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
  public static void main(String[] args) {

    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    Function<String,List<String>> flines = s -> {
      List<String> myList = new ArrayList<>();
      try {
        FileInputStream fis = new FileInputStream(new File(s));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        String s_reader;
        while ((s_reader = reader.readLine())!=null){
          myList.add(s_reader);
        }
      }catch (IOException e){
        e.printStackTrace();
      }
      return myList;
    };

    Function<List<String>,String> join = s -> {
        StringBuilder sb = new StringBuilder();
        int index=0;
        for(String str : s){
            sb.append(str);
            //System.out.println(index++ + " " +sb);
        }
        return sb.toString();
    };

    Function<String,List<Integer>> collectInts = s -> {
        List<Integer> intList = new ArrayList<>();
        Pattern p = Pattern.compile("([+-]?[0-9]\\d*|0)");
        Matcher m = p.matcher(s);

        while (m.find()){
            intList.add(Integer.valueOf(m.group()));
        }
        return intList;

    };

    Function<List<Integer>,Integer> sum = s -> {
        int mySum=0;
        for(int i=0;i<s.size();i++){
            mySum+=s.get(i);
        }
        return mySum;
    };


    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);
  }

}