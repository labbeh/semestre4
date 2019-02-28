package mobapp.lh150094.multiplicationlh;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by lh150094 on 22/01/19.
 */

public class Calculs implements Iterable<String>, Serializable {
    private String[] calculs;
    private int curs;

    public Calculs(int nbCalculs){
        calculs = new String[nbCalculs];
        curs = 0;

        for(int i=0; i<calculs.length; i++)
            calculs[i] = ((int)(Math.random() * 20) -10) +" x "+((int)(Math.random() * 20) -10) +" = ";
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return new CalculIterator();
    }
    public int getCurs(){return curs;}



    private class CalculIterator implements Iterator<String>, Serializable{

        CalculIterator(){
            //curs = 0;
        }
        @Override
        public boolean hasNext() {
            return curs < calculs.length;
        }

        @Override
        public String next() {
            return calculs[curs++];
        }
    }
}
