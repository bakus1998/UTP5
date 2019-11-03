package zad3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class XList<T> extends ArrayList<T>{
    Collection<T> myCollection;

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
        XList myList =  this;
        myList.addAll(Arrays.asList(t));
        return myList;

    }

    public <T>XList<T> union (Collection<T> myCollection){
        XList myList =  this;
        myList.addAll(myCollection);
        return myList;

    }

    public <T>XList<T> diff(Collection<T> myCollection){
        XList myList =  this;
        myCollection.removeAll(this);
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












    public XList<T> combine(){
        List myList = this;
        List<Object> objList = new ArrayList<>();

        //System.out.println("Size: "+ tokensOfString(myList.get(1).toString()));
        //System.out.println("FOR: ");

        for(int j=0;j<this.size();j++) {
            char[] cArray =myList.get(j).toString().toCharArray();
            List l = new ArrayList();
            for (int i = 0; i < cArray.length; i++) {
                if (cArray[i] != 91 && cArray[i] != ']' && cArray[i] != ' ' && cArray[i] != ',') {
                    //System.out.println(cArray[i]);
                    l.add(cArray[i]);
                }
            }
            objList.add(l);
        }
        //System.out.println(objList.get(0));

/*        List testt = new ArrayList();
        testt.add(myList.get(0));

        List<String> signsToDelete = Arrays.asList("[" , "]" , " " , ",");
        signsToDelete.removeAll(myList);


        System.out.println("TEST: ");
        System.out.println(testt);*/

        return this;
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
