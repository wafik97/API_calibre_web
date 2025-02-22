//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.io.File;
//import java.io.IOException;
//import java.util.Iterator;
//
//public class JsonTableViewer {
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            try {
//                // Read JSON file
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode jsonNode = objectMapper.readTree(new File("data.json"));
//
//                // Convert JSON to table
//                JTable table = createTableFromJson(jsonNode);
//
//                // Show GUI
//                JFrame frame = new JFrame("JSON Table Viewer");
//                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                frame.setSize(600, 400);
//                frame.add(new JScrollPane(table), BorderLayout.CENTER);
//                frame.setVisible(true);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    private static JTable createTableFromJson(JsonNode jsonNode) {
//        // If JSON is an array, extract first object for column names
//        JsonNode firstObject = jsonNode.isArray() ? jsonNode.get(0) : jsonNode;
//
//        // Extract column names
//        Iterator<String> fieldNames = firstObject.fieldNames();
//        String[] columns = fieldNamesToArray(fieldNames);
//
//        // Fill table data
//        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
//
//        if (jsonNode.isArray()) {
//            for (JsonNode row : jsonNode) {
//                tableModel.addRow(jsonRowToArray(row, columns));
//            }
//        } else {
//            tableModel.addRow(jsonRowToArray(jsonNode, columns));
//        }
//
//        return new JTable(tableModel);
//    }
//
//    private static String[] fieldNamesToArray(Iterator<String> fieldNames) {
//        return fieldNames.hasNext() ? fieldNames.next().split(",") : new String[]{};
//    }
//
//    private static Object[] jsonRowToArray(JsonNode jsonNode, String[] columns) {
//        Object[] rowData = new Object[columns.length];
//        for (int i = 0; i < columns.length; i++) {
//            rowData[i] = jsonNode.has(columns[i]) ? jsonNode.get(columns[i]).asText() : "";
//        }
//        return rowData;
//    }
//}
