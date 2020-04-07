package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    static DatabaseCommandResult success(String result) {
        return new CommandResult(result, DatabaseCommandStatus.SUCCESS);
    }

    static DatabaseCommandResult error(String message) {
        return new CommandResult(message, DatabaseCommandStatus.FAILED);
    }

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class CommandResult implements DatabaseCommandResult {
        private String result;
        private DatabaseCommandStatus status;

        private CommandResult(String result, DatabaseCommandStatus status) {
            this.result = result;
            this.status = status;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(this.result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return this.status;
        }

        @Override
        public boolean isSuccess() {
            return this.status.equals(DatabaseCommandStatus.SUCCESS);
        }

        @Override
        public String getErrorMessage() {
            return this.result;
        }
    }
}