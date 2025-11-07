package api.data;

import model.AuthDemoQaModel;

public class AuthData {

  static String userName = System.getProperty("userName");
  static String password = System.getProperty("password");

  public static final AuthDemoQaModel AUTH_DATA = new AuthDemoQaModel(userName, password);
}
