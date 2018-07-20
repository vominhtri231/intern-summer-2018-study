package asiantech.internship.summer.restful.model;

import java.io.Serializable;

public class Photo implements Serializable {
    private final String url;

    public Photo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
