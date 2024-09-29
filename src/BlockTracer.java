import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class BlockTracer {

    public static int MAX_VALUES =10;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            try {
                System.out.println("Enter C program filename: ");
                FileInputStream fis = new FileInputStream(sc.nextLine());
                InputStreamReader inStream = new InputStreamReader(fis);
                BufferedReader stdin = new BufferedReader(inStream);
                parse(stdin);
                break;
            }
            catch(Exception e){
                System.out.println("File not found.");
            }
        }
        sc.close();
    }

    private static void parse(BufferedReader stdin) throws IOException {
        Stack<Block> blockStack = new Stack<>();
        String line;

        while((line = stdin.readLine()) != null){
            line = line.trim();

            if(line.contains("{"))
                blockStack.push(new Block());
            else if (line.contains("}"))
                blockStack.pop();
            else if (line.startsWith("int ")) {
                addVars(line, blockStack.peek());
            }
            else if (line.contains("/*$print LOCAL*/"))
                System.out.println(blockStack.peek());
            else if (line.contains("/*$print ")) {
                printVariable(line, blockStack);
            }

        }
    }

    private static void addVars(String line, Block block){
        line = line.replace("int ", "").replace(";", "");
        String[] vars = line.split(",");
        for (String var: vars){
            String[] parts = var.split("=");
            int value = (parts.length >1) ? Integer.parseInt(parts[1].trim()) : 0;
            block.addValue(new INTitialValue(parts[0].trim(), value));
        }
    }

    private static void printVariable(String line, Stack<Block> blockStack){
        String name = line.split(" ")[1].split("\\*")[0];
        Stack<Block> temp = new Stack<>();
        while(!blockStack.isEmpty()){
            temp.push(blockStack.pop());
            int[] res = temp.peek().findValue(name);
            if(res[0] == 1) {
                StringBuilder table = new StringBuilder();
                String format = "%-13s %-13s \n";
                table.append(String.format(format, "Variable Name", "Initial Value"));
                table.append(String.format(format, name, res[1]));
                System.out.println(table);
                break;
            }
        }

        while(!temp.isEmpty()){
            blockStack.push(temp.pop());
        }
    }


}
