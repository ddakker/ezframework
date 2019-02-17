package org.ezdevgroup.sample.persist.session;

import org.apache.ibatis.annotations.Select;
import org.ezdevgroup.sample.domain.EzMap;

import java.util.List;

public interface SessionTestMapper {

    @Select("SELECT "
            + "		* "
            + "	FROM SPRING_SESSION "
            + "	ORDER BY PRIMARY_ID DESC")
    public List<EzMap> test1();

    @Select("SELECT "
            + "		* "
            + "	FROM SPRING_SESSION_ATTRIBUTES ")
    public List<EzMap> test2();
}
