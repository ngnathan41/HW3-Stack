/**Helper method for BlockTracer, is implemented in a stack, holds an array of INTitialValues. Helps return a string representation and find a value in a Block.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class Block {
    INTitialValue[] values;
    int numValues;

    /**Instantiates a new instance of Block with no values and an array with length of MAX_VALUES.
     *
     */
    public Block() {
        values = new INTitialValue[BlockTracer.MAX_VALUES];
        numValues = 0;
    }

    /**Adds a INTitialValue to the Block and increments numValues.
     *
     * @param value INTitialValue to add.
     */
    public void addValue(INTitialValue value){
        values[numValues] = value;
        numValues++;
    }

    /**Returns a string representation of a Block.
     *
     * @return Returns a string of INTitialValues in tabular format.
     */
    public String toString(){
        if(numValues == 0){
            return "No local variables to print.";
        }
        StringBuilder table = new StringBuilder();
        String format = "%-13s %-13s \n";
        table.append(String.format(format, "Variable Name", "Initial Value"));
        for (int i = 0; i < numValues; i++) {
            table.append(String.format(format, values[i].getName(), values[i].getValue()));
        }

        return table.toString();
    }

    /**Finds a value in a block.
     *
     * @param name Name of INTitialValue to search for.
     * @return An array with true/false in index 0, and the value in index 1 if found.
     */
    public int[] findValue(String name){
        for (int i = 0; i < numValues; i++) {
            if(values[i].getName().equals(name))
                return new int[]{1, values[i].getValue()};
        }
        return new int[]{0};
    }


}
