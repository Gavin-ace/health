package com.bjpowernode.service;

import com.bjpowernode.entity.Result;

import java.util.Map;

public interface OrderService {
    Result submit(Map<String, Object> map) throws Exception;

    Map<String, Object> findById(Integer id);
}
