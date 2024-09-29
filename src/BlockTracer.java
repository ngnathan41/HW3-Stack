import java.util.Scanner;
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
            catch(FileNotFoundException e){
                System.out.println("File not found.");
            }
        }
        sc.close();
    }

    private void parse(BufferedReader stdin){
        Stack<Block> blockStack = new Stack<Block>();
        String line;

        while((line = stdin.readLine()) != null){
            line = line.trim();

            if(line.contains("{"))
                blockStack.push(new Block());
            else if (line.contains("}"))
                blockStack.pop();
            else if (line.)
        }
    }

}
