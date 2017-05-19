import java.util.*;
import java.io.*;
import java.math.*;

/**
 * 
 * Main class that performs the base logic of our program. The puzzle that this program solves
 * is here. 	https://www.codingame.com/ide/puzzle/skynet-revolution-episode-1
 * @author Logan Spencer.
 *         Created May 19, 2017.
 */
class Main
{
    public static void main(String args[]) 
    {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        int EI[] = new int[E];
        Graph graph = new Graph(N); // Create the graph
        
        /* Create the Graph */
        for (int i = 0; i < L; i++) 
        {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            graph.AddEdge(N1, N2);
        }
        for (int i = 0; i < E; i++) 
        {
            EI[i] = in.nextInt(); // the index of a gateway node
        }

        // game loop
        while (true) 
        {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            
            int[] linkedNodes = new int[2]; // Array that represents two vertices (an edge).
            linkedNodes[0] = SI;            // The index vertex of the Skynet Agent this step
            
            /* Cycle through each exit gate node, check if it has any edges. If so, check if there is an edge
             * between the Skynet Agent and the gate, if this condition is met we cut that link. If this condition
             * is not met we cut any link between the Agent and one of his surrounding vertices to try to cut him off. */
            for (int a = 0; a < EI.length; a++)
            {
                if (graph.HasConnection(EI[a])) 
                {                    
                    if (graph.IsEdge(SI, EI[a])) 
                    {
                       linkedNodes[1] = EI[a];
                       break;
                    } 
                    else 
                    {
                        linkedNodes[1] = graph.GetConnection(SI);
                    }
                }
                else if (a == EI.length) 
                {
                    linkedNodes[1] = graph.GetConnection(SI);
                }
            }
            graph.CutEdge(linkedNodes[0], linkedNodes[1]); // Finally cut the found edge
            
            // This is the output taken by the compiler that performs the game Logic
            System.out.println(linkedNodes[0] + " " + linkedNodes[1]);  
        }
    }
}

/**
 * 
 * A basic Undirected Graph data structure represented by an boolean ArrayMatrix.
 * An edge is represented by edge[v1][v2] = true. A value of false between two vertices
 * lets us know that there is no edge.
 * 
 * @author Logan Spencer.
 *         Created May 19, 2017.
 */
class Graph
{
	// A matrix of Edges indexed by two vertices. True means an edge exists.
    boolean[][] edge; 
    int numNodes; // Number of nodes in the Graph
    
    /**
     * Graph constructor that initializes an array matrix in the size of
     * the number of nodes of the graph squared.
     * @param num
     */
    Graph(int num) 
    {
        numNodes = num;
        edge = new boolean[num][num];
    }
    
    /**
     * 
     * Add an edge represented between the two vertices v1, and v2
     * @param v1
     * @param v2
     */
    public void AddEdge(int v1, int v2) 
    {
        edge[v1][v2] = true;
        edge[v2][v1] = true; 
    }
    
    /**
     * Cut an Edge from the graph. Since the graph is undirected we cut
     * both v1, v2 as well as v2, v1 as it represents the same edge.
     * @param v1
     * @param v2
     */
    public void CutEdge(int v1, int v2) 
    {
        edge[v1][v2] = false;
        edge[v2][v1] = false; 
    }
    
    /**
     * Return true if the edge between vertices v1 and v2 exist.
     * @param v1
     * @param v2
     * @return boolean of whether or not the specified edge exists
     */
    public boolean IsEdge(int v1, int v2) 
    {
        return edge[v1][v2];   
    }
    
    /**
     * Find out if a specified vertex (index) has any connecting vertices.
     * @param index
     * @return boolean saying whether or not the specified vertex has any edges
     */
    public boolean HasConnection(int index) {
        for (int i = 0; i < numNodes; i++) {
            if (IsEdge(index, i))
                return true;
        }
        return false;
    }
    
    /**
     * Get an edge between the vertex parameterized by index and
     * the vertex that pops up first in the ArrayMatrix.
     * @param index
     * @return integer of the found vertex
     */
    public int GetConnection(int index) 
    {
        for (int i = 0; i < numNodes; i++) 
        {
            if (IsEdge(index, i))
                return i;
        }
        return 0;
    }
}