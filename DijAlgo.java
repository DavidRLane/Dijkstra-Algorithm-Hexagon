import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijAlgo {
		//Private Variables
        private final List<Point> nodes;
        private final List<Edge> edges;
        private Set<Point> visited;
        private Set<Point> unvisited;
        private Map<Point, Point> prev;
        private Map<Point, Integer> dist;

        //Initialize the Graph
        public DijAlgo(Graph graph) {
                // create a copy of the array so that we can operate on this array
                this.nodes = new ArrayList<Point>(graph.getPoints());
                this.edges = new ArrayList<Edge>(graph.getEdges());
        }
        
        //Change status of the node
        private boolean isVisited(Point vertex) {
                return visited.contains(vertex);
        }

        //Initialize all variables
        public void execute(Point source) {
                visited = new HashSet<Point>();
                unvisited = new HashSet<Point>();
                dist = new HashMap<Point, Integer>();
                prev = new HashMap<Point, Point>();
                
                //Set up first node
                dist.put(source, 0);
                unvisited.add(source);
                
                //Set each node to be "unvisited"
                while (unvisited.size() > 0) {
                        Point node = getMinimum(unvisited);
                        visited.add(node);
                        unvisited.remove(node);
                        MinDist(node);
                }
        }

        //Find the minimum distance between each adjacent node from the current node
        private void MinDist(Point node) {
                List<Point> adjacentNodes = getAdjacent(node);
                
                for (Point target : adjacentNodes) {
                		//change the current shortest distance based on a shorter path
                        if (getBestDist(target) > getBestDist(node) + getDist(node, target)) {
                                dist.put(target, getBestDist(node) + getDist(node, target));
                                prev.put(target, node);
                                unvisited.add(target);
                        }
                }

        }

        //Grab distance of the adjacent node
        private int getDist(Point node, Point target) {
                for (Edge edge : edges) {
                        if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                                return edge.getWeight();
                        }
                }
                //Occurs if the edge does not exist, which should not happen
                throw new RuntimeException("Unexpected Error: Edge does not exist");
        }
        
        //Update the distance to the target
        private int getBestDist(Point destination) {
                Integer d = dist.get(destination);
                
                //Set each Node to max value if not set, otherwise set distance
                if (d == null) {
                        return Integer.MAX_VALUE;
                } else {
                        return d;
                }
        }

        //Grab all adjacent nodes to be checked
        private List<Point> getAdjacent(Point node) {
                List<Point> neighbors = new ArrayList<Point>();
                for (Edge edge : edges) {
                		//Grab adjacent node that is not yet visited and add to "neighbors"
                        if (edge.getSource().equals(node) && !isVisited(edge.getDestination())) {
                                neighbors.add(edge.getDestination());
                        }
                }
                return neighbors;
        }

        //Set minimum distance for the current node; 
        //If not set, use the current node as default
        private Point getMinimum(Set<Point> points) {
                Point min = null;
                for (Point point : points) {
                		//Min is not yet set
                        if (min == null) {
                                min = point;
                        } else {
                        		//Min is compared and set
                                if (getBestDist(point) < getBestDist(min)) {
                                        min = point;
                                }
                        }
                }
                return min;
        }

        //Returns the path from the source node to the target node;
        //Will return null if the path to the target node is blocked 
        //Blocked: Nulls surround target node (Should not happen)
        public LinkedList<Point> getPath(Point target) {
                LinkedList<Point> path = new LinkedList<Point>();
                Point step = target;
                
                //If a path doesn't exist, return null
                if (prev.get(step) == null) {
                        return null;
                }
                //Else, add to the path
                path.add(step);
                while (prev.get(step) != null) {
                        step = prev.get(step);
                        path.add(step);
                }
                // Put it into the correct order
                Collections.reverse(path);
                return path;
        }

}