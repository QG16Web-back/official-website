package com.qg.officialwebsite.domain;

/**
 * @author 小铭
 * Date: 2018/2/4
 * Time: 22:28
 * No struggle, talent how to match the willfulness.
 * Description: 剪影实体类
 */
public class Silhouette {

    /**
     * 剪影ID
     */
    private Integer silhouetteId;

    /**
     * 剪影描述
     */
    private String silhouetteDescription;

    /**
     * 剪影类型
     */
    private String silhouetteType;

    /**
     * 剪影路径
     */
    private String silhouettePath;

    /**
     * 剪影时间
     */
    private String silhouetteTime;

    public Silhouette() {
    }

    public Integer getSilhouetteId() {
        return silhouetteId;
    }

    public void setSilhouetteId(Integer silhouetteId) {
        this.silhouetteId = silhouetteId;
    }

    public String getSilhouetteDescription() {
        return silhouetteDescription;
    }

    public void setSilhouetteDescription(String silhouetteDescription) {
        this.silhouetteDescription = silhouetteDescription;
    }

    public String getSilhouetteType() {
        return silhouetteType;
    }

    public void setSilhouetteType(String silhouetteType) {
        this.silhouetteType = silhouetteType;
    }

    public String getSilhouettePath() {
        return silhouettePath;
    }

    public void setSilhouettePath(String silhouettePath) {
        this.silhouettePath = silhouettePath;
    }

    public String getSilhouetteTime() {
        return silhouetteTime;
    }

    public void setSilhouetteTime(String silhouetteTime) {
        this.silhouetteTime = silhouetteTime;
    }

    @Override
    public String toString() {
        return "Silhouette{" +
                "silhouetteId=" + silhouetteId +
                ", silhouetteDescription='" + silhouetteDescription + '\'' +
                ", silhouetteType='" + silhouetteType + '\'' +
                ", silhouettePath='" + silhouettePath + '\'' +
                ", silhouetteTime='" + silhouetteTime + '\'' +
                '}';
    }
}
