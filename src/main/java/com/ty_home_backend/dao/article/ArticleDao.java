package com.ty_home_backend.dao.article;

import com.ty_home_backend.dao.CRUDDao;
import com.ty_home_backend.model.article.ArticleModel;
import com.ty_home_backend.model.article.ArticleModelParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Mapper
public interface ArticleDao extends CRUDDao {
    List<ArticleModel> getAuthorArticleListByPage (@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("isAll") Boolean isAll, @Param("obj") ArticleModelParams articleModelParams);
    List<ArticleModel> getAuthorArticleListByAll (@Param("isAll") Boolean isAll, @Param("obj") ArticleModelParams articleModelParams);
    List<ArticleModel> getReaderArticleListByPage (@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("isAll") Boolean isAll, @Param("obj") ArticleModelParams articleModelParams);
    int moveArticle(@Param("articleId") Integer articleId, @Param("targetAnthologyId") Integer targetAnthologyId);
    int getCountByCondition(@Param("isAll") Boolean isAll, @Param("obj") ArticleModelParams articleModelParams);
    ArticleModel getArticleDetailById(@Param("articleId") Integer articleId);
}
