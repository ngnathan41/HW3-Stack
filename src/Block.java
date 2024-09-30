public class Block {
    INTitialValue[] values;
    int numValues;

    public Block() {
        values = new INTitialValue[BlockTracer.MAX_VALUES];
        numValues = 0;
    }

    public void addValue(INTitialValue value){
        values[numValues] = value;
        numValues++;
    }

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

    public int[] findValue(String name){
        for (int i = 0; i < numValues; i++) {
            if(values[i].getName().equals(name))
                return new int[]{1, values[i].getValue()};
        }
        return new int[]{0};
    }


}
