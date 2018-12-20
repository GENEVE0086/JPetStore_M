package org.csu.geneve.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.csu.geneve.domain.Account;
import org.csu.geneve.persistence.AccountDao;

public class AccountDaoImpl implements AccountDao {
  //constants
  //SQL Language
  private static final String GET_ACCOUNT_BY_USERNAME =
          "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,"
              + "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,"
              + "ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,"
              + "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,"
              + "PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME FROM ACCOUNT,"
              + "PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND "
              + "SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID "
              + "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";


  private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD =
          "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,"
              + "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,"
              + "ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,"
              + "PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,"
              + "PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME FROM ACCOUNT, "
              + "PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND "
              + "SIGNON.PASSWORD = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND "
              + "PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

  private static final String UPDATE_ACCOUNT =
          "UPDATE ACCOUNT SET EMAIL = ?,FIRSTNAME = ?,LASTNAME = ?,STATUS = ?,ADDR1 = ?,"
              + "ADDR2 = ?,CITY = ?,STATE = ?,ZIP = ?,COUNTRY = ?,PHONE = ? WHERE USERID = ?";

  private static final String INSERT_ACCOUNT =
          "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, "
              + "STATE, ZIP, COUNTRY, PHONE, USERID) VALUES(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?)";

  private static final String UPDATE_PROFILE =
          "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ?,"
              + "mylistopt = ?,banneropt = ? WHERE USERID = ?";

  private static final String INSERT_PROFILE =
          "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, mylistopt, "
              + "banneropt, USERID) VALUES (?, ?, ?, ?, ?)";

  private static final String UPDATE_SIGONO =
          "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

  private static final String INSERT_SIGNON =
          "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";

  @Override
  public Account getAccountByUsername(String username) {
    //result object
    Account resultAccount = null;

    try {
      //connect to mysql and get data
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME);
      preparedStatement.setString(1,username);
      ResultSet resultSet = preparedStatement.executeQuery();

      //progress data
      if (resultSet.next()) {
        resultAccount = new Account();
        resultAccount.setUsername(resultSet.getString(1));
        resultAccount.setEmail(resultSet.getString(2));
        resultAccount.setFirstName(resultSet.getString(3));
        resultAccount.setLastName(resultSet.getString(4));
        resultAccount.setStatus(resultSet.getString(5));
        resultAccount.setAddress1(resultSet.getString(6));
        resultAccount.setAddress2(resultSet.getString(7));
        resultAccount.setCity(resultSet.getString(8));
        resultAccount.setState(resultSet.getString(9));
        resultAccount.setZip(resultSet.getString(10));
        resultAccount.setCountry(resultSet.getString(11));
        resultAccount.setPhone(resultSet.getString(12));
        resultAccount.setLanguagePreference(resultSet.getString(13));
        resultAccount.setFavouriteCategoryId(resultSet.getString(14));
        resultAccount.setListOption(resultSet.getInt(15) == 1);
        resultAccount.setBannerOption(resultSet.getInt(16) == 1);
        resultAccount.setBannerName(resultSet.getString(17));
      }

      //disconnect to database
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultAccount;
  }

  @Override
  public Account getAccountByUsernameAndPassword(Account account) {
    //result object
    Account resultAccount = null;

    try {
      //connect to mysql and get data
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement =
              connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
      preparedStatement.setString(1,account.getUsername());
      preparedStatement.setString(2,account.getPassword());
      ResultSet resultSet = preparedStatement.executeQuery();

      //progress data
      if (resultSet.next()) {
        resultAccount = new Account();
        resultAccount.setUsername(resultSet.getString(1));
        resultAccount.setEmail(resultSet.getString(2));
        resultAccount.setFirstName(resultSet.getString(3));
        resultAccount.setLastName(resultSet.getString(4));
        resultAccount.setStatus(resultSet.getString(5));
        resultAccount.setAddress1(resultSet.getString(6));
        resultAccount.setAddress2(resultSet.getString(7));
        resultAccount.setCity(resultSet.getString(8));
        resultAccount.setState(resultSet.getString(9));
        resultAccount.setZip(resultSet.getString(10));
        resultAccount.setCountry(resultSet.getString(11));
        resultAccount.setPhone(resultSet.getString(12));
        resultAccount.setLanguagePreference(resultSet.getString(13));
        resultAccount.setFavouriteCategoryId(resultSet.getString(14));
        resultAccount.setListOption((resultSet.getInt(15) == 1));
        resultAccount.setBannerOption((resultSet.getInt(16) == 1));
        resultAccount.setBannerName(resultSet.getString(17));
      }

      //disconnect to database
      DataBaseUtil.closeResultSet(resultSet);
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultAccount;
  }

  @Override
  public void insertAccount(Account account) {
    updateAccount(account, INSERT_ACCOUNT);
  }

  @Override
  public void insertProfile(Account account) {
    updateProfile(account, INSERT_PROFILE);
  }

  @Override
  public void insertSignon(Account account) {
    updateSignon(account, INSERT_SIGNON);
  }

  @Override
  public void updateAccount(Account account) {
    updateAccount(account, UPDATE_ACCOUNT);
  }

  private void updateAccount(Account account, String updateAccount) {
    try {
      //connect to mysql and get data
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(updateAccount);

      //config the update info
      preparedStatement.setString(1,account.getEmail());
      preparedStatement.setString(2,account.getFirstName());
      preparedStatement.setString(3,account.getLastName());
      preparedStatement.setString(4,account.getStatus());
      preparedStatement.setString(5,account.getAddress1());
      preparedStatement.setString(6,account.getAddress2());
      preparedStatement.setString(7,account.getCity());
      preparedStatement.setString(8,account.getState());
      preparedStatement.setString(9,account.getZip());
      preparedStatement.setString(10,account.getCountry());
      preparedStatement.setString(11,account.getPhone());
      preparedStatement.setString(12,account.getUsername());

      //update
      preparedStatement.executeUpdate();

      //disconnect to database
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateProfile(Account account) {
    updateProfile(account, UPDATE_PROFILE);
  }

  private void updateProfile(Account account, String insertProfile) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(insertProfile);

      /* config the update info */
      preparedStatement.setString(1,account.getLanguagePreference());
      preparedStatement.setString(2,account.getFavouriteCategoryId());
      preparedStatement.setInt(3,account.isListOption() ? 1 : 0);
      preparedStatement.setInt(4,account.isBannerOption() ? 1 : 0);
      preparedStatement.setString(5,account.getUsername());

      /* update */
      preparedStatement.executeUpdate();

      /* disconnect to database */
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void updateSignon(Account account) {
    updateSignon(account, UPDATE_SIGONO);
  }

  private void updateSignon(Account account, String updateSigono) {
    try {
      /* connect to mysql and get data */
      Connection connection = DataBaseUtil.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(updateSigono);

      /* config the update info */
      preparedStatement.setString(1,account.getPassword());
      preparedStatement.setString(2,account.getUsername());

      /* update */
      preparedStatement.executeUpdate();

      /* disconnect to database */
      DataBaseUtil.closePreparedStatement(preparedStatement);
      DataBaseUtil.closeConnection(connection);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
