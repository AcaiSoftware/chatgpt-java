package gg.acai.chatgpt.entities;

import java.util.List;

/**
 * © Acai Software - All Rights Reserved
 *
 * @author Clouke
 * @since 09.12.2022 19:33
 */
public record UserEntity(String id, String name, String email, String image, String picture, List<String> groups, List<String> features) {}
