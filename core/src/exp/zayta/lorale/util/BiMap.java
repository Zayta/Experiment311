package exp.zayta.lorale.util;

import java.util.HashMap;
import java.util.Set;

public class BiMap<K,V> {

    HashMap<K,V> map = new HashMap<K, V>();
    HashMap<V,K> inversedMap = new HashMap<V, K>();

    public void put(K k, V v) {
        map.put(k, v);
        inversedMap.put(v, k);
    }

    public V get(K k) {
        return map.get(k);
    }

    public K getKey(V v) {
        return inversedMap.get(v);
    }
    public int size()
    {
        return map.size();
    }

    public Set<K> keySet()
    {
        return map.keySet();
    }

    public void clear()
    {
        map.clear();
        inversedMap.clear();
    }

    public V remove(K key)
    {
        V ret = map.remove(key);
        inversedMap.remove(ret);
        return ret;
    }
    public boolean containsKey(Object key)
    {
        return map.containsKey(key);
    }
    public boolean containsValue(Object value)
    {
        return map.containsValue(value);
    }
    //Note map and inversedMap need to match up when removing
    public K removeKey(V value)
    {
        K ret = inversedMap.remove(value);
        map.remove(ret);
        return ret;
    }

    public String toString()
    {
        String ret = "BiMap Size: "+map.size()+"\n";
        for (K name: map.keySet()){

            String key =name.toString();
            String value = map.get(name).toString();
            ret+=("\n("+key+", "+value+"\n");

        }
        return ret;
    }





}