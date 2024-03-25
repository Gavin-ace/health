//package com.bjpowernode.service;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.bjpowernode.pojo.Permission;
//import com.bjpowernode.pojo.Role;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//public class SpringSecurityUserService implements UserDetailsService {
//
//    @Reference
//    UserService userService;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user=userService.findUserByUserName(username);
//        if(user==null)
//            return null;
//        List<GrantedAuthority> list=new ArrayList<>();
//        Set<Role> roles=user.getRoles();
//        for(Role role:roles){
//            list.add(new SimpleGrantedAuthority(role.getKeyword()));
//            Set<Permission> permissions = role.getPermissions();
//            for(Permission permission:permissions){
//                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
//            }
//        }
//        UserDetails userDetails = new
//                org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
//        return userDetails;
//    }
//}
