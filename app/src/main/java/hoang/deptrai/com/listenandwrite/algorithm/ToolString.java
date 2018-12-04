package hoang.deptrai.com.listenandwrite.algorithm;

public class ToolString {
    public ToolString(){

    }
    public String[] split_lyrics(String string){
        return string.split("\\n");
    }
    public String[] split_string_to_string_array(String string){
        String standardized_string = standardize_string(string);
        return standardized_string.split("\\s");
    }
    public int[] split_string_to_int_array(String string){
        String[] splited_string_array = string.split("\\s");
        int[] result_array = new int[splited_string_array.length];
        for (int i=0; i<splited_string_array.length; i++){
            result_array[i] = Integer.parseInt(splited_string_array[i]);
        }
        return result_array;
    }
    private String standardize_string(String inputString){
        String output = inputString.trim().toLowerCase();
        output = output.replaceAll("[']","");
        output = output.replaceAll("\\p{Punct}"," ");
        output = output.replaceAll("\\s+"," ");
        return output;
    }
    public String standardize_lyrics(String inputString){
        String output = inputString.trim().toLowerCase();
        output = output.replaceAll("[']","");
        return output;
    }

}
