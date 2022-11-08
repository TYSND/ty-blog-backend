package com.ty_home_backend.service.article;

import com.ty_home_backend.model.article.ArticleModel;
import com.ty_home_backend.model.article.ArticleModelParams;
import com.ty_home_backend.service.CRUDService;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;

public interface ArticleService extends CRUDService {
    ResponseMessage searchAuthorListByPage(ArticleModelParams articleModelParams);
    ResponseMessage searchReaderListByPage(ArticleModelParams articleModelParams);
    ResponseMessage searchAuthorListByAll(ArticleModelParams articleModelParams);
    ResponseMessage newArticle(ArticleModel articleModel);
    ResponseMessage saveArticle(ArticleModel articleModel);
    ResponseMessage publishArticle(ArticleModel articleModel);
    ResponseMessage moveArticle(Integer articleId, Integer targetAnthologyId);
    ResponseMessage deleteArticle(Integer articleId);
    ResponseMessage searchArticleDetailById(Integer articleId);
}
