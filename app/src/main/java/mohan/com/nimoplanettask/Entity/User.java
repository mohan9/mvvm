package mohan.com.nimoplanettask.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;
    @NonNull
    @ColumnInfo(name = "time")
    @SerializedName("time")
    private String time;
    @NonNull
    @ColumnInfo(name = "content")
    @SerializedName("content")
    private String content;
    @NonNull
    @ColumnInfo(name = "image")
    @SerializedName("image")
    private String image;

    public User(@NonNull String name, @NonNull String time, @NonNull String content, @NonNull String image) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.image = image;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    @NonNull
    public String getImage() {
        return image;
    }
}
