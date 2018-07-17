package novel.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import novel.spider.entity.Novel;
import novel.web.mapper.NovelMapper;


@Service
public class NovelServiceIMPL implements NovelService{
	@Resource//依赖注入
	private NovelMapper mapper;
	@Override
	public List<Novel> getNovelByKeyord(String keyWord) {
		keyWord="%" +keyWord+"%";
		
		return mapper.getNovelByKeyord(keyWord);
	}
	@Override
	public List<Novel> getNovelByKeyord(String keyWord, int platfromId) {
		Map<String ,String> map=new HashMap<>();
		map.put("keyWord", "%" +keyWord+"%");
		map.put("platfromId", platfromId+"");
		return mapper.getNovelByKeyord2(map);
	}
	
}
