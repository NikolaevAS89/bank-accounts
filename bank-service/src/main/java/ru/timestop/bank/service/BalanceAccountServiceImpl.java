package ru.timestop.bank.service;

import ru.timestop.bank.entity.BalanceAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 14.10.2018
 */
public class BalanceAccountServiceImpl implements BalanceAccountService {

    private ConnectionService connectionService;

    public BalanceAccountServiceImpl() {
    }

    void initPlan(Properties properties) {

    }

    @Override
    public BalanceAccount getAccount(int account) {
        try {
            Connection connection = connectionService.createConnection();
            PreparedStatement statement = connection.prepareStatement("");
            statement.setInt(0, account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ConnectionService getConnectionService() {
        return connectionService;
    }

    public void setConnectionService(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }
}
