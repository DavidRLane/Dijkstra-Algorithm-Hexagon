import java.util.List;

//Graph Type for the general manipulation of the hexagon grid
public class Graph {
        private final List<Point> points;
        private final List<Edge> edges;

        public Graph(List<Point> points, List<Edge> edges) {
                this.points = points;
                this.edges = edges;
        }

        public List<Point> getPoints() {
                return points;
        }

        public List<Edge> getEdges() {
                return edges;
        }
}