import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main {
		//Elements of the Graph
        private static List<Point> nodes;
        private static List<Edge> edges;

        //Execution Function that sets up the graph with the given inputs
        //Adds all the edges as though the graph were set to be a hexagon
        //Fixed size of 232 elements; offset to be 1 to 233
        public static void Excute() {
                nodes = new ArrayList<Point>();
                edges = new ArrayList<Edge>();
                int distance = 0;
                
                //Add Nodes into the Graph; Initialization
                for (int i = 0; i < 233; i++) {
                        Point location = new Point(Integer.toString(i+1), Integer.toString(i+1));
                        nodes.add(location);
                }

                //Input Reader
                //Array to hold inputs
                int[] inputArr = new int[233];
                try {
             	   String sCurrentLine; 
                    BufferedReader br = new BufferedReader(new FileReader("input.txt"));
                    
                    //Read Inputs until Null
             	   outerloop:
                    while ((sCurrentLine = br.readLine()) != null) {
                 	   for(int i=0; i<=233; i++){
                 		   //Delimiter
         				   String[] parts = sCurrentLine.split(" ");
         				   
         				   //Place Input Into Array
         				   inputArr[i] = Integer.parseInt(parts[1]);
         				   
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
                
                //Hex Generation for Each Node; 
                //Offset by -1 b/c text starts at 1
                for(int i=0; i<233; i++){
                	//Corners; 2 Adjacent Nodes
                	if(i==0){
                		addEdge(i, 8, inputArr[8]);
                		addEdge(i, 15, inputArr[15]);
                	}
					else if(i==7){
						addEdge(i, 14, inputArr[14]);
						addEdge(i, 22, inputArr[22]);
					}
					else if(i==225){
						addEdge(i, 210, inputArr[210]);
						addEdge(i, 218, inputArr[218]);
					}
					else if(i==232){
						addEdge(i, 224, inputArr[224]);
						addEdge(i, 217, inputArr[217]);
					}
                	
                	//Top Sides; 3 Adjacent Nodes or 5 Adjacent Nodes
                	else if(i==1 || i==2 || i==3 || i==4 || i==5 || i==6){
                		addEdge(i, i+7, inputArr[i+7]);
                		addEdge(i, i+15, inputArr[i+15]);
                		addEdge(i, i+8, inputArr[i+8]);
                	}
					else if(i==8 || i==9 || i==10 || i==11 || i==12 || i==13 || i==14){
						addEdge(i, i-8, inputArr[i-8]);
						addEdge(i, i-7, inputArr[i-7]);
						addEdge(i, i+15, inputArr[i+15]);
						addEdge(i, i+7, inputArr[i+7]);
						addEdge(i, i+8, inputArr[i+8]);
					}
                	
                	//Bottom Sides; 5 Adjacent Nodes or 3 Adjacent Nodes
                	else if(i==218 || i==219 || i==220 || i==221 || i==222 || i==223 || i==224){
                		addEdge(i, i+7, inputArr[i+7]);
                		addEdge(i, i-8, inputArr[i-8]);
                		addEdge(i, i-15, inputArr[i-15]);
                		addEdge(i, i-7, inputArr[i-7]);
                		addEdge(i, i+8, inputArr[i+8]);
                	}
					else if(i==226 || i==227 || i==228 || i==229 || i==230 || i==231){
						addEdge(i, i-8, inputArr[i-8]);
                		addEdge(i, i-15, inputArr[i-15]);
                		addEdge(i, i-7, inputArr[i-7]);
					}
                	
                	//Left Sides; 4 Adjacent Nodes
					else if(i==15 || i==30 || i==45 || i==60 || i==75 || i==90 || i==105 
							|| i==120 || i==135 || i==150 || i==165 || i==180 || i==195 || i==210){
						addEdge(i, i-15, inputArr[i-15]);
                		addEdge(i, i-7, inputArr[i-7]);
                		addEdge(i, i+8, inputArr[i+8]);
                		addEdge(i, i+15, inputArr[i+15]);
					}
                	
                	//Right Side; 4 Adjacent Nodes
					else if(i==22 || i==37 || i==52 || i==67 || i==82 || i==97 || i==112 
							|| i==127 || i==142 || i==157 || i==172 || i==187 || i==202 || i==217){
						addEdge(i, i-15, inputArr[i-15]);
                		addEdge(i, i-8, inputArr[i-8]);
                		addEdge(i, i+7, inputArr[i+7]);
                		addEdge(i, i+15, inputArr[i+15]);
					}
                	
                	//Regular Hex; 6 Adjacent Nodes
					else{
						addEdge(i, i-15, inputArr[i-15]);
                		addEdge(i, i-8, inputArr[i-8]);
                		addEdge(i, i+7, inputArr[i+7]);
                		addEdge(i, i+15, inputArr[i+15]);
                		addEdge(i, i+8, inputArr[i+8]);
                		addEdge(i, i-7, inputArr[i-7]);
					}
                }
                
                //Grab Shortest Distance from "226" to "8"; Nodes offset by 1
                Graph graph = new Graph(nodes, edges);
                DijAlgo dijAlgo = new DijAlgo(graph);
                
                //Starting Point
                dijAlgo.execute(nodes.get(225));
                
                //Ending Point
                LinkedList<Point> path = dijAlgo.getPath(nodes.get(7));
                
                //Print the shortest path; Node path and then Minimum Cost
                for (Point point : path) {
                        System.out.println(point);
                        distance += inputArr[(Integer.parseInt(point.getId()))-1];
                }
                System.out.println("MINIMAL-COST PATH COSTS: " + distance);
                
                

        }

        //Add Edges to the Node, one at a time
        private static void addEdge(int source, int dest,int weight) {
                Edge lane = new Edge(nodes.get(source), nodes.get(dest), weight);
                edges.add(lane);
        }

        //Main Function; runs the function above and makes the appropriate calls
        public static void main(String args[]){
        	Excute();
        }
}