import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

        private final List<Vertex> nodes;
        private final List<Edge> edges;
        private Set<Vertex> visited;
        private Set<Vertex> unvisited;
        private Map<Vertex, Vertex> predecessors;
        private Map<Vertex, Integer> distance;

        //Initialize the Graph
        public DijkstraAlgorithm(Graph graph) {
                // create a copy of the array so that we can operate on this array
                this.nodes = new ArrayList<Vertex>(graph.getVertexes());
                this.edges = new ArrayList<Edge>(graph.getEdges());
        }

        //Construct all variables
        public void execute(Vertex source) {
                visited = new HashSet<Vertex>();
                unvisited = new HashSet<Vertex>();
                distance = new HashMap<Vertex, Integer>();
                predecessors = new HashMap<Vertex, Vertex>();
                distance.put(source, 0);
                unvisited.add(source);
                
                //Initialize each node to be "unvisited"
                while (unvisited.size() > 0) {
                        Vertex node = getMinimum(unvisited);
                        visited.add(node);
                        unvisited.remove(node);
                        findMinimalDistances(node);
                }
        }

        //Find the mimimum distance between each adjacent node from the current node
        private void findMinimalDistances(Vertex node) {
                List<Vertex> adjacentNodes = getNeighbors(node);
                for (Vertex target : adjacentNodes) {
                		//change the current shortest distance based on a shorter path
                        if (getShortestDistance(target) > getShortestDistance(node)
                                        + getDistance(node, target)) {
                                distance.put(target, getShortestDistance(node)
                                                + getDistance(node, target));
                                predecessors.put(target, node);
                                unvisited.add(target);
                        }
                }

        }

        //Grab distance of the adjacent node
        private int getDistance(Vertex node, Vertex target) {
                for (Edge edge : edges) {
                        if (edge.getSource().equals(node)
                                        && edge.getDestination().equals(target)) {
                                return edge.getWeight();
                        }
                }
                throw new RuntimeException("Should not happen");
        }

        //Grab all adjacent nodes to be checked
        private List<Vertex> getNeighbors(Vertex node) {
                List<Vertex> neighbors = new ArrayList<Vertex>();
                for (Edge edge : edges) {
                        if (edge.getSource().equals(node)
                                        && !isSettled(edge.getDestination())) {
                                neighbors.add(edge.getDestination());
                        }
                }
                return neighbors;
        }

        //Set minimum distance for the current node; 
        //If not set, use the current node as default
        private Vertex getMinimum(Set<Vertex> vertexes) {
                Vertex minimum = null;
                for (Vertex vertex : vertexes) {
                        if (minimum == null) {
                                minimum = vertex;
                        } else {
                                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                                        minimum = vertex;
                                }
                        }
                }
                return minimum;
        }

        //Change status of the node
        private boolean isSettled(Vertex vertex) {
                return visited.contains(vertex);
        }

        //Update the distance to the target
        private int getShortestDistance(Vertex destination) {
                Integer d = distance.get(destination);
                if (d == null) {
                        return Integer.MAX_VALUE;
                } else {
                        return d;
                }
        }

        //Returns the path from the source node to the target node;
        //Will return null if the path to the target node is blocked 
        //Blocked: Nulls surround target node
        public LinkedList<Vertex> getPath(Vertex target) {
                LinkedList<Vertex> path = new LinkedList<Vertex>();
                Vertex step = target;
                //If a path doesn't exist, return null
                if (predecessors.get(step) == null) {
                        return null;
                }
                //Else, add to the path
                path.add(step);
                while (predecessors.get(step) != null) {
                        step = predecessors.get(step);
                        path.add(step);
                }
                // Put it into the correct order
                Collections.reverse(path);
                return path;
        }

}