package com.example.eshikshauser.Constants;

public class EntityConstants {
    public static final String updateUser = "update user_user set name=(case when :name is NULL THEN name ELSE :name end),email=(case when :email is NULL THEN email ELSE :email end),gender=(case when :gender is NULL THEN gender ELSE :gender end),password=(case when :password is NULL THEN password ELSE :password end) where id=:id";
    public static final String deleteUser = "update user_user set deleted=1 where id=:id";
    public static final String updateEducator = "update user_educator set rating=(case when :rating is NULL THEN rating ELSE :rating end), experience=(case when :experience is NULL THEN experience ELSE :experience end) where id=:id";
    public static final String deleteEducator = "update user_educator set deleted=1 where id=:id";
    public static final String findEducatorByUserEmail = "select ue.id as id,uu.id as uid,name,email,gender,rating,uu.deleted as user_deleted,ue.deleted as educator_deleted from user_user uu inner join user_educator ue on uu.id=ue.user_id where uu.email=:email";

}