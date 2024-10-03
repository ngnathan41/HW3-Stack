/**Helper method for Block and BlockTracer; INTitialValue implements a bag adt to store a variables initial value and name.
 * @author Nathan Ng
 *  email: nathan.ng@stonybrook.edu
 *  ID: 116188023
 *  Recitation: 4
 */
public class INTitialValue {
    String name;
    int value;

    /**Initializes an instance of INTitialValue
     *
     * @param name name of the variable
     * @param value integer value of the variable
     */
    public INTitialValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    /**Returns the name of the variable.
     *
     * @return name of the variable.
     */
    public String getName() {
        return name;
    }

    /**Returns the int value of the variable
     *
     * @return integer value of the variable.
     */
    public int getValue() {
        return value;
    }
}
