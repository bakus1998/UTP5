package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T> {
    T t;

    public InputConverter(T t){
        this.t=t;
    }

    public <T,S> S convertBy (Function...f){
        List list = new ArrayList<>();

        list.add(f[0].apply(t));

        for(int i=1;i<f.length;i++){
            list.add(f[i].apply(list.get(i-1)));
        }
        return (S) list.get(list.size()-1);
    }
}

