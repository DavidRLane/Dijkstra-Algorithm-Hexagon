import java.util.List;

//Graph Type for the general manipulation of the hexagon grid
public class Graph {
        private final List<Vertex> vertexes;
        private final List<Edge> edges;

        public Graph(List<Vertex> vertexes, List<Edge> edges) {
                this.vertexes = vertexes;
                this.edges = edges;
        }

        public List<Vertex> getVertexes() {
                return vertexes;
        }

        public List<Edge> getEdges() {
                return edges;
        }
}