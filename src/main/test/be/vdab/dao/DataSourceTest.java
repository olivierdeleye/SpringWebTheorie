package be.vdab.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/dao.xml")
public class DataSourceTest {
	
	@Autowired 
	private DataSource dataSource; //de bean met id 'dataSource' in dao.xml
	@Test
	public void getConnection() throws SQLException {
		try (Connection connection = dataSource.getConnection()) {
			
		}
	}

}
