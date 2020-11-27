package com.ikubinfo.utils;

public final class AppProperties {

	public static final String APPLICATION_PROPERTIES = "application.properties";
	public static final String DATABASE_PROPERTIES = "application.properties";
	public static final String HIBERNATE_PROPERTIES = "hibernate.properties";

	public static final class JdbcProperties {
		public static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
		public static final String URL = "jdbc.url";
		public static final String USERNAME = "jdbc.username";
		public static final String PASSWORD = "jdbc.password";
	}

	public static final class HibernateProperties {
		public static final String DIALECT = "hibernate.dialect";
		public static final String SHOW_SQL = "hibernate.show_sql";
		public static final String FORMAT_SQL = "hibernate.format_sql";
		public static final String USE_SQL_COMMENTS = "hibernate.use_sql_comments";
		//public static final String MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
		public static final String BATCH_SIZE = "hibernate.batch_size";
		public static final String FETCH_SIZE = "hibernate.fetch_size";
		public static final String HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	}

	public static final String PACKAGE_BASE = "com.ikubinfo";
	public static final String PACKAGE_CONTROLLER = PACKAGE_BASE + ".controller";
	public static final String PACKAGE_SERVICE = PACKAGE_BASE + ".service";
	public static final String PACKAGE_REPOSITORY = PACKAGE_BASE + ".repository";
	public static final String PACKAGE_ENTITIES = PACKAGE_BASE + ".entities";
	public static final String JWTPROVIDER = "classpath:application.properties";
}
