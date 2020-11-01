package tests.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SuiteDetails {
    @Expose
    int id;
    @Expose
    String title;
    @Expose
    String description;
    @Expose
    String precondition;
    @Expose
    @SerializedName("parent_id")
    int parentId;
}
