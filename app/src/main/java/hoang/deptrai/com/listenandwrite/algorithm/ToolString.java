package hoang.deptrai.com.listenandwrite.algorithm;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class ToolString {
    int similar_color = Color.rgb(5,127,50);
    int not_similar_color = Color.RED;
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
        String standardized_string = standardize_string(string);//this should be replace by s single code, not a whole function, it costs a lot
        String[] splited_string_array = standardized_string.split("\\s");
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
    public SpannableString colorize(String string_result, int[] result_index_array){
        //get spannable String
        SpannableString ss_result = new SpannableString(string_result);
        String[] stringArray_result = split_string_to_string_array(string_result);
        int index_now = 0;

        for(int i=0; i<result_index_array.length; i++){
            if(result_index_array[i]==1){
                ss_result.setSpan(new ForegroundColorSpan(similar_color),
                        index_now,(index_now+stringArray_result[i].length()),
                        Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            }else{
                ss_result.setSpan(new ForegroundColorSpan(not_similar_color),
                        index_now,(index_now+stringArray_result[i].length()),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            index_now += stringArray_result[i].length()+1;
        }

        return ss_result;
    }

}
