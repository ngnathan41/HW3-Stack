import java.io.*;
import java.util.Scanner;
import java.util.Stack;
/**Implements a CLI to parse a file from user input and process in-line comment commands.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class BlockTracer {

    public final static int MAX_VALUES =10;

    /**Asks user for C filename to process.
     *
     * @param args args passed in.
     */
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

    /**Helper method that reads a file line by line and splits each line by tokens, then parse each part.
     *
     * @param stdin File to parse lines from.
     * @throws IOException Indicates that an I/O exception has occurred.
     */
    private static void parse(BufferedReader stdin) throws IOException {
        Stack<Block> blockStack = new Stack<>();
        String line;
        String[] split;
        while((line = stdin.readLine()) != null) {
            line = line.trim();
            split = line.split(";|/\\*|\\*/");
            for(String str: split){
                read(str, blockStack);
            }
        }
    }

    /**Helper method that reads a line for specific tokens then processes them accordingly.
     *
     * @param line Line to parse.
     * @param blockStack blockStack to push/pop/peek blocks from.
     */
    private static void read(String line, Stack<Block> blockStack){
            line = line.trim();
            if(line.isEmpty())
                return;

        /**As a result of the way the lines are parsed, '{' and '}' will always come first
         * and therefore can be executed first. Comments do not have brackets so it
         * does not matter.
         */
        if(line.contains("{") && line.contains("}")){
                if(line.indexOf("{") < line.indexOf("}")){
                    blockStack.push(new Block());
                    read(line.substring(line.indexOf("{")+1), blockStack);
                }
                else{
                    blockStack.pop();
                    read(line.substring(line.indexOf("}")+1), blockStack);
                }
            }
            else if(line.contains("{")) {
                blockStack.push(new Block());
                read(line.substring(line.indexOf("{")+1), blockStack);
            }
            else if (line.contains("}")) {
                blockStack.pop();
                read(line.substring(line.indexOf("}")+1), blockStack);
            }
            else if (line.startsWith("int ")) {
                addVars(line, blockStack.peek());
            }
            else if (line.contains("$print LOCAL"))
                System.out.println(blockStack.peek());
            else if (line.contains("$print ")) {
                printVariable(line, blockStack);
        }
    }

    /**Helper method that parses a line and adds each variable to the current block.
     *
     * @param line Line to parse variables from.
     * @param block Current block.
     */
    private static void addVars(String line, Block block){
        /**
         * Splits by comma and finds associated value otherwise defaults to 0.
         */
        line = line.replace("int ", "");//.replace(";", "");
        String[] vars = line.split(",");
        for (String var: vars){
            String[] parts = var.split("=");
            int value = (parts.length >1) ? Integer.parseInt(parts[1].trim()) : 0;
            block.addValue(new INTitialValue(parts[0].trim(), value));
        }
    }

    /**Helper method for print variable command that finds the name of the variable and tries to find the variable in the blockStack.
     *
     * @param line Line to find name of variable to read.
     * @param blockStack blockStack that holds all initial variables.
     */
    private static void printVariable(String line, Stack<Block> blockStack){
        boolean found = false;
        String name = line.split(" ")[1];
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
                found = true;
                break;
            }
        }

        while(!temp.isEmpty()){
            blockStack.push(temp.pop());
        }
        if(!found){
            System.out.println("Variable not found: " + name);
        }
    }


}
