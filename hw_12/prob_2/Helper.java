/**
 * Helper.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */
public class Helper {
    /**
     * helper function to convert the byte array of data to string
     * @param data
     * @return
     */
    public  String convertToString(byte[] data){
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (data[index] != 0){
            stringBuilder.append((char)data[index]);
            index++;
        }
        return stringBuilder.toString();
    }
}
