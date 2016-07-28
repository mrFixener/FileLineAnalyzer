/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fileanalyzer.dao.impl;

import com.fileanalyzer.dao.FileStatisticDAO;
import com.fileanalyzer.dbconnector.DBConnector;
import com.fileanalyzer.domain.FileStatistic;
import com.fileanalyzer.util.SqlGenerator;
import static com.fileanalyzer.util.SqlGenerator.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dimon
 */
@Component
public class FileStatisticDAOImpl implements FileStatisticDAO{
    private static final Logger log = Logger.getLogger(FileStatisticDAOImpl.class);
    @Override
    public void add(FileStatistic fStat) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnector.getConnection();
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement(getInsertByFileStatistic(fStat));
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    log.error("Transaction is being rolled back", e);
                    con.rollback();
                } catch (SQLException excep) {
                    log.error(excep);
                }
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                log.error("setAutoCommit(true)",ex);
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            } 
        }
    }

    @Override
    public void add(StringBuilder fStat) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnector.getConnection();
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement(fStat.toString());
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    log.error("Transaction is being rolled back", e);
                    con.rollback();
                } catch (SQLException excep) {
                    log.error(excep);
                }
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                log.error("setAutoCommit(true)",ex);
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }  
        }
    }

    @Override
    public void add(List<FileStatistic> fStat) {
        for (FileStatistic fs : fStat) {
            add(fs);
        }
    }
    @Override
    public void update(FileStatistic fStat) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DBConnector.getConnection();
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement(SqlGenerator.getUpdateByFileStatistic(fStat));
            preparedStatement.executeUpdate();
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    log.error("Transaction is being rolled back", e);
                    con.rollback();
                } catch (SQLException excep) {
                    log.error(excep);
                }
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                log.error("setAutoCommit(true)",ex);
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            } 
        }
    }

    @Override
    public void delete(FileStatistic fStat) {
        Connection con = null;
        Statement statement = null;
        try {
            con = DBConnector.getConnection();
            con.setAutoCommit(false);
            statement = con.createStatement();
            statement.execute("delete from "+FileStatistic.FileStatisticKey.TABLE+" where id="+fStat.getId());
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    log.error("Transaction is being rolled back", e);
                    con.rollback();
                } catch (SQLException excep) {
                    log.error(excep);
                }
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                log.error("setAutoCommit(true)",ex);
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            } 
        }
    }

    @Override
    public FileStatistic getFileStatisticById(Long id) {
        Connection con = null;
        PreparedStatement  statement = null;
        String sql = "select * from "+FileStatistic.FileStatisticKey.TABLE+" where id=?";
        FileStatistic fStat = new FileStatistic();
        try {
            con = DBConnector.getConnection();
            con.setAutoCommit(false);
            statement = con.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            ResultSetToFileStatistic(rs, fStat);
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    log.error("Transaction is being rolled back", e);
                    con.rollback();
                } catch (SQLException excep) {
                    log.error(excep);
                }
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                log.error("setAutoCommit(true)",ex);
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }    
        }
        return fStat;
    }

    @Override
    public Long getMaxId() {
        Connection con = null;
        PreparedStatement  statement = null;
        String sql = "select max(id) from "+FileStatistic.FileStatisticKey.TABLE;
        Long maxId = null;
        try {
            con = DBConnector.getConnection();
            con.setAutoCommit(false);
            statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
                maxId = rs.getLong(1);
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    log.error("Transaction is being rolled back", e);
                    con.rollback();
                } catch (SQLException excep) {
                    log.error(excep);
                }
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                log.error("setAutoCommit(true)",ex);
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    log.error(ex);
                }
            }
        }
        return maxId;
    }
  
}
