package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTable implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName, tableName;

    CreateTable(ExecutionEnvironment environment, String databaseName, String tableName) {
        this.environment = environment;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        Optional<Database> database = this.environment.getDatabase(this.databaseName);
        if (database.isEmpty()) {
            return DatabaseCommandResult.error("Database " + this.databaseName + " doesn't exist.");
        }
        try {
            database.get().createTableIfNotExists(this.tableName);
            return DatabaseCommandResult.success("Table " + this.tableName + " was created in database " + this.databaseName);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
