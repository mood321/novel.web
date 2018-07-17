package novel.web.service;

import java.util.List;

import novel.spider.entity.Novel;

public interface NovelService {
	
	/**
	 * 通过查询关键词  到数据库查询 查找小说
	 * @param keyWord
	 * @return
	 */
	public List<Novel> getNovelByKeyord(String keyWord);
	
	/**
	 * 	查找平台下的小说 
	 * @param keyWord
	 * @param platfromId
	 * @return
	 */
	public List<Novel> getNovelByKeyord(String keyWord,int platfromId);
	
}
