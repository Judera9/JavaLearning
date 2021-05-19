package Ass4.problem2; // FIXME:

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
    private boolean isDirected; // graph type
    private int graphSize; // equals to the number of vertexes
    private List<Vertex> vertexes = new ArrayList<>(); // use array to represent graph (no need to add nodes)
    private int[][] adjMatrix; // adjacent matrix
    private static final int infinity = 2147483646; // the max value, cannot reach

//    public static void main(String[] args) throws IOException {
//        Graph graph = new Graph();
//        graph.readGraphFile("Homework\\src\\Ass4\\problem2\\test\\graph1.txt");
//        System.out.println(graph.printAdjacencyMatrix());
//        System.out.println(graph.printAdjacencyList());
//        System.out.println(graph.ShortestPath(graph.vertexes.get(1).val, graph.vertexes.get(6).val));
//    }

    public void readGraphFile(String strFile) throws IOException {
        File file = new File(strFile);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr);

        isDirected = (Integer.parseInt(reader.readLine()) == 1);
        String readInStringLine = reader.readLine();
        while (readInStringLine != null) {
            String[] pathAttr = readInStringLine.split(" "); // the first is start node, the second is end node, third
            // is dis
            int newReadInVertex = Integer.parseInt(pathAttr[0]);
            Vertex curVertex = findVertex(newReadInVertex);
            Vertex endVertex = findVertex(Integer.parseInt(pathAttr[1]));
            curVertex.addPath(Integer.parseInt(pathAttr[2]), endVertex);
            if (!isDirected) { // add an inverse path for directed graph
                endVertex.addPath(Integer.parseInt(pathAttr[2]), curVertex);
            }
            readInStringLine = reader.readLine();
        }

        // sort all the vertexes and paths
        Collections.sort(vertexes);
        for (Vertex vertex : vertexes) {
            Collections.sort(vertex.paths);
        }

        graphSize = vertexes.size();
        adjMatrix = new int[graphSize][graphSize]; // now update the adjacent matrix
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < vertexes.get(i).paths.size(); j++) {
                adjMatrix[i][vertexes.get(i).paths.get(j).endVertex.val] = vertexes.get(i).paths.get(j).dis;
            }
        }

        reader.close();
    }

    public String printAdjacencyList() {
        StringBuilder buffer = new StringBuilder();
        for (Vertex vertex : vertexes) { // use the adjMatrix to print list
            buffer.append(String.format("%d:", vertex.val));
            for (int j = 0; j < vertex.paths.size(); j++) {
                Path curPath = vertex.paths.get(j);
                buffer.append(String.format("(%d,%d:%d)", vertex.val, curPath.endVertex.val, curPath.dis));
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public String printAdjacencyMatrix() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                buffer.append(adjMatrix[i][j]).append(" "); // there are space at the end of line in the tests
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public String ShortestPath(int startVertex) {
        StringBuilder builder = new StringBuilder();
        if (!findVertex(startVertex).isStartVertex) {
            Dijkstra(findVertex(startVertex));
        }
        for (int i = 0; i < graphSize; i++) {
            if (vertexes.get(i).val == startVertex)
                continue;
            if (findVertex(startVertex).shortDis[i] == infinity)
                builder.append(String.format("[%d]", 0));
            else
                builder.append(String.format("[%d]", findVertex(startVertex).shortDis[i]));
            if (findVertex(startVertex).shortDis[i] == infinity) {
                builder.append("null\n");
                continue;
            }
            builder.append(String.format("%d", findVertex(i).val));
            Vertex curVertex = findVertex(i).prevVertex;
            while (curVertex != null) {
                builder.insert(builder.lastIndexOf("]") + 1, String.format("%d,", curVertex.val));
                curVertex = curVertex.prevVertex;
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public String ShortestPath(int startVertex, int endVertex) {
        StringBuilder builder = new StringBuilder();
        if (!findVertex(startVertex).isStartVertex) {
            Dijkstra(findVertex(startVertex));
        }
        if (findVertex(startVertex).shortDis[endVertex] == infinity)
            builder.append(String.format("[%d]", 0));
        else
            builder.append(String.format("[%d]", findVertex(startVertex).shortDis[endVertex]));
        if (findVertex(startVertex).shortDis[endVertex] == infinity) {
            builder.append("null");
            return builder.toString();
        }
        builder.append(String.format("%d", endVertex));
        Vertex curVertex = findVertex(endVertex).prevVertex;
        while (curVertex != null) {
            builder.insert(builder.lastIndexOf("]") + 1, String.format("%d,", curVertex.val));
            curVertex = curVertex.prevVertex;
        }
        return builder.toString();
    }

    public void Dijkstra(Vertex startVertex) {
        startVertex.upgrade();
        for (int i = 0; i < graphSize; i++) {
            startVertex.shortDis[i] = infinity;
        }
        startVertex.prevVertex = null;
        Vertex curVertex = startVertex;
        while (!isAllVisited()) {
            curVertex.isVisited = true;
            System.out.println(">> " + curVertex.toString());
            for (Path path : curVertex.paths) {  // find in all nearby nodes(paths)
                System.out.println(">> Current path: " + path.toString());

                if (findVertex(path.endVertex.val).isVisited) {
                    System.out.println(">> " + path.endVertex.val + " already visited");
                    continue;
                }
                int newDis;
                System.out.println(">> newDis is 0 now");
                if (startVertex.shortDis[curVertex.val] == infinity) {
                    newDis = adjMatrix[curVertex.val][path.endVertex.val];
                    System.out.println(">> newDis is not 0 for connected: " + newDis);
                } else {
                    newDis = startVertex.shortDis[curVertex.val] + adjMatrix[curVertex.val][path.endVertex.val];
                    System.out.println(">> not the first time update: " + startVertex.shortDis[curVertex.val] + "to" + newDis);
                }
                // is a nearby node & newDis > original
                if (startVertex.shortDis[path.endVertex.val] > newDis || startVertex.shortDis[path.endVertex.val] == infinity) {
                    System.out.println(String.format(">> Update dis from %d to %d", startVertex.shortDis[path.endVertex.val], newDis));
                    startVertex.shortDis[path.endVertex.val] = newDis;
                    findVertex(path.endVertex.val).prevVertex = curVertex;
                } else {
                    System.out.println(">> not update, still " + startVertex.shortDis[path.endVertex.val]);
                }
                System.out.println();
            }
            curVertex = nextVertex(startVertex, curVertex);
            if (curVertex == null) {
                break;
            }
        }
    }

    class Path implements Comparable<Path> { // implements Comparable
        int dis; // the distance of the path
        Vertex startVertex;
        Vertex endVertex;

        public Path(int dis, Vertex startVertex, Vertex endVertex) {
            this.dis = dis;
            this.startVertex = startVertex;
            this.endVertex = endVertex;
        }

        @Override
        public int compareTo(Path o) {
            if (this.endVertex.val > o.endVertex.val)
                return 1;
            else
                return -1;
        }

        public String toString() {
            return String.format("from %d pass %d to %d", startVertex.val, dis, endVertex.val);
        }
    }

    class Vertex implements Comparable<Vertex> {
        int val; // the nth vertex, n = val
        List<Path> paths = new ArrayList<>();

        public Vertex(int val) {
            this.val = val;
        }

        public void addPath(int dis, Vertex endVertex) {
            paths.add(new Path(dis, this, endVertex));
        }

        @Override
        public int compareTo(Vertex o) {
            if (this.val > o.val)
                return 1; // ascendant
            else
                return -1;
        }

        boolean isVisited = false;
        Vertex prevVertex;

        // have the following attributes when it is a startVertex
        boolean isStartVertex;
        int[] shortDis; // store the shortest distances found

        public void upgrade() {
            isStartVertex = true;
            shortDis = new int[graphSize];
        }

        public String toString() {
            return String.format("Vertex: %d\n>> %s", val, paths.toString());
        }
    }

    public Vertex findVertex(int newVertexVal) { // find if the node val already exists, return the Vertex anyway
        for (Vertex vertex : vertexes) {
            if (newVertexVal == vertex.val) { // find the node existed
                return vertex;
            }
        }
        Vertex newVertex = new Vertex(newVertexVal);
        vertexes.add(newVertex);
        return newVertex;
    }

    public boolean isAllVisited() { // judge when to end the Dj method' loop
        for (int i = 0; i < graphSize; i++) {
            if (!vertexes.get(i).isVisited) {
                return false;
            }
        }
        return true;
    }

    public Vertex nextVertex(Vertex startVertex, Vertex curVertex) {
        System.out.println("-> In method @nextVertex");
        int min = 2147483646; // infinity
        Vertex next = null;
        for (int i = 0; i < graphSize; i++) {
            if (findVertex(i).isVisited) { // need to find not visited node
                System.out.println(">> this vertex had visited: " + findVertex(i).val);
                continue;
            }
            if (startVertex.shortDis[i] == infinity) {
                System.out.println("node " + i + " is not connected");
                continue;
            }

            if (startVertex.shortDis[i] < min) {
                min = startVertex.shortDis[i];
                next = findVertex(i);
                System.out.println(">> min : " + min + " | next: " + next.val);
            }
        }
        if (next == null) {
            System.out.println(">> has null @next vertex");
        }
        return next;
    }
}
