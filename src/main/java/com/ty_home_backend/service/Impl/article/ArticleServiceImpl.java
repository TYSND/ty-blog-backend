package com.ty_home_backend.service.Impl.article;

import com.ty_home_backend.dao.article.ArticleDao;
import com.ty_home_backend.model.article.ArticleModel;
import com.ty_home_backend.model.article.ArticleModelParams;
import com.ty_home_backend.service.Impl.CRUDServiceImpl;
import com.ty_home_backend.service.article.ArticleService;
import com.ty_home_backend.utils.Response.DataObj;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseMessage;
import com.ty_home_backend.utils.Response.ResponseMessage.ResponseSuccessMessage;
import lombok.var;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;



import javax.annotation.Resource;
import java.util.Date;

@Service
public class ArticleServiceImpl extends CRUDServiceImpl implements ArticleService {
    @Resource
    ArticleDao articleDao;

    public ArticleServiceImpl(ArticleDao articleDao) {super(articleDao);}

    @Override
    public ResponseMessage searchAuthorListByPage(ArticleModelParams articleModelParams) {
        var res = articleDao.getAuthorArticleListByPage(articleModelParams.getPager().getLimit(), articleModelParams.getPager().getOffset(), true, articleModelParams);
        Integer totalNum = articleDao.getCountByCondition(true, articleModelParams);
        return new ResponseSuccessMessage(new DataObj(res, totalNum));
    }

    @Override
    public ResponseMessage searchReaderListByPage(ArticleModelParams articleModelParams) {
        var res = articleDao.getReaderArticleListByPage(articleModelParams.getPager().getLimit(), articleModelParams.getPager().getOffset(), false, articleModelParams);
        Integer totalNum = articleDao.getCountByCondition(false, articleModelParams);
        return new ResponseSuccessMessage(new DataObj(res, totalNum));
    }
    @Override
    public ResponseMessage searchAuthorListByAll(ArticleModelParams articleModelParams) {
        var res = articleDao.getAuthorArticleListByAll(true, articleModelParams);
        return new ResponseSuccessMessage(new DataObj(res));
    }

    @Override
    public ResponseMessage searchArticleDetailById(Integer articleId) {
        var res = articleDao.getArticleDetailById(articleId);
        return new ResponseSuccessMessage(new DataObj(res));
    }

    private String generateDesc (String content) {
        String desc = "";

        Parser parser = Parser.builder().build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .sanitizeUrls(true) //去除<a>链接和<img>连接
                .build();
        String html = renderer.render(document);
        String text = Jsoup.parse(html).text();
        if (text.length() < 50) {
            desc = text;
        } else {
            desc = text.substring(0, 50) + "...";
        }

        return desc;
    }

    @Override
    public ResponseMessage newArticle(ArticleModel articleModel) {
        articleModel.setCreateTime(new Date());
        articleModel.setIsDelete(false);
        articleModel.setDescription(this.generateDesc(articleModel.getContent()));
        articleDao.create(articleModel);
        return new ResponseSuccessMessage();
    }

    @Override
    public ResponseMessage saveArticle(ArticleModel articleModel) {
        articleDao.update(articleModel);
        articleModel.setDescription(this.generateDesc(articleModel.getContent()));
        return new ResponseSuccessMessage();
    }

    @Override
    public ResponseMessage publishArticle(ArticleModel articleModel) {
        articleDao.update(articleModel);
        articleModel.setDescription(this.generateDesc(articleModel.getContent()));
        return new ResponseSuccessMessage();
    }

    @Override
    public ResponseMessage moveArticle(Integer articleId, Integer targetAnthologyId) {
        articleDao.moveArticle(articleId, targetAnthologyId);
        return new ResponseSuccessMessage();
    }
    @Override
    public ResponseMessage deleteArticle(Integer articleId) {
        articleDao.deleteById(articleId);
        return new ResponseSuccessMessage();
    }
}
