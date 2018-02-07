package com.qg.officialwebsite.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 20:03
 * No struggle, talent how to match the willfulness.
 * Description: 剪影管理操作Mapper
 */
@Mapper
@Component
public interface SilhouetteMapper {

    /**
     * 增加剪影
     *
     * @param silhouette 剪影实体类
     */
    void addSilhouette(@Param("silhouette") Silhouette silhouette);

    /**
     * 根据ID查找剪影
     *
     * @param silhouetteId 剪影ID
     * @return 剪影实体类
     */
    Silhouette selectSilhouetteById(@Param("silhouetteId") int silhouetteId);

    /**
     * 删除剪影
     *
     * @param silhouetteId 剪影ID
     */
    void deleteSilhouette(@Param("silhouetteId") int silhouetteId);

    /**
     * 更新剪影
     *
     * @param silhouette 剪影实体类
     */
    void updateSilhouette(@Param("silhouette") Silhouette silhouette);

    /**
     * 根据剪影类别查找剪影
     *
     * @param silhouetteTypeId 剪影类别ID
     * @return 剪影集合
     */
    List<Silhouette> selectSilhouetteByTypeId(@Param("silhouetteTypeId") int silhouetteTypeId);
}
