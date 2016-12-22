package com.fileanalyzer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Dimon on 22.12.2016.
 */
public interface DAOMinimizer {
    public void handleException(final Exception e, final Connection con);
    public void doFinal(final Connection con, final PreparedStatement preparedStatement);
}
