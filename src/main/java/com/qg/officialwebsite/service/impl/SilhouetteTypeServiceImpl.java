package com.qg.officialwebsite.service.impl;

import com.qg.officialwebsite.domain.SilhouetteType;
import com.qg.officialwebsite.domain.SilhouetteTypeMapper;
import com.qg.officialwebsite.dto.Result;
import com.qg.officialwebsite.enums.StateEnum;
import com.qg.officialwebsite.exception.SilhouetteException;
import com.qg.officialwebsite.service.SilhouetteTypeService;
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
 * Time: 20:00
 * No struggle, talent how to match the willfulness.
 * Description: 剪影类别管理逻辑层实现类
 */
@Service
public class SilhouetteTypeServiceImpl implements SilhouetteTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SilhouetteTypeServiceImpl.class);

    private final SilhouetteTypeMapper silhouetteTypeMapper;

    @Autowired
    public SilhouetteTypeServiceImpl(SilhouetteTypeMapper silhouetteTypeMapper) {
        this.silhouetteTypeMapper = silhouetteTypeMapper;
    }

    /**
     * 增加剪影类别
     *
     * @param silhouetteType 剪影类别实体类
     * @return Result结果
     */
    @Override
    public Result addSilhouetteType(SilhouetteType silhouetteType) {

        if (silhouetteType.getSilhouetteTypeName() == null) {
            LOGGER.warn("参数为空");
            throw new SilhouetteException(StateEnum.PARAM_IS_EMPTY);
        }
        if (silhouetteTypeMapper.selectTypeByName(silhouetteType.getSilhouetteTypeName()) != null) {
            LOGGER.warn("剪影类别已存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_TYPE_HAS_EXISTED);
        }
        silhouetteTypeMapper.addSilhouetteType(silhouetteType);
        return new Result(StateEnum.OK);
    }

    /**
     * 删除剪影类别
     *
     * @param silhouetteTypeId 剪影类别ID
     * @return Result结果
     */
    @Override
    public Result deleteSilhouetteType(int silhouetteTypeId) {
        if (null == silhouetteTypeMapper.selectTypeById(silhouetteTypeId)) {
            LOGGER.warn("剪影类别不存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_TYPE_NOT_EXIST);
        }
        silhouetteTypeMapper.deleteSilhouetteTypeById(silhouetteTypeId);
        return new Result(StateEnum.OK);
    }

    /**
     * 更新剪影
     *
     * @param silhouetteType 剪影类别实体类
     * @return Result
     */
    @Override
    public Result updateSilhouetteType(SilhouetteType silhouetteType) {
        if (silhouetteType.getSilhouetteTypeName() == null) {
            LOGGER.warn("参数为空");
            throw new SilhouetteException(StateEnum.PARAM_IS_EMPTY);
        }
        if (null != silhouetteTypeMapper.selectTypeByName(silhouetteType.getSilhouetteTypeName())) {
            LOGGER.warn("剪影类别已存在");
            throw new SilhouetteException(StateEnum.SILHOUETTE_TYPE_HAS_EXISTED);
        }
        silhouetteTypeMapper.updateSilhouetteType(silhouetteType);
        return new Result(StateEnum.OK);
    }

    @Override
    public Result showSilhouetteType() {
        List<SilhouetteType> silhouetteTypes = silhouetteTypeMapper.showSilhouetteType();
        Map<String, List<Map<String, Object>>> typeMap = new HashMap<>(1);
        List<Map<String, Object>> list = new ArrayList<>();
        for (SilhouetteType silhouetteType : silhouetteTypes) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("silhouetteTypeId", silhouetteType.getSilhouetteTypeId());
            map.put("silhouetteTypeName", silhouetteType.getSilhouetteTypeName());
            list.add(map);
        }
        typeMap.put("silhouetteTypes", list);
        return new Result<>(StateEnum.OK, typeMap);
    }
}
