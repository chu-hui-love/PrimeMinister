package com.chuhui.primeminister.db.datastruct;

/**
 * SkipList
 *
 * @author: 纯阳子
 * @Date: 2019/6/27
 * @Description: 跳跃表实现 redis中跳跃表的java版
 */
public class RedisSkipList {


    static final int ZSKIPLIST_MAXLEVEL = 64;

    public ZSkipListNode zslCreateNode(int level, double score, String ele) {
        ZSkipListNode listNode = new ZSkipListNode();
        listNode.score = score;
        listNode.ele = ele;
        return listNode;

    }


    public ZSkipList zslCreate() {
        int j;
        ZSkipList zsl = new ZSkipList();

        zsl.level = 1;
        zsl.length = 0;
        zsl.header = zslCreateNode(ZSKIPLIST_MAXLEVEL, 0, null);
        for (j = 0; j < ZSKIPLIST_MAXLEVEL; j++) {
            zsl.header.level[j].forward = null;
            zsl.header.level[j].span = 0;
        }
        zsl.header.backward = null;
        zsl.tail = null;
        return zsl;
    }





    static class ZSkipListNode {

        private String ele;
        private double score;
        private ZSkipListNode backward;
        private ZSkipListLevel[] level;
    }

    static class ZSkipListLevel {
        private ZSkipListNode forward;
        private long span;
    }

    static class ZSkipList {
        private ZSkipListNode header;
        private ZSkipListNode tail;
        private long length;
        private int level;
    }

}