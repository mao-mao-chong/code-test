package org.com.bmw.model;

import java.io.Serializable;
import java.util.List;

public class Permission  extends BaseModel{
    //菜单code
    private String permissionCode;
    //菜单名称
    private String permissionName;
    //菜单url
    private String permissionUrl;
    //父级菜单code
    private String parentCode;
    //子节点信息
    private List<Permission> sonPermission;

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public List<Permission> getSonPermission() {
        return sonPermission;
    }

    public void setSonPermission(List<Permission> sonPermission) {
        this.sonPermission = sonPermission;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionCode='" + permissionCode + '\'' +
                ", permissionName='" + permissionName + '\'' +
                ", permissionUrl='" + permissionUrl + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", sonPermission=" + sonPermission +
                '}';
    }
}
