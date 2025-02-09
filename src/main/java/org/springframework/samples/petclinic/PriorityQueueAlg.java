package org.springframework.samples.petclinic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

public class PriorityQueueAlg {
    
}

// 合并K个排序链表
class MergeKLists {
    public static void main(String[] args) {
        ListNode[] lists = {
            new ListNode(1, new ListNode(4, new ListNode(5))),
            new ListNode(1, new ListNode(3, new ListNode(4))),
            new ListNode(2, new ListNode(6))
        };
        ListNode result = mergeKLists(lists); // 返回合并后的链表
        System.out.println(result);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
    
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
    
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;
    
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
    
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; 
    }

    ListNode(int x,ListNode next) { val = x; 
        this.val = x;
        this.next = next;
    }
}


// 找到数据流的中位数
class MedianFinder {
    private PriorityQueue<Integer> maxHeap; // 大顶堆
    private PriorityQueue<Integer> minHeap; // 小顶堆

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        double median = mf.findMedian(); // 1.5
        System.out.println(median);
        mf.addNum(3);
        median = mf.findMedian(); // 2.0
        System.out.println(median);
    }
}


// 最小K个元素
class FindKthSmallest {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        List<Integer> result = findKthSmallest(nums, k); // [2, 3]
        System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(",")));
        // 2,1
    }

    public static List<Integer> findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
    
        return maxHeap.stream().collect(Collectors.toCollection(ArrayList::new));
    }
}


// 网络延迟时间
class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = {
            {2, 1, 1},
            {2, 3, 1},
            {1, 2, 1}
        };
        int N = 3;
        int K = 2;
        int result = networkDelayTime(times, N, K); // 2
        System.out.println(result); // 1
    }

    public static int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.computeIfAbsent(time[0], x -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }
    
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeap.offer(new int[]{K, 0});
        Map<Integer, Integer> dist = new HashMap<>();
    
        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int node = curr[0], d = curr[1];
    
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
    
            for (int[] neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                int nextNode = neighbor[0], time = neighbor[1];
                if (!dist.containsKey(nextNode)) {
                    minHeap.offer(new int[]{nextNode, d + time});
                }
            }
        }
    
        return dist.size() == N ? dist.values().stream().max(Integer::compare).get() : -1;
    }
}

// 跳跃游戏 II
class Jump {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        int result = jump(nums); // 2
        System.out.println(result);// 2
    }

    public static int jump(int[] nums) {
        if (nums.length <= 1) return 0;
    
        int jumps = 0, currentEnd = 0, farthest = 0;
    
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
    
        return jumps;
    }
}

