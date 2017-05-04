//Weights of Nodes are based on their traversal; Edge cost is counted
public class Edge  {
        private final Point source;
        private final Point destination;
        private final int weight;

        public Edge(Point source, Point destination, int weight) {
                this.source = source;
                this.destination = destination;
                this.weight = weight;
        }

        public Point getDestination() {
                return destination;
        }

        public Point getSource() {
                return source;
        }
        public int getWeight() {
                return weight;
        }

        @Override
        public String toString() {
                return source + " " + destination;
        }


}