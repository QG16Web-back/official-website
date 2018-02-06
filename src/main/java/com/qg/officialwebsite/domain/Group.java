package com.qg.officialwebsite.domain;

import java.util.List;

/**
 * @author 小铭
 * Date: 2018/2/4
 * Time: 22:10
 * No struggle, talent how to match the willfulness.
 * Description: 组别实体类
 */
public class Group {

    /**
     * 组别ID
     */
    private Integer groupId;

    /**
     * 组别名称
     */
    private String groupName;

    /**
     * 组别描述
     */
    private String groupDescription;

    /**
     * 组别成员
     */
    private List<Member> members;

    public Group() {
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupDescription='" + groupDescription + '\'' +
                ", members=" + members +
                '}';
    }
}
