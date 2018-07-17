package novel.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.ws.soap.AddressingFeature.Responses;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import novel.spider.entity.Chapter;
import novel.spider.entity.ChapterDetail;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.ChapteSpiderFactory;
import novel.spider.util.ChapterDetailSpiderFactory;
import novel.spider.util.NovelSpiderUtil;
import novel.web.entitys.JSONResponse;
import novel.web.service.NovelService;

@Controller
public class NovelController {
	@Resource
	private NovelService service;
	static {
		NovelSpiderUtil.setConfigPath("D:/javaa/web/novel/conf/Spider-Rult.xml");

	}

	@RequestMapping(value = "/test/chapters.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse getChapter(String url) {
		IChapterSpider spider = ChapteSpiderFactory.getChapterSpider(url);
		List<Chapter> chapters = spider.getsChapter(url);
		return JSONResponse.success(chapters);

	};

	@RequestMapping(value = "/test/chapterDetail.do", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse getChapterDetail(String url) {
		IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterSpider(url);

		ChapterDetail chapterDetails = spider.getChapterDetail(url);
		return JSONResponse.success(chapterDetails);

	};
	@RequestMapping(value="/novelSearch.do" ,method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getNovelByKeyword(String keyWord) {
		/*try {
		 //转字符格式
			keyWord=new String(keyWord.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return JSONResponse.success(service.getNovelByKeyord(keyWord));
	}
	
	@RequestMapping(value="/novelSearch2.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONResponse getNovelByKeyword(String keyWord,int platfromId) {
		return JSONResponse.success(service.getNovelByKeyord(keyWord, platfromId));
	}
	
	@RequestMapping(value="/chapterList.do",method=RequestMethod.GET)
	public ModelAndView showChapterList(String url) {
		ModelAndView view=new ModelAndView();
		view.setViewName("chapterList");
		view.getModelMap().put("chapters", ChapteSpiderFactory.getChapterSpider(url).getsChapter(url));
		view.getModelMap().put("beasUrl",url);
		return view; 
		
	}
	@RequestMapping(value="/chapterDetail.do",method=RequestMethod.GET)
	public ModelAndView showChapterDetailList(String url,String beasUrl) {
		ModelAndView view=new ModelAndView();
		view.setViewName("chapterDetail");
		
		try {
			
			ChapterDetail detail=ChapterDetailSpiderFactory.getChapterSpider(url).getChapterDetail(url);
			detail.setContent(detail.getContent().replaceAll("\\{line\\}", "</br>"));
			System.out.println(detail.getContent());
			view.getModelMap().put("chapterDetail", detail);
			view.getModelMap().put("isSuccess",true);
		}catch(Exception e) {
			e.printStackTrace();
			view.getModelMap().put("isSuccess",false);
		}
			
		view.getModelMap().put("beasUrl",beasUrl);
		return view; 
		
	}
}
