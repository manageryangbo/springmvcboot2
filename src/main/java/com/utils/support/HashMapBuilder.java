package com.utils.support;

import java.util.HashMap;


public  class HashMapBuilder extends HashMap<String, Object>{

    public  HashMapBuilder map(String key, Object value){
        super.put(key, value);
        return this;
    }

    public static HashMapBuilder build() {
        HashMapBuilder mapBuilder = new HashMapBuilder();
        return mapBuilder;
    }

    public static void main(String[] args) {
        HashMapBuilder build = HashMapBuilder.build().map("a", "b");
        System.out.println(build.get("a"));
    }
     
}