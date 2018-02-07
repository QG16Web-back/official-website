package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.SilhouetteType;
import com.qg.officialwebsite.dto.Result;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 19:59
 * No struggle, talent how to match the willfulness.
 * Description: 剪影类别管理逻辑层接口
 */
public interface SilhouetteTypeService {
    /**
     * 增加剪影类别
     *
     * @param silhouetteType 剪影类别实体类
     * @return Result结果
     */
    Result addSilhouetteType(SilhouetteType silhouetteType);

    /**
     * 删除剪影类别
     *
     * @param silhouetteTypeId 剪影类别ID
     * @return Result结果
     */
    Result deleteSilhouetteType(int silhouetteTypeId);

    /**
     * 更新剪影类别
     *
     * @param silhouetteType 剪影类别实体类
     * @return Result结果
     */
    Result updateSilhouetteType(SilhouetteType silhouetteType);

    /**
     * 显示所有剪影类别
     *
     * @return Result结果
     */
    Result showSilhouetteType();
}
