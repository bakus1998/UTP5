package zad3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T>{

    public XList(){
        super();
    }

    public XList(Collection<T> myCollection){
        super(myCollection);
    }

    public XList(T... t){
        super(Arrays.asList(t));

    }

    public static <T>XList<T> of(T... t){
        return new XList<>(t);
    }

    public static <T>XList<T> of(Collection<T> myCollection){
        return new XList<>(myCollection);
    }

    public static <T>XList<T> charsOf(String s){
        char[] arr = s.toCharArray();
        List<Character> list = new ArrayList<>();

        for(int i=0;i<arr.length;i++){
            list.add((char)arr[i]);
        }

        return new XList(list);
    }

    public static <T>XList<T> tokensOf(String ... s){
        List<String> list = new ArrayList<>();

        if(s.length==1) {
            StringTokenizer st = new StringTokenizer(s[0], " ");
            while (st.hasMoreTokens()) {
                list.add(st.nextToken());
            }
        }else{
            StringTokenizer st = new StringTokenizer(s[0], s[s.length-1]);
            while (st.hasMoreTokens()) {
                list.add(st.nextToken());
            }
        }
        return new XList(list);
    }


    public <T>XList<T> union (T ... t){
        XList<T> myList =  new XList(this);
        myList.addAll(Arrays.asList(t));
        return myList;

    }

    public <T>XList<T> union (Collection<T> myCollection){
        XList myList =  this;
        myList.addAll(myCollection);
        return myList;

    }

    public XList<T> diff(Collection<T> myCollection){
        XList<T> myList =  new XList<>(this);
        myList.removeAll(myCollection);
        return myList;

    }

    public <T>XList<T> unique() {
        List myList =  this;
        List myListWithoutDuplicates = (List) myList.stream().distinct().collect(Collectors.toList());
        return new XList<>(myListWithoutDuplicates);
    }

    public<S>XList<S> collect(Function<T,S> func){
        List<S> myList = new ArrayList<>();
        for (S s : myList) {
            myList.add(func.apply((T) s));
        }

        return new XList<>(myList);
    }

    public String join(){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<this.size();i++){
            sb.append(this.get(i) + " ");
        }
        return sb.toString();
    }

    public String join(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<this.size();i++){
            sb.append(this.get(i) + s);
        }
        return sb.toString();
    }


    public void forEachWithIndex(BiConsumer<T,Integer> b) {
        for (int i = 0; i < this.size(); i++) {
            b.accept(this.get(i), i);
        }
    }




    public <Y> XList<XList<Y>> combine() {

        List myList = this;
        List<Object> objList = new ArrayList<>();
        List<Integer> lenghts = new ArrayList<>();
        List vals = new ArrayList<>();

        //System.out.println("Size: "+ tokensOfString(myList.get(1).toString()));
        //System.out.println("FOR: ");
        int counter_whole_size = 0;

        for(int j=0;j<this.size();j++) {
            char[] cArray =myList.get(j).toString().toCharArray();
            List l = new ArrayList();
            for (int i = 0; i < cArray.length; i++) {
                if (cArray[i] != 91 && cArray[i] != ']' && cArray[i] != ' ' && cArray[i] != ',') {
                    //System.out.println(cArray[i]);
                    counter_whole_size++;
                    l.add(cArray[i]);
                    vals.add(cArray[i]);
                }
            }
            lenghts.add(l.size());
            objList.add(l);
        }

        for(int i=0;i<lenghts.size();i++){
            if(i==0){
             counter_whole_size=lenghts.get(i);
            }else{
                counter_whole_size*=lenghts.get(i);
            }
        }

        System.out.println("TO SIE OBLICZA: " + counter_whole_size);
        System.out.println("Lengths: " + lenghts + ", all size: " + counter_whole_size);
        System.out.println("Vals: " + vals);


        ArrayList<Integer> sizes = new ArrayList<>();
        sizes.add(1);
        for(int i=0; i<this.size(); i++) {
            sizes.add(sizes.get(i)*((List<Y>)this.get(i)).size());
        }
        //Zaczynamy od tworzenia arraya który ma wielkosc wszystkich możliwości
/*        XList myList = this;
        List comSize = new ArrayList();

        boolean whenStart = false;
        System.out.println("START!: ");
        for(int i=0;i<myList.size();i++){
            System.out.println(this.get(i));

        }*/





        ArrayList<XList<Y>> al = new ArrayList<XList<Y>>();
        for(int i=0; i<sizes.get(sizes.size()-1); i++) {
            al.add(new XList<Y>());
        }
        System.out.println("AL: " + al);
        for(int i=0; i<this.size(); i++) {
            int counter = 0;
            int index = 0;
            for(int j=0; j<al.size(); j++) {
                if(counter>=sizes.get(i)) {
                    counter = 0;
                    index++;
                }
                al.get(j).add(((List<Y>)this.get(i)).get(index%((List<Y>)this.get(i)).size()));
                counter++;
                System.out.println("I: " + i + ", J: " + j +", counter= " + counter + ", index: " + index);
                System.out.println(al);
                System.out.println("Index: " + index + " size: " + ((List<Y>)this.get(i)).size()+ ", %:" + index%((List<Y>)this.get(i)).size() + "\n");
            }
        }
        XList<XList<Y>> x = new XList<XList<Y>>(al);
        return x;
    }

/*


    private List tokensOfString(String s){
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(s, ",");
        while (st.hasMoreTokens()) {
                list.add(st.nextToken());
        }
        return list;
    }
*/


}
