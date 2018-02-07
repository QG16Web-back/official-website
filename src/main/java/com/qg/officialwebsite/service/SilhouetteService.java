package com.qg.officialwebsite.service;

import com.qg.officialwebsite.domain.Silhouette;
import com.qg.officialwebsite.dto.Result;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 20:01
 * No struggle, talent how to match the willfulness.
 * Description: 剪影管理逻辑层接口
 */
public interface SilhouetteService {
    /**
     * 增加剪影
     *
     * @param silhouette 剪影实体类
     * @return Result结果
     */
    Result addSilhouette(Silhouette silhouette);

    /**
     * 删除剪影
     *
     * @param silhouetteId 剪影ID
     * @return Result结果
     */
    Result deleteSilhouette(int silhouetteId);

    /**
     * 更新剪影
     *
     * @param silhouette 剪影实体类
     * @return Result结果
     */
    Result updateSilhouette(Silhouette silhouette);

    /**
     * 显示剪影（后台）
     *
     * @param silhouetteTypeName 剪影类别
     * @return Result结果
     */
    Result showSilhouetteForBackstage(String silhouetteTypeName);

    /**
     * 显示剪影（前端）
     *
     * @return Result结果
     */
    Result showSilhouette();
}
