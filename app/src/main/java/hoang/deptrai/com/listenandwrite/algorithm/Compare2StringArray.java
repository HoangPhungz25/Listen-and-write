package hoang.deptrai.com.listenandwrite.algorithm;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Compare2StringArray {
    int similar_color = Color.rgb(5,127,50);
    int not_similar_color = Color.RED;

    public Compare2StringArray(){

    }
    public SpannableString compare2StringArray(String string_answer, String string_result){
        String[] stringArray_answer = split_string_to_stringArray(string_answer);
        String[] stringArray_result = split_string_to_stringArray(string_result);
        ArrayList<SubStrings_Indexs_Model> list_2D_subStrings = new ArrayList<SubStrings_Indexs_Model>();

//        int[] return_result = new int[stringArray_result.length];
//        int[] temp_result = new int[stringArray_result.length];
//        int number_of_similar_string = 0;
//        int max_number_of_similar_string = 0;

        for(int i=0; i<stringArray_answer.length; i++){
            for (int j=0; j<stringArray_result.length; j++)
                if(comapre2String(stringArray_answer[i],stringArray_result[j])){
                    boolean is_belong_to_a_existed_substring = false;
                    for(int k=0; k<list_2D_subStrings.size(); k++){
                        /*if j > the first index of any substring,
                         that means stringArray_result[j] belongs to that substring */
                        if(j>list_2D_subStrings.get(k).getLast_index_of_similar_substring()){
                            is_belong_to_a_existed_substring = true;
                            list_2D_subStrings.get(k).getIndex_array_of_similar_substrings()[j]=1;
                            list_2D_subStrings.get(k).setNumber_of_similar_substrings(
                                    list_2D_subStrings.get(k).getNumber_of_similar_substrings()+1);
                            list_2D_subStrings.get(k).setLast_index_of_similar_substring(j);
                        }
                    }
                    /*if is_belong_to_a_existed_substring is  false,
                      then create new substring which has first index is j*/
                    if(!is_belong_to_a_existed_substring){
                        list_2D_subStrings.add(new SubStrings_Indexs_Model(stringArray_result.length,j));
                        list_2D_subStrings.get(list_2D_subStrings.size()-1).getIndex_array_of_similar_substrings()[j]=1;
                    }
                }
        }
        int max_length_substring_in_list2Dsubstring = 0;
        int index_of_max_length_substring_in_list2Dsubstring = -1;

        for(int i=0; i<list_2D_subStrings.size(); i++){
            if(list_2D_subStrings.get(i).getNumber_of_similar_substrings()>max_length_substring_in_list2Dsubstring){
                max_length_substring_in_list2Dsubstring= list_2D_subStrings.get(i).getNumber_of_similar_substrings();
                index_of_max_length_substring_in_list2Dsubstring = i;
            }
        }

          //get spannable String
            SpannableString ss_result = new SpannableString(string_result);
            int[] result_index_array = new int[stringArray_result.length];
            //if there is at least a similar substring, get a result_index_array that represent max length similar substrings
            // else get a result_index_array that represent an string that has no similar substring
            if(index_of_max_length_substring_in_list2Dsubstring!=-1){
                result_index_array = list_2D_subStrings.
                        get(index_of_max_length_substring_in_list2Dsubstring).
                        getIndex_array_of_similar_substrings();
            }else{
                Arrays.fill(result_index_array,0);
            }

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


    private String[] split_string_to_stringArray(String string){
        String standardized_string = standardize_string(string);
        String[] result; result =  standardized_string.split("\\s");
        return standardized_string.split("\\s");
    }

    private String standardize_string(String inputString){
        String output = inputString.trim().toLowerCase();
        output = output.replaceAll("\\p{Punct}","");
        output = output.replaceAll("\\s+"," ");
        return output;
    }
    private boolean comapre2String(String string_src, String string_des){
        return ((string_src).equals(string_des));
    }
}
