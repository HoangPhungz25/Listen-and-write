package hoang.deptrai.com.listenandwrite.algorithm;

import android.text.SpannableString;

import java.io.Serializable;

public class ObjectModel_To_DrawChart implements Serializable{
    private int[] result_index_array;
    private int percent_OfSimilarSubString = 0;

    public ObjectModel_To_DrawChart(int[] result_index_array, int percent_OfSimilarSubString) {
        this.result_index_array = result_index_array;
        this.percent_OfSimilarSubString = percent_OfSimilarSubString;
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
