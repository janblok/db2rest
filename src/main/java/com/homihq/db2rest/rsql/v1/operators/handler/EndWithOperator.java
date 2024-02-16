package com.homihq.db2rest.rsql.v1.operators.handler;

import com.homihq.db2rest.rsql.v1.operators.Operator;
import org.mybatis.dynamic.sql.ColumnAndConditionCriterion;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlCriterion;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

public class EndWithOperator implements Operator {

    @Override
    public SqlCriterion handle(SqlColumn<Object> column, String value, Class<?> type) {

        return ColumnAndConditionCriterion.withColumn(column)
                .withCondition(isLike("%" + value)).build();

    }

}