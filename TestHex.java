import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestHex {
        private static List<Vertex> nodes;
        private static List<Edge> edges;

        public static void Excute() {
                nodes = new ArrayList<Vertex>();
                edges = new ArrayList<Edge>();
                int distance = 0;
                
                //Add Nodes into the Graph; Initialization
                for (int i = 0; i < 233; i++) {
                        Vertex location = new Vertex(Integer.toString(i+1), Integer.toString(i+1));
                        nodes.add(location);
                }

                //Input Reader
                //Array to hold inputs
                int[] graphIn = new int[233];
                try {
             	   String sCurrentLine; 
                    BufferedReader br = new BufferedReader(new FileReader("Hex Input.txt"));
                    
                    //Read Inputs until Null
             	   outerloop:
                    while ((sCurrentLine = br.readLine()) != null) {
                 	   for(int i=0; i<=233; i++){
                 		   //Delimiter
         				   String[] parts = sCurrentLine.split(" ");
         				   
         				   //Place Input Into Array
         				   graphIn[i] = Integer.parseInt(parts[1]);
         				   
         				   //Break out of Loop when last line is read
         				   if((sCurrentLine = br.readLine()) == null){
         					   break outerloop;
         				   
         				   }
         			   }
         		   }
         		} catch (NumberFormatException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		} catch (IOException e) {
         			// TODO Auto-generated catch block
         			e.printStackTrace();
         		}
                
                //Hex Generation for Each Node
                for(int i=0; i<233; i++){
                	//Corners; 2 Adjacent Nodes
                	if(i==0){
                		addLane(i, 8, graphIn[8]);
                		addLane(i, 15, graphIn[15]);
                	}
					else if(i==7){
						addLane(i, 14, graphIn[14]);
						addLane(i, 22, graphIn[22]);
					}
					else if(i==225){
						addLane(i, 210, graphIn[210]);
						addLane(i, 218, graphIn[218]);
					}
					else if(i==232){
						addLane(i, 224, graphIn[224]);
						addLane(i, 217, graphIn[217]);
					}
                	
                	//Top Sides; 3 Adjacent Nodes or 5 Adjacent Nodes
                	else if(i==1 || i==2 || i==3 || i==4 || i==5 || i==6){
                		addLane(i, i+7, graphIn[i+7]);
                		addLane(i, i+15, graphIn[i+15]);
                		addLane(i, i+8, graphIn[i+8]);
                	}
					else if(i==8 || i==9 || i==10 || i==11 || i==12 || i==13 || i==14){
						addLane(i, i-8, graphIn[i-8]);
						addLane(i, i-7, graphIn[i-7]);
						addLane(i, i+15, graphIn[i+15]);
						addLane(i, i+7, graphIn[i+7]);
						addLane(i, i+8, graphIn[i+8]);
					}
                	
                	//Bottom Sides; 5 Adjacent Nodes or 3 Adjacent Nodes
                	else if(i==218 || i==219 || i==220 || i==221 || i==222 || i==223 || i==224){
                		addLane(i, i+7, graphIn[i+7]);
                		addLane(i, i-8, graphIn[i-8]);
                		addLane(i, i-15, graphIn[i-15]);
                		addLane(i, i-7, graphIn[i-7]);
                		addLane(i, i+8, graphIn[i+8]);
                	}
					else if(i==226 || i==227 || i==228 || i==229 || i==230 || i==231){
						addLane(i, i-8, graphIn[i-8]);
                		addLane(i, i-15, graphIn[i-15]);
                		addLane(i, i-7, graphIn[i-7]);
					}
                	
                	//Left Sides; 4 Adjacent Nodes
					else if(i==15 || i==30 || i==45 || i==60 || i==75 || i==90 || i==105 
							|| i==120 || i==135 || i==150 || i==165 || i==180 || i==195 || i==210){
						addLane(i, i-15, graphIn[i-15]);
                		addLane(i, i-7, graphIn[i-7]);
                		addLane(i, i+8, graphIn[i+8]);
                		addLane(i, i+15, graphIn[i+15]);
					}
                	
                	//Right Side; 4 Adjacent Nodes
					else if(i==22 || i==37 || i==52 || i==67 || i==82 || i==97 || i==112 
							|| i==127 || i==142 || i==157 || i==172 || i==187 || i==202 || i==217){
						addLane(i, i-15, graphIn[i-15]);
                		addLane(i, i-8, graphIn[i-8]);
                		addLane(i, i+7, graphIn[i+7]);
                		addLane(i, i+15, graphIn[i+15]);
					}
                	
                	//Regular Hex; 6 Adjacent Nodes
					else{
						addLane(i, i-15, graphIn[i-15]);
                		addLane(i, i-8, graphIn[i-8]);
                		addLane(i, i+7, graphIn[i+7]);
                		addLane(i, i+15, graphIn[i+15]);
                		addLane(i, i+8, graphIn[i+8]);
                		addLane(i, i-7, graphIn[i-7]);
					}
                }
                
                //Grab Shortest Distance from "226" to "8"; Nodes offset by 1
                Graph graph = new Graph(nodes, edges);
                DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
                
                //Starting Point
                dijkstra.execute(nodes.get(225));
                
                //Ending Point
                LinkedList<Vertex> path = dijkstra.getPath(nodes.get(7));
                
                //Print the shortest path; Node path and then Minimum Cost
                for (Vertex vertex : path) {
                        System.out.println(vertex);
                        distance += graphIn[(Integer.parseInt(vertex.getId()))-1];
                }
                System.out.println("MINIMAL-COST PATH COSTS: " + distance);
                
                

        }

        //Add Edges to the Node, one at a time
        private static void addLane(int sourceLocNo, int destLocNo,int weight) {
                Edge lane = new Edge(nodes.get(sourceLocNo), nodes.get(destLocNo), weight);
                edges.add(lane);
        }

        //Main Function
        public static void main(String args[]){
        	Excute();
        }
}