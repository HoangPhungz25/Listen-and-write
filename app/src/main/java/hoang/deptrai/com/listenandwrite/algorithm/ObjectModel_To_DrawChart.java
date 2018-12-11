package hoang.deptrai.com.listenandwrite.algorithm;

import android.text.SpannableString;

import java.io.Serializable;

public class ObjectModel_To_DrawChart implements Serializable{
    private int[] result_index_array;
    private String subLyric;
    private int percent_OfSimilarSubString;
    private int number_OfSimilarSubString;

    public ObjectModel_To_DrawChart(String subLyric, int[] result_index_array, int number_OfSimilarSubString, int percent_OfSimilarSubString) {
        this.subLyric = subLyric;
        this.result_index_array = result_index_array;
        this.number_OfSimilarSubString = number_OfSimilarSubString;
        this.percent_OfSimilarSubString = percent_OfSimilarSubString;
    }

    public int getNumber_OfSimilarSubString() {
        return number_OfSimilarSubString;
    }

    public void setNumber_OfSimilarSubString(int number_OfSimilarSubString) {
        this.number_OfSimilarSubString = number_OfSimilarSubString;
    }

    public String getSubLyric() {
        return subLyric;
    }

    public void setSubLyric(String subLyric) {
        this.subLyric = subLyric;
    }

    public int[] getResult_index_array() {
        return result_index_array;
    }

    public void setResult_index_array(int[] result_index_array) {
        this.result_index_array = result_index_array;
    }

    public int getPercent_OfSimilarSubString() {
        return percent_OfSimilarSubString;
    }

    public void setPercent_OfSimilarSubString(int percent_OfSimilarSubString) {
        this.percent_OfSimilarSubString = percent_OfSimilarSubString;
    }
}
