package com.example.eshikshauser.Constants;

public class EntityConstants {
    public static final String updateUser = "update user_user set name=(case when :name is NULL THEN name ELSE :name end),email=(case when :email is NULL THEN email ELSE :email end),gender=(case when :gender is NULL THEN gender ELSE :gender end),password=(case when :password is NULL THEN password ELSE :password end)";
}