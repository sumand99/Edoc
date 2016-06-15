package com.infogain.edoc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import com.infogain.edoc.dao.FileCategoryDao;
import com.infogain.edoc.dao.HrBgcDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.dao.UserDao;
import com.infogain.edoc.exception.RegistrationException;
import com.infogain.edoc.model.DashboardData;
import com.infogain.edoc.model.Employee;
import com.infogain.edoc.model.FileCategory;
import com.infogain.edoc.model.FileDelete;
import com.infogain.edoc.model.HrBgcApprove;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.Registration;
import com.infogain.edoc.model.SubmitData;
import com.infogain.edoc.model.User;
import com.infogain.edoc.utils.DbQueries.PreEmployeeDaoQueries;
import com.infogain.edoc.utils.RandomStringGenerator;

/**
 * The PreEmployeeDaoImpl performs all the data access operation related to
 * Pre-Employee. This class implements com.infogain.edoc.dao.PreEmployeeDao
 * interface.
 * 
 * @author amant.kumar
 * 
 */
@Configurable
@Transactional
public class PreEmployeeDaoImpl implements PreEmployeeDao {

	private DataSource dataSource;
	private JdbcTemplate templateObj;
	private NamedParameterJdbcTemplate namedTemplateObj;
	public static final String ROLE_RMG = "ROLE_RMG";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_RMG_ADMIN = "ROLE_RMG_ADMIN";
	@Autowired
	private FileCategoryDao fileCategoryDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private HrBgcDao hrBgcDao;

	/**
	 * This method is used to set the data source to the JdbcTemplate
	 * initialisation.
	 * 
	 * @param dataSource
	 *            This contains database information and credentials required to
	 *            connect to the database.
	 * @return nothing
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		templateObj = new JdbcTemplate(dataSource);
		namedTemplateObj = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * This method is used to get application id of a pre-employee based on the
	 * username provided.
	 * 
	 * @param username
	 * @return int This returns the application id of the pre-employee.
	 */
	@Override
	public int getPreEmployeeApplicationId(String username) {
		//String sql = "select application_id from pre_emp where username = ?";
		String sql = PreEmployeeDaoQueries.getPreEmployeeApplicationId;
		
		Integer applicationId = templateObj.queryForObject(sql, Integer.class,
				username);
		return (null == applicationId) ? 0 : applicationId;
	}

