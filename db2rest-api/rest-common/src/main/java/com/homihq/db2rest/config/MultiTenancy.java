package com.homihq.db2rest.config;

import java.util.List;
import java.util.Map;

import com.homihq.db2rest.auth.data.RoleDataFilter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiTenancy {
    public static String joinFilters(String filter, String tableName, List<RoleDataFilter> roleBasedDataFilters) {
        StringBuilder filterBuilder = new StringBuilder(filter);
        if (roleBasedDataFilters != null) {
            for (RoleDataFilter roleDataFilter : roleBasedDataFilters) {
                if (tableName.equalsIgnoreCase(roleDataFilter.tableName())) {
                    if (!filterBuilder.isEmpty()) filterBuilder.append(";");
                    filterBuilder.append(roleDataFilter.columnName());
                    filterBuilder.append("==");
                    filterBuilder.append(roleDataFilter.value());
                }
            }
        }
        return filterBuilder.toString();
    }

    public static void addTenantColumns(List<Map<String, Object>> data, String tableName, List<RoleDataFilter> roleBasedDataFilters) {
        for(Map<String, Object> dataItem : data) {
            addTenantColumns(dataItem, tableName, roleBasedDataFilters);
        }
    }

    public static void addTenantColumns(Map<String, Object> data, String tableName, List<RoleDataFilter> roleBasedDataFilters) {
        if (roleBasedDataFilters != null) {
            for (RoleDataFilter roleDataFilter : roleBasedDataFilters) {
                if (tableName.equalsIgnoreCase(roleDataFilter.tableName())) {
                    data.put(roleDataFilter.columnName(), roleDataFilter.value());
                }
            }
        }
    }
}
