package src.cliquenumber2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CliqueNumber2 {

    Vertex[] V;
    int[][] A;        // 0/1 adjacency matrix
    int n;            // n vertices
    long nodes;       // number of decisions
    long timeLimit;   // milliseconds
    long nodeLimit;   // node limit
    long cpuTime;     // milliseconds
    int maxSize;      // size of max clique
    int style;        // used to flavor algorithm
    int[] solution;   // as it says
    boolean trace;
    boolean complete; // did search complete?
    long colourTime;
    ArrayList[] colourClass;

    public CliqueNumber2 (int[][]A,int[] degree,int style) {
        this.n = A.length;
        this.A = A;
        nodes = maxSize = 0;
        cpuTime = timeLimit = -1;
        this.style = style;
        solution = new int[n];
        nodeLimit = Long.MAX_VALUE;
        V = new Vertex[n];
        for (int i=0;i<n;i++) V[i] = new Vertex(i,degree[i]);
    }

    void search(){
        colourTime                           = 0;
        cpuTime                              = System.currentTimeMillis();
        nodes                                = 0;
        colourClass                          = new ArrayList[n];
        ArrayList<Integer> C                 = new ArrayList<Integer>(n);
        ArrayList<Integer> ColOrd            = new ArrayList<Integer>(n);
        for (int i=0;i<n;i++) colourClass[i] = new ArrayList<Integer>(n);
        orderVertices(ColOrd);
        expand(C,ColOrd);
    }

    void expand(ArrayList<Integer> C,ArrayList<Integer> ColOrd){
        if (timeLimit > 0 && System.currentTimeMillis() - cpuTime >= timeLimit) return;
        nodes++;
        int m = ColOrd.size();
        int[] colour = new int[m];
        ArrayList<Integer> P = new ArrayList<Integer>(m);
        long now = System.currentTimeMillis();
        numberSort(C,ColOrd,P,colour);
        colourTime = colourTime + System.currentTimeMillis() - now;
        for (int i=m-1;i>=0;i--){
            int v = P.get(i);
            if (C.size() + colour[i] <= maxSize) return;
            C.add(v);
            ArrayList<Integer> newColOrd = new ArrayList<Integer>(i);
            for (int j=0;j<=i;j++){
                int w = ColOrd.get(j);
                if (A[v][w] == 1) newColOrd.add(w);
            }
            if (newColOrd.isEmpty() && C.size() > maxSize) saveSolution(C);
            if (!newColOrd.isEmpty()) expand(C,newColOrd);
            C.remove(C.size()-1);
            ColOrd.remove((Integer)v);
        }
    }

    void numberSort(ArrayList<Integer> C,ArrayList<Integer> ColOrd,ArrayList<Integer> P,int[] colour){
        int colours = 0;
        int m = ColOrd.size();
        for (int i=0;i<m;i++) colourClass[i].clear();
        for (int i=0;i<m;i++){
            int v = ColOrd.get(i);
            int k = 0;
            while (conflicts(v,colourClass[k])) k++;
            colourClass[k].add(v);
            colours = Math.max(colours,k+1);
        }
        P.clear();
        int i = 0;
        for (int k=0;k<colours;k++)
            for (int j=0;j<colourClass[k].size();j++){
                int v = (Integer)(colourClass[k].get(j));
                P.add(v);
                colour[i++] = k+1;
            }
    }

    boolean conflicts(int v,ArrayList<Integer> colourClass){
        for (int i=0;i<colourClass.size();i++){
            int w = colourClass.get(i);
            if (A[v][w] == 1) return true;
        }
        return false;
    }

    void saveSolution(ArrayList<Integer> C){
        Arrays.fill(solution,0);
        for (int i : C) solution[i] = 1;
        maxSize = C.size();
    }

    void orderVertices(ArrayList<Integer> VOrd){
	/*
	Vertex[] V = new Vertex[n];
	for (int i=0;i<n;i++) V[i] = new Vertex(i,degree[i]);
	for (int i=0;i<n;i++)
	    for (int j=0;j<n;j++)
		if (A[i][j] == 1) V[i].nebDeg = V[i].nebDeg + degree[j];
	*/
        if (style == 1) Arrays.sort(V);
        if (style == 2) minWidthOrder(V);
        if (style == 3) Arrays.sort(V,new MCRComparator());
        for (Vertex v : V) VOrd.add(v.index);
    }

    void minWidthOrder(Vertex[] V){
        ArrayList<Vertex> L = new ArrayList<Vertex>(n);
        Stack<Vertex> S = new Stack<Vertex>();
        for (Vertex v : V) L.add(v);
        while (!L.isEmpty()){
            Vertex v = L.get(0);
            for (Vertex u : L) if (u.degree < v.degree) v = u;
            S.push(v); L.remove(v);
            for (Vertex u : L) if (A[u.index][v.index] == 1) u.degree--;
        }
        int k = 0;
        while (!S.isEmpty()) V[k++] = S.pop();
    }

    public int[] giveResult(){
        this.search();
        return this.solution;
    }
}
