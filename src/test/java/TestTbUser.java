import com.example.mybatis.my.shop.entity.TbUser;
import com.example.mybatis.my.shop.mapper.TbuserMapper;
import com.example.mybatis.my.shop.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTbUser
{
    SqlSession session=MybatisUtils.getSqlSession();

    @Test
    public void testFindTbUser(){
        TbuserMapper tbuserMapper=session.getMapper(TbuserMapper.class);
        TbUser tbUser= tbuserMapper.findById(7);
        System.out.println(tbUser);
    }
    @Test
    public void testFindAllTbUser(){
        TbuserMapper tbuserMapper=session.getMapper(TbuserMapper.class);
        List<TbUser> list=tbuserMapper.findAllTbUsers();
        for (TbUser tbUser:list) {
            System.out.println(tbUser);
        }
    }
    @Test
    public void testDelTbUserById(){
        TbuserMapper tbuserMapper=session.getMapper(TbuserMapper.class);
        int i =tbuserMapper.delTbUserById(9);
        if(i>0){
            System.out.println("删除成功！！！"+i);
        }
        session.commit();
    }
    @Test
    public void testUpdateTbUser(){
        TbuserMapper tbuserMapper=session.getMapper(TbuserMapper.class);
        TbUser tbUser=new TbUser();
        tbUser.setUserName("张三");
        tbUser.setPassWord("1234");
        tbUser.setEmail("lisi@qq[com");
        tbUser.setPhone("22343424");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbUser.setId(37);
        tbuserMapper.updateTbUser(tbUser);
        session.commit();
    }
    @Test
    public void testInsertTbUser(){
        TbuserMapper tbuserMapper=session.getMapper(TbuserMapper.class);
        TbUser tbUser=new TbUser();
        tbUser.setUserName("王五3424");
        tbUser.setPassWord("12131");
        tbUser.setPhone("1231231dasd234");
        tbUser.setEmail("sadasd@asd11.com");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        tbuserMapper.insertTbUser(tbUser);
        session.commit();
    }
    @Test
    public void testSearchByTbUser(){
        Map<String,String> map=new HashMap<String,String>(){{
            this.put("search","");
            this.put("username","五");
            this.put("phone","");
            this.put("email","");
        }};
        TbuserMapper tbuserMapper=session.getMapper(TbuserMapper.class);
        List<TbUser> list=tbuserMapper.searchByTbUser(map);
        for (TbUser tbUser : list) {
            System.out.println(tbUser);
        }
    }
    @Test
    public void testSelectByEmail(){
        TbuserMapper tbuserMapper=session.getMapper(TbuserMapper.class);
        TbUser tbUser = tbuserMapper.selectByEmail("sadasd@11.com");
        System.out.println(tbUser);
    }
}
