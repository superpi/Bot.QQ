package ninja.skyrocketing.bot.fuyao.mapper.group;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ninja.skyrocketing.bot.fuyao.pojo.group.GroupTimelyMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupTimelyMessageMapper extends BaseMapper<GroupTimelyMessage> {
    int deleteByPrimaryKey(@Param("groupId") Long groupId, @Param("userId") Long userId);

    int insert(GroupTimelyMessage record);

    int insertSelective(GroupTimelyMessage record);

    GroupTimelyMessage selectByPrimaryKey(@Param("groupId") Long groupId, @Param("userId") Long userId);

    int updateByPrimaryKeySelective(GroupTimelyMessage record);

    int updateByPrimaryKey(GroupTimelyMessage record);

    List<GroupTimelyMessage> selectAllGroupTimelyMessage();
}