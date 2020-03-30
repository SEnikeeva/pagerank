import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GraphRepository {

    public List<Node> findAll() {
        Connection connection = new DbConnection().getConnection();
        List<Node> graph = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM graph ");
            while (resultSet.next()) {
                graph.add(Node.builder().source(resultSet.getString("source"))
                        .destination(resultSet.getString("destination")).build());
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return graph;
    }

    public List<String> dests(String source) {
        List<String> dests = new ArrayList<>();
        for (Node node : findAll()) {
            if (node.getSource().equals(source)) {
                dests.add(node.getDestination());
            }
        }
        return dests;
    }

    public double[][] matrix(int len, String[] nodes) {
        List<String> dests;
        double[][] M = new double[len][len];
        for (int i = 0; i < len; i++) {
            dests = dests(nodes[i]);
            int amount = dests.size();
            for (int j = 0; j < len; j++) {
                if (dests.contains(nodes[j])) {
                    M[i][j] = (double) 1 / amount;
                }
            }
        }
        return M;
    }
}
