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
        ToolString toolString = new ToolString();
        String[] stringArray_answer = toolString.split_string_to_string_array(string_answer);
        String[] stringArray_result = toolString.split_string_to_string_array(string_result);
        ArrayList<SubStrings_Indexs_Model> list_2D_subStrings = new ArrayList<SubStrings_Indexs_Model>();

//        int[] return_result = new int[stringArray_result.length];
//        int[] temp_result = new int[stringArray_result.length];
//        int number_of_similar_string = 0;
//        int max_number_of_similar_string = 0;

        for(int i=0; i<stringArray_answer.length; i++){
            for (int j=0; j<stringArray_result.length; j++)
                if(compare2Word(stringArray_answer[i],stringArray_result[j])){
                    boolean is_belong_to_a_existed_substring = false;
                    boolean is_between_a_existed_substring = false;
                    for(int k=0; k<list_2D_subStrings.size(); k++){
                        /*if j > the first index of any substring,
                         that means stringArray_result[j] belongs to that substring */
                        if(j>list_2D_subStrings.get(k).getFirst_index_of_similar_substring()){
                            if(j<list_2D_subStrings.get(k).getLast_index_of_similar_substring()){
                                Log.d("strComp","n-o"+stringArray_result[j]);
                                is_between_a_existed_substring=true;

                                SubStrings_Indexs_Model subStrings_indexs_model = list_2D_subStrings.get(k);
                                //create a new_substring_index_model that has same start similar word index with the substring_index_model,
                                //and the end similar word index is j
                                SubStrings_Indexs_Model new_subStrings_indexs_model =
                                        new SubStrings_Indexs_Model(stringArray_result.length,
                                                                    subStrings_indexs_model.getFirst_index_of_similar_substring(),
                                                                    j);
                                //copy index_array of the old subStrings_indexs_model to index_array of the new one from index start to j
                                for(int i2=subStrings_indexs_model.getFirst_index_of_similar_substring(); i2<j; i2++){
                                        new_subStrings_indexs_model.getIndex_array_of_similar_substrings()[i2] =
                                                subStrings_indexs_model.getIndex_array_of_similar_substrings()[i2];
                                }
                                new_subStrings_indexs_model.getIndex_array_of_similar_substrings()[j] = 1;
                                new_subStrings_indexs_model.setNumber_of_similar_substrings(subStrings_indexs_model.getNumber_of_similar_substrings()+1);
                                list_2D_subStrings.add(new_subStrings_indexs_model);

                            }
                            else
                            if(j>list_2D_subStrings.get(k).getLast_index_of_similar_substring()){
                                Log.d("strComp","old"+stringArray_result[j]);
                                is_belong_to_a_existed_substring = true;
                                list_2D_subStrings.get(k).getIndex_array_of_similar_substrings()[j]=1;
                                list_2D_subStrings.get(k).setNumber_of_similar_substrings(
                                        list_2D_subStrings.get(k).getNumber_of_similar_substrings()+1);
                                list_2D_subStrings.get(k).setLast_index_of_similar_substring(j);
                            }
                        }
                    }
                    /*if is_belong_to_a_existed_substring is  false,
                      then create new substring which has first index is j*/
                    if(!is_belong_to_a_existed_substring && !is_between_a_existed_substring){
                        Log.d("strComp","new"+stringArray_result[j]);
                        list_2D_subStrings.add(new SubStrings_Indexs_Model(stringArray_result.length,j,j));
                        list_2D_subStrings.get(list_2D_subStrings.size()-1).getIndex_array_of_similar_substrings()[j]=1;
                    }
                    break;
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

    private boolean compare2Word(String string_src, String string_des){
        return ((string_src).equals(string_des));
    }
}
