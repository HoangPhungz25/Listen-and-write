package hoang.deptrai.com.listenandwrite.algorithm;

import java.util.Arrays;

public class SubStrings_Indexs_Model {
    private int[] index_array_of_similar_substrings;
    private int last_index_of_similar_substring;
    private int first_index_of_similar_substring;
    private int number_of_similar_substrings;

    public SubStrings_Indexs_Model(int result_string_array_length, int first_index_of_similar_substring, int last_index_of_similar_substring) {
        this.index_array_of_similar_substrings = new int[result_string_array_length];
        Arrays.fill(index_array_of_similar_substrings,0);
        this.first_index_of_similar_substring = first_index_of_similar_substring;
        this.last_index_of_similar_substring = last_index_of_similar_substring;
        this.number_of_similar_substrings = 1;
    }

    public int getFirst_index_of_similar_substring() {
        return first_index_of_similar_substring;
    }

    public void setFirst_index_of_similar_substring(int first_index_of_similar_substring) {
        this.first_index_of_similar_substring = first_index_of_similar_substring;
    }

    public int[] getIndex_array_of_similar_substrings() {
        return index_array_of_similar_substrings;
    }

    public void setIndex_array_of_similar_substrings(int[] index_of_similar_substrings) {
        this.index_array_of_similar_substrings = index_of_similar_substrings;
    }

    public int getLast_index_of_similar_substring() {
        return last_index_of_similar_substring;
    }

    public void setLast_index_of_similar_substring(int first_index_of_similar_substring) {
        this.last_index_of_similar_substring = first_index_of_similar_substring;
    }

    public int getNumber_of_similar_substrings() {
        return number_of_similar_substrings;
    }

    public void setNumber_of_similar_substrings(int number_of_similar_substrings) {
        this.number_of_similar_substrings = number_of_similar_substrings;
    }
}
