package leetcode.editor.cn;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private final int size ;
    public LRUCache(int size){
        super((int)Math.ceil(size / 0.75) + 1,0.75f,true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }
}
