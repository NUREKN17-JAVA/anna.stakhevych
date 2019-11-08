package ua.nure.kn.stakhevych.domain.db;

import java.sql.Connection;

public interface ConnectionFactory {
   Connection createConnection() throws DatabaseException;
}
