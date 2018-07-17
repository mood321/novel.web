package novel.web.mapper;

import java.util.List;
import java.util.Map;

import novel.spider.entity.Novel;



public interface NovelMapper {
	public int deleteByPrimaryKey(Long id);

	int insert(Novel record);

    public int insertSelective(Novel record);

    public Novel selectByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(Novel record);

    public int updateByPrimaryKey(Novel record);
    public int batchInsert(List<Novel> novels);
    
    public List<Novel> getNovelByKeyord(String keyWord);
    
    public List<Novel> getNovelByKeyord2(Map<String,String> map);
}