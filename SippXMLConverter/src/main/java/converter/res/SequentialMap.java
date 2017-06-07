/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.res;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author christian
 */
public class SequentialMap<E,F> {
    private ArrayList<E> keys = new ArrayList<E>();
    private ArrayList<F> values = new ArrayList<F>();
     
    public void clear() {
        keys.clear();
        values.clear();
    }
    public boolean containsKey(E key) {
        return keys.contains(key);
    }
    public boolean containsValue(F value) {
        return values.contains(value);
    }
    public List<F> entryList() {
        return values;
    }
    public Iterator<F> entryIterator() {
        return values.iterator();
    }
    public boolean equals(Object o) {
        if(o instanceof SequentialMap) {
            SequentialMap sm = (SequentialMap)o;
            if(this.keys.equals(sm.keys)&&this.values.equals(sm.values))
                return true;
        }
        return false;
    }
    public F get(E key) {
        return values.get(keys.indexOf(key));
    }
    public F get(int i) {
        return values.get(i);
    }
    public E getKey(int i) {
        return keys.get(i);
    }
    public E firstKey() {
        return keys.get(0);
    }
    public E lastKey() {
        return keys.get(keys.size()-1);
    }
    public int getIndex(E key) {
        return keys.indexOf(key);
    }
    public int hashCode() {
        return keys.hashCode()+values.hashCode();
    }
    public String toString() {
        StringBuffer ret = new StringBuffer();
        for(int i=0;i<keys.size();i++) {
            ret.append(keys.get(i)+": "+values.get(i)+"\n");
        }
        return new String(ret);
    }
    public boolean isEmpty() {
        return keys.isEmpty();
    }
    public List<E> keyList() {
        return keys;
    }
    public Iterator<E> keyIterator() {
        return keys.iterator();
    }
    public F put(E key, F value) {
        keys.add(key);
        values.add(value);
        return value;
    }
    public void putAll(SequentialMap<E,F> t) {
        for(E key : t.keyList()) {
            keys.add(key);
        }
        for(F value : t.entryList()) {
            values.add(value);
        }
    }
    public F remove(E key) {
        int index = keys.indexOf(key);
        F prev = values.get(index);
        keys.remove(index);
        values.remove(index);
        return prev;
    }
    public int size() {
        return keys.size();
    }
    public Collection<F> values() {
        return values;
    }
}