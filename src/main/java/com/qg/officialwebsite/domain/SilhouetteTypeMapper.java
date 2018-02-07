package com.qg.officialwebsite.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 20:04
 * No struggle, talent how to match the willfulness.
 * Description: 剪影类别管理操作Mapper
 */
@Mapper
@Component
public interface SilhouetteTypeMapper {

    /**
     * 添加剪影类别
     *
     * @param silhouetteType 剪影；类别实体类
     */
    void addSilhouetteType(@Param("silhouetteType") SilhouetteType silhouetteType);

    /**
     * 根据剪影类别名称查找
     *
     * @param silhouetteTypeName 剪影类别名称
     * @return 剪影类别实体类
     */
    SilhouetteType selectTypeByName(@Param("silhouetteTypeName") String silhouetteTypeName);

    /**
     * 根据剪影类别ID查找
     *
     * @param silhouetteTypeId 剪影类别ID
     * @return 剪影实体类
     */
    SilhouetteType selectTypeById(@Param("silhouetteTypeId") int silhouetteTypeId);

    /**
     * 根据ID删除剪影类别
     *
     * @param silhouetteTypeId 剪影类别ID
     */
    void deleteSilhouetteTypeById(@Param("silhouetteTypeId") int silhouetteTypeId);

    /**
     * 更新剪影类别
     *
     * @param silhouetteType 剪影类别
     */
    void updateSilhouetteType(@Param("silhouetteType") SilhouetteType silhouetteType);

    /**
     * 显示剪影类别
     *
     * @return 剪影类别集合
     */
    List<SilhouetteType> showSilhouetteType();
}
