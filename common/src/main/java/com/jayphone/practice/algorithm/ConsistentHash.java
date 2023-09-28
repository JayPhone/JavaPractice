package com.jayphone.practice.algorithm;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/27
 */

import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHash<T> {
    private final SortedMap<Integer, T> circle = new TreeMap<>();
    private final HashFunction hashFunction;

    public ConsistentHash(HashFunction hashFunction) {
        this.hashFunction = hashFunction;
    }

    public void addNode(T node, int replicas) {
        for (int i = 0; i < replicas; i++) {
            int hash = hashFunction.hash(node.toString() + i);
            circle.put(hash, node);
        }
    }

    public void removeNode(T node) {
        for (int i = 0; i < hashFunction.getNumReplicas(); i++) {
            int hash = hashFunction.hash(node.toString() + i);
            circle.remove(hash);
        }
    }

    public T getNode(String key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashFunction.hash(key);
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public interface HashFunction {
        int hash(String key);

        int getNumReplicas();
    }

    public static void main(String[] args) {
        ConsistentHash<String> consistentHash = new ConsistentHash<>(new HashFunction() {
            @Override
            public int hash(String key) {
                // 简单的哈希函数示例，您可以替换为更复杂的哈希函数
                return key.hashCode();
            }

            @Override
            public int getNumReplicas() {
                return 3; // 每个节点的虚拟副本数量
            }
        });

        // 添加节点
        consistentHash.addNode("Node-1", 3);
        consistentHash.addNode("Node-2", 3);

        // 根据键获取节点
        String key1 = "Data-1";
        String key2 = "Data-2";
        String node1 = consistentHash.getNode(key1);
        String node2 = consistentHash.getNode(key2);

        System.out.println(key1 + " -> " + node1);
        System.out.println(key2 + " -> " + node2);
    }
}

