package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.Silhouette;
import com.qg.officialwebsite.domain.SilhouetteMapper;
import com.qg.officialwebsite.domain.SilhouetteType;
import com.qg.officialwebsite.domain.SilhouetteTypeMapper;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.SilhouetteException;
import com.qg.officialwebsite.service.SilhouetteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 20:02
 * No struggle, talent how to match the willfulness.
 * Description: 剪影管理逻辑层实现类
 */
@Service
public class SilhouetteServiceImpl implements SilhouetteService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SilhouetteServiceImpl.class);
    private final SilhouetteMapper silhouetteMapper;
    private final SilhouetteTypeMapper silhouetteTypeMapper;

    @Autowired
    public SilhouetteServiceImpl(SilhouetteMapper silhouetteMapper, SilhouetteTypeMapper silhouetteTypeMapper) {
        this.silhouetteMapper = silhouetteMapper;
        this.silhouetteTypeMapper = silhouetteTypeMapper;
    }

    @Override
    public Result addSilhouette(Silhouette silhouette) {
        silhouetteMapper.addSilhouette(silhouette);
        return new Result(StateEnum.OK);
    }

    @Override
    public Result deleteSilhouette(int silhouetteId) {
        if (null == silhouetteMapper.selectSilhouetteById(silhouetteId)) {
            LOGGER.warn("剪影不存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_NOT_EXIST);
        }
        silhouetteMapper.deleteSilhouette(silhouetteId);
        return new Result(StateEnum.OK);
    }

    @Override
    public Result updateSilhouette(Silhouette silhouette) {
        silhouetteMapper.updateSilhouette(silhouette);
        return new Result(StateEnum.OK);
    }

    @Override
    public Result showSilhouetteForBackstage(String silhouetteTypeName) {
        SilhouetteType silhouetteType = silhouetteTypeMapper.selectTypeByName(silhouetteTypeName);
        if (null == silhouetteType) {
            LOGGER.warn("剪影类别不存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_TYPE_NOT_EXIST);
        }
        List<Silhouette> silhouettes = silhouetteMapper.selectSilhouetteByTypeId(silhouetteType.getSilhouetteTypeId());

        Map<String, List<Map<String, Object>>> silhouetteMap = new HashMap<>(1);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Silhouette silhouette : silhouettes) {
            Map<String, Object> map = new HashMap<>(4);
            map.put("silhouetteId", silhouette.getSilhouetteId());
            map.put("silhouetteDescription", silhouette.getSilhouetteDescription());
            map.put("silhouettePath", silhouette.getSilhouettePath());
            map.put("silhouetteTime", silhouette.getSilhouetteTime());
            list.add(map);
        }
        silhouetteMap.put("silhouetteTypes", list);
        return new Result<>(StateEnum.OK, silhouetteMap);
    }

    @Override
    public Result showSilhouette() {
        Map<String, List<Map<String, String>>> silhouetteMap = new HashMap<>();

        List<SilhouetteType> silhouetteTypes = silhouetteTypeMapper.showSilhouetteType();
        for (SilhouetteType silhouetteType : silhouetteTypes) {
            List<Map<String, String>> list = new ArrayList<>();
            List<Silhouette> silhouettes = silhouetteMapper.selectSilhouetteByTypeId(silhouetteType.getSilhouetteTypeId());
            for (Silhouette silhouette : silhouettes) {
                Map<String, String> map = new HashMap<>(3);
                map.put("silhouetteDescription", silhouette.getSilhouetteDescription());
                map.put("silhouettePath", silhouette.getSilhouettePath());
                map.put("silhouetteTime", silhouette.getSilhouetteTime());
                list.add(map);
            }
            silhouetteMap.put(silhouetteType.getSilhouetteTypeName(), list);
        }
        return new Result<>(StateEnum.OK, silhouetteMap);
    }
}
