package com.github.zyt;

import net.minidev.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zyt
 * @Date: 2019/4/1 10:46
 * @Description:
 */
public class BlogDao {
    void insert(Blog blog) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        Map<String, Object> map = new HashMap<>(3);
        map.put("key", blog.getKey());
        map.put("title", blog.getTitle());
        map.put("content", blog.getContent());
        JSONObject jsonObject = new JSONObject(map);
        jedis.set(String.valueOf(blog.getKey()),jsonObject.toString());
        jedis.close();
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        jedis.set("name", "zyt");
    }

}
