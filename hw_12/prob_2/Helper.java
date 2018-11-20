public class Helper {
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
