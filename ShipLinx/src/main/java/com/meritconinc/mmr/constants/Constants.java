package com.meritconinc.mmr.constants;

public interface Constants {
  public static final String STATUS_INACTIVE = "I"; // Inactive

  public static final String STATUS_ACTIVE = "A"; // Active

  public static final String STATUS_UNAPPROVED = "U"; // Unapproved

  public static final String STATUS_LOCKED = "L"; // Account Locked

  public static final String STATUS_REJECTED = "R"; // Account Rejected

  public static final String VISITOR_CODE = "visitor";

  public static final String BASE_USER_ROLE = "base";

  public static final String PUBLIC_ROLE_CODE = "public";

  public static final String ADMIN_ROLE_CODE = "admin";

  public static final String SYS_ADMIN_ROLE_CODE = "sysadmin";

  // public static final String CURRENT_USER = "CurrentUser";

  public static final String CURRENT_PAGE = "CurrentPage";

  public static final String RIGHT_MENU = "rightmenu";

  public static final String USER_SESSION_LISTENER = "USER_SESSION_LISTENER";

  public static final String USER_TRACKING_SESSION_LISTENER = "USER_TRACKING_SESSION_LISTENER";

  public static final String ANONYMOUS_USER = "anonymous";

  // properties scope
  public static final String SYSTEM_SCOPE = "SYSTEM";
  // session timeout property
  public static final String TIMEOUT = "SESSION_TIMEOUT";
  // login retry
  public static final String LOGIN_RETRY = "LOGIN_RETRY";
  // password history count
  public static final String PASSWORD_HISTORY_COUNT = "PASSWORD_HISTORY_COUNT";
  // password expiry period (days)
  public static final String PASSWORD_EXPIRES_DAYS = "PASSWORD_EXPIRES_DAYS";

  public static final String UPLOAD_FILE_MAX_SIZE = "UPLOAD_FILE_MAX_SIZE";

  // tracking on flag
  public static final String TRACKING_ON = "TRACKING_ON";

  // tracking stack size
  public static final String TRACKING_STACK_SIZE = "TRACKING_STACK_SIZE";

  // request access default role
  public static final String REQUEST_ACCESS_DEFAULT_ROLE = "REQUEST_ACCESS_DEFAULT_ROLE";

  // separate between framework and application
  public static final int FMK = 1;
  public static final int NOT_FMK = 0;

  // Application Default Locale
  public static final String DEFAULT_LOCALE = "DEFAULT_LOCALE";

  // ACEGI rememberme service
  public static final String REMEMBER_PASSWORD = "REMEMBER_PASSWORD";

  // Query Tool default max rows
  public static final String QUERY_TOOL_DEFAULT_MAX_ROWS = "QUERY_TOOL_DEFAULT_MAX_ROWS";

  public static final String RATE_TEMPLATE_FOLDER = "RateTemplate";

}
