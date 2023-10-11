package com.maoxian.service;

import java.util.List;

public interface PermService {

    List<String> queryPermByUserId(Integer userId);
}
