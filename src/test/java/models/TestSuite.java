package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestSuite<T> {
    int id;
    @SerializedName("title")
    @Expose
    String suiteName;
    @Expose
    @SerializedName("parent_id")
    T parent;
    @Expose
    String description;
    @Expose
    String preconditions;
    }
