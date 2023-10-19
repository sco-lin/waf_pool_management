package com.maoxian.service.impl;

import com.maoxian.exceprion.BusinessExp;
import com.maoxian.mapper.LogMapper;
import com.maoxian.mapper.WafMapper;
import com.maoxian.pojo.Log;
import com.maoxian.pojo.Waf;
import com.maoxian.service.LogService;
import com.maoxian.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private WafMapper wafMapper;

    @Override
    public List<LogVo> findLogChain(Integer id) {
        Log log = logMapper.selectLog(id);
        if (log == null) {
            throw new BusinessExp("请求不存在");
        } else if (log.getParentId() != 0) {
            throw new BusinessExp("此请求序列不存在");
        }

        ArrayList<LogVo> logVos = new ArrayList<>();
        do {
            Waf waf = wafMapper.queryWafById(log.getWafId());
            if (waf == null) {
                throw new BusinessExp("waf不存在");
            }
            LogVo logVo = new LogVo(log.getId(), log.getUuid(),
                    log.getMethod(), log.getSourceIp(), log.getPath(), log.getTime(), waf);
            logVos.add(logVo);
            log = logMapper.selectLogByParentId(log.getId());
        } while (log != null);

        return logVos;
    }

    @Override
    public List<String> findUUIDList() {
        return logMapper.findUUIDList();
    }

    @Override
    public List<List<LogVo>> findLogChainByUUID(String uuid) {
        List<Integer> ids = logMapper.selectLogIdByUUID(uuid);
        if (ids.isEmpty()) {
            throw new BusinessExp("uuid不存在");
        }
        List<List<LogVo>> list = new ArrayList<>();
        for (Integer id : ids) {
            List<LogVo> logChain = findLogChain(id);
            list.add(logChain);
        }
        return list;
    }
}
