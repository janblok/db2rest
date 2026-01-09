package com.homihq.db2rest.auth.data;

public record RoleDataFilter(String roleName, String optionalTableName, String columnName, String filterValue) {
}
