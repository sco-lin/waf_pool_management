package com.maoxian.service;

import com.maoxian.vo.LogVo;

import java.util.List;

public interface LogService {

    List<LogVo> findLogChain(Integer id);

    List<String> findUUIDList();

    List<List<LogVo>> findLogChainByUUID(String uuid);
}
