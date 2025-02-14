package org.springframework.samples.petclinic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GreedyAlg {
    
}


// 贪心算法
// 活动选择问题
class MaxActivities {
	public static void main(String[] args) {
		int[][] activities = {
			{4, 6},
			{5, 7},
			{1, 3},
			{2, 5},
			{3, 9},
			{8, 10}
		};
		int result = maxActivities(activities);// 3
		System.out.println(result);
	}

	public static int maxActivities(int[][] activities) {
		Arrays.sort(activities,Comparator.comparingInt(a -> a[1]));
		int count = 0;
		int lastEndTime = 0;
		for (int[] activite : activities) {
			if (activite[0] >= lastEndTime) {
				count++;
				lastEndTime = activite[1];	
			}
		}
		return count;
	}	
	
}

// 最小生成树（Prim或Kruskal算法）
class Kruskal {
	public static void main(String[] args) {
		List<Edge> edges = Arrays.asList(
			new Edge(0, 1, 10),
			new Edge(0, 2, 6),
			new Edge(0, 3, 5),
			new Edge(1, 3, 15),
			new Edge(2, 3, 4)
		);
		int numVertices = 4;
		List<Edge> result = kruskal(edges, numVertices); // 返回最小生成树的边
		System.out.println(result.stream().map(String::valueOf).collect(Collectors.joining(","))); // 2 3 4 , 0 3 5,0 1 10
	}

	public static List<Edge> kruskal(List<Edge> edges, int numVertices) {
		Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
		UnionFind uf = new UnionFind(numVertices);
		List<Edge> mst = new ArrayList<>();
	
		for (Edge edge : edges) {
			if (uf.union(edge.u, edge.v)) {
				mst.add(edge);
			}
		}
		return mst;
	}
}

class Edge {
	public int u;
	public int v;
	public int weight;

	public Edge(int u, int v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [u=" + u + ", v=" + v + ", weight=" + weight + "]";
	}

	
}

class UnionFind {
    private int[] parent; // 每个元素的父节点
    private int[] rank;   // 每个元素的树的高度
    private int count;    // 连接分量的数量

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        count = size;
        for (int i = 0; i < size; i++) {
            parent[i] = i; // 初始化，每个节点的父节点指向自己
            rank[i] = 1;    // 初始化每个树的高度为 1
        }
    }

    // 查找根节点，并进行路径压缩
    public int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]); // 递归查找并压缩路径
        }
        return parent[p];
    }

    // 合并两个集合
    public boolean union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return false; // 已经在同一集合中

        // 按秩合并
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP; // 随便选择一个作为根
            rank[rootP]++;          // 增加树的高度
        }
        count--; // 连接分量数量减少
		return true;
    }

    // 返回连接分量的数量
    public int count() {
        return count;
    }

    // 判断两个元素是否在同一集合中
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}

// 分配糖果问题
class Candy {
	public static void main(String[] args) {
		int[] ratings = {1, 0, 2};
		int result = candy(ratings); // 5 (2 + 1 + 2)
		System.out.println(result);// 5
	}

	public static int candy(int[] ratings) {
		int n = ratings.length;
		int[] candies = new int[n];
		Arrays.fill(candies, 1);
	
		for (int i = 1; i < n; i++) {
			if (ratings[i] > ratings[i - 1]) {
				candies[i] = candies[i - 1] + 1;
			}
		}
		for (int i = n - 2; i >= 0; i--) {
			if (ratings[i] > ratings[i + 1]) {
				candies[i] = Math.max(candies[i], candies[i + 1] + 1);
			}
		}
		return Arrays.stream(candies).sum();
	}
}

// 区间调度问题
class MaxNonOverlappingIntervals {
	public static void main(String[] args) {
		Interval[] intervals = {
			new Interval(0, 2),
			new Interval(1, 3),
			new Interval(2, 4),
			new Interval(3, 5)
		};
		int result = maxNonOverlappingIntervals(intervals); // 3
		System.out.println(result);// 2
	}

	public static int maxNonOverlappingIntervals(Interval[] intervals) {
		Arrays.sort(intervals, Comparator.comparingInt(a -> a.end));
		int count = 0, lastEnd = Integer.MIN_VALUE;
	
		for (Interval interval : intervals) {
			if (interval.start >= lastEnd) {
				count++;
				lastEnd = interval.end;
			}
		}
		return count;
	}
}

class Interval {
	public int start;
	public int end;
	public Interval(int start,int end) {
		this.start = start;
		this.end = end;
	}
}

// 零钱兑换问题
class MinCoins {
	public static void main(String[] args) {
		int[] coins = {1, 2, 5};
		int amount = 11;
		int result = minCoins(coins, amount); // 3 (5 + 5 + 1)
		System.out.println(result); // 3
	}

	public static int minCoins(int[] coins, int amount) {
		Arrays.sort(coins);
		int count = 0;
	
		for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {
			while (amount >= coins[i]) {
				amount -= coins[i];
				count++;
			}
		}
		return amount == 0 ? count : -1; // -1表示无法兑换
	}
}





