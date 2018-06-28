package asiantech.internship.summer.recycler_view.model;

import java.util.Random;

import asiantech.internship.summer.R;


public class AuthorCreator {
    private static final String[] NAMES = new String[]{
            "Le Van A", "Tran Van Rua", "Ly Thi Nam", "GG WP", "BA BA"
    };
    private static final int[] PROFILE_IMAGE_IDS = new int[]{
            R.drawable.img_face1, R.drawable.img_face2
    };

    public static Author createAuthor() {
        Random random = new Random();
        String name = NAMES[random.nextInt(NAMES.length)];
        int profileImageId = PROFILE_IMAGE_IDS[random.nextInt(PROFILE_IMAGE_IDS.length)];
        Author author = new Author(name, profileImageId);
        return author;
    }
}
