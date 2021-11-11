//package com.khanhdv.spring.jwt.dto;
//
//import com.khanhdv.spring.jwt.models.User;
//import com.khanhdv.spring.jwt.payload.request.UserUpdateRequest;
//import com.khanhdv.spring.jwt.payload.response.UserResponse;
//import org.mapstruct.Mapper;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.factory.Mappers;
//
//import java.util.List;
//
//@Mapper
//public interface UserMapper {
//
//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
//
//    User toUser(UserUpdateRequest request, @MappingTarget User user);
//
//    List<UserResponse> toListUserResponse(List<User> users);
//
//    UserResponse toUserResponse(User user);
//
//}
