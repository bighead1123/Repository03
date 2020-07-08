package org.dal;

import org.common.Conn;
import org.common.DataValidator;
import org.model.PageModel;
import org.model.ReaderInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
	Conn conn=new Conn();
	/**
	 *判断登陆用户是否合法
	 * @param readerName
	 * @param readerPassword
	 * @return
	 * @throws SQLException
	 */
	public boolean isExist(String readerName,String readerPassword)throws SQLException{
		boolean result=false;
		ReaderInfo rinfo=new ReaderInfo();
		String sql="select * from reader a where username='"+readerName+"'and password='"+readerPassword+"'";
		System.out.println(sql);
		ResultSet rs=conn.executeQuery(sql);
		if(rs.next()){
			rinfo.setUsername(rs.getString("username"));
			rinfo.setPassword(rs.getString("password"));
			result=true;
		}
		conn.close();
		return result;
	}
	/**
	 * 当前注册用户是否存在
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistReaderInfo(String readerName)throws SQLException{
		boolean result=false;
		ReaderInfo rinfo=new ReaderInfo();
		String sql="select * from reader  where username='"+readerName+"'";
		ResultSet rs=conn.executeQuery(sql);
		if(rs.next()){
			rinfo.setUsername(rs.getString("username"));
			result=true;
		}
		conn.close();
		return result;
	}
	/**
	 * ߲用户注册
	 * @param info
	 * @return
	 */
	public int insert(ReaderInfo info)throws SQLException{
		String sql="insert into reader(username,name,password,mail) values";
		sql=sql+"('"+info.getUsername()+"','"+info.getUsername()+"','"+info.getPassword()+"','"+info.getMail()+"')";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 用户信息更新
	 * @param info
	 * @return
	 */
	public int update(ReaderInfo info){
		String sql="update reader set password='"+info.getPassword()+"',mail='"+info.getMail()+"'"
				+ "where username='"+info.getUsername()+"'";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 注册用户的删除
	 * @return
	 */
	public int delete(String readerName){
		String sql="delete from reader where name='"+readerName+"'";
		int result=0;
		result=conn.executeUpdate(sql);
		conn.close();
		return result;
	}
	/**
	 * 获取注册用户的列表
	 * @param keyword
	 * @return
	 * @throws SQLException
	 */
	public List<ReaderInfo>getList(String keyword)throws SQLException{
		List<ReaderInfo> list=new ArrayList<ReaderInfo>();
		String sql="select * from reader";
		if(DataValidator.isNullOrEmpty(keyword)){
			sql=sql+ " order by username desc";
		}else{
			sql=sql+" where name like '%"+keyword+"%' order by username desc";
		}
		ResultSet rs=conn.executeQuery(sql);
		while(rs.next()){
			ReaderInfo ainfo=new ReaderInfo();
			ainfo.setUsername(rs.getString("username"));
			ainfo.setUsername(rs.getString("name"));
			ainfo.setPassword(rs.getString("password"));
			ainfo.setMail(rs.getString("mail"));
			list.add(ainfo);
		}
		conn.close();
		return list;
		
	}
	//根据页码和每页的容量来得到数据
    public PageModel getPage(int pageNo, int pageSize) {
        ResultSet rs = null;
        List<ReaderInfo> list = new ArrayList<ReaderInfo>();
        PageModel page = null;
        try {
            //获取总数据条数
            int totalCount = 0; 
            String sql="select * from reader";
            rs = conn.executeQuery(sql);
            while(rs.next()){
                totalCount = rs.getInt(1);
            }
            while(rs.next()){
                ReaderInfo rin=new ReaderInfo();
                rin.setUsername(rs.getString("username"));
    			rin.setUsername(rs.getString("name"));
    			rin.setPassword(rs.getString("password"));
    			rin.setMail(rs.getString("mail"));
    			list.add(rin);
            }
            page = new PageModel(pageSize, totalCount);
            page.setData(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	conn.close();
        }

        return page;
    }
}
