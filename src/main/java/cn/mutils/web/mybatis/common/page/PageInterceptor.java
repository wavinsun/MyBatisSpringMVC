package cn.mutils.web.mybatis.common.page;

import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * Created by wenhua.ywh on 2016/11/21.
 */
@Intercepts({
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class}), // 预处理
        @Signature(method = "handleResultSets", type = ResultSetHandler.class, args = {Statement.class}) // 结果集
})
public class PageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof StatementHandler) {
            return intercept(invocation, (StatementHandler) target);
        } else if (target instanceof ResultSetHandler) {
            return intercept(invocation, (ResultSetHandler) target);
        }
        return invocation.proceed();
    }

    private Object intercept(Invocation invocation, StatementHandler handler) throws Throwable {
        BoundSql boundSql = handler.getBoundSql();
        Object parameter = boundSql.getParameterObject();
        Page<?> page = InterceptorUtil.getPageParam(parameter);
        if (page == null) {
            return invocation.proceed();
        }
        Connection conn = (Connection) invocation.getArgs()[0];
        String sql = boundSql.getSql();
        int totalCount = InterceptorUtil.queryTotalCount(sql, conn);
        page.setTotalCount(totalCount);
        int pageNo = page.getPageNo();
        if (pageNo < 1) {
            pageNo = 1;
            page.setPageNo(pageNo);
        }
        int pageSize = page.getPageSize();
        if (pageSize < 1) {
            pageSize = Page.PAGE_SIZE_DEFAULT;
            page.setPageSize(pageSize);
        }
        int totalPageCount = (int) Math.ceil(((double) totalCount) / pageSize);
        page.setTotalPageCount(totalPageCount);
        if (pageNo > totalPageCount) {
            pageNo = totalPageCount + 1;
            page.setPageNo(pageNo);
        }
        page.setStartRow(totalCount == 0 ? 0 : ((pageNo - 1) * pageSize + 1));
        page.setEndRow(totalCount == 0 ? 0 : (pageNo * pageSize));
        page.setFirstPage(totalCount == 0 ? true : pageNo == 1);
        page.setLastPage(totalCount == 0 ? true : pageNo >= totalPageCount);
        page.setHasPreviousPage(totalPageCount == 0 ? false : (pageNo > 1));
        page.setHasNextPage(totalPageCount == 0 ? false : (pageNo >= 1 && pageNo < totalPageCount));
        page.setPreviousPage(pageNo - 1);
        page.setNextPage(pageNo + 1);
        int pos = 0;
        if (pageNo > 1) {
            pos = (pageNo - 1) * page.getPageSize();
        }
        String pageSql = sql + " limit " + pos + "," + page.getPageSize();
        InterceptorUtil.setFieldValue(boundSql, "sql", pageSql);
        return invocation.proceed();
    }

    private Object intercept(Invocation invocation, ResultSetHandler handler) throws Throwable {
        Object resultSet = invocation.proceed();
        if (!(resultSet instanceof List)) {
            return resultSet;
        }
        List resultList = (List) resultSet;
        if (!(handler instanceof DefaultResultSetHandler)) {
            return resultList;
        }
        DefaultResultSetHandler defaultHandler = (DefaultResultSetHandler) handler;
        BoundSql boundSql = (BoundSql) InterceptorUtil.getFieldValue(defaultHandler, "boundSql");
        if (boundSql == null) {
            return resultList;
        }
        Object parameter = boundSql.getParameterObject();
        Page<?> page = InterceptorUtil.getPageParam(parameter);
        if (page == null) {
            return resultList;
        }
        page.setResultList(resultList);
        page.setSize(resultList.size());
        return resultList;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }


}
