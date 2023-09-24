package com.potato.study.leetcode.domain;


import lombok.Data;

/**
 * @author liuzhao07 <liuzhao07@kuaishou.com>
 * Created on 2023-09-24
 */
@Data
public class Pair<A, B> {

    public final A key;
    public final B value;

    public Pair(A key, B value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "Pair[" + key + "," + value + "]";
    }


    public int hashCode() {
        if (key == null) return (value == null) ? 0 : value.hashCode() + 1;
        else if (value == null) return key.hashCode() + 2;
        else return key.hashCode() * 17 + value.hashCode();
    }

}