	/**
	 * This method is used to get an object of Pre-employee based on the
	 * username provided.
	 * 
	 * @param username
	 * @return PreEmployee This returns an object of PreEmployee class.
	 * @see PreEmployee
	 */
	@Override
	public PreEmployee getPreEmployee(String username) {
		//String sql = "select username, application_id, first_name, last_name, email, contact, address,file_location, pre_flag, edit_flag, enable_flag, rmg_done_flag, rmg_admin_done_flag, total_edit_flag from pre_emp where username = ?";
		String sql = PreEmployeeDaoQueries.getPreEmployee_1;
		
		List<PreEmployee> preEmp = templateObj.query(sql,
				new RowMapper<PreEmployee>() {
					@Override
					public PreEmployee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return new PreEmployee(rs.getString(1), rs.getInt(2),
								rs.getString(3), rs.getString(4), rs
										.getString(5), rs.getString(6), rs
										.getString(7), rs.getString(8), rs
										.getInt(9), rs.getInt(10), rs
										.getInt(11), rs.getInt(12), rs
										.getInt(13), rs.getInt(14));
					}
				}, username);
		return (preEmp.isEmpty()) ? null : preEmp.get(0);
	}

	/**
	 * This method is used to a List of dashboard data which will be populated
	 * on different based on the role of the users.
	 * 
	 * @param role
	 * @return List of Dashboard Data.
	 * @see DashBoardData
	 */
	@Override
	public List<DashboardData> getPreEmployeeList(String role) {
		String sql = "";
		if (ROLE_RMG.equalsIgnoreCase(role)) {
			//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag,pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and rmg_done_flag = 0)page_data where rownum >=0";
			sql = PreEmployeeDaoQueries.getPreEmployeeList_1_1;
			
		} else if (ROLE_RMG_ADMIN.equalsIgnoreCase(role)) {
			//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 0)page_data where rownum >=0";
			sql = PreEmployeeDaoQueries.getPreEmployeeList_1_2;
			
		} else if (ROLE_ADMIN.equalsIgnoreCase(role)) {
			//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 1)page_data where rownum >=0";
			sql = PreEmployeeDaoQueries.getPreEmployeeList_1_3;
			
		}
		else{
			//sql = "select distinct top 10 bgc_done_flag,application_id, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag, emp_id,email from (select distinct em.email, em.emp_id,hb.bgc_done_flag,pe.application_id, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag,pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id left join emp em on pe.application_id = em.application_id where pe.edit_flag = 0)page_data where rownum >=0";
			sql = PreEmployeeDaoQueries.getPreEmployeeList_1_4;
		}
		List<DashboardData> empList = templateObj.query(sql,
				new RowMapper<DashboardData>() {
					@Override
					public DashboardData mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return new DashboardData(rs.getInt(1), rs.getInt(2), rs
								.getString(3), rs.getString(4),
								 rs.getInt(5), rs.getInt(6), rs
										.getInt(7),rs.getString(8),rs.getString(9));
					}
				});
		return (empList.isEmpty()) ? null : empList;
	}

	/**
	 * This method is used to a List of dashboard data which will be populated
	 * on different based on the role of the users and the search term provided.
	 * It also consider the numeber of table rows to be skipped in the database
	 * query result.
	 * 
	 * @param role
	 * @param searchTerm
	 * @param offset
	 * @return List of Dashboard Data.
	 * @see DashBoardData
	 */
	@Override
	public List<DashboardData> getPreEmployeeList(int offset,
			String searchTerm, String role) {
		List<DashboardData> empList = new ArrayList<DashboardData>();
		String sql = "";
		if (ROLE_RMG.equalsIgnoreCase(role)) {
			if ("".equals(searchTerm)) {
				//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag,pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and rmg_done_flag = 0)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_1;
				
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, offset);
			} else {
				//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag,pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and rmg_done_flag = 0 and pe.first_name like ?)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_2;
				
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, "%" + searchTerm + "%", offset);
			}
		} else if (ROLE_RMG_ADMIN.equalsIgnoreCase(role)) {
			if (searchTerm.equals("")) {
				//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 0)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_3;
				
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, offset);
			} else {
				//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 0 and pe.first_name like ?)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_4;
				
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, "%" + searchTerm + "%", offset);
			}
		} else if (ROLE_ADMIN.equalsIgnoreCase(role)) {
			if (searchTerm.equals("")) {
				//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 1)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_5;
				
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, offset);
			} else {
				//sql = "select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 1 and pe.first_name like ?)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_6;
				
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, "%" + searchTerm + "%", offset);
			}
		}
		else{
			if ("".equals(searchTerm)) {
				//sql = "select distinct top 10 bgc_done_flag,application_id, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag, emp_id, email from (select distinct em.email, em.emp_id,hb.bgc_done_flag,pe.application_id, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag,pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id  left join emp em on pe.application_id = em.application_id where pe.edit_flag = 0)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_7;
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, offset);
			} else {
				//sql = "select distinct top 10 bgc_done_flag,application_id, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag, emp_id, email from (select distinct em.email, em.emp_id,hb.bgc_done_flag,pe.application_id, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag,pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id  left join emp em on pe.application_id = em.application_id where pe.edit_flag = 0 and pe.first_name like ?)page_data where rownum >=?";
				sql = PreEmployeeDaoQueries.getPreEmployeeList_2_8;
				empList = templateObj.query(sql,
						new RowMapper<DashboardData>() {

							@Override
							public DashboardData mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return new DashboardData(rs.getInt(1), rs.getInt(2), rs
										.getString(3), rs.getString(4),
										 rs.getInt(5), rs.getInt(6), rs
												.getInt(7),rs.getString(8),rs.getString(9));
							}
						}, "%" + searchTerm + "%", offset);
			}
		}
		return (empList.isEmpty()) ? null : empList;
	}

	/**
	 * This method is used to get total number of results.
	 * 
	 * @param role
	 * @param searchTerm
	 * @return int count
	 * 
	 */
	@Override
	public int getCount(String searchTerm, String role) {
		int count = 0;
		String sql = "";
		if (ROLE_RMG.equalsIgnoreCase(role)) {
			if (searchTerm.equals("")) {
				//sql = "select count(*) from (select distinct bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and rmg_done_flag = 0)page_data where rownum >=0) q1";
				sql = PreEmployeeDaoQueries.getCount1;
				
				count = templateObj.queryForObject(sql, Integer.class);
			} else {
				//sql = "select count(*) from (select distinct bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag,pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and rmg_done_flag = 0 and pe.first_name like ?)page_data where rownum >=0) q1";
				sql = PreEmployeeDaoQueries.getCount2;
				
				count = templateObj.queryForObject(sql, Integer.class, "%"
						+ searchTerm + "%");
			}
		} else if (ROLE_RMG_ADMIN.equalsIgnoreCase(role)) {
			if (searchTerm.equals("")) {
				//sql = "select count(*) from (select distinct bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 0)page_data where rownum >=0) q1";
				sql = PreEmployeeDaoQueries.getCount3;
				
				count = templateObj.queryForObject(sql, Integer.class);
			} else {
				//sql = "select count(*) from (select distinct bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 0 and pe.first_name like ?)page_data where rownum >=0) q1";
				sql = PreEmployeeDaoQueries.getCount4;
				
				count = templateObj.queryForObject(sql, Integer.class, "%"
						+ searchTerm + "%");
			}
		} else if (ROLE_ADMIN.equalsIgnoreCase(role)) {
			if (searchTerm.equals("")) {
				//sql = "select count(*) from (select distinct bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 1)page_data where rownum >=0) q1";
				sql = PreEmployeeDaoQueries.getCount5;
				
				count = templateObj.queryForObject(sql, Integer.class);
			} else {
				//sql = "select count(*) from (select distinct top 10 bgc_done_flag,application_id, email, first_name,last_name, pre_flag, rmg_done_flag, rmg_admin_done_flag from (select distinct hb.bgc_done_flag,pe.application_id, pe.email, pe.first_name,pe.last_name, pe.pre_flag, pe.rmg_done_flag, pe.rmg_admin_done_flag, ROW_NUMBER() over (order by fd.last_modified_date desc) as rownum from file_data fd left join pre_emp pe on pe.application_id = fd.application_id left join hr_bgc hb on pe.application_id = hb.application_id where pe.edit_flag = 0 and pe.rmg_done_flag = 1 and pe.rmg_admin_done_flag = 1 and pe.first_name like ?)page_data where rownum >=0) q1";
				sql = PreEmployeeDaoQueries.getCount6;
				
				count = templateObj.queryForObject(sql, Integer.class, "%"
						+ searchTerm + "%");
			}
		}
		return count;
	}

	/**
	 * This method is used to get Pre-employee based on the application id
	 * provided.
	 * 
	 * @param applicationId
	 * @return PreEmployee The object of a pre-employee.
	 * @see PreEmployee
	 * 
	 */
	@Override
	public PreEmployee getPreEmployee(int applicationId) {
		//String sql = "select username, application_id, first_name, last_name, email, contact, address,file_location, pre_flag, edit_flag, enable_flag, rmg_done_flag, rmg_admin_done_flag from pre_emp where application_id = ?";
		String sql = PreEmployeeDaoQueries.getPreEmployee_2;
		
		List<PreEmployee> preEmp = templateObj.query(sql,
				new RowMapper<PreEmployee>() {
					@Override
					public PreEmployee mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return new PreEmployee(rs.getString(1), rs.getInt(2),
								rs.getString(3), rs.getString(4), rs
										.getString(5), rs.getString(6), rs
										.getString(7), rs.getString(8), rs
										.getInt(9), rs.getInt(10), rs
										.getInt(11), rs.getInt(12), rs
										.getInt(13), rs.getInt(14));
					}
				}, applicationId);
		return (preEmp.isEmpty()) ? null : preEmp.get(0);
	}

	/**
	 * This method is used to register a new pre-employee.
	 * 
	 * @param register
	 *            This is an object of registration type.
	 * @param fileLocation
	 *            This specicies where the files of this particular pre-employee
	 *            will be uploaded.
	 * @see Registration
	 * @return Registration
	 * @throws RegistrationException
	 * 
	 */
	@Override
	public Registration registration(Registration register, String fileLocation)
			throws RegistrationException {
		Registration r;
		String password = "";
		FileCategory fileCategory;
		PreEmployee preEmp;
		SqlParameterSource fileParameters;
		SqlParameterSource preEmpParameters;
		KeyHolder categoryKeyHolder = new GeneratedKeyHolder();
		KeyHolder preEmpKeyHolder = new GeneratedKeyHolder();
		int applicationId;
		int categoryId;
		String userEmails = register.getEmail().get(0);
		for(int i=1;i<register.getEmail().size();i++){
			userEmails += "," + register.getEmail().get(i);
		}
		if (!isSameEmail(register.getEmail().get(0))) {
			//password = String.valueOf(new Random().nextInt(1000));
			password = RandomStringGenerator.generateString();
			preEmp = new PreEmployee(register.getEmail().get(0), password,
					register.getFirstName(), register.getLastName(),
					userEmails, null, null, 0, fileLocation, 1, 1, 1,
					0, 0);
			preEmpParameters = new BeanPropertySqlParameterSource(preEmp);
			List<String> folderList = fileCategoryDao.getDefaultFolders();
			//String sql1 = "insert into pre_emp(username,password,first_name,last_name, email,contact, address, zip, file_location, pre_flag, edit_flag, enable_flag, rmg_done_flag, rmg_admin_done_flag ) values (:username,:password,:firstName,:lastName,:email,null,null,null,:fileLocation,:preFlag,:editFlag, :enableFlag, :rmgDoneFlag,:rmgAdminDoneFlag)";
			String sql1 = PreEmployeeDaoQueries.registration_insert1;
			
			//String sql2 = "insert into emp values (?,?,?,?,?,?,?,?,?,?)";
			String sql2 = PreEmployeeDaoQueries.registration_insert2;
			
			//String sql3 = "insert into file_category(category_name,parent_category_id, application_id, visibility) values(:categoryName,null,:applicationId,1)";
			String sql3 = PreEmployeeDaoQueries.registration_insert3;
			
			//String sql3_1 = "insert into file_category(category_name,parent_category_id, application_id,visibility) values (?,?,?,1) ";
			String sql3_1 = PreEmployeeDaoQueries.registration_insert4;
			
			//String sql4 = "insert into hr_bgc(application_id, bgc_done_flag, remark) values (?,?,?)";
			String sql4 = PreEmployeeDaoQueries.registration_insert5;
			
			int rows1 = namedTemplateObj.update(sql1, preEmpParameters,
					preEmpKeyHolder);
			applicationId = preEmpKeyHolder.getKey().intValue();
			fileCategory = new FileCategory("root", applicationId, 1);
			fileParameters = new BeanPropertySqlParameterSource(fileCategory);
			int rows2 = templateObj.update(sql2, applicationId, null,
					register.getFirstName(), register.getLastName(),
					register.getEmail().get(0), register.getContact(),
					register.getAddress(), register.getZip(), fileLocation,
					null);
			int rows3 = namedTemplateObj.update(sql3, fileParameters,
					categoryKeyHolder);
			int rows4 = templateObj.update(sql4, applicationId, 0, null);
			if (rows1 >= 1 && rows2 >= 1 && rows3 >= 1 && rows4 >= 1) {
				categoryId = categoryKeyHolder.getKey().intValue();
				if (userDao.insertUser(new User(register.getEmail().get(0)))) {
					for (String folderName : folderList) {
						templateObj.update(sql3_1, folderName, categoryId,
								applicationId);
					}
					r = new Registration();
					r.setUsername(register.getEmail().get(0));
					r.setPassword(password);
					return r;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			throw new RegistrationException("Same Username Or Email");
		}
	}

	/**
	 * This method is used to verify if a username or a email already exists in
	 * the database.
	 * 
	 * @param username
	 *            the username provided during registration.
	 * @param email
	 *            the email provided during registration.
	 * @return boolean
	 * 
	 */
	public boolean isSameUsernameOrEmail(String username, String email) {
		//String sql = "select count(*) from pre_emp where username= ? or email = ?";
		String emailString = "%" + email + "%";
		String usernameString = "%" + username + "%";
		String sql = PreEmployeeDaoQueries.isSameUsernameOrEmail;
		
		int count = templateObj.queryForObject(sql, Integer.class, usernameString,
				emailString);
		if (count > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * This method is used to verify if a email already exists in the database.
	 *
	 * @param email
	 *            the email provided during registration
	 * @return boolean
	 * 
	 */
	public boolean isSameEmail(String email) {
		//String sql = "select count(*) from pre_emp where email = ?";
		String emailString = "%" + email + "%";
		String sql = PreEmployeeDaoQueries.isSameEmail;
		
		int count = templateObj.queryForObject(sql, Integer.class, emailString);
		if (count > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	/**
	 * This method is used to submit pre-employee data.
	 * 
	 * @param applicationId
	 * @return String It returns success if successful and failure if fails.
	 * 
	 */
	@Override
	public String submitData(int applicationId) {
		//String sql = "update pre_emp set edit_flag = 0, total_edit_flag = 0 where application_id = ?";
		String sql = PreEmployeeDaoQueries.submitData_update;
		
		int rows = templateObj.update(sql, applicationId);
		return (rows >= 1) ? "success" : "failure";
	}

	/**
	 * This method is used to initiate background check or approve background
	 * check of a pre-employee.
	 * 
	 * @param hrBgcApproveList
	 *            list of HrBgcApprove type.
	 * @param approve
	 *            if true the approve or else initiate.
	 * @return hrBgcApprovedList list of HrBgcApprove type.
	 * @see HrBgcApprove
	 * 
	 */
	@Override
	public List<HrBgcApprove> approveBgc(List<HrBgcApprove> hrBgcApproveList,
			boolean approve) {
		HrBgcApprove hrbgcApprove;
		List<HrBgcApprove> hrBgcApprovedList = new ArrayList<HrBgcApprove>();
		FileDelete fd;
		String sql = "";
		int rows = 0;
		if (approve) {
			//sql = "update hr_bgc set bgc_done_flag = 1 where application_id = ?";
			sql = PreEmployeeDaoQueries.approveBgc_update1;
			
		} else {
			//sql = "update hr_bgc set bgc_done_flag = 2 where application_id = ?";
			sql = PreEmployeeDaoQueries.approveBgc_update2;
			
		}
		for (HrBgcApprove bgc : hrBgcApproveList) {
			rows = templateObj.update(sql, bgc.getApplicationId());
			hrbgcApprove = new HrBgcApprove();
			hrbgcApprove.setApplicationId(bgc.getApplicationId());
			hrbgcApprove.setBgcDoneFlag(1);
			hrBgcApprovedList.add(hrbgcApprove);
		}
		return hrBgcApprovedList;
	}

	/**
	 * This method is used to update the approval of RMG.
	 * 
	 * @param approveRmgList
	 *            list of SubmitData type.
	 * @see SubmitData
	 * @return rmgApprovedList list of SubmitData type.
	 * 
	 */
	@Override
	public List<SubmitData> approveRmg(List<SubmitData> approveRmgList) {
		SubmitData rmg;
		List<SubmitData> rmgApprovedList = new ArrayList<SubmitData>();
		String sql = "";
		int rows = 0;
		//sql = "update pre_emp set rmg_done_flag = 1 where application_id = ?";
		sql = PreEmployeeDaoQueries.approveRmg_update;
		
		for (SubmitData rmgApprove : approveRmgList) {
			rows = templateObj.update(sql, rmgApprove.getApplicationId());
			rmg = new SubmitData();
			rmg.setApplicationId(rmgApprove.getApplicationId());
			rmgApprovedList.add(rmg);
		}
		return rmgApprovedList;

	}

	/**
	 * This method is used to update the approval of RMG_ADMIN.
	 * 
	 * @param approveRmgAdminList
	 *            list of SubmitData type.
	 * @see SubmitData
	 * @return rmgAdminApprovedList list of SubmitData type.
	 * 
	 */
	@Override
	public List<SubmitData> approveRmgAdmin(List<SubmitData> approveRmgAdminList) {
		SubmitData rmgAdmin;
		List<SubmitData> rmgAdminApprovedList = new ArrayList<SubmitData>();
		String sql = "";
		int rows = 0;
		//sql = "update pre_emp set rmg_admin_done_flag = 1 where application_id = ?";
		sql = PreEmployeeDaoQueries.approveRmgAdmin_update;
		
		for (SubmitData rmgAdminApprove : approveRmgAdminList) {
			rows = templateObj.update(sql, rmgAdminApprove.getApplicationId());
			rmgAdmin = new SubmitData();
			rmgAdmin.setApplicationId(rmgAdminApprove.getApplicationId());
			rmgAdminApprovedList.add(rmgAdmin);
		}
		return rmgAdminApprovedList;

	}

	@Override
	/**
	 * This method is used to update the rejection of RMG_ADMIN.
	 * @param rejectRmgAdminList list of SubmitData type.
	 * @see SubmitData
	 * @return rmgAdminRejectedList list of SubmitData type.
	 *  
	 */
	public List<SubmitData> rejectRmgAdmin(List<SubmitData> rejectRmgAdminList) {
		SubmitData rmgAdmin;
		List<SubmitData> rmgAdminRejectedList = new ArrayList<SubmitData>();
		String sql = "";
		int rows = 0;
		//sql = "update pre_emp set rmg_admin_done_flag = 0 where application_id = ?";
		sql = PreEmployeeDaoQueries.rejectRmgAdmin_update;
		
		for (SubmitData rmgAdminReject : rejectRmgAdminList) {
			rows = templateObj.update(sql, rmgAdminReject.getApplicationId());
			rmgAdmin = new SubmitData();
			rmgAdmin.setApplicationId(rmgAdminReject.getApplicationId());
			rmgAdminRejectedList.add(rmgAdmin);
		}
		return rmgAdminRejectedList;
	}

	@Override
	/**
	 * This method is used to update the rejection of RMG.
	 * @param rejectRmgList list of SubmitData type.
	 * @see SubmitData
	 * @return rmgRejectedList list of SubmitData type.
	 *  
	 */
	public List<SubmitData> rejectRmg(List<SubmitData> rejectRmgList) {
		SubmitData rmgAdmin;
		List<SubmitData> rmgRejectedList = new ArrayList<SubmitData>();
		String sql = "";
		int rows = 0;

		//sql = "update pre_emp set rmg_done_flag = 0 where application_id = ?";
		sql = PreEmployeeDaoQueries.rejectRmg_update;
		
		for (SubmitData rmgReject : rejectRmgList) {
			rows = templateObj.update(sql, rmgReject.getApplicationId());
			rmgAdmin = new SubmitData();
			rmgAdmin.setApplicationId(rmgReject.getApplicationId());
			rmgRejectedList.add(rmgAdmin);
		}
		return rmgRejectedList;
	}

	/**
	 * This method is used to check if an employee is enabled or disabled.
	 * 
	 * @param applicationId
	 *            .
	 * 
	 * @return boolean If employee is enabled then true else false.
	 * 
	 */
	@Override
	public boolean isEmployeeEnabled(int applicationId) {
		//String sql = "select count(*) from pre_emp where application_id = ? and enable_flag = 1";
		String sql = PreEmployeeDaoQueries.isEmployeeEnabled;
		
		Integer count = templateObj.queryForObject(sql, Integer.class,
				applicationId);
		return (count >= 1) ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	/**
	 * This method is used to enabled or disabled employee.
	 * @param applicationId application id of a pre-employee.
	 * @param enable current status of employee.
	 * @return boolean If operation is successful then true else false.
	 *  
	 */
	public boolean enableDisableEmployee(int applicationId, boolean enable) {
		String sql = "";
		if (enable) {
			//sql = "update pre_emp set enable_flag = 1 where application_id = ?";
			sql = PreEmployeeDaoQueries.enableDisableEmployee_update1;
			
		} else {
			//sql = "update pre_emp set enable_flag = 0 where application_id = ?";
			sql = PreEmployeeDaoQueries.enableDisableEmployee_update2;
			
		}
		int count = templateObj.update(sql, applicationId);
		return (count >= 1) ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean enableDisableEdit(int applicationId, boolean enable) {
			String sql = "";
			if (enable) {
				
				//sql = "update pre_emp set total_edit_flag = 1 where application_id = ?";
				sql = PreEmployeeDaoQueries.enableDisableEdit_update1;
			} else {
				
				//sql = "update pre_emp set total_edit_flag = 0 where application_id = ?";
				sql = PreEmployeeDaoQueries.enableDisableEdit_update2;
			}
			int count = templateObj.update(sql, applicationId);
			return (count >= 1) ? Boolean.TRUE : Boolean.FALSE;
		}
	
	@Override
	public Registration existingEmployeeRegistration(Employee emp, Registration register, String fileLocation)
			throws RegistrationException {
		Registration r;
		String password = "";
		FileCategory fileCategory;
		PreEmployee preEmp;
		SqlParameterSource fileParameters;
		SqlParameterSource preEmpParameters;
		KeyHolder categoryKeyHolder = new GeneratedKeyHolder();
		KeyHolder preEmpKeyHolder = new GeneratedKeyHolder();
		int applicationId;
		int categoryId;
		String userEmails = register.getEmail().get(0);
		for(int i=1;i<register.getEmail().size();i++){
			userEmails += "," + register.getEmail().get(i);
		}
		if (!isSameEmail(register.getEmail().get(0))) {
			//password = String.valueOf(new Random().nextInt(1000));
			password = RandomStringGenerator.generateString();
			preEmp = new PreEmployee(emp.getEmpUsername(), password,
					register.getFirstName(), register.getLastName(),
					userEmails, null, null, 0, fileLocation, 0, 0, 1,
					1, 1);
			preEmpParameters = new BeanPropertySqlParameterSource(preEmp);
			List<String> folderList = fileCategoryDao.getDefaultFolders();
			//String sql1 = "insert into pre_emp(username,password,first_name,last_name, email,contact, address, zip, file_location, pre_flag, edit_flag, enable_flag, rmg_done_flag, rmg_admin_done_flag ) values (:username,:password,:firstName,:lastName,:email,null,null,null,:fileLocation,:preFlag,:editFlag, :enableFlag, :rmgDoneFlag,:rmgAdminDoneFlag)";
			String sql1 = PreEmployeeDaoQueries.existingEmployeeRegistration_insert1;
			
			//String sql2 = "insert into emp values (?,?,?,?,?,?,?,?,?,?)";
			String sql2 = PreEmployeeDaoQueries.existingEmployeeRegistration_insert2;
			
			//String sql3 = "insert into file_category(category_name,parent_category_id, application_id, visibility) values(:categoryName,null,:applicationId,1)";
			String sql3 = PreEmployeeDaoQueries.existingEmployeeRegistration_insert3;
			
			//String sql3_1 = "insert into file_category(category_name,parent_category_id, application_id,visibility) values (?,?,?,1) ";
			String sql3_1 = PreEmployeeDaoQueries.existingEmployeeRegistration_insert4;
			
			//String sql4 = "insert into hr_bgc(application_id, bgc_done_flag, remark) values (?,?,?)";
			String sql4 = PreEmployeeDaoQueries.existingEmployeeRegistration_insert5;
			
			int rows1 = namedTemplateObj.update(sql1, preEmpParameters,
					preEmpKeyHolder);
			applicationId = preEmpKeyHolder.getKey().intValue();
			fileCategory = new FileCategory("root", applicationId, 1);
			fileParameters = new BeanPropertySqlParameterSource(fileCategory);
			int rows2 = templateObj.update(sql2, applicationId, emp.getEmpId(),
					register.getFirstName(), register.getLastName(),
					emp.getEmail(), register.getContact(),
					register.getAddress(), register.getZip(), fileLocation,
					emp.getEmpUsername());
			int rows3 = namedTemplateObj.update(sql3, fileParameters,
					categoryKeyHolder);
			int rows4 = templateObj.update(sql4, applicationId, 0, null);
			if (rows1 >= 1 && rows2 >= 1 && rows3 >= 1 && rows4 >= 1) {
				categoryId = categoryKeyHolder.getKey().intValue();
				if (userDao.insertUser(new User(emp.getEmpUsername()))) {
					for (String folderName : folderList) {
						templateObj.update(sql3_1, folderName, categoryId,
								applicationId);
					}
					r = new Registration();
					r.setUsername(register.getEmail().get(0));
					r.setPassword(password);
					return r;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			throw new RegistrationException("Same Username Or Email");
		}
	}
}
