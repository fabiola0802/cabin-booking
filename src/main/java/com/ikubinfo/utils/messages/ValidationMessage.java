package com.ikubinfo.utils.messages;

public class ValidationMessage {

	public static final String USERNAME_NOT_NULL = "Username must not be null";
	public static final String USERNAME_FORMAT = "Username should contain at least 3 characters";
	public static final String NAME_NOT_NULL = "Name must not be null";
	public static final String NAME_FORMAT = "Name should  contain only letters and at least be 3 characters long";
	public static final String SURNAME_NOT_NULL = "Surname must not be null";
	public static final String SURNAME_FORMAT = "Surname should  contain only letters and at least be 3 characters long";
	public static final String EMAIL_NOT_NULL = "Email must not be null";
	public static final String EMAIL_FORMAT = "Email is not in the correct format";
	public static final String PASSWORD_NOT_NULL = "Password  must not be null";
	public static final String PASSWORD_FORMAT = "Password should contain at least 6 characters, at least one lowercase letter at least one uppercase letter, at least a number and a special character, and no spaces allowed";
	public static final String CABIN_NOT_NULL = "Cabin must not be null";
	public static final String TYPE_NOT_NULL = "Type must not be null. It should be 'SITE' or 'CABIN' ";
	public static final String ATTRIBUTE_NOT_EMPTY = "Attribute must be not null nor empty";
	public static final String ATTRIBUTE_NAME_SIZE = "The attribute name should contain at least  3 characters";
	public static final String CABIN_NUMBER_SIZE = "Cabin number should be with 3 digits";
	public static final String NUMBER_OF_FLOORS_NOT_NULL = "Number of floors must not be null";
	public static final String FLOOR_NUMBER_MIN = "Cabin should have at least 1 floor";
	public static final String FLOOR_NUMBER_MAX = "Cabin cannot have more than 2 floors";
	public static final String NUMBER_OF_BATHROOMS_NOT_NULL = "Number of bathrooms must not be null";
	public static final String NUMBER_OF_BEDROOMS_NOT_NULL = "Number of bedrooms must not be null";
	public static final String MAX_CAPACITY_NOT_NULL = "Cabin capacity must not be null";
	public static final String PRICE_NOT_NULL = "Price must not be null";
	public static final String SITE_NOT_NULL = "Site code must not be null";
	public static final String SITE_CODE_SIZE = "Site code should be with 4 digits";
	public static final String DATA_NOT_VALID = "You are not allowed to provide id !";
	public static final String ID_NOT_VALID = "id must be a positive integer only!";
	public static final String USERNAME_EXISTS = "Username already exists! Username must be unique";
	public static final String SITE_CODE_EXISTS = "Site already exists! Code should be unique";
	public static final String CABIN_NUMBER_EXISTS = "Cabin number already exists for that site";
}
