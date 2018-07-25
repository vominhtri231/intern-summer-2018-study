package asiantech.internship.summer.retrofitandgson.model;

public class Image {


    /**
     * image_id : cf7d9a2e9374fbaaa7c2a26e5a789d0e
     * permalink_url : https://api.gyazo.com/cf7d9a2e9374fbaaa7c2a26e5a789d0e
     * url : https://i.gyazo.com/cf7d9a2e9374fbaaa7c2a26e5a789d0e.jpg
     * type : jpg
     * thumb_url : https://thumb.gyazo.com/thumb/200/eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpbWciOiJfYTM5ZjBjNjJhODU2NWJjNjY2NDcxY2VmYTU2ZjljN2IifQ.zloTo55N52eXaGZVApYUguIC_y5hiF8eDEcYKysIKj4-jpg.jpg
     * created_at : 2018-07-21T15:43:40+0000
     */

    private String image_id;
    private String permalink_url;
    private String url;
    private String type;
    private String thumb_url;
    private String created_at;

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
