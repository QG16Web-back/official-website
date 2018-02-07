package com.qg.officialwebsite.domain;

import java.util.List;

/**
 * @author 小铭
 * Date: 2018/2/7
 * Time: 19:23
 * No struggle, talent how to match the willfulness.
 * Description: 剪影类型实体类
 */
public class SilhouetteType {
    /**
     * 剪影类型ID
     */
    private int silhouetteTypeId;
    /**
     * 剪影类型名称
     */
    private String silhouetteTypeName;

    /**
     * 该剪影类型的剪影集合
     */
    private List<Silhouette> silhouettes;

    public int getSilhouetteTypeId() {
        return silhouetteTypeId;
    }

    public void setSilhouetteTypeId(int silhouetteTypeId) {
        this.silhouetteTypeId = silhouetteTypeId;
    }

    public String getSilhouetteTypeName() {
        return silhouetteTypeName;
    }

    public void setSilhouetteTypeName(String silhouetteTypeName) {
        this.silhouetteTypeName = silhouetteTypeName;
    }

    public List<Silhouette> getSilhouettes() {
        return silhouettes;
    }

    public void setSilhouettes(List<Silhouette> silhouettes) {
        this.silhouettes = silhouettes;
    }

    @Override
    public String toString() {
        return "SilhouetteType{" +
                "silhouetteTypeId=" + silhouetteTypeId +
                ", silhouetteTypeName='" + silhouetteTypeName + '\'' +
                ", silhouettes=" + silhouettes +
                '}';
    }
}
