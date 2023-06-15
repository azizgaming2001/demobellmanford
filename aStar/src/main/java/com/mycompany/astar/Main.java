/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.astar;


/**
 *
 * @author ADMIN
 */
import java.util.*;

class Edge {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class BellmanFord {
    int vertices, edges;
    List<Edge> edgeList;

    BellmanFord(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edgeList = new ArrayList<>();
    }

    void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
    }

    void shortestPath(int source) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        for (int i = 1; i < vertices; ++i) {
            for (int j = 0; j < edges; ++j) {
                int u = edgeList.get(j).source;
                int v = edgeList.get(j).destination;
                int weight = edgeList.get(j).weight;
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                }
            }
        }

        // Kiểm tra chu trình âm
        for (int j = 0; j < edges; ++j) {
            int u = edgeList.get(j).source;
            int v = edgeList.get(j).destination;
            int weight = edgeList.get(j).weight;
            if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                System.out.println("do thi chua chu trinh am");
                return;
            }
        }

        // In kết quả
        printDistances(distances);
    }

    void printDistances(int[] distances) {
        System.out.println("duong di ngan nhat tu nguon:");
        for (int i = 0; i < vertices; ++i) {
            System.out.println("dinh " + i + ": " + distances[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;
        BellmanFord bellmanFord = new BellmanFord(vertices, edges);

        bellmanFord.addEdge(0, 1, 4);
        bellmanFord.addEdge(0, 2, 3);
        bellmanFord.addEdge(1, 3, 2);
        bellmanFord.addEdge(1, 2, 5);
        bellmanFord.addEdge(2, 3, 7);
        bellmanFord.addEdge(3, 4, 2);
        bellmanFord.addEdge(4, 0, 4);
        bellmanFord.addEdge(4, 1, 4);

        int source = 0;
        bellmanFord.shortestPath(source);
    }
}
