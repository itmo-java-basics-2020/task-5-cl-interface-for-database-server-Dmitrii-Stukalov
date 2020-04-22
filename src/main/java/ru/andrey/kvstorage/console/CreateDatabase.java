package ru.andrey.kvstorage.console;

import ru.andrey.kvstorage.exception.DatabaseException;

public class CreateDatabase implements DatabaseCommand {
    private ExecutionEnvironment environment;
    private String databaseName;

    public CreateDatabase(ExecutionEnvironment environment, String databaseName) {
        this.environment = environment;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() throws DatabaseException {
        this.environment.addDatabase(null);
        return DatabaseCommandResult.success("Database " + this.databaseName + " was created.");
    }
}
