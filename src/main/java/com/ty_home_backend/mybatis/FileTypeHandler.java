package com.ty_home_backend.mybatis;

import org.apache.ibatis.type.*;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 将java File类型的路径转成varchar
 * @Author lpjworkroom
 * @Date 2021/1/1 16:55
 * @Version 1.0
 */
public class FileTypeHandler extends BaseTypeHandler<File> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, File parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getPath());
    }

    @Override
    public File getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new File(rs.getString(columnName));
    }

    @Override
    public File getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new File(rs.getString(columnIndex));
    }

    @Override
    public File getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new File(cs.getString(columnIndex));
    }
}
