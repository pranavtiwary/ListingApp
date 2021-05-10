package com.pranav.listing.service;

import java.util.Locale;
import java.util.Optional;
import java.util.PriorityQueue;

public class CacheService {

    private static final PriorityQueue<CategoryCount> cache = new PriorityQueue<>((x,y)->
            Integer.compare(x.count, y.count));

    static class CategoryCount{
        public String category;
        public int count;
        public CategoryCount(String category, int count){
            this.category=category;
            this.count =count;
        }
    }

    public static void updateOnCreate(String categoryS){
        final String category = categoryS.toLowerCase(Locale.ROOT);
        Optional<CategoryCount> any = cache.stream().filter(e -> e.category.equalsIgnoreCase(category)).findAny();
        int count = 1;
        if(any.isPresent()){
            CategoryCount inCahe = any.get();
            cache.remove(inCahe);
            count = inCahe.count + 1;
        }
        cache.add(new CategoryCount(category, count));
    }

    public static void updateOnDelete(String categoryS){
        final String category = categoryS.toLowerCase(Locale.ROOT);
        Optional<CategoryCount> any = cache.stream().filter(e -> e.category.equalsIgnoreCase(category)).findAny();
        int count = 0;
        if(any.isPresent()){
            CategoryCount inCahe = any.get();
            cache.remove(inCahe);
            count = inCahe.count - 1;
        }
        if(count != 0){
            cache.add(new CategoryCount(category, count));
        }
    }
    public static String getTopCategory(){
        if(cache.isEmpty()){
            return "";
        }
        return cache.peek().category;
    }
}
