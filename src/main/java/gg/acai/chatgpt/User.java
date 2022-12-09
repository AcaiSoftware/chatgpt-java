package gg.acai.chatgpt;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 19:33
 */
public class User {

    private final String id;
    private final String name;
    private final String email;
    private final String image;
    private final String picture;
    private final List<String> groups;
    private final List<String> features;

    public User(String id, String name, String email, String image, String picture, List<String> groups, List<String> features) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.image = image;
        this.picture = picture;
        this.groups = groups;
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public String getPicture() {
        return picture;
    }

    public List<String> getGroups() {
        return groups;
    }

    public List<String> getFeatures() {
        return features;
    }


}
