package asiantech.internship.summer.restful.model;

import java.io.Serializable;

public class Photo implements Serializable {
    String image_id;
    String permalink_url;
    String url;
    String type;
    String thumb_url;
    String created_at;

    public Photo(String image_id, String permalink_url, String url, String type, String thumb_url, String created_at) {
        this.image_id = image_id;
        this.permalink_url = permalink_url;
        this.url = url;
        this.type = type;
        this.thumb_url = thumb_url;
        this.created_at = created_at;
    }

    public Photo() {
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getPermalink_url() {
        return permalink_url;
    }

    public void setPermalink_url(String permalink_url) {
        this.permalink_url = permalink_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
