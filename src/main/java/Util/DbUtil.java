package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {
	private static final String JNDI_NAME = "java:comp/env/jdbc/MijuPrice";
	private static volatile DataSource dataSource;
	private static volatile boolean jndiLookupDone = false;

	private DbUtil() {}

	public static Connection getConnection() throws SQLException {
		DataSource ds = lookupDataSource();
		if (ds != null) {
			return ds.getConnection();
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL JDBC driver not found", e);
		}
		return DriverManager.getConnection(Constant.DBUrl, Constant.DBUserName, Constant.DBPassword);
	}

	// Looks up the pooled DataSource Tomcat exposes under JNDI_NAME (configured server-side
	// in Tomcat's context.xml, see docs/DEPLOYMENT.md) and caches the result. Falls back to
	// a plain DriverManager connection using Constant's values when no such resource is bound,
	// which keeps local development working without requiring JNDI setup.
	private static DataSource lookupDataSource() {
		if (!jndiLookupDone) {
			synchronized (DbUtil.class) {
				if (!jndiLookupDone) {
					try {
						dataSource = (DataSource) new InitialContext().lookup(JNDI_NAME);
						System.out.println("DbUtil: using JNDI DataSource " + JNDI_NAME);
					} catch (NamingException e) {
						dataSource = null;
						System.out.println("DbUtil: no JNDI DataSource bound at " + JNDI_NAME
								+ ", falling back to DriverManager with Constant.DBUrl");
					}
					jndiLookupDone = true;
				}
			}
		}
		return dataSource;
	}
}
