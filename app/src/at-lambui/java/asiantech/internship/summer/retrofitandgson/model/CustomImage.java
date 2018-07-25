package asiantech.internship.summer.retrofitandgson.model;

public class CustomImage {

    /**
     * image_id : 8980c52421e452ac3355ca3e5cfe7a0c
     * permalink_url : http://gyazo.com/8980c52421e452ac3355ca3e5cfe7a0c
     * thumb_url : https://i.gyazo.com/thumb/180/afaiefnaf.png
     * url : https://i.gyazo.com/8980c52421e452ac3355ca3e5cfe7a0c.png
     * type : png
     */

    private String image_id;
    private String permalink_url;
    private String thumb_url;
    private String url;
    private String type;

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

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
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
}
