package com.homihq.db2rest.auth.data;

public record RoleDataFilter(String roleName, String tableName, String columnName, Object value) {
}
