package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Project {
    @Expose
    @SerializedName("title")
    String name;
    @Expose
    String code;
    String description;
}
