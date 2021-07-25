package softuni.exam.instagraphlite.models.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInputDto {

    @Expose
    @NotBlank
    @Size(min = 2, max = 18)
    private String username;

    @Expose
    @NotNull
    @Size(min = 4)
    private String password;

    @Expose
    @NotNull
    @SerializedName("profilePicture")
    private String profilePicturePath;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}
