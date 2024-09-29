public class Block {
    INTitialValue[] values;
    int numValues;

    public Block() {
        values = new INTitialValue[BlockTracer.MAX_VALUES];
        numValues = 0;
    }

    public addValue(INTitialValue value){
        values[numValues] = value;
    }

    public String returnValues(){
        StringBuilder table = new StringBuilder();
        String format = "%-13s %-13s";
        for (int i = 0; i < numValues; i++) {

            table.append(String.format(format, values[i].getName(), values[i].getValue()));
        hdoiwad;
        }
    }

    public
}
