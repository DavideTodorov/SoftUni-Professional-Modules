package softuni.exam.instagraphlite.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostInputRootDto {

    @XmlElement(name = "post")
    List<PostInputDto> posts;

    public List<PostInputDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostInputDto> posts) {
        this.posts = posts;
    }
}
